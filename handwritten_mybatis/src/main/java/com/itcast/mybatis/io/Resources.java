package com.itcast.mybatis.io;


import java.io.InputStream;

/**
 * 使用类加载器读取配置文件
 */
public class Resources {

    /**
     * 根绝传入文件路径，获取流
     */

    public static InputStream getResourceAsStream(String classPath){
        return Resources.class.getClassLoader().getResourceAsStream(classPath);
    }
}
