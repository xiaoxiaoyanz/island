package com.wucc.jvm.stack;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-08 10:45
 *
 * 默认情况：11406
 *
 * 设置栈大小： -Xss256k
 * 值大小为：2460
 *
 */
public class StackErrorTest {

    private static int count = 0;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
