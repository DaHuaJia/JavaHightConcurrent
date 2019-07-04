package com.krt.demo1;

import org.junit.Test;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/5 10:02
 */
public class Test4 implements Runnable {
    private int count = 10;

    // 加上synchronized，
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count=" + count);

    }

    @Test
    public static void main(String[] args) {
        Test4 T = new Test4();

        // 这里并不是new了5个Test4对象，而是创建了一个对象，所有线程共用一个对象的方法
        for (int i = 0; i < 5; i++) {
            new Thread(T, "name" + i).start();
        }

    }
}
