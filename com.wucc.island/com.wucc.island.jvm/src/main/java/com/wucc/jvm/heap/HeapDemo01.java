package com.wucc.jvm.heap;

import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-13 09:50
 * -Xms10m -Xmx10m
 */
public class HeapDemo01 {

    public static void main(String[] args) {
        System.out.println("start");

        try{
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
