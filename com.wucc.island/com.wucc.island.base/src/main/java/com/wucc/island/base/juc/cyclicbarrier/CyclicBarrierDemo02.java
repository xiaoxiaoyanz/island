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
public class CyclicBarrierDemo02 {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread{
        int sleep;

        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }
        void eat(){
            try{
                TimeUnit.SECONDS.sleep(sleep);
                long startTime = System.currentTimeMillis();
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName()+"slepp:"+this.sleep+"等待了"+(endTime-startTime)+"开始吃饭!");

            }catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        }

        void dirver(){
            try{
                TimeUnit.SECONDS.sleep(sleep);
                long startTime = System.currentTimeMillis();
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName()+"slepp:"+this.sleep+"等待了"+(endTime-startTime)+"开始去下一个景点!");

            }catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
           this.eat();
           this.dirver();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {

            new T("员工"+i,i).start();
        }

    }
}
