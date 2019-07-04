package com.krt.demo1;

import static java.lang.Thread.sleep;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/5 16:55
 */
public class Test5 {
    public synchronized void fun2() {
        System.out.println("fun2 start.");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun2 end.");
    }

    public void fun3() {
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun3 start....");
    }

    public static void main(String[] args) {
        final Test5 test5 = new Test5();

        // 同步方法和非同步方法可以同时调用
        new Thread(() -> test5.fun2(), "fun2").start();
        new Thread(() -> test5.fun3(), "fun3").start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                test5.fun2();
            }
        }, "fun2");*/

    }

}
