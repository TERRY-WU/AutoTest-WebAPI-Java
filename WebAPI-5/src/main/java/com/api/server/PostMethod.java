package com.api.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Api(value = "/", tags = "这是我全部的post请求")
@RequestMapping("/api/v1")
public class PostMethod {

    //这个变量是用来装我们cookies信息的
    private static Cookie cookie;

    //用户登陆成功获取到cookies，然后再访问其他接口获取到列表
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，成功后获取cookies信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName", required = true) String userName,
                        @RequestParam(value = "password", required = true) String password) {
        if (userName.equals("zhangsan") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登陆成功了!";
        }
        return "用户名或者是密码错误！";
    }

    @RequestMapping(value = "/post/with/cookies", method = RequestMethod.POST)
    @ApiOperation(value = "要求客户端携带cookies访问", httpMethod = "POST")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            System.out.println(cookie.getName()+"---"+cookie.getValue());
        }
        if (Objects.isNull(cookies)) {
            return "Error: cookies null...You must take cookies to access this page...!";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                return "Congratulations...you access this page with cookies";
            }
        }
        return "Sorry, you access this page without cookies...!";
    }

}