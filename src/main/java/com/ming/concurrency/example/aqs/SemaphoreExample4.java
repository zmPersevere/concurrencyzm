package com.ming.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 12:10 PM 2019/4/27
 */
@Slf4j
public class SemaphoreExample4 {

    private static int threadCount = 30;

    public static void main(String[] args)throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0 ; i < threadCount ; i ++){
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){
                        test(threadNum);
                        semaphore.release();
                    }
                }catch (Exception e){
                    log.info("exception",e);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum)throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
