package com.ming.concurrency.example.singleton;

import com.ming.concurrency.annoations.ThreadSafe;

/**
 * @Description : 饿汉模式，类装载时创建.
 * @Author : zhangMing
 * @Date : Created in 8:56 PM 2019/4/24
 */
@ThreadSafe
public class SingletonExample2 {

    //私有构建方法
    private SingletonExample2(){}

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
