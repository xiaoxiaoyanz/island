package com.wucc.island.base.spring.proxy.service;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-08 19:39
 */
public class ServiceB implements IService{
    @Override
    public void m1() {

        System.out.println("m1 in serviceB");
    }

    @Override
    public void m2() {

        System.out.println("m2 in serviceB");
    }

    @Override
    public void m3() {

        System.out.println("m3 in serviceB");

    }
}
