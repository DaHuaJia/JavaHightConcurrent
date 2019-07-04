package com.krt.demo5;

import java.util.concurrent.*;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 10:47
 */
public class T03_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        // 获取任务执行完的返回值，阻塞方法，一直等待任务的结束
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        // 判断任务是否执行完 Boolean
        System.out.println(f.isDone());
        // 阻塞方法，等待线程结束，获取返回值
        System.out.println(f.get());
        System.out.println(f.isDone());

        /**
         * 线程池的结束，需要调用shutdown方法 手动结束，
         * shutdown方法只是起通知作用，通知线程在任务完成之后销毁
         */
    }

}
