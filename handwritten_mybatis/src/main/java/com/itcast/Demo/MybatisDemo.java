package com.itcast.Demo;

import com.itcast.Demo.dao.UserDao;
import com.itcast.Demo.pojo.User;
import com.itcast.mybatis.io.Resources;
import com.itcast.mybatis.sqlsession.SqlSession;
import com.itcast.mybatis.sqlsession.SqlSessionFactory;
import com.itcast.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisDemo {

    public static void main(String[] args){
        //1.读取配置文件
        InputStream in= Resources.class.getClassLoader().getResourceAsStream("com/itcast/mybatis/mappers/config.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session=factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        UserDao userDao=session.getMapper(UserDao.class);
        //5.使用接口
        List<User> list=userDao.selectAll();
        System.out.println(list.size());
        for(User user:list){
            System.out.println(user);
        }

    }
}
