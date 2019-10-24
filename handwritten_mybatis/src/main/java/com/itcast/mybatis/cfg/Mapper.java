package com.itcast.mybatis.cfg;


/**
 * 封装执行sql语句和类的路径    ----->  mapper.xml
 *
 */
public class Mapper {

    private String queryString;  //sql
    private String resultType;   //类的路径

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
