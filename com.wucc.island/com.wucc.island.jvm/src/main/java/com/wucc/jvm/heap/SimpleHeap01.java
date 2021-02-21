package com.wucc.jvm.heap;

import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-13 10:49
 */
public class SimpleHeap01 {

    public static void main(String[] args) {
        SimpleHeap01 simpleHeap01 = new SimpleHeap01();
        SimpleHeap01 simpleHeap02 = new SimpleHeap01();
        SimpleHeap01 simpleHeap03 = new SimpleHeap01();

        System.out.println("创建完成");


        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
