package com.api.cases;

import com.api.config.TestConfig;
import com.api.utils.*;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class News {

    @Test
    public void test01() {
//        BasicCookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//        CloseableHttpResponse httpResponse = null;

        Map<String, String> map = new HashMap<>();
        map.put("type", "");
        map.put("key", "1e5bcba32bc59fbd15a3f354c404e5df");

        String res = HttpClientUtil.doGet("http://v.juhe.cn/toutiao/index", map);
        System.out.println(res);

        JSONObject jsonObject = new JSONObject(res);
        System.out.println(jsonObject.getString("reason"));
        System.out.println(jsonObject.getJSONObject("result").get("stat"));

        JSONArray jsonArray = new JSONArray(jsonObject.getJSONObject("result").get("data").toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println(jsonArray.get(i));
            JSONObject jsonObject1 = new JSONObject(jsonArray.get(i).toString());
            System.out.println(jsonObject1.get("author_name"));
        }
        System.out.println(jsonArray.length());

        Assert.assertEquals(jsonObject.getString("reason"), "成功的返回");
    }


}
