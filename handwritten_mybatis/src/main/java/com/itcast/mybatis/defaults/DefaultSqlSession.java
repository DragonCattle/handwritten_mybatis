package com.itcast.mybatis.defaults;

import com.itcast.mybatis.Proxy.MapperProxy;
import com.itcast.mybatis.cfg.Configuration;
import com.itcast.mybatis.sqlsession.SqlSession;
import com.itcast.mybatis.utils.DatasourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {


    private Configuration cfg;
    //数据库连接
    private Connection connection;

    public  DefaultSqlSession(Configuration cfg){
        this.cfg=cfg;
        connection= DatasourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     * 第一个参数：加载动态代理的类（类加载器）
     * 第二个参数：我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
     * 第三个参数：这是一个实行的类
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
                                //接口类                           //获取所有的接口方法
        return (T)Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 释放资源
     */
    public void close() {
        //关闭数据库连接
        if(null!=connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
