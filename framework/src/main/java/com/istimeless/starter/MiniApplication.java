package com.istimeless.starter;

import com.istimeless.beans.BeanFactory;
import com.istimeless.core.ClassScanner;
import com.istimeless.web.handler.HandlerManager;
import com.istimeless.web.server.TomcatServer;

import java.util.List;

/**
 * @author lijiayin
 */
public class MiniApplication {
    public static void run(Class<?> cls, String[] args){
        System.out.println("Hello Mini-Spring!");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
            //处理请求
            HandlerManager.resolveMappingHandler(classList);
            //初始化Bean
            BeanFactory.initBean(classList);
            classList.forEach(e -> System.out.println(e.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
