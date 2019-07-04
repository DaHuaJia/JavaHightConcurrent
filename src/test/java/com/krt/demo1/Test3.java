package com.krt.demo1;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description Test2的精简写法
 * @date 2019/6/4 9:31
 */
public class Test3 {

    private int count = 10;

    @Test
    // synchronized锁定的是该实例化对象，而不是代码块
    // 在该方法被执行时，相当于给该实例化对象加了一把锁，其他线程需要调用该实例化对象时，需要等待该方法结束
    public synchronized void fun() { // 这种写法等同于synchronized(this)
        count--;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

    public synchronized void fun2() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

    public static void main(String[] args) {
        Test3 test3 = new Test3();

        new Thread(() -> test3.fun(), "fun").start();
        new Thread(() -> test3.fun2(), "fun2").start();
    }
}
