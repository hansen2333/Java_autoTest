package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion
{
    //invocationCount：调用计数，线程池3个
    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test()
    {
        System.out.println(1);
        //这里的%s%n的参数是：Thread.currentThread.getId()传递进去的
        //  %n是换行
        System.out.printf("Thread Id: %s%n", Thread.currentThread().getId());
    }
}
