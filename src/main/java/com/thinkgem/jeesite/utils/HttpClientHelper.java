package com.thinkgem.jeesite.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.exception.GZException;

/**
 * 用于处理REST HTTP GET/POST请求与回复。
 */
public class HttpClientHelper {

    private final static Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

    private static HttpClient httpsClient = SSLClientWrapper.wrapClient(HttpClientFactory.getHttpClient());
    private static HttpClient httpClient = HttpClientFactory.getHttpClient();

    public static String sendRequest(String requestUrl) {
        return sendRequest(false, requestUrl, null, null, Consts.UTF8, null);
    }

    public static String sendRequest(String requestUrl, String respEncoding) {
        return sendRequest(false, requestUrl, null, null, respEncoding, null);
    }

    public static String sendRequest(boolean isPost, String requestUrl, String requestBody, String encoding,
            Map<String, String> headers) {
        return sendRequest(isPost, requestUrl, requestBody, encoding, encoding, headers);
    }

    public static String sendRequest(String requestUrl, File uploadFile, String encoding, Map<String, String> headers) {
        LOG.info("Sending out request with URL: {}", requestUrl);
        try {

            HttpResponse response = null;

            HttpPost httpPost = new HttpPost(requestUrl);
            for (String key : headers.keySet()) {
                httpPost.addHeader(key, headers.get(key));
            }

            MultipartEntity mpEntity = new MultipartEntity();
            ContentBody cbFile =  new FileBody(uploadFile, MediaType.APPLICATION_OCTET_STREAM);
            mpEntity.addPart("file", cbFile);
            httpPost.setEntity(mpEntity);

            boolean isHttps = StringUtils.startsWith(requestUrl, "https");
            response = (isHttps ? httpsClient : httpClient).execute(httpPost);

            HttpEntity entity = response.getEntity();
            if (null != entity) {
                String responseContent = EntityUtils.toString(entity, "UTF-8");
                return responseContent;
            }
        } catch (Exception e) {
            LOG.error("error ", e);
        }
        return null;
    }

    /**
     * 用于发送HTTP的POST/Get请求，接收并解析响应。
     * 
     * @param isPost 是否Post
     * @param reqUrl
     * @param requestBody
     * @param requestEncoding
     * @param respEncoding
     * @return
     */
    private static String sendRequest(boolean isPost, String reqUrl, String requestBody, String requestEncoding,
            String respEncoding, Map<String, String> headers) {
        LOG.info("Sending out request with URL: {}", reqUrl);

        boolean isHttps = StringUtils.startsWith(reqUrl, "https");
        // 构造request
        HttpUriRequest httpRequest = null;
        if (isPost) {
            httpRequest = new HttpPost(reqUrl);
            StringEntity entity = null;
            try {
                entity = new StringEntity(requestBody, requestEncoding);
            } catch (UnsupportedEncodingException e) {
                throw new GZException(Consts.Code.DATA_ERROR, "Failed to create http request!", e);
            }
            ((HttpPost) httpRequest).setEntity(entity);

        } else {
            httpRequest = new HttpGet(reqUrl);
        }
        if (headers == null) {
            headers = new HashMap<String, String>();
            headers.put("Content-Type", "text/html;charset=" + requestEncoding);
        }
        for (String key : headers.keySet()) {
            httpRequest.addHeader(key, headers.get(key));
        }
        // 发送请求，获取Response
        HttpResponse response;
        try {
            response = (isHttps ? httpsClient : httpClient).execute(httpRequest);
        } catch (Exception e) {
            LOG.error("Error sending request to:" + reqUrl, e);
            throw new GZException(Consts.Code.NET_CONN_ERROR, "Error sending request to remote server", e);
        }

        // 检查Response内容不为空
        if (response == null || response.getEntity() == null) {
            LOG.error("response or its enclosed entity empty, url:{}", reqUrl);
            throw new GZException(Consts.Code.NET_READ_ERROR, "remote server response or its enclosed entity empty");
        }

        // 返回内容
        String respStr = null;
        try {
            respStr = EntityUtils.toString(response.getEntity(), respEncoding);
            LOG.info("get response with content: {}, original response: {}", respStr, response);
        } catch (Exception e) {
            throw new GZException(Consts.Code.DATA_ERROR, "Parsing response error! content: " + respStr, e);
        }

        return respStr;
    }

    /**
     * 避免HttpClient的”SSLPeerUnverifiedException: peer not authenticated”异常
     * 不用导入SSL证书
     */
    // FIXME 以后用到https访问的时候，要注意重构
    public static class SSLClientWrapper {
        public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                X509TrustManager tm = new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                            throws java.security.cert.CertificateException {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                            throws java.security.cert.CertificateException {
                        // TODO Auto-generated method stub

                    }
                };
                ctx.init(null, new TrustManager[] { tm }, null);
                SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("https", 443, ssf));
                ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
                return new DefaultHttpClient(mgr, base.getParams());
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}