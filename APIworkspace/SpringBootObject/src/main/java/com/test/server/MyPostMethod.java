package com.test.server;

import com.test.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "my all post request")
@RequestMapping("/v1")
public class MyPostMethod
{
    private static Cookie cookie;//装cookie信息

    //用户登录成功获取到cookie，然后再访问其他接口获取列表
    @RequestMapping(value ="login", method = RequestMethod.POST)
    @ApiOperation(value ="login API, success get cookies", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value="userName",required = true) String userName,
                        @RequestParam(value="password",required = true) String password)
    {
        if(userName.equals("hansen")&& password.equals("123456"))
        {
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "congratulations,you're logged in";
        }
        return "userName or password error";
    }

    @RequestMapping(value ="/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "get userName list" , httpMethod = "POST")
    //httpServletRequest的参数是传入cookie信息，如果没有cookie参数传不进来
    public String getUserList(HttpServletRequest request, @RequestBody User u)
    {
        User user;
        Cookie[] cookies = request.getCookies(); //获取cookies
        for(Cookie c:cookies)
        {
            if(c.getName().equals("login") && c.getValue().equals("true") && u.getUserName().equals("hansen")
            && u.getPassword().equals("123456"))
            {
                user = new User();
                user.setName("hansen");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "parameter not legal";
    }
}
