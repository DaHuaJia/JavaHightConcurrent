package com.krt.demo3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭明德
 * @description 面试题：写一个固定容量的同步容器，拥有put，get 以及getCount方法
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 * <p>
 * 使用Lock 和 Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指出哪些线程被唤醒
 * @date 2019/6/13 11:44
 */
public class MyContainer2 {
    private final LinkedList<Integer> lists = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    // 程序有问题，不知道producer是哪些线程
    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public synchronized int put(int el) {
        try {
            lock.lock();
            while (lists.size() == MAX) {
                producer.await();
            }

            lists.add(el);
            ++count;
            consumer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }
        return el;
    }

    public synchronized int get() {
        int el = -1;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            el = lists.removeFirst();
            count--;
            producer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }
        return el;
    }

    public static void main(String[] args) {
        MyContainer2 mycont = new MyContainer2();

        // 启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName() + " get = " + mycont.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    System.out.println(Thread.currentThread().getName() + " put = " + mycont.put(j));
                }
            }, "p" + i).start();
        }

    }
}
