package com.ming.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 12:10 PM 2019/4/27
 */
@Slf4j
public class CountDownLatchExample2 {

    private static int threadCount = 200;

    public static void main(String[] args)throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0 ; i < threadCount ; i ++){
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                }catch (Exception e){
                    log.info("exception",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
    }

    private static void test(int threadNum)throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
