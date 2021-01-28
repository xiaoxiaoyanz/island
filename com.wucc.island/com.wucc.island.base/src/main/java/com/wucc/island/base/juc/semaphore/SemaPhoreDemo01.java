package com.wucc.island.base.juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-27 15:07
 */
public class SemaPhoreDemo01 {
    static Semaphore semaphore = new Semaphore(2);

   public static class T extends Thread{

       public T(String name) {
           super(name);
       }

       @Override
       public void run() {

           Thread thread = Thread.currentThread();

           try {
               semaphore.acquire();
               System.out.println(System.currentTimeMillis()+","+thread.getName()+"----------获取许可!");
               TimeUnit.SECONDS.sleep(3);

           } catch (InterruptedException exception) {
               exception.printStackTrace();
           }finally {
               semaphore.release();
               System.out.println(System.currentTimeMillis()+","+thread.getName()+"释放许可!");
           }
       }
   }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new T("t-"+i).start();
        }
    }
}
