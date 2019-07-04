package com.krt.demo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭明德
 * @description reentrantLock用于替代synchronized
 * 本例中有fun1锁定this,只有fun1执行完成之后，fun2才能开始执行
 * 在这里复习synchronized 最原始的语句
 * <p>
 * 使用reentrantLock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁
 * 使用syn锁定的话，如果遇到异常，jvm会主动释放锁，但是lock必须手动释放锁，因此经常在finally中释放锁
 * <p>
 * 使用reentrantLock可以进行尝试锁定tryLock, 如果无法锁定或者在指定时间内无法锁定，线程可以决定是否继续等待
 * @date 2019/6/13 9:09
 */
public class ReentrantLock3 {
    // Lock是ReentrantLock的一个实现接口
    Lock lock = new ReentrantLock();

    void fun1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 需要手动释放
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法豆浆继续执行
     * 可以根据tryLock的返回值来判断是否锁定
     * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
     */
    synchronized void fun2() {
        Boolean locked = lock.tryLock();
        //Boolean locked = lock.tryLock(5, TimeUnit.SECONDS); // 在指定时间内无法锁定
        if (locked) {
            System.out.println("fun2 start.....");
            lock.unlock();
        } else {
            System.out.println("fun2 fail.");
        }
    }

    public static void main(String[] args) {
        ReentrantLock3 re = new ReentrantLock3();

        new Thread(() -> re.fun1()).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> re.fun2()).start();
    }

}