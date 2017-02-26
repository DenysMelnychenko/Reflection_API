package com.reflection_api.models;

import com.reflection_api.annotations.AfterTests;
import com.reflection_api.annotations.BeforeTests;
import com.reflection_api.annotations.Ignore;
import com.reflection_api.annotations.Test;

public class TestClass {

    private Integer a;
    private Integer b;
    private Integer c;


    @BeforeTests
    public void init() {
        System.out.println("Data initialization");
        this.a = 0;
        this.b = 5;
        this.c = 120;
    }

    @Test
    public void sum() throws MismatchedData {
        int result = b + c;
        Assert.assertEquals(125, result);
    }

    @Test
    public void division() throws MismatchedData {
        int result = b / a;
        Assert.assertEquals(0, result);
    }

    @Test
    @Ignore
    public void multiply() throws MismatchedData {
        int result = b * c;
        Assert.assertEquals(600, result);
    }

    @AfterTests
    public void destroy() {
        System.out.println("Data utilization");
        this.a = null;
        this.b = null;
        this.c = null;
    }
}

 class Assert {

    public static void assertEquals(int expected, int actual) throws MismatchedData {
        if(expected != actual){
            throw new MismatchedData();
        }
    }
}


 class MismatchedData extends Exception {
}


