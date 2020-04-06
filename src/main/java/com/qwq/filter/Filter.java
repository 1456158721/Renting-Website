package com.qwq.filter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 请求拦截器
 * @Author: QWQ
 * @Date: 2019.12.11 11:27:00
 * @Version: 2.0
 */
public class Filter implements Interceptor {
    @Override
    //拦截器的销毁方法
    public void destroy() {

    }

    @Override
    //拦截器的初始化方法
    public void init() {

    }

    @Override
    //拦截器的拦截方法
    public String intercept( ActionInvocation actionInvocation ) throws Exception {
        //调用invoke()方法，代表着放行执行下一个拦截器，如果没有拦截器了，那么就执行Action的业务代码
        //可看成是过滤器的doFilter()方法
        actionInvocation.invoke();
        return null;
    }
}
