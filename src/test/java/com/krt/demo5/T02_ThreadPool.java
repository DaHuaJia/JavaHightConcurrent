package com.krt.demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 10:04
 */
public class T02_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 定义一个线程池(service)，线程池由工具类Executors创建
         * newFixedThreadPool(5)表示创建固定长度的(fixed)，大小为5的线程，该线程池里面有5个线程
         * ExecutorService有execute和submit方法
         * execute用来执行那些没有返回值的方法 Runnable
         * submit即可以执行有返回值的方法，可以执行没有返回值的方法 Runnable，callable
         */
        ExecutorService service = Executors.newFixedThreadPool(5);

        /**
         * 一个线程在一个时刻，只能执行一个任务，当给线程池的任务数多于线程的数的时候，
         * 多余的任务就会放到线程池所维护的一个任务队列(queued tasks)中，当线程池的线程完成了任务之后，线程不会结束，
         * 而是等待线程池的处理，此时，线程池会把任务队列里的里的任务取出交给线程处理
         *
         * 线程池的好处就在于，当处理多个任务时不需要重新启动线程，只需要等线程空闲，然后重新给新的任务
         * 任务完成之后，线程也不会结束，效率高。
         *
         * 启动线程和关闭线程都是需要消耗资源的，而且资源的消耗还比较大
         */
        // 5个线程，启动了6个任务
        for (int i = 0; i < 6; i++) {
            // 重写ExecutorService的execute方法
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
         * 关闭线程池(即关闭线程池中所有线程)，是等所有任务执行完成之后关闭线程
         * shutdown() 和 shutdownnow()是有区别的
         */
        service.shutdown();
        // 判断线程池的任务是否都执行完 (即任务队列是否为空)
        System.out.println(service.isTerminated());
        // 判断线程池是否关闭
        System.out.println(service.isShutdown());
        /**
         * 该线程池还维护着另外一个队列， completed tasks保存已经完成的任务
         */
        System.out.println(service);


        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
