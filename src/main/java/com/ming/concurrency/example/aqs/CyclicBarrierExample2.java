package com.ming.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 12:44 PM 2019/4/27
 */
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 10 ; i ++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try{
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        log.info("{} is ready",threadNum);
        try {
            Thread.sleep(1000);
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException e){
            log.warn("BrokenBarrierException or TimeoutException",e);
        }
        log.info("{} continue",threadNum);
    }
}
