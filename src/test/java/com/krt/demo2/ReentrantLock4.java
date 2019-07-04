package com.krt.demo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭明德
 * @description reentrantLock用于替代synchronized
 * <p>
 * 使用 reentrantLock还可以调用lockInterruptibly方法，可以使线程 interrupt方法做出响应，
 * 在一个线程等待的锁的过程中，可以被打断
 * @date 2019/6/13 9:09
 */
public class ReentrantLock4 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start.....");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                //lock.lock();
                // 被打断后会抛出InterruptedException异常
                lock.lockInterruptibly(); // 可以对interrupt()方法做出响应
                System.out.println("t2 start.....");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("interrupt!");

            } finally {
                lock.unlock();
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt(); // 打断t2的等待

    }

}