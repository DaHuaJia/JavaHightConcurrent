package com.krt.demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 11:47
 */
public class T05_CachePool {

    public static void main(String[] args) throws InterruptedException {
        /**
         * newCachedThreadPool()初始化的的线程池没有固定的大小，刚开始时的线程个数为0，
         * 增加一个任务就判断当前线程池中有没有空闲线程，
         * 如果有则将任务交给该线程处理，如果没有则启动一个新的线程去处理该任务
         * 该线程池可以一直新建线程，直到cpu的最大承受范围
         */
        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        /**
         * 默认情况下，线程空闲时间超过60s(可自定义)就会自动销毁，
         */
        TimeUnit.SECONDS.sleep(70);
        System.out.println(service);

    }

}
