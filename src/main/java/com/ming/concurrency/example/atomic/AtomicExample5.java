package com.ming.concurrency.example.atomic;

import com.ming.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 9:49 PM 2019/4/23
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }
        AtomicExample5 example = new AtomicExample5();
        if (updater.compareAndSet(example,100,120)){
            log.info("update success 2,{}",example.getCount());
        }else {
            log.info("update failed,{}",example.getCount());
        }
    }

}
