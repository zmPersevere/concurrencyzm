package com.ming.concurrency.example.syncContainer;

import com.ming.concurrency.annoations.NotThreadSafe;
import com.ming.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 9:25 PM 2019/4/25
 */
@Slf4j
@ThreadSafe
public class VectorExample1 {

    //请求总数
    public static int clientTotal = 50000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    private static List<Long> list = new Vector<>();

    public static void main(String[] args)throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        System.out.println(countDownLatch);
        for (int i = 0 ; i < clientTotal ; i ++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(countDownLatch.getCount());
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
        log.info("size:{}",list.size());
    }


    /**
     * 每次执行时在尾部添加字符1
     */
    private static void update(Long i){
        list.add(i);
    }

}
