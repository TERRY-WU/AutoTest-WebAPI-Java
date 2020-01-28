package com.webapi.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadTest {

    @Test(invocationCount = 3, threadPoolSize = 3)
    public void test01() {
        System.out.println("haha...!");
        System.out.printf("Thread ID: %s%n", Thread.currentThread().getId());

        System.out.printf("true = %b; false = ", true);
        System.out.printf("%b%n", false);
        System.out.printf("%s = %s%n", "Name", "Zhangsan");

    }
}
