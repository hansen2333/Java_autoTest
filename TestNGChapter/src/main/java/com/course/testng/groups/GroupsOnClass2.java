package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2
{
    public void stu1()
    {
        System.out.println("groupOnClass stu1 execute");
    }

    public void stu2()
    {
        System.out.println("groupOnClass stu2 execute");
    }
}
