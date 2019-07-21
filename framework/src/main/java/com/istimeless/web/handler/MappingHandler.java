package com.istimeless.web.handler;

import lombok.AllArgsConstructor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lijiayin
 */
@AllArgsConstructor
public class MappingHandler {
    /**
     * 请求uri
     */
    private String uri;
    /**
     * 请求方法
     */
    private Method method;
    /**
     * 请求的类
     */
    private Class<?> controller;
    /**
     * 请求的参数名
     */
    private String[] args;
    
    public boolean handle(ServletRequest req, ServletResponse res)
            throws IllegalAccessException, InstantiationException, 
            InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        if(!uri.equals(requestUri)){
            return false;
        }
        
        //获取参数
        Object[] parameters = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            parameters[i] = req.getParameter(args[i]);
        }
        
        Object ctl = controller.newInstance();
        Object response = method.invoke(ctl, parameters);
        res.getWriter().println(response.toString());
        return true;
    }
}
