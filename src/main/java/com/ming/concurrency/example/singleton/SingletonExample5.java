package com.ming.concurrency.example.singleton;

import com.ming.concurrency.annoations.NotRecommend;
import com.ming.concurrency.annoations.ThreadSafe;

/**
 * @Description : 懒汉模式 -> 双重同步锁，单例实例在第一次使用时实现。
 * @Author : zhangMing
 * @Date : Created in 8:50 PM 2019/4/24
 */
@ThreadSafe
public class SingletonExample5 {

    //私有构造方法
    private SingletonExample5(){}

    //单例对象
    private static volatile SingletonExample5 instance = null;

    //1、memory = allocate() 分配对象的内存空间
    //2、ctorInstace() 初始化对象
    //3、instance = memory 设置instance指向刚分配的内存

    //JVM和cpu优化，发生了指令重排
    //1、memory = allocate() 分配对象的内存空间
    //3、instance = memory 设置instance指向刚分配的内存
    //2、ctorInstace() 初始化对象

    /**
     * 静态的工厂方法
     * @return
     */
    private static synchronized SingletonExample5 getSingleton(){
        if (instance == null){
            synchronized(SingletonExample5.class){
                if (instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

}
