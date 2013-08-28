package com.gonglin.test.htmlparser;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import org.apache.log4j.Logger;
import qunar.QunarServer;
import qunar.hc.QunarClient;
import qunar.hc.tools.Strategies;

public class HttpUtil {
    private static final int TIME_OUT_IN_MILLSECONDS = 5000;
    private static final int CONN_TIMEOUT_IN_MILLISECONDS = 3000;
    private static final int RETRY_TIMES = 2;

    private static final QunarClient httpClient;
    static {
        HttpParams params = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(params, CONN_TIMEOUT_IN_MILLISECONDS);
        HttpConnectionParams.setSoTimeout(params, TIME_OUT_IN_MILLSECONDS);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "Chrome/5.0.342.9 Safari/533.2");

        HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);

        cm.setMaxTotal(600);
        cm.setDefaultMaxPerRoute(100);

        httpClient = new QunarClient(cm, params);
        Strategies.keepAlive(httpClient, 5000);

    }

    public static String getContent(final String url) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpGet request = new HttpGet(url);
                    HttpResponse response = httpClient.execute(request);
                    HttpEntity entity = null;
                    try {
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("请求失败,url:" + url, e);
                    } finally {
                        EntityUtils.consume(entity);
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        return fu.get(TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
    }

    public static String getContentByPost(final String url, final List<NameValuePair> formParams) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpPost request = new HttpPost(url);
                    request.setEntity(new UrlEncodedFormEntity(formParams, "utf-8"));
                    HttpResponse response = httpClient.execute(request);
                    HttpEntity entity = null;
                    try {
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("请求失败,url:" + url, e);
                    } finally {
                        EntityUtils.consume(entity);
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        return fu.get(TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
    }

    public static String getContentByPost(final HttpPost post, final String requestXml) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpEntity entityReq = new StringEntity(requestXml, "UTF-8");
                    post.setEntity(entityReq);
                    HttpResponse response = httpClient.execute(post);
                    try {
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(response.getEntity());
                            return resultString;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("请求失败:url:" + post.getURI(), e);
                    } finally {
                        EntityUtils.consume(entityReq);
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        return fu.get(TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);

    }
}
