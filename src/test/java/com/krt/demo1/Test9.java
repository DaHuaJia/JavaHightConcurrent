package com.krt.demo1;

import static java.lang.Thread.sleep;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/6 20:26
 */
public class Test9 {

    /**
     * 一个同步方法调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会的到该对象的锁
     * 也就是说synchronized获得的锁是可重入的
     * 这里的继承可能发生的情形，子类的同步方法可以调用父类的同步方法并且不会发生死锁
     */
    public synchronized void fun() {
        System.out.println("fun start....");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fun end....");

    }

    public static void main(String[] args) {
        new TT().fun();
    }

}

class TT extends Test9 {
    @Override
    public synchronized void fun() {
        System.out.println("child start.....");
        super.fun();
        System.out.println("child end.....");
    }

}