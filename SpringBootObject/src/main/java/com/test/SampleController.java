package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 第一个springboot的web接口
 * springboot内置了Tomcat，默认接口是8080
 * 访问是ip+端口，因为是本地跑所以是：localhost:8080
 * 这是官方文档
 */
@Controller //控制器
@EnableAutoConfiguration //启用自动配置  enable：启用、使用
public class SampleController
{
    @RequestMapping("/") //请求映射
    @ResponseBody
    String home()
    {
        return "hello,world111";
    }

    public static void main(String[] args)
    {

        SpringApplication.run(SampleController.class,args);
    }
}
