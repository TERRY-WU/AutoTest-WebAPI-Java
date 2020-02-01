package com.api.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class CookiesGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void getConfig() {
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String res;
        String uri = bundle.getString("getCookies.uri");
        String test_url = this.url + uri;
        HttpGet get = new HttpGet(test_url);
        store = new BasicCookieStore();
        /*
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(get);
         */
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        CloseableHttpResponse response = client.execute(get);

        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            System.out.print(cookie.getName());
            System.out.println(cookie.getValue());
        }
        res = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(res);
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testCookie() throws IOException {
        String res;
        String uri = bundle.getString("get.cookie.uri");
        String testUrl = this.url+uri;
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();
        CloseableHttpResponse response = client.execute(get);
        res = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(res);
    }

}
