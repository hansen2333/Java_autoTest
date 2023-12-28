package com.test.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost
{
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest()
    {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url+uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for(Cookie cookie:cookieList)
        {
            String name = cookie.getName();
            String value = cookie.getName();
            System.out.println("cookie name=" + name + "; cookie value" + value);
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testPosMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        String testUrl = this.url+uri;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost(testUrl);
        //传入Json参数，与JMeter body效果是相同的
        JSONObject param = new JSONObject();
        param.put("name","hansen");
        param.put("age","40");
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //将参数化传入到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;

        client.setCookieStore(this.store);
        HttpResponse response = client.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //处理返回结果
        JSONObject resultJson = new JSONObject(result); //转换成json格式

        //处理response的对应信息
        String success = (String) resultJson.get("hansen");
        String status = (String) resultJson.get("status");

        Assert.assertEquals("success", success);
        Assert.assertEquals("1",status);
    }
}
