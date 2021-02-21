package com.wucc.jvm.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-13 10:54
 */
public class HeapErrorTest {

    private static Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        //堆内存总量大小M
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        //Java虚拟机堆使用的最大内存
        long maxMemory = runtime.maxMemory() / 1024 / 1024;

        System.out.println("-Xms:"+totalMemory+"M");
        System.out.println("-Xmx:"+maxMemory+"M");

        /*try{
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        List<HeapErrorTest> heapErrorTestList = new ArrayList<>();

        int count = 0;
        while (true){
            count++;
            heapErrorTestList.add(new HeapErrorTest());
            System.out.println(count);
        }


    }

}
