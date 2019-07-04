package com.krt.demo4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author 郭明德
 * @description SynchronousQueue是一个种特殊的TransferQueue
 * @date 2019/6/14 17:22
 */
public class T07_SynchronusQueue {

    public static void main(String[] args) {
        // strs容器没有容量 不能使用add 或 put方法
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /**
         * 该容器在插入一个对象时，必须有一个线程使用take方法取出
         * 该容器不能储存对象，只能直接交给消费者(take)
         */

        /**
         * 不能使用这些方法
         * strs容器容量为0, 调用put方法会阻塞，add方法会报错
         */
        //strs.put("aaa");
        //strs.add("aaa");

        System.out.println(strs.size());
    }

}
