package com.krt.demo4;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 郭明德
 * @description 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高，适合写少读多的环境
 * @date 2019/6/14 11:58
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        /**
         * 效率最高，但是会导致并发问题，难以保证运行结果
         */
        //List<String> lists = new ArrayList<>();
        /**
         * 容器加锁，不会出现并发问题，效率稍差
         */
        List<String> lists = new Vector();
        /**
         * 该容器在添加元素时，会将原来的元素复制一份，在新复制的对象上添加元素，添加完成之后，变量指向新的引用
         * 这样读操作时就不必加锁，写的时候要加锁
         */
        //List<String> lists = new CopyOnWriteArrayList<>();

        // 产生随机数对象
        Random r = new Random();
        // 线程数组
        Thread[] ths = new Thread[100];

        for (int i = 0; i < 100; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    lists.add("a" + r.nextInt(10000));
                }
            });
        }

        runAndComputeTime(ths);

        System.out.println("size = " + lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long s1 = System.currentTimeMillis();

        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }

}
