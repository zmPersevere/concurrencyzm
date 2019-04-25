package com.ming.concurrency.example.singleton;

import com.ming.concurrency.annoations.ThreadSafe;

/**
 * @Description : 饿汉模式，类装载时创建.
 * @Author : zhangMing
 * @Date : Created in 8:56 PM 2019/4/24
 */
@ThreadSafe
public class SingletonExample6 {

    //私有构建方法
    private SingletonExample6(){
    }

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
    }
}
