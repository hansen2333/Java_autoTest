package com.test.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    //资源绑定
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;
    //读取配置文件
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException
    {

        String result;
//       从配置文件中 拼接测试的url
        String uri = bundle.getString("getCookies.uri"); //实际路径/getCookies
        //http://localhost:8888/getCookies
        String testUrl = this.url+uri;

//        测试逻辑代码
        //实例化get请求
        HttpGet get = new HttpGet(testUrl);
        //模拟client访问，要先实例化默认的HTTP客户端
        DefaultHttpClient client = new DefaultHttpClient();
        //执行的get请求返回给response
        HttpResponse response = client.execute(get);
        //返回的实体转变为字符串，并赋值
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        this.store = client.getCookieStore();
        //list集合：元素可以重复
        List<Cookie> cookieList = store.getCookies();

        //遍历list集合
        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name + ";  cookie value = " + value);
        }

        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);

        if(statusCode == 200){
            try{
                String cookiesresult = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(cookiesresult);
            }catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url+uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息,代入cookies信息
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);

        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);

        if(statusCode == 200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }
}