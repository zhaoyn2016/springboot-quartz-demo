package com.zhaoyn.springboot.quartz.demo.quartz;

import com.zhaoyn.springboot.quartz.demo.service.DemoService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 * Created by win on 2017/5/28.
 */
public class MyJob extends QuartzJobBean {

    @Autowired
    private DemoService demoService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        demoService.say();
    }
}
