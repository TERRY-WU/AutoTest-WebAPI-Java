package com.webapi.testng;

import org.testng.annotations.*;

public class IgnoreTest {
    @Test
    public void test01() {
        System.out.println("01测试执行");
    }

    @Test(enabled = false)
    public void test02() {
        System.out.println("02测试执行");
    }

}
