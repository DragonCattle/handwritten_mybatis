package com.itcast.mybatis.Proxy;
import com.itcast.mybatis.cfg.Mapper;
import com.itcast.mybatis.utils.Executor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;


/**
 * 代理对象
 */
public class MapperProxy implements InvocationHandler{

    //map的key是接口路径+方法名称id
    private Map<String,Mapper>  mappers;
    private Connection connection;

    public  MapperProxy(Map<String,Mapper>  mapper,Connection connection){
        this.mappers=mapper;
        this.connection=connection;
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     * 第一个参数：
     * 第二个参数：被拦截的方法
     * 第三个参数：改方法的参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName=method.getName();
        //2.获取方法的所在类名
        String className=method.getDeclaringClass().getName();
        //3.组合key
        String key=className+":"+methodName;
        //4.获取mappers中的mapper对象
        Mapper mapper=mappers.get(key);
        //5.判断是否为空
        if(mapper==null){
            throw new IllegalAccessException("传入参数有误");
        }
        //6.工具类执行sql查询
        return new Executor().selectList(mapper,connection);
    }
}
