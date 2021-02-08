package com.wucc.jvm.stack;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-08 10:59
 */
public class StackFrameTest {

    private static int count= 0;
    public static void main(String[] args) {
        StackFrameTest stackFrameTest = new StackFrameTest();
        stackFrameTest.method01();
    }

    public int method01(){
        return 10+method02();
    }


    public int method02(){
        return 20+method03();
    }

    public int method03(){
        return 30;
    }

    public int method04(){
        this.count = 9;
        return  this.count;
    }

}
