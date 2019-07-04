package com.krt.demo5;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 14:44
 */
public class T07_ScheduledPool {
    public static void main(String[] args) {
        /**
         * 创建线程池，执行定时任务
         * newScheduledThreadPool(4) 表示线程池中的线程数
         * 有4个线程用于执行定时任务
         */
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        /**
         * 参数1：定时任务的内容（即Runnable的实现）
         * 参数2：第一次执行的延时
         * 参数3：定时间隔时间
         * 参数4：定时单位
         */
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());

        }, 0, 500, TimeUnit.MILLISECONDS);
    }

}
