package com.zxp.helloplus.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.zxp.helloplus.config.SchoolConfig;
import com.zxp.helloplus.model.Person;
import com.zxp.helloplus.utils.AsyncUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author zxp
 * @date 2020-10-30 14:14
 * @description:
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private SchoolConfig schoolConfig;

    @Autowired
    private AsyncUtil testUtil;

    @RequestMapping("/async")
    public String doTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        testUtil.task1();
        testUtil.task2();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms " + new Random().nextInt(100);
    }

    @RequestMapping(value = "/email/callableReq")
    @ResponseBody
    public Callable<Person> callableReq() {
        System.out.println("外部线程："+Thread.currentThread().getThreadGroup()+"~~~" + Thread.currentThread().getName());
        Callable r = new Callable<Person>() {
            @Override
            public Person call() throws Exception {
                Thread.sleep(10000);
                Person person = new Person();
                person.setId(10);
                person.setDesct("测试");
                System.out.println("内部线程："+Thread.currentThread().getThreadGroup()+"~~~" + Thread.currentThread().getName());
                return person;
            }
        };
        log.info("1");
        log.info("2");
        log.info("3");
        return r;
    }
}
