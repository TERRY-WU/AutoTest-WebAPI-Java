package com.api.config;

import java.util.List;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;

public class TestConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;

    public static CloseableHttpClient httpClient;
    public static List<Cookie> store;

}
