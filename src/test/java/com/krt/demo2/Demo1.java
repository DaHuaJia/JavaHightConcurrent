package com.krt.demo2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 郭明德
 * @description 测试AtomicInteger 比 synchronized更高效
 * @date 2019/6/12 9:22
 */
public class Demo1 {
    AtomicInteger count = new AtomicInteger(0);
    int num = 0;
    Object object = new Object();

    public void fun2() {
        for (int i = 0; i < 1000000; i++) {
            count.incrementAndGet();
        }
    }

    public void fun3() {
        for (int i = 0; i < 1000000; i++) {
            synchronized (object) {
                num++;
            }
        }
    }

    public static void main(String[] args) {
        Demo1 demo = new Demo1();
        Thread[] threads = new Thread[2];

        threads[0] = new Thread(() -> demo.fun2(), "fun2");

        long t1 = System.currentTimeMillis();
        threads[0].start();
        try {
            threads[0].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) + "====" + demo.count);


        threads[1] = new Thread(() -> demo.fun3(), "fun3");

        long t3 = System.currentTimeMillis();
        threads[1].start();
        try {
            threads[1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t4 = System.currentTimeMillis();
        System.out.println((t4 - t3) + "====" + demo.num);


    }

}
