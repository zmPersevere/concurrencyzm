package com.ming.concurrency.example.commonunsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 9:28 PM 2019/4/25
 */
@Slf4j
public class HashMapExample {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new HashMap<>(7000);

    public static void main(String[] args)throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        System.out.println(countDownLatch);
        for (int i = 0 ; i < clientTotal ; i ++){
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("excepiton",e);
                }
                countDownLatch.countDown();
            });
        }
        System.out.println(countDownLatch);
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",map.size());
    }


    /**
     * 每次执行时在尾部添加字符1
     */
    private static void update(int i){
        map.put(i,i);
    }
}
