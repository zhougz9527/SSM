package com.ssm.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Think
 * @Date: 2019/4/22
 * @Time: 10:36
 */
public class HttpClientUtil {

    private static final int HTTP_CONNECT_TIMEOUT = 10000; //连接超时时间
    private static final int HTTP_REQUEST_TIMEOUT = 60000; //请求超时时间
    private static final int HTTP_SOCKET_TIMEOUT = 10000; //等待数据时间
    private static final String DEFAULT_ENCODING = "UTF-8"; //默认编码
    private static final String HTTP = "http";// http
    private static final String HTTPS = "https";// https
    protected static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> requestMap) {
        String result = "";
        if (StringUtils.isBlank(url)) {
            return result;
        }
        List<NameValuePair> params = new ArrayList<>();
        if (null != requestMap && requestMap.keySet().size() > 0) {
            requestMap.forEach((k, v) -> params.add(new BasicNameValuePair(k, v)));
        }
        CloseableHttpClient httpClient = url.toLowerCase().startsWith(HTTPS) ? createSSLHttpClient() : HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = params.size() > 0 ? new HttpGet(new URIBuilder(url).setParameters(params).build()) : new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(HTTP_REQUEST_TIMEOUT)
                    .setSocketTimeout(HTTP_SOCKET_TIMEOUT)
                    .setRedirectsEnabled(true)// 默认允许自动重定向
                    .build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            result = handleResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("请求{}异常: {}", url, e.getMessage());
        } finally{
            closeResource(httpClient, response);
        }
        return result;
    }

    public static String post(String url, Map<String, String> requestMap) {
        return post(url, requestMap, DEFAULT_ENCODING);
    }

    public static String post(String url, Map<String, String> requestMap, String encoding) {
        String result = "";
        if (StringUtils.isBlank(url)) {
            return result;
        }
        List<NameValuePair> params = new ArrayList<>();
        if (null != requestMap && requestMap.keySet().size() > 0) {
            requestMap.forEach((k, v) -> params.add(new BasicNameValuePair(k, v)));
        }
        CloseableHttpClient httpClient = url.toLowerCase().startsWith(HTTPS) ? createSSLHttpClient() : HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(HTTP_REQUEST_TIMEOUT)
                    .setSocketTimeout(HTTP_SOCKET_TIMEOUT)
                    .setRedirectsEnabled(true)// 默认允许自动重定向
                    .build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setEntity(new UrlEncodedFormEntity(params, encoding));
            response = httpClient.execute(httpPost);
            result = handleResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("请求{}异常: {}", url, e.getMessage());
        } finally{
            closeResource(httpClient, response);
        }
        return result;
    }

    public static String post(String url, String jsonParams) {
        return post(url, jsonParams, DEFAULT_ENCODING);
    }

    public static String post(String url, String jsonParams, String encoding) {
        String result = "";
        if (StringUtils.isBlank(url)) {
            return result;
        }
        CloseableHttpClient httpClient = url.toLowerCase().startsWith(HTTPS) ? createSSLHttpClient() : HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(HTTP_REQUEST_TIMEOUT)
                    .setSocketTimeout(HTTP_SOCKET_TIMEOUT)
                    .setRedirectsEnabled(true)// 默认允许自动重定向
                    .build();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-Type","application/json");
            httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", encoding)));
            response = httpClient.execute(httpPost);
            result = handleResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("请求{}异常: {}", url, e.getMessage());
        } finally{
            closeResource(httpClient, response);
        }
        return result;
    }

    private static String handleResponse(CloseableHttpResponse response) {
        String result = "";
        try {
            if (null != response) {
                logger.error("响应码: {}", response.getStatusLine().getStatusCode());
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (null != entity) {
                        result = EntityUtils.toString(entity);
                        EntityUtils.consume(entity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("响应异常: {}", e.getMessage());
        }
        return result;

    }

    private static void closeResource(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        if (null != response) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭response资源异常: {}", e.getMessage());
            }
        }
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("关闭httpClient资源异常: {}", e.getMessage());
            }
        }

    }


    /**
     * 创建一个SSL信任所有证书的httpClient对象
     * @return
     */
    private static CloseableHttpClient createSSLHttpClient() {
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true).build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }


}
