package com.ming.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 9:22 PM 2019/4/28
 */
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal");//1
                condition.await();
            }catch (InterruptedException e){
                log.error("InterruptedException",e);
            }
            log.info("get signal");//4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock");
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                log.error("InterruptedException",e);
            }
            condition.signalAll();
            log.info("send sigal ~");
            reentrantLock.unlock();
        }).start();
    }

}
