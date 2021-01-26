package com.wucc.island.base.juc.condition;

import java.util.LinkedList;
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
public class ConditionDemo04<E> {

    int size;

    ReentrantLock lock = new ReentrantLock();

    LinkedList<E> linkedList = new LinkedList<>();
    Condition notFull = lock.newCondition();//队列满时的等待条件
    Condition notEmpty = lock.newCondition();//队列空时的等待条件

    public ConditionDemo04(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();

        try{
            while (linkedList.size() == size){
                notFull.await();
            }
            linkedList.add(e);
            System.out.println("入队："+e);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();

        try {
            while (linkedList.size() == 0){
                notEmpty.await();
            }
            e = linkedList.removeFirst();
            System.out.println("出队："+e);
            notFull.signal();
            return e;

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo04<Integer> conditionDemo04 = new ConditionDemo04<>(6);
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        conditionDemo04.enqueue(data);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        conditionDemo04.dequeue();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
