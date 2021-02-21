package com.wucc.jvm.heap;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-13 14:58
 */
public class ThreadLocalAllocationBufferTest {

    public int a;
    public Integer b;

    public static void main(String[] args) {
        ThreadLocalAllocationBufferTest threadLocalAllocationBufferTest = new ThreadLocalAllocationBufferTest();
        System.out.println(threadLocalAllocationBufferTest.a); //0
        System.out.println(threadLocalAllocationBufferTest.b); //null
    }
}
