package com.krt.demo3;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description ThreadLocal局部变量
 * @date 2019/6/13 16:43
 */
public class ThreadLocal1 {
    static Person p = new Person();

    /**
     * 线程t2修改了属性值后，线程t1可以收到改变
     */
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }, "t2").start();

    }

    static class Person {
        String name = "zhangsan";
    }

}
