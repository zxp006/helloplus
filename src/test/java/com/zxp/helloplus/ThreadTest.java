package com.zxp.helloplus;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author zxp
 * @date 2020-11-02 10:39
 * @description:
 */
@Slf4j
public class ThreadTest {

    @Test
    public void test01() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("子线程暂停3s");
                Thread.sleep(3000);
                return String.format("当前的线程信息：%d_%s",Thread.currentThread().getId(),Thread.currentThread().getName());
            }
        });
        new Thread(task).start();
        log.info("主线程执行。。。");
        log.info(task.get());
        log.info("主线程结束。。。");
    }


    @Test
    public void  test02() throws ExecutionException, InterruptedException {
        ExecutorService executorService=Executors.newFixedThreadPool(10);
        Future<String> future= executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("线程池_子线程暂停5s");
                Thread.sleep(5000);
                return String.format("线程池_当前的线程信息：%d_%s",Thread.currentThread().getId(),Thread.currentThread().getName());
            }
        });
        log.info("线程池_主线程执行。。。");
        log.info(future.get());
        log.info("线程池_主线程结束。。。");
    }
}
