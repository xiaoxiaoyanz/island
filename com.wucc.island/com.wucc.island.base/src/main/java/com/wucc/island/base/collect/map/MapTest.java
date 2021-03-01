package com.wucc.island.base.collect.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-24 16:46
 */
@Slf4j
public class MapTest {

    @Test
    public void test01(){
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("h1",123);
        hashMap.put("h2",124);
        hashMap.put("h3",125);
        hashMap.put("h4",126);
        hashMap.put("h5",127);

        Set<Map.Entry<String, Object>> entries = hashMap.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            if ("h1".equals(next.getKey())){
                iterator.remove();
            }
        }

        hashMap.forEach((key,value) ->{
            System.out.println(key+value);
        });

        Map<String,Integer> hashtable = new Hashtable<>();
        hashtable.put("haha",12313);
        Stack<Integer> stack = new Stack<>();

        stack.add(123123);


        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    }
}
