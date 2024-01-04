package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.table.InterfaceName;
import com.tester.table.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class LoginTest
{
    @BeforeTest(groups = "loginTrue", description = "test preparatory work, get HttpClient object")
    public void beforeTest()
    {
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
    }

    @Test(groups = "loginTrue",description = "user login success")
    public void loginTrue() throws Exception {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        //写完接口测试代码
        String result = getResult(loginCase);
        //断言
//        Assert.assertEquals(loginCase.getExpected(), result);
    }

    @Test(description = "user login fail")
    public void loginFalse() throws Exception {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        String result = getResult(loginCase);
//        Assert.assertEquals(loginCase.getExpected(), result);
    }


    private String getResult(LoginCase loginCase) throws  Exception
    {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName", loginCase.getUserName());
        param.put("password", loginCase.getPassword());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;
//        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
//        result = EntityUtils.toString(response.getEntity(),"utf-8");
//        System.out.println(result);
//        TestConfig.store = (CookieStore) TestConfig.defaultHttpClient.getCookieStore();
//        return result;

        return null;
    }
}
