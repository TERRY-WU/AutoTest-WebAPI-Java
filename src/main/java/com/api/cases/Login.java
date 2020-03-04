package com.api.cases;

import com.api.config.TestConfig;
import com.api.model.LoginModel;
import com.api.utils.DatabaseUtil;
import com.api.utils.HttpClientUtil;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Login {

    public static String expected_result;

    @BeforeTest(groups = "login", description = "初始化数据")
    public void setUp() throws IOException {
        InputStream config = Login.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(config);
        TestConfig.login_url = properties.getProperty("login.url");
        TestConfig.login_s = properties.getProperty("login.s");
        TestConfig.login_username = properties.getProperty("login.username");
        TestConfig.login_password = properties.getProperty("login.password");
        TestConfig.login_app_key = properties.getProperty("login.app_key");
        TestConfig.login_sign = properties.getProperty("login.sign");
        TestConfig.login_is_allow_many = properties.getProperty("login.is_allow_many");
        TestConfig.login_client = properties.getProperty("login.client");
    }

    @Test(groups = "login", description = "用户名密码正确 登陆成功")
    public void test01() throws IOException {
        run(1);
    }

    @Test(groups = "login", description = "登陆失败 密码错误")
    public void test02() throws IOException {
        run(2);
    }

    @Test(groups = "login", description = "登陆失败 用户不存在")
    public void test03() throws IOException {
        run(3);
    }

    @Test(groups = "login", description = "用户名只有1个字符")
    public void test04() throws IOException {
        run(4);
    }

    @Test(groups = "login", description = "用户名含有20个字符")
    public void test05() throws IOException {
        run(5);
    }

    @Test(groups = "login", description = "用户名为中文")
    public void test06() throws IOException {
        run(6);
    }

    @Test(groups = "login", description = "用户名为数字")
    public void test07() throws IOException {
        run(7);
    }

    @Test(groups = "login", description = "用户名为特殊字符")
    public void test08() throws IOException {
        run(8);
    }

    @Test(groups = "login", description = "用户名中间有空格")
    public void test09() throws IOException {
        run(9);
    }

    @AfterTest
    public void tearDown(){
        System.out.println("Test done...");
    }

    public void run(int number) throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginModel model = sqlSession.selectOne("login", number);
        expected_result = model.getExpected_result();
        String expected_err_code = getErrCode(expected_result, "ret");
        String res = getResult(model);
        String actual_err_code = getErrCode(res, "ret");
        Assert.assertEquals(actual_err_code, expected_err_code);
        System.out.println(res);
    }

    public static String getErrCode(String result, String ret){
        JSONObject jsonObject = new JSONObject(result);
        return jsonObject.get(ret).toString();
    }

    public static String getResult(LoginModel model) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(TestConfig.login_s, model.getS());
        map.put(TestConfig.login_username, model.getUsername());
        map.put(TestConfig.login_password, model.getPassword());
        map.put(TestConfig.login_app_key, model.getApp_key());
        map.put(TestConfig.login_is_allow_many, model.getIs_allow_many());
        map.put(TestConfig.login_client, model.getClient());
        map.put(TestConfig.login_app_key, model.getApp_key());
        return HttpClientUtil.doGet(TestConfig.login_url, map);
    }
}
