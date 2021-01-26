package com.wucc.island.base.juc.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-25 15:12
 */
public class ReentrantLockAddDemo {

    private static int num = 0;

    //可重入锁，可以实现公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    private static synchronized void add(){
        lock.lock();
        lock.lock();
        try {
            num++;
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public static class ReentrantLockT extends Thread{
        public ReentrantLockT(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获得锁");
                }finally {
                    lock.unlock();
                }

                //ReentrantLockAddDemo.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockT t1 = new ReentrantLockT("t1");
        ReentrantLockT t2 = new ReentrantLockT("t2");
        ReentrantLockT t3 = new ReentrantLockT("t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();


        System.out.println(num);

    }
}
