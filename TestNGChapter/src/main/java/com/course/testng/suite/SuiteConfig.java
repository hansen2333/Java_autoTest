package com.course.testng.suite;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

//测试套件之前运行的方法
public class SuiteConfig
{
    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("suite run");
    }
    @AfterSuite
    public void afterSuite()
    {
        System.out.println("suite stop");
    }
    @BeforeTest
    public void beforeTest()
    {
        System.out.println("beforeTest run");
    }
    @AfterTest
    public void afterTest()
    {
        System.out.println("afterTest stop");
    }
}
