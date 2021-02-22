package com.zxp.helloplus.controller;

import com.zxp.helloplus.utils.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AsyncEmailController {

	//获取ApplicationContext对象方式有多种,这种最简单,其它的大家自行了解一下
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
    private EmailService emailService;
	
    @RequestMapping(value = "/email/asyncCall1", method = RequestMethod.GET)
    public Map<String, Object> asyncCall () {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try{
            //通过上下文获取自己的代理对象调用异步方法
            AsyncEmailController emailController = (AsyncEmailController)applicationContext.getBean(AsyncEmailController.class);

            //这样调用同类下的异步方法是不起作用的
            this.testAsyncTask();
    		emailController.testAsyncTask();
            resMap.put("code",200);
        }catch (Exception e) {
			resMap.put("code",400);
            log.error("error!",e);
        }
        return resMap;
    }

	//注意一定是public,且是非static方法
	@Async
    public void testAsyncTask() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("异步任务执行完成！");
        log.info("线程:"+Thread.currentThread().getThreadGroup()+" ~ "+Thread.currentThread().getName()+"异步执行");
    }


    @RequestMapping(value = "/email/asyncCall2", method = RequestMethod.GET)
    public Map<String, Object> asyncCall2 () {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try{
            boolean isAop0 = AopUtils.isAopProxy(AsyncEmailController.class);//是否是代理对象；
            boolean isCglib0 = AopUtils.isCglibProxy(AsyncEmailController.class);  //是否是CGLIB方式的代理对象；
            boolean isJdk0 = AopUtils.isJdkDynamicProxy(AsyncEmailController.class);  //是否是JDK动态代理方式的代理对象；

            boolean isAop1 = AopUtils.isAopProxy(EmailService.class);//是否是代理对象；
            boolean isCglib1 = AopUtils.isCglibProxy(EmailService.class);  //是否是CGLIB方式的代理对象；
            boolean isJdk1 = AopUtils.isJdkDynamicProxy(EmailService.class);  //是否是JDK动态代理方式的代理对象；

            boolean isAop = AopUtils.isAopProxy(emailService);//是否是代理对象；
            boolean isCglib = AopUtils.isCglibProxy(emailService);  //是否是CGLIB方式的代理对象；
            boolean isJdk = AopUtils.isJdkDynamicProxy(emailService);  //是否是JDK动态代理方式的代理对象；
            emailService.asyncCallTwo();
            resMap.put("code",200);
        }catch (Exception e) {
            resMap.put("code",400);
            log.error("error!",e);
        }
        return resMap;
    }
}