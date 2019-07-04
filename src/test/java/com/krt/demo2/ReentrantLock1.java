package com.krt.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description reentrantLock用于替代synchronized
 * 本例中有fun1锁定this,只有fun1执行完成之后，fun2才能开始执行
 * 在这里复习synchronized 最原始的语句
 * @date 2019/6/13 9:09
 */
public class ReentrantLock1 {
    synchronized void fun1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void fun2() {
        System.out.println("fun2 start.....");
    }

    public static void main(String[] args) {
        ReentrantLock1 re = new ReentrantLock1();

        new Thread(() -> re.fun1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> re.fun2()).start();
    }

}