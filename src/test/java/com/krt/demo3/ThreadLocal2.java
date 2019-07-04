package com.krt.demo3;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description ThreadLocal局部变量
 * @date 2019/6/13 16:43
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> p = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.set(new Person());
            System.out.println(p);
        }, "t2").start();

    }

    static class Person {
        String name = "zhangsan";
    }

}
