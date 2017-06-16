package com.thinkgem.jeesite.utils;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpClientUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 从指定URL读入, 并返回字符串
     * 
     * @param httpClient
     * @param url
     * @return null if return state code not equals 200
     */
    public static String getUrlAsString(HttpClient httpClient, String url) {
        HttpGet request = new HttpGet(url);
        return getUrlAsString(httpClient, request);
    }

    /**
     * 从指定URL读入, 并返回字符串
     * 
     * @param httpClient
     * @param url
     * @return
     */
    public static String getPostUrlAsString(HttpClient httpClient, String url, List<NameValuePair> requestParams) {
        HttpPost request = new HttpPost(url);
        try {
            HttpEntity requestEntity = new UrlEncodedFormEntity(requestParams, "UTF-8");
            request.setEntity(requestEntity);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error when synchronize to topic center.", e);
        }
        return getUrlAsString(httpClient, request);
    }

    /**
     * 执行传入的HttpMethod实例
     * 
     * @param httpClient
     * @param request
     * @return null if return state code not equals 200
     */
    public static String getUrlAsString(HttpClient httpClient, HttpRequestBase request) {
        String result = null;
        HttpEntity entity = null;
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            handleException(e);
        } finally {
            EntityUtils.consumeQuietly(entity);
            request.releaseConnection();
        }
        return result;
    }

    /**
     * @param e
     */
    private static void handleException(Exception e) {
        LOG.error("Error when get remote http content with http client.", e);
    }

    @SuppressWarnings("deprecation")
    public static String doPost(HttpClient httpClient, String url, List<NameValuePair> params) {
        /* 建立HTTPPost对象 */
        HttpPost httpRequest = new HttpPost(url);
        String strResult = null;
        HttpEntity entity = null;
        try {
            /* 添加请求参数到请求对象 */
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            /* 发送请求并等待响应 */
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            /* 若状态码为200 ok */
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                entity = httpResponse.getEntity();
                /* 读返回数据 */
                strResult = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            handleException(e);
        } finally {
            EntityUtils.consumeQuietly(entity);
            httpRequest.releaseConnection();
        }

        return strResult;
    }

    /**
     * 描述：获取网络文件的大小.
     * 
     * @param Url
     *            图片的网络路径
     * @return int 网络文件的大小
     */
    public static int getContentLengthFormUrl(String Url) {
        int mContentLength = 0;
        try {
            URL url = new URL(Url);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) url.openConnection();
            mHttpURLConnection.setConnectTimeout(5 * 1000);
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection
                    .setRequestProperty(
                            "Accept",
                            "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            mHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
            mHttpURLConnection.setRequestProperty("Referer", Url);
            mHttpURLConnection.setRequestProperty("Charset", "UTF-8");
            mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            mHttpURLConnection.connect();
            if (mHttpURLConnection.getResponseCode() == 200) {
                // 根据响应获取文件大小
                mContentLength = mHttpURLConnection.getContentLength();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mContentLength;
    }

}
