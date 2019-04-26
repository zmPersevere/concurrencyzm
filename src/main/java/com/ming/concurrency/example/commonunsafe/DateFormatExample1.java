package com.ming.concurrency.example.commonunsafe;

import com.ming.concurrency.annoations.NotThreadSafe;
import com.ming.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 8:56 PM 2019/4/25
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

    //请求总数
    public static int clientTotal = 500;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args)throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        System.out.println(countDownLatch);
        for (int i = 0 ; i < clientTotal ; i ++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
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
    }


    /**
     * 每次执行时在尾部添加字符1
     */
    private static void update(){
        try {
            dateFormat.parse("20181111");
        }catch (Exception e){
            log.error("parse exception",e);
        }
    }

}
