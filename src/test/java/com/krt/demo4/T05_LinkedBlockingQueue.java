package com.krt.demo4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/14 15:14
 */
public class T05_LinkedBlockingQueue {

    /**
     * 阻塞式高并发队列容器 Linked实现
     */
    public static BlockingQueue strs = new java.util.concurrent.LinkedBlockingQueue<>();

    // 产生随机数对象
    public static Random r = new Random();

    public static void main(String[] args) {
        /**
         * 定义一个生产者线程
         */
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    // 如果满了就会自动等待
                    strs.put("a" + i);
                    // 随机休眠不超过1s
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        /**
         * 定义5个消费者线程
         */
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        // 如果空了就会自动等待
                        System.out.println(Thread.currentThread().getName() + "---" + strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }

    }

}
