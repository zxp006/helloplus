package com.zxp.helloplus.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zxp
 * @date 2020-10-30 17:46
 * @description:
 */
@Service
@Slf4j
public class AsyncUtil {
    @Async
    public void task1() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("线程:"+Thread.currentThread().getThreadGroup()+" ~ "+Thread.currentThread().getName()+"异步执行"+" task1任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }

    @Async
    public void task2() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(5000);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("线程:"+Thread.currentThread().getThreadGroup()+" ~ "+Thread.currentThread().getName()+"异步执行"+" task2任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
    }
}
