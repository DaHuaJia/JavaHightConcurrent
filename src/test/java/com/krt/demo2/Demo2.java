package com.krt.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/12 10:31
 */
public class Demo2 {
    //public String count = "count";
    public Object count = 0;

    public void fun2() {
        synchronized (count) {
            System.out.println("fun2 start.....");
            /**
             * 当synchronized锁定的对象的引用发生改变时，该锁也会发生改变，导致锁失效
             * 如果修改对象的某个属性，锁不会改变，例如：修改POJO类的某个属性
             */
            count = 2;

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fun3() {
        synchronized (count) {
            System.out.println("fun3 start.....");
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.fun2();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.fun3();
            }
        });

        t1.start();
        t2.start();

    }

}
