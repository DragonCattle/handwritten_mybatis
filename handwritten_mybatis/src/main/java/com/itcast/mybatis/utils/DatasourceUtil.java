package com.itcast.mybatis.utils;

import com.itcast.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatasourceUtil {


    public static Connection getConnection(Configuration cfg){
        Connection connection=null;
        try {
            Class.forName(cfg.getDriver());
            connection=DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
