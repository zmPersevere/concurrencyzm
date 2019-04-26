package com.ming.concurrency.example.Immutable;

import com.google.common.collect.Maps;
import com.ming.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 7:46 PM 2019/4/25
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static int c = 3;
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
        // a、b、c都不可变更
        // map引用地址也不可变更
        map.put(1,3);
        log.info("{}",map.get(1));
    }

    private void test(final int d){
        // 参数d不可变更
    }
}
