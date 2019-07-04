package com.krt.demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 15:50
 */
public class ThreadExexutorPool {
    /**
     * 几乎所有的线程池，都是继承这个线程池类进行实现的
     * 阅读源码，即可看到具体实现
     * <p>
     * 参数1：线程池的核心线程数
     * 参数2：线程池的最大线程数
     * 参数3：线程的空闲时间，表示线程空闲多久之后，销毁该线程
     * 参数4：空闲时间单位
     * 参数5：任务的具体实现方法
     */
    ExecutorService service = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

}
