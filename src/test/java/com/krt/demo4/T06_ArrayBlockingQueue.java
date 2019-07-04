package com.krt.demo4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/14 15:58
 */
public class T06_ArrayBlockingQueue {
    public static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    public static Random r = new Random();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }
        /**
         * put方法是阻塞式的，容器满了就会阻塞，无限时间
         */
        // strs.put("aaa");
        /**
         * add方法会抛异常，
         */
        // strs.add("aaa");
        /**
         * offer(a1) 不会抛出异常，通过返回boolean判断是否插入成功
         */
        strs.offer("aaa");
        /**
         * offer(a1,a2,a3) 插入对象，超时时间，时间单位
         * 在一段时间内判断是否插入成功，该方法会阻塞一段时间(a2)
         */
        //strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);
    }
}
