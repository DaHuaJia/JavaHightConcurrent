package com.krt.demo3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 郭明德
 * @description 面试题：写一个固定容量的同步容器，拥有put，get 以及getCount方法
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 * <p>
 * 使用两种锁Lock
 * @date 2019/6/13 11:44
 */
public class MyContainer3 {
    private final LinkedList<Integer> lists = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    Lock producer = new ReentrantLock();
    Lock consumer = new ReentrantLock();

    // 生产者方法
    public int put(int el) {
        while (lists.size() == MAX) {
            try {
                consumer.lock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        lists.add(el);
        count++;
        if (consumer.tryLock()) {
            consumer.unlock();
        }
        return el;
    }

    // 消费者方法
    public synchronized int get() {
        while (lists.size() == 0) {
            try {
                producer.lock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int el = lists.removeFirst();
        count--;
        if (producer.tryLock()) {
            producer.unlock();
        }
        return el;
    }

    public static void main(String[] args) {
        MyContainer3 mycont = new MyContainer3();

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
