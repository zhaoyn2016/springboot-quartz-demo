package com.zhaoyn.springboot.quartz.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by win on 2017/5/28.
 */
@SpringBootApplication
public class Application   {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.web(true);
        ApplicationContext ctx = builder.run(args);
        DispatcherServlet dispatcherServlet = ctx.getBean(DispatcherServlet.class);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }


}
