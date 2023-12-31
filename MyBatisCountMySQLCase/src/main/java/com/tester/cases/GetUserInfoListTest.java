package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.table.GetUserListCase;
import com.tester.table.User;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest
{
    @Test(dependsOnGroups = "loginTrue", description = "get information about users who are male")
    public void getUserListInfo() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        JSONArray resultJson = getJsonResult(getUserListCase);

        Thread.sleep(2000);
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User u:userList)
        {
            System.out.println("list get user:" + u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);

        Assert.assertEquals(userListJson.length(), resultJson.length());
        for(int i=0;i<resultJson.length();i++)
        {
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }

    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString() ,"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);
        System.out.println("calling interface list result:"+ result);
        return  jsonArray;
    }
}
