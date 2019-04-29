package com.ming.concurrency.example.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 7:17 PM 2019/4/29
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });
        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
