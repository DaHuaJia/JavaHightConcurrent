package com.krt.demo1;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author 郭明德
 * @description 对业务写方法加锁，对业务读方法不加锁 容易产生脏读情况
 * @date 2019/6/5 20:50
 */
public class Test6 {
    // 账户名称 余额
    String name;
    double money;

    // 设置账户的信息
    public synchronized void set(String name, double money) {
        this.name = name;

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.money = money;
    }

    // 获取账户余额
    public double getMoney(String name) {
        return this.money;
    }

    public static void main(String[] args) {
        Test6 a = new Test6();
        // 该线程在执行的过程中，可能被其他线程打断 通过sleep()方法，模拟放大线程的切换时隙
        new Thread(() -> a.set("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getMoney("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 读取到错误的数据就是脏读
        // 可以给读业务加上锁，这可能会导致性能下降，所以得结合具体的业务进行分析
        System.out.println(a.getMoney("zhangsan"));
    }
}
