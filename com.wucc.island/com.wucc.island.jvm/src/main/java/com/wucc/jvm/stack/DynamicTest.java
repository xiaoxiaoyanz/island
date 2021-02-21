package com.wucc.jvm.stack;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-10 09:49
 */
public class DynamicTest {

    private int num = 10;

    public void methodA(){
        System.out.println("A-----------");
        methodB();
        num++;
    }

    public void methodB(){
        System.out.println("B-----------");
    }
}
