package com.course.testng;

import org.testng.annotations.*;
import org.testng.internal.annotations.IBeforeSuite;

public class BasicAnnotation {
    @Test
    public void testCase1(){
        //这个占位符被multThread.xml的参数调用了，传进来了参数
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("主子2");
    }
    @BeforeMethod
    public void testCase2()
    {
        System.out.printf("Thread Id:%s%n", Thread.currentThread().getId());
        System.out.println("奴才跟着主子");
    }
    @AfterMethod
    public void testCase3()
    {
        System.out.println("奴才跟着主子");
        System.out.println("-----------------------------------------------------");
    }
    @Test
    public void testCase4()
    {
        System.out.println("主子1");
    }

    @BeforeTest
    public void testCase5()
    {
        System.out.println("随时接客的妓女");
    }
    @AfterTest
    public void testCase8()
    {
        System.out.println("龟奴收拾场子");
    }

    @BeforeClass
    public void testCase6()
    {
        System.out.println("做局的");
    }
    @AfterClass
    public void testCase7()
    {
        System.out.println("买单的");
    }

    @BeforeSuite
    public void BeforeSuite()
    {
        System.out.println("????");
    }
    @AfterSuite
    public void afterSuite()
    {
        System.out.println("????111");
    }
}
