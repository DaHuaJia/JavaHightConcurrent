package com.krt.demo1;


import static java.lang.Thread.sleep;

/**
 * @author 郭明德
 * @description 模拟死锁
 * @date 2019/6/6 20:04
 */
public class Test8 {
    public String a = "aaaa";
    public String b = "bbbb";

    public void fun2() {
        synchronized (a) {
            System.out.println(a);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fun3();
        }
    }

    public void fun3() {
        synchronized (b) {
            System.out.println(b);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fun2();
        }
    }

    public static void main(String[] args) {
        Test8 test8 = new Test8();

        new Thread(() -> test8.fun2()).start();
        new Thread(() -> test8.fun3()).start();

        // fun2方法先锁定a,然后锁定b
        // fun3方法先锁定b,然后锁定a
    }

}
