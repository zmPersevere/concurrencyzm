package com.ming.concurrency.example.pubish;

import com.ming.concurrency.annoations.NotRecommend;
import com.ming.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 8:46 PM 2019/4/24
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCannbeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            log.info("{}",Escape.this.thisCannbeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
