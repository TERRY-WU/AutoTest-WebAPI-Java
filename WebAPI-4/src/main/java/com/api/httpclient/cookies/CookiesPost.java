package com.api.httpclient.cookies;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class CookiesPost {

    private String url;
    private ResourceBundle bundle;

    @BeforeTest
    public void getConfig() {
        bundle = ResourceBundle.getBundle("application");
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String res;
        String uri = bundle.getString("post.cookie.uri");
        String test_url = this.url + uri;
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("login", "true");
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        System.out.println(cookieStore);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpPost post = new HttpPost(test_url);
        post.setHeader("content-type","application/json");
//        post.addHeader("Cookie", cookieStore.toString()); //或通过addHeader带过去也可以
//        addHeader，如果同名header已存在，则追加至原同名header后面。
//        setHeader，如果同名header已存在，则覆盖一个同名header。

        JSONObject param = new JSONObject();
        param.put("name","gaga");
        param.put("age","18");
        System.out.println(param.toString());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        CloseableHttpResponse response = client.execute(post);

        System.out.println(response.getStatusLine().getStatusCode());

        res = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(res);

        JSONObject resultJson = new JSONObject(res);
        String success = (String) resultJson.get("returned_status");
        String status_code = (String) resultJson.get("status_code");

        Assert.assertEquals("success", success);
        Assert.assertEquals("10000", status_code);

    }

}
