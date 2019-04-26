package com.ming.concurrency.example.syncContainer;

import com.ming.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 7:36 PM 2019/4/26
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector();

    public static void main(String[] args) {
        while (true){
            for (int i = 0 ; i < 10 ; i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                public void run(){
                    for (int i = 0 ; i < vector.size() ; i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                public void run(){
                    for (int i = 0 ; i < vector.size() ; i ++){
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
