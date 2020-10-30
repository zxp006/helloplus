package com.zxp.helloplus.controller;

import com.zxp.helloplus.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author zxp
 * @date 2020-10-30 14:14
 * @description:
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private TestUtil testUtil;
    @RequestMapping("/async")
    public String doTask() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        testUtil.task1();
        testUtil.task2();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms "+new Random().nextInt(100);
    }


}
