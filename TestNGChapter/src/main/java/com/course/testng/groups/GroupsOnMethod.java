package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;


public class GroupsOnMethod
{
    @Test(groups = "server")
    public void test1()
    {
        System.out.println("server group Test execute 001");
    }

    @Test(groups = "server")
    public void test2()
    {
        System.out.println("server group test execute02");
    }

    @Test(groups = "client")
    public void test3()
    {
        System.out.println("client group test execute01");
    }

    @Test(groups = "client")
    public void test4()
    {
        System.out.println("client group test execute02");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer()
    {
        System.out.println("server group test before execute");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer()
    {
        System.out.println("server group test after execute");
    }
    @BeforeGroups("client")
    public void beforeGroupsOnClient()
    {
        System.out.println("client group test Before execute");
    }
    @AfterGroups("client")
    public void afterGroupsOnClient()
    {
        System.out.println("client group test after execute");
    }
}
