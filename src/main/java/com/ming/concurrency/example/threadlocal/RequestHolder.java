package com.ming.concurrency.example.threadlocal;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 8:29 PM 2019/4/25
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
