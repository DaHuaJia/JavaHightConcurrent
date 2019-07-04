package com.krt.demo5;

import java.util.concurrent.Executor;

/**
 * @author 郭明德
 * @description 线程池的顶级接口 Executor
 * @date 2019/6/17 9:23
 */
public class T01_MyExecutor implements Executor {
    /**
     * 认识Executor 和 ExecutorService
     */
    // public class T01_MyExecutor implements ExecutorService
    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> {
            System.out.println("Hello Executor.");
        });
    }

    @Override
    public void execute(Runnable command) {
        System.out.println("aaa");
        command.run();
        System.out.println("bbbb");
    }

    /**
     * Runnable 和 Callable的区别
     * 两者的设计思想是一致的，作用也是一致的
     * Runnable的自由度小，不能有返回值，不能抛出异常
     * Callable的自由度大，自定义返回值类型，可以抛出异常
     */

    /**
     * Executors
     * Executors是一个工厂类/工具方法，里面有很多静态方法
     */

}
