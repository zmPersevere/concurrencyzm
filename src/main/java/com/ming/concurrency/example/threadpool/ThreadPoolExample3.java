package com.ming.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 7:41 PM 2019/5/5
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0 ; i < 10 ; i ++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        log.info("task:{}",index);
                    }catch (Exception e){
                        log.error("sleep",e);
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
