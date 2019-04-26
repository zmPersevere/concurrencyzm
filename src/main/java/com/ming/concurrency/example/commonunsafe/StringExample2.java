package com.ming.concurrency.example.commonunsafe;

import com.ming.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

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
@ThreadSafe
public class StringExample2 {

    //请求总数
    public static int clientTotal = 50000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

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
        log.info("size:{}",stringBuffer.length());
    }


    /**
     * 每次执行时在尾部添加字符1
     */
    private static void update(){
        stringBuffer.append("1");
    }

}
