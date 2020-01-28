package com.webapi.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    @Test
    public void testCase01() {
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("This is testcase01");
    }

    @Test
    public void testCase02(){
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("This is testcase02");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }
}
