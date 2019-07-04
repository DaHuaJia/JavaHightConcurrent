package com.krt.demo4;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 郭明德
 * @description ConcurrentListedQueue是一种用的比较多的高并发容器
 * @date 2019/6/14 14:52
 */
public class T04_ConcurrentQueue {
    /**
     * 高并发的Queue队列分两种
     * 1.内部加锁式 ConcurrentLinkedQueue
     * 2.阻塞式 BlockingQueue
     * 阻塞式根据不同实现也分两种
     * 1.Linked 链表实现 T05_LinkedBlockingQueue
     * 2.Array 数组实现 T06_ArrayBlockingQueue
     */
    public static void main(String[] args) {
        /**
         * Queue 队列容器
         * 没有容量限制，直到内存耗尽
         */
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            /**
             * 类似于add()方法，具有Boolean返回值表示是否插入成功
             * add()方法具有容量限制，超出容量会抛出异常，
             * offer()方法没有限制，不会抛出异常
             */
            strs.offer("a" + i);
        }

        System.out.println(strs);
        System.out.println(strs.size());

        /**
         * 将头部的一个元素取出，并删除
         */
        System.out.println(strs.poll());
        System.out.println(strs.size());

        /**
         * 将头部的一个元素取出，不删除
         */
        System.out.println(strs.peek());
        System.out.println(strs.size());

        // 双端队列 Deque
    }
}
