package com.course.testng;

import org.testng.annotations.Test;

/*
异常测试使用环境
在我们期望结果为一个异常的时间
比如：传入的参数不合法，抛出了异常
 */
public class ExpectedException
{
    @Test(expectedExceptions = RuntimeException.class)
    public void reunTimeExceptionFailed()
    {
        System.out.println("runTime exception");
        //加了抛出异常的代码，自然就抛出了，程序运行就对了
//        throw new RuntimeException();
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess()
    {
        System.out.println("run success");
        //核心是这段代码，这里关键信息是抛出了异常，如果没有抛出就是失败filed
        throw new RuntimeException();
    }
}
