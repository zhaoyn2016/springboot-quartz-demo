package com.zhaoyn.springboot.quartz.demo.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

/**
 * Created by win on 2017/5/28.
 */
@Configuration
public class ScheduleConfig {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ScheduleConfig.class);

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    @Bean(name = "myJobTrigger")
    public CronTriggerFactoryBean startMyJob() throws SchedulerException {
        logger.info("myJob定时任务开始启动......");
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "myJobGroup")
                .build();
        CronTriggerFactoryBean bean = createCronTrigger(jobDetail,
                "0/10 * * * * ?");
        logger.info("myJob定时任务启动完成......");
        return bean;
    }

    @Bean(name = "demoJobTrigger")
    public CronTriggerFactoryBean startDemoJob() throws SchedulerException {
        logger.info("demoJob定时任务开始启动......");
        JobDetail jobDetail = JobBuilder.newJob(DemoJob.class)
                .withIdentity("demojob", "myJobGroup")
                .build();
        CronTriggerFactoryBean bean = createCronTrigger(jobDetail,
                "0/30 * * * * ?");
        logger.info("demoJob定时任务启动完成......");
        return bean;
    }

    private CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        return factoryBean;
    }

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger... triggers) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory);
        factory.setTriggers(triggers);
        return factory;
    }

}
