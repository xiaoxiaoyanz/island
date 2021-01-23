package com.wucc.island.base.design.sigleton;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-19 15:18
 */
public class DoubleSigton {

    private static volatile DoubleSigton instance;

    private DoubleSigton(){

    }

    public static DoubleSigton getInstance(){
        if(null != instance){
            return instance;
        }
        synchronized (DoubleSigton.class){
            if(null == instance){
                 instance = new DoubleSigton();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(DoubleSigton.getInstance());
        System.out.println(DoubleSigton.getInstance());
        System.out.println(DoubleSigton.getInstance());
        System.out.println(DoubleSigton.getInstance());
        System.out.println(DoubleSigton.getInstance());
    }
}
