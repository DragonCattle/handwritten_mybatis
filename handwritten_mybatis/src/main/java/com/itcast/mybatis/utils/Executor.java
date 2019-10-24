package com.itcast.mybatis.utils;

import com.itcast.Demo.pojo.User;
import com.itcast.mybatis.cfg.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行sql语句的工具类
 */
public class Executor {


    public <E> List<E>  selectList(Mapper mapper, Connection conn){
        PreparedStatement pstm=null;
        ResultSet rs=null;
        //1.获取执行sql
        String sql=mapper.getQueryString();
        //2.获取返回对象
        String classPaht=mapper.getResultType();
        List<User> list=new ArrayList<>();
        //3.获取对象
        try {
            Class claz=Class.forName(classPaht);
            pstm=conn.prepareStatement(sql);
            rs=pstm.executeQuery();
            //4.封装结果集
            User user=new User();
            while (rs.next()){
                user.setId(rs.getString(1));
                user.setUser_name(rs.getString(2));
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (List<E>) list;
    }

}
