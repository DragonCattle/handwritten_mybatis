package com.itcast.mybatis.utils;

import com.itcast.mybatis.cfg.Configuration;
import com.itcast.mybatis.cfg.Mapper;
import com.itcast.mybatis.io.Resources;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Dome4jUtil {

    private Configuration cfg=new Configuration();

    /**
     * 读取连接数据库的配置文件
     * @return
     */
    public  Configuration getConfiguration(InputStream config){
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = null;
        try {
            document = reader.read(config);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        //4.获取节点属性
        for(Object item:rootElement.elements("porperty")){
            Element element=(Element)item;
            String name=element.attribute("name").getValue();
            switch (name){
                case "driver" :
                    cfg.setDriver(element.getStringValue());
                    break;
                case "url":
                    cfg.setUrl(element.getStringValue());
                    break;
                case "username":
                    cfg.setUsername(element.getStringValue());
                    break;
                case "password":
                    cfg.setPassword(element.getStringValue());
                    break;
            }
        }
        for(Object i:rootElement.elements("mappers")){
            Element elements=(Element)i;
            List<Element> list=elements.elements();
            for(Element e:list){
                String mapperName=e.attribute("resource").getValue();
                cfg.setMappers(loadMapperConfiguration(mapperName));
            }
        }


        return cfg;
    }

    public  static Map<String,Mapper> loadMapperConfiguration(String mapperPath){
        InputStream in=null;
        Map<String,Mapper> map=new HashMap<>();
        in=Dome4jUtil.class.getClassLoader().getResourceAsStream(mapperPath);
        //1.获取解析对象
        SAXReader reader = new SAXReader();
        //2.获取流
        Document document = null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //3.获取根节点
        Element root=document.getRootElement();
        //4.获取去namespace
        String namespace=root.attributeValue("namespace");
        for(Element e:root.elements("select")){
            String id=e.attributeValue("id");
            String resultType=e.attributeValue("resultType");
            String sql=e.getText();
            //map的key   namespace+id
            String key=namespace+":"+id;
            //创建value
            Mapper mapper=new Mapper();
            mapper.setQueryString(sql);
            mapper.setResultType(resultType);
            map.put(key,mapper);
        }

        return map;
    }
}
