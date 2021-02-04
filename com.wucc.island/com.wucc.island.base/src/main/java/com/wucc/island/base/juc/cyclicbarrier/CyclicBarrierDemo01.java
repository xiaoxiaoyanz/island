package com.wucc.island.base.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-04 09:59
 */
public class CyclicBarrierDemo01 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread{
        int sleep;

        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try{
                TimeUnit.SECONDS.sleep(sleep);
                long startTime = System.currentTimeMillis();
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName()+"slepp:"+this.sleep+"等待了"+(endTime-startTime));

            }catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {

            new T("员工"+i,i).start();
        }

    }
}
