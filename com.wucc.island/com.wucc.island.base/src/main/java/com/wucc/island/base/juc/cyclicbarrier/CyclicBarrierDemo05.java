package com.wucc.island.base.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-04 09:59
 */
public class CyclicBarrierDemo05 {
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

                //有一个员工只愿意等5秒钟
                if(this.getName().equals("员工1")){
                    cyclicBarrier.await(5,TimeUnit.SECONDS);
                }else {
                    cyclicBarrier.await();
                }


            }catch (InterruptedException | BrokenBarrierException | TimeoutException e){
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(this.getName()+"slepp:"+this.sleep+"等待了"+(endTime-startTime));

        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            T t = new T("员工" + i, i);
            t.start();

        }

    }
}
