package com.istimeless.web.server;

import com.istimeless.web.servlet.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * @author lijiayin
 */
public class TomcatServer {
    private Tomcat tomcat;
    private String[] args;
    
    public TomcatServer(String[] args){
        this.args = args;
    }
    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(6699);
        tomcat.start();
        
        //注入servlet
        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());
        //自己定义的servlet
        DispatcherServlet servlet = new DispatcherServlet();
        Tomcat.addServlet(context, "dispatcherServlet", servlet)
        .setAsyncSupported(true);
        //名称和上面的一致
        context.addServletMappingDecoded("/", "dispatcherServlet");
        //context添加到host中
        tomcat.getHost().addChild(context);
        
        Thread awaitThread = new Thread("tomcat_await_thread") {
            @Override
            public void run() {
                TomcatServer.this.tomcat.getServer().await();
            }
        };
        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}
