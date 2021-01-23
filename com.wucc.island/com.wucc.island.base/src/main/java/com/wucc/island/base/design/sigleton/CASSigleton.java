package com.wucc.island.base.design.sigleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-19 14:29
 */
public class CASSigleton {

    private static final AtomicReference<CASSigleton> INSTANCE = new AtomicReference<CASSigleton>();

    private CASSigleton instance;

    private CASSigleton(){}

    public static final CASSigleton getInstance(){
       for ( ; ; ){
           CASSigleton casSigleton = INSTANCE.get();
           if(null != casSigleton){
               return casSigleton;

           }
           INSTANCE.compareAndSet(null, new CASSigleton());
           return INSTANCE.get();

       }
    }

    public static void main(String[] args) {
        System.out.println(CASSigleton.getInstance());
        System.out.println(CASSigleton.getInstance());
        System.out.println(CASSigleton.getInstance());
        System.out.println(CASSigleton.getInstance());
    }


}
