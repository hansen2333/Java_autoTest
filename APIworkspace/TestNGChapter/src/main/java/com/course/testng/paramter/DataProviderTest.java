package com.course.testng.paramter;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest
{
    @Test(dataProvider = "data")
    public void testDataProvider(String name ,int age)
    {
        System.out.println("name=" + name + ";age=" + age);
    }

    @DataProvider(name="data") //数据提供给testDataProvider方法
    public Object[][] providerData()
    {
        Object[][] o = new Object[][]
                {
                        {"zhangshan",10},
                        {"wangwu",20},
                        {"hansen",90}
                };
                return o;
    }
    @Test(dataProvider = "methodData")
    public void test1(String name, int age)
    {
        System.out.println("test1 name = " + name + ";age =" + age);
    }
    @Test(dataProvider = "methodData")
    public void test2(String name, int age)
    {
        System.out.println("test2 name =" + name + "; age =" + age);
    }

    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method)
    {
        Object[][] result = null;
        if(method.getName().equals("test1"))
        {
            result = new Object[][]{
                    {"zhangsan",28},
                    {"lisi",98}
            };
        }
        else if(method.getName().equals("test2"))
        {
            result = new Object[][]{
                    {"wangwu",10},
                    {"wuliu",19}
            };
        }
        return result;
    }
}
