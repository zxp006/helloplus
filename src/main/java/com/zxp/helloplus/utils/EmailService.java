package com.zxp.helloplus.utils;

import com.zxp.helloplus.controller.AsyncEmailController;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private ApplicationContext applicationContext;

    @Async
    public void testSyncTask() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("异步任务执行完成！");
    }


    public void asyncCallTwo() throws InterruptedException {
        //这样调用同类下的异步方法是不起作用的
        this.testSyncTask();

        boolean isAop = AopUtils.isAopProxy(EmailService.class);//是否是代理对象；
        boolean isCglib = AopUtils.isCglibProxy(EmailService.class);  //是否是CGLIB方式的代理对象；
        boolean isJdk = AopUtils.isJdkDynamicProxy(EmailService.class);  //是否是JDK动态代理方式的代理对象；
        //以下才是重点!!! 通过上下文获取自己的代理对象调用异步方法
		EmailService emailService = (EmailService)applicationContext.getBean(EmailService.class);
//        EmailService proxy = (EmailService) AopContext.currentProxy();
//        System.out.println(emailService == proxy ? true : false);
        emailService.testSyncTask();
        System.out.println("end!!!");
    }
}