package com.thinkgem.jeesite.utils;


import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * 因在Spring XML中组装HttpClient比较麻烦，这里通过Factory的方式来获取。<br>
 * 建议通过Spring来获得和使用HttpClient。
 * 
 * @author duanshao
 */
public class HttpClientFactory {
    private static final int HTTP_CONN_TIMEOUT = 10 * 1000; // The timeout until
                                                            // a connection is
                                                            // established.
    private static final int HTTP_SOCKET_TIMEOUT = 45 * 1000; // the timeout for
                                                              // waiting for
                                                              // data

    public static HttpClient getHttpClient() {

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        // the default port here is not necessarily the port used in the actual
        // connection
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);
        // configure connection pool size, default is 2 per route, 20 for total,
        // which usually no enough
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(40);

        HttpClient httpClient = new DefaultHttpClient(cm);
        HttpParams params = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, HTTP_CONN_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, HTTP_SOCKET_TIMEOUT);
        return httpClient;
    }

    public static void shutdownHttpClient(HttpClient httpClient) {
        if (httpClient == null) {
            return;
        }
        HttpClient tmp = httpClient;
        httpClient = null;
        tmp.getConnectionManager().shutdown();
    }
}
