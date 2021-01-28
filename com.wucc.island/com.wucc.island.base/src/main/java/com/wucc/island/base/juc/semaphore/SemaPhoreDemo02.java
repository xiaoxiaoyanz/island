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
public class SemaPhoreDemo02 {
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
               System.out.println(System.currentTimeMillis()+","+thread.getName()+"----------获取许可!,当前剩余可申请量："+semaphore.availablePermits());
               TimeUnit.SECONDS.sleep(3);
               System.out.println(System.currentTimeMillis()+","+thread.getName()+"运行结束!");

           } catch (InterruptedException exception) {
               exception.printStackTrace();
           }finally {
               semaphore.release();
               System.out.println(System.currentTimeMillis()+","+thread.getName()+"释放许可!");
           }
           System.out.println(System.currentTimeMillis()+","+thread.getName()+"当前剩余可申请量："+semaphore.availablePermits());
       }
   }

    public static void main(String[] args) throws InterruptedException {
       T t1 = new T("t1");
       t1.start();

       TimeUnit.SECONDS.sleep(1);
       T t2 = new T("t2");
       t2.start();
       TimeUnit.SECONDS.sleep(1);

       T t3 = new T("t3");
       t3.start();

       t2.interrupt();
       t3.interrupt();

    }
}
