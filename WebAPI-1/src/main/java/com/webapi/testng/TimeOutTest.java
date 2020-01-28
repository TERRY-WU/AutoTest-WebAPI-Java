package com.webapi.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000)
    public void testSuccess() throws InterruptedException {
        System.out.println("Test success...!");
        Thread.sleep(1000);
    }

    @Test(timeOut = 2000)
    public void testFailed() throws InterruptedException {
        System.out.println("Test failed...!");
        Thread.sleep(3000);
    }
}
