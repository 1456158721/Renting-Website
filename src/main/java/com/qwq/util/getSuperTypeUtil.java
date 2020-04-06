package com.qwq.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 公共Dao
 * @Author: QWQ
 * @Date: 2019.12.14 15:44
 * @Version: 1.0
 */
public class getSuperTypeUtil {
    public static Class getSuperType(Class clazz){
        ////获取到父类的类型
        Type type = clazz.getGenericSuperclass();
        //这个类型是否支持泛型
        if(! (type instanceof ParameterizedType)){
            return Object.class;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        if(types.length <= 0 ){
            return Object.class;
        }
        return (Class)types[0];
    }
}
