package com.ming.concurrency.example.pubish;

import com.ming.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 8:43 PM 2019/4/24
 */
@NotThreadSafe
@Slf4j
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

    }
}
