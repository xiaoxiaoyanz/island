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
public class CyclicBarrierDemo04 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread{
        int sleep;

        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            long startTime = 0L;
            try{
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();

                System.out.println(this.getName()+"到了");
                cyclicBarrier.await();



            }catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(this.getName()+"slepp:"+this.sleep+"等待了"+(endTime-startTime));

        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            int sleep = 0;
            if(i == 10){
                sleep = i;
            }

            T t = new T("员工" + i, sleep);
            t.start();


            if(i == 5){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(t.getName()+"先干了");
                t.interrupt();
                TimeUnit.SECONDS.sleep(2);
            }


        }

    }
}
