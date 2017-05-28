package com.zhaoyn.springboot.quartz.demo.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by win on 2017/5/28.
 */

@Service
public class DemoService {

    public void say(){
        System.out.println("quartz定时任务开始执行，执行方法:say,时间: "+new Date());
    }

    public void hello(){
        System.out.println("quartz定时任务开始执行，执行方法:hello,执行时间: "+new Date());
    }
}
