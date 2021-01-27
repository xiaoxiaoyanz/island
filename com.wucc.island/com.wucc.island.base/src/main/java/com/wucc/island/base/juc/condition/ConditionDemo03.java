package com.wucc.island.base.juc.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-25 15:58
 */
public class ConditionDemo03 {

    //private static Object lock = new Object();

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static  class T1 extends Thread{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+","+this.getName()+"准备获取锁");
            /*synchronized (lock){
                System.out.println(System.currentTimeMillis()+","+this.getName()+"获取锁成功");
                try {
                    lock.wait();
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }

            }*/
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis()+","+this.getName()+"获取锁成功");
                condition.await();
            }catch (InterruptedException exception){
                System.out.println("中断标志"+this.isInterrupted());
                exception.printStackTrace();
            }finally {
                lock.unlock();
            }


            System.out.println(System.currentTimeMillis()+","+this.getName()+"释放锁成功");

        }
    }

    public static  class T2 extends Thread{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+","+this.getName()+"准备获取锁");
           /* synchronized (lock){
                System.out.println(System.currentTimeMillis()+","+this.getName()+"获取锁成功");
                lock.notify();
                System.out.println(System.currentTimeMillis()+","+this.getName()+"notify!");
                try {
                   TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+","+this.getName()+"准备释放锁");

            }*/

            lock.lock();

            try{
                System.out.println(System.currentTimeMillis()+","+this.getName()+"获取锁成功");
                condition.signal();
                System.out.println(System.currentTimeMillis()+","+this.getName()+"signal!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+","+this.getName()+"准备释放锁");
            }finally {
                lock.unlock();
            }

            System.out.println(System.currentTimeMillis()+","+this.getName()+"释放锁成功");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        T2 t2 = new T2();
        t2.setName("t2");

        t1.start();


        TimeUnit.SECONDS.sleep(5);

        System.out.println("t1的中断标志是:"+t1.isInterrupted());
        t1.interrupt();
        System.out.println("t1的中断标志是:"+t1.isInterrupted());

        t2.start();

    }
}
