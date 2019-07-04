package com.krt.demo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭明德
 * @description reentrantLock用于替代synchronized
 * <p>
 * ReentrantLock 还可以指定为公平锁
 * @date 2019/6/13 9:09
 */
public class ReentrantLock5 extends Thread {
    /**
     * 公平锁和非公平锁
     * 公平锁是指，每个线程在获取锁时，会查看此锁的等待队列，
     * 如果等待队列为空则占有锁，如果不为空，则加入等待队列中，以后线程调度器按照先后顺序把锁给对应的线程
     * <p>
     * 非公平锁是指，线程在获取锁时，就直接尝试占有锁，
     * 如果尝试失败，则等待，以后线程调度器随机把锁分配给某个线程
     * <p>
     * 非公平锁性能高于公平锁性能。首先，在恢复一个被挂起的线程与该线程真正运行之间存在着严重的延迟。
     * 而且，非公平锁能更充分的利用cpu的时间片，尽量的减少cpu空闲的状态时间。
     */

    // 非公平锁
    //private static ReentrantLock lock = new ReentrantLock();
    // 公平锁 对比输出结果
    private static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 re = new ReentrantLock5();

        Thread t1 = new Thread(re);
        Thread t2 = new Thread(re);

        t1.start();
        t2.start();

    }

}