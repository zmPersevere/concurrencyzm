package com.ming.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 8:06 PM 2019/4/24
 */
@Slf4j
public class SynchronizedExample2 {

    public void test1(){
        synchronized (SynchronizedExample2.class){
            for (int i = 0 ; i < 100 ; i ++){
                log.info("test1 - {}",i);
            }
        }
    }

    public synchronized static void test2(){
        for (int i = 0 ; i < 100 ; i ++){
            log.info("test2 - {}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            example1.test1();
        });
        executorService.execute(() ->{
            example2.test2();
        });
        executorService.shutdown();
    }
}
