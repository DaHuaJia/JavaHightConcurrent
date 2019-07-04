package com.krt.demo4;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author 郭明德
 * @description http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/488061.html
 * @date 2019/6/14 11:08
 */
public class T01_ConcurrentMap {

    public static void main(String[] args) {
        /**
         * 比较早的一种Map实现，所有的操作都是带锁的，锁定的是整个map容器，效率比较低
         * 用于并发性不高
         */
        //Map<String, String> map = new Hashtable<>(); //437-442
        /**
         * 普通Map容器 不具有原子性
         * 用于普通Map容器
         */
        //Map<String, String> map = new HashMap<>(); //382-397
        /**
         * 针对高并发的一种的容器，所有的操作都是带锁的，锁定的是map中的字段，细化锁，效率相对更高
         * 用于高并发
         */
        Map<String, String> map = new ConcurrentHashMap<>(); //348-552
        /**
         * 跳表，保存的结果是有序的，高并发并且排序 效率一般
         * 用于高并发且要求排序 或 需要搜索查找
         */
        //Map<String, String> map = new ConcurrentSkipListMap<>();//486-529

        // 建立一个产生随机数的对象
        Random random = new Random();
        // 线程数组
        Thread[] ths = new Thread[100];
        // 门栓 100
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    // 向Map中装入10000个随机字符串
                    map.put("a" + random.nextInt(10000), "a" + random.nextInt(10000));
                }
                // 线程结束，门栓减一
                latch.countDown();
            });
        }
        // 让所有线程都启动
        Arrays.asList(ths).forEach(t -> t.start());

        try {
            // 等待子线程执行完
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
