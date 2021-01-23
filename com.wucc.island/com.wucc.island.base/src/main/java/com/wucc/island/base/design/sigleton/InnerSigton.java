package com.wucc.island.base.design.sigleton;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-19 15:14
 */
public class InnerSigton {

    private static class GetInnerInstance{
        private static InnerSigton innerSigton = new InnerSigton();
    }

    public static InnerSigton getInstance(){
        return GetInnerInstance.innerSigton;
    }

    public static void main(String[] args) {
        System.out.println(InnerSigton.getInstance());
        System.out.println(InnerSigton.getInstance());
        System.out.println(InnerSigton.getInstance());
        System.out.println(InnerSigton.getInstance());
    }
}
