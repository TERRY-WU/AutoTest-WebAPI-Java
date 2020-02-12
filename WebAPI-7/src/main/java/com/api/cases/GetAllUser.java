package com.api.cases;

import com.api.config.TestConfig;
import com.api.model.LoginCase;
import com.api.utils.DatabaseUtil;
import org.apache.http.client.methods.HttpGet;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllUser {

    @Test
    public void getAllUser() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        List<LoginCase> loginCase = sqlSession.selectList("getAllUser");
        System.out.println(loginCase.toString());
        for(LoginCase login: loginCase){
            System.out.println(login.getUserName());
            System.out.println(login.getPassword());
            System.out.println(login.getId());
            System.out.println(login.getExpected());
        }
    }

}
