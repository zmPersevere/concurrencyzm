package com.ming.concurrency.example.singleton;

import com.ming.concurrency.annoations.NotRecommend;
import com.ming.concurrency.annoations.NotThreadSafe;
import com.ming.concurrency.annoations.ThreadSafe;

/**
 * @Description : 懒汉模式，单例实例在第一次使用时实现。
 * @Author : zhangMing
 * @Date : Created in 8:50 PM 2019/4/24
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    //私有构造方法
    private SingletonExample3(){}

    //单例对象
    private static SingletonExample3 instance = null;

    /**
     * 静态的工厂方法
     * @return
     */
    private static synchronized SingletonExample3 getSingleton(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }

}
