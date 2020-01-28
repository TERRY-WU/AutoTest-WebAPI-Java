package com.webapi.testng.groups;

import org.testng.annotations.*;

public class GroupsOnMethod {

    @Test(groups = "server")
    public void test1() {
        System.out.print("服务端测试方法11111");
    }

    @Test(groups = "server")
    public void test2() {
        System.out.print("服务端测试方法22222");
    }

    @Test(groups = "client")
    public void test3() {
        System.out.print("客户端测试方法333333");
    }

    @Test(groups = "client")
    public void test4() {
        System.out.print("客户端测试方法444444");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer() {
        System.out.print("这是服务端组运行之前运行的方法");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer() {
        System.out.print("这是服务端组运行之后运行的方法！！！！！");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient() {
        System.out.print("这是客户端组运行之前运行的方法");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient() {
        System.out.print("这是客户端组运行之后运行的方法！！！！！");
    }

}
