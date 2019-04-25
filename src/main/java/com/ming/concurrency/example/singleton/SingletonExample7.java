package com.ming.concurrency.example.singleton;

import com.ming.concurrency.annoations.Recommend;
import com.ming.concurrency.annoations.ThreadSafe;

/**
 * @Description : 枚举模式：最安全的。
 * @Author : zhangMing
 * @Date : Created in 9:12 PM 2019/4/24
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){}

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{

        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
