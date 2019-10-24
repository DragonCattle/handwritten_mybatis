package com.itcast.mybatis.defaults;

import com.itcast.mybatis.cfg.Configuration;
import com.itcast.mybatis.sqlsession.SqlSession;
import com.itcast.mybatis.sqlsession.SqlSessionFactory;


/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public  DefaultSqlSessionFactory(Configuration cfg){
        this.cfg=cfg;
    }

    /**
     * 用于创建一个操作数据库的对象
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
