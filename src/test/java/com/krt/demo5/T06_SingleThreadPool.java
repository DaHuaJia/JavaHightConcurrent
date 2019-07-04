package com.krt.demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 14:33
 */
public class T06_SingleThreadPool {
    public static void main(String[] args) {
        /**
         * 创建线程池，其中只有一个线程
         * 有利于任务的顺序执行
         */
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            int j = i;
            service.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "---" + j);
            });
        }

    }
}
