package com.api.controller;

import com.api.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@Api(value = "/", tags = "Mybatis 查询MySQL数据库")
@RequestMapping("/")
public class Demo {

    //首先获取一个执行sql语句的对象
//    Mybatis里面有提供SqlSessionTemplate，由于SpringBoot都是用的注解的方式注入，
//    所以没有Spring-Mybatis.xml也就不需要配置，用Autowired直接自动注入即可。
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数", httpMethod = "GET")
    public int getUserCount() {
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        int result = template.insert("addUser", user);
        return result;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public int updateUser(@RequestBody User user) {

        return template.update("updateUser", user);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public int delUser(@RequestParam int id) {
        return template.delete("deleteUser", id);
    }

}
