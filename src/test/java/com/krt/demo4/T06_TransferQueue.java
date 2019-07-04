package com.krt.demo4;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author 郭明德
 * @description LinkedTransferQueue容器用于更高的并发
 * @date 2019/6/14 17:06
 */
public class T06_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        // strs具有一定的容量
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        // 启动一个消费者线程
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /**
         * 一般情况下，生产者向容器加入一个对象后，再由消费者线程去取这个对象。
         * 如果在消费者线程饱和，处于等待状态。生产者依然是先将对象放到容器，再通知消费者消费，就会造成资源的浪费
         *
         * transfer() 方法也是向容器加入一条对象，但是，该方法不会将对象先插入容器中，然后在通知消费者，而是直接给消费者。
         * 如果没有消费者，方法就会阻塞线程，因此，该容器用于更高的高并发情况。
         */

        // 向容器加入一个对象 生产者
        strs.transfer("aaaa");
        // strs.add("aaa");
        // strs.put("aaa");

        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
