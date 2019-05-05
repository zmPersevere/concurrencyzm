package com.ming.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description : 一个简单的死锁累
 * @Author : zhangMing
 * @Date : Created in 7:58 PM 2019/5/5
 */
@Slf4j
public class DeadLock implements Runnable {

    public int flag = 1;

    //静态对象是累的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}",flag);
        if (flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    log.error("sleep",e);
                }
                synchronized (o2){
                    log.info("wait");
                }
            }
        }

        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    log.error("sleep",e);
                }
                synchronized (o1){
                    log.info("wait");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock dl1 = new DeadLock();
        DeadLock dl2 = new DeadLock();
        dl1.flag = 1 ;
        dl2.flag = 0;
        //td1,td2都处于可执行状态，但JVM线程调度先执行哪个线程是不确定的
        //td2的run()可能在td1的run()之前运行
        new Thread(dl1).start();
        new Thread(dl2).start();
    }
}
