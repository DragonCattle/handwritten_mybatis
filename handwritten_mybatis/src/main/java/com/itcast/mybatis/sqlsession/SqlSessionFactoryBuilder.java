package com.itcast.mybatis.sqlsession;

import com.itcast.mybatis.cfg.Configuration;
import com.itcast.mybatis.defaults.DefaultSqlSessionFactory;
import com.itcast.mybatis.utils.Dome4jUtil;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    private Dome4jUtil dome4jUtil=new Dome4jUtil();

    /**
     * 根据参数的字节流构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){

        //获取连接数据
        Configuration cfg= dome4jUtil.getConfiguration(config);

        return new DefaultSqlSessionFactory(cfg);
    }
}
