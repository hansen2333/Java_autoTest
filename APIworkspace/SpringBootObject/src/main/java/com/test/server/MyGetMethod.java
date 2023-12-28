package com.test.server;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod
{
    @RequestMapping(value="/getCookiest",method= RequestMethod.GET)
    public String getCookies(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "Congratulations on obtaining cookies success";
    }

    /**
     * 要求客户端携带Cookies方法
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies))
        {
            return "you must write to carry cookie information to access the";
        }
        for(Cookie cookie:cookies)
        {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true"))
            {
                return "this is must write to carry cookie information to access the request";
            }
        }
        return "you must carry cookie !!!";
    }
    /**
     * 开发一个需需要携带参数才能访问的get请求
     *第一种实现方式：url拼接
     */

    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    //访问方式：http://localhost:8898/get/with/param?start=1&end=20
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end)
    {
        Map<String,Integer> myList = new HashMap<>();
        myList.put("shoe",200);
        myList.put("noodle",15);
        myList.put("shirt",99);

        return myList;
    }
    /**
     * 第二种方式携带参数访问的get请求
     */
    @RequestMapping(value="/get/with/param/{start}/{end}")
    @ApiOperation(value=" must write to carry parameter to access!!!", httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start, @PathVariable Integer end)
    {
        Map<String,Integer> myList = new HashMap<>();

        myList.put("pencil",3);
        myList.put("fan",15);
        myList.put("mug",55);

        return myList;
    }
}
