package com.wucc.island.jvm.chapter01;
/*
* 反编译
* javap -v XX.class
*
*
* Java线程信息
* jps
*
* */
public class StackStructTest {

    public static void main(String[] args) {
        int i = 2 + 3;

        int j = 100;

        int k = i + j;

        System.out.println(k);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello");

    }
}
