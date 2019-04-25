package com.ming.concurrency.example.singleton;

import com.ming.concurrency.annoations.NotThreadSafe;

/**
 * @Description : 懒汉模式，单例实例在第一次使用时实现。
 * @Author : zhangMing
 * @Date : Created in 8:50 PM 2019/4/24
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有构造方法
    private SingletonExample1(){}

    //单例对象
    private static SingletonExample1 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    private static SingletonExample1 getSingleton(){
        if (instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }

}
