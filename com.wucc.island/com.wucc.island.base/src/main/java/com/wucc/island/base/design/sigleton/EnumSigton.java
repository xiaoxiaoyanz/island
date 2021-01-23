package com.wucc.island.base.design.sigleton;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-19 15:07
 */
public enum EnumSigton {

    INSTANCE;

    public void say(){
        System.out.println("i am enum instance");
    }

    public static void main(String[] args) {
        System.out.println(EnumSigton.INSTANCE);
        System.out.println(EnumSigton.INSTANCE);
        System.out.println(EnumSigton.INSTANCE);
        System.out.println(EnumSigton.INSTANCE);
        EnumSigton.INSTANCE.say();
    }
}
