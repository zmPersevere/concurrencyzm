package com.ming.concurrency.example.Immutable;

import com.google.common.collect.Maps;
import com.ming.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 7:46 PM 2019/4/25
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}",map.get(1));
    }

}
