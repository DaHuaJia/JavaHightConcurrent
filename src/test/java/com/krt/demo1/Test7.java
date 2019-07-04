package com.krt.demo1;

import org.junit.Test;

import static java.lang.Thread.sleep;

/**
 * @author 郭明德
 * @description synchronized是一种可重入锁
 * @date 2019/6/6 19:39
 */
public class Test7 {
    // 一个同步(synchronized)方法可以调用另外一个同步方法
    // 当一个线程拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁，这就是可重入

    // 当fun2执行的时候，会锁住该对象，fun2调用fun3的时候，fun3也需要锁住该对象，此时fun3不会等待其他方法释放该对象的锁
    @Test
    public synchronized void fun2() throws InterruptedException {
        System.out.println("fun2 start....");
        sleep(1000);
        fun3();
    }

    public synchronized void fun3() throws InterruptedException {
        sleep(1000);
        System.out.println("This is fun3");
    }

    // 使用synchronized时，同一个线程可以给已经锁定的对象再上锁，不同的线程必须等待
}
