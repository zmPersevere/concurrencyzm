package com.ming.concurrency.example.concurrent;

import com.ming.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 9:28 PM 2019/4/25
 */
@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {

    //请求总数
    public static int clientTotal = 50000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new ConcurrentHashMap<>(70000);

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
