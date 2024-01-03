package com.tester.utils;

import com.tester.table.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile
{
    //读取配置文件
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name)
    {
        String address = bundle.getString("test.url");
        String uri = "";
        String testUrl;
        //这里调用是枚举的参数
        if(name == InterfaceName.GETUSERLIST)
        {
            uri = bundle.getString("getUserList.uri");
        }
        if(name == InterfaceName.LOGIN)
        {
            uri = bundle.getString("login.uri");
        }
        if(name == InterfaceName.UPDATEUSERINFO)
        {
            uri = bundle.getString("getUserInfo.uri");
        }
        if(name == InterfaceName.ADDUSERINFO)
        {
            uri = bundle.getString("addUser.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
}
