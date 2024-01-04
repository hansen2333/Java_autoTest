package com.tester.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.assertNotNull;

public class DatabaseUtil
{
    public static SqlSession getSqlSession() throws IOException
    {
        //读取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //得到SqlSessionFactory，使用类加载器加载xml文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //得到SqlSession对象，这个对象就能执行配置文件中的sql语句。
        SqlSession session = factory.openSession();

        return  session;
    }

    /**
     * description:databases test code
     * @throws IOException
     */
    @Test
    public void  testGetSession() throws IOException
    {
        SqlSession session = DatabaseUtil.getSqlSession();
        assertNotNull(session);
    }
}
