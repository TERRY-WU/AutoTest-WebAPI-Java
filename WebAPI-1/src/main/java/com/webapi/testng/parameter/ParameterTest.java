package com.webapi.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    @Test
    @Parameters({"name", "age"})
    public void params(String name, int age) {
        System.out.println("name: " + name + "..." + "age: " + age);
    }

}
