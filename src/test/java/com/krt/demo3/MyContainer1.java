package com.krt.demo3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description 面试题：写一个固定容量的同步容器，拥有put，get 以及getCount方法
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 * @date 2019/6/13 11:44
 */
public class MyContainer1 {
    private final LinkedList<Integer> lists = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized int put(int el) {
        /**
         * 为什么用while 不用if ?
         * 99.9%的Java程序使用wait时都和while搭配
         * 如果使用if,那么当生产者唤醒所有的消费者的时候，所有的消费者都从wait之后开始运行，如果此时容器中只有一个数据，
         * 其中一个消费者占有锁后消费一个数据，然后释放锁，结果又由一个消费者占有锁，此时便不再判断容器是否有值，都直接运行lists.removeFirst()
         * 导致程序错误。
         */
        while (lists.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(el);
        ++count;
        /**
         * 为什么使用notifyAll?
         * 推荐使用notifyAll.
         * 如果一个生产放入一个值之后，使容器的满了，当使用notify时，随机唤醒一个线程，如果此时唤醒的是另外一个消费者，
         * 该消费者判断 容器此时已满，进入 wait ,导致程序死锁
         */
        this.notifyAll();
        return el;
    }

    public synchronized int get() {
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int el = lists.removeFirst();
        count--;
        this.notifyAll();
        return el;
    }

    public static void main(String[] args) {
        MyContainer1 mycont = new MyContainer1();

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
