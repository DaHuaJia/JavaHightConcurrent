package com.krt.demo1;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description 程序在执行的过程中，如果出现了异常，默认情况下锁会被释放
 * @date 2019/6/11 15:51
 */
public class Test10 {
    /**
     * 在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况
     * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这是如果异常不合适
     * 那么在第一个线程抛出异常之后，其他线程就会进入同步代码区，有可能访问到异常生产时的数据
     * 因此要非常小心的处理同步业务逻辑中的异常
     */
    int count = 0;

    public synchronized void fun() {
        System.out.println(Thread.currentThread().getName() + " start....");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                // 此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后循环继续
                int i = 1 / 0;
            }
        }
    }

    public static void main(String[] args) {
        Test10 test10 = new Test10();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                test10.fun();
            }
        };

        /**
         * 正常情况下，test10a会一直执行下去，test10b没有执行的机会
         * 但是在当count=5的时候，抛出了异常，导致test10a失去了锁，然后被test10b锁定
         */
        new Thread(runnable, "test10a").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(runnable, "test10b").start();
    }

}
