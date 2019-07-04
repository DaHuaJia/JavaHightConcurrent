package com.krt.demo5;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 14:56
 */
public class T08_WorkStealingPool {

    public static void main(String[] args) throws IOException {
        /**
         * 创建一个线程池，里面的线程可以窃取其他线程的任务
         * 默认大小为CPU的核数
         * 该线程池中的每一个线程都有自己的队列，当其中一个线程执行完自己的自己任务队列中的任务，
         * 就是去别的线程的任务队列中拿还未执行的线程过来执行
         */
        ExecutorService service = Executors.newWorkStealingPool();

        // 打印CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        /**
         * 由于该线程池的线程属于后台线程(守护线程，精灵线程)，主线程不阻塞就看不到线程输出
         * 可以通过DEBUG方式看到它启动的线程的种类
         */
        System.in.read();// 阻塞主程序
    }

    /**
     * 任务实现类
     */
    static class R implements Runnable {
        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + "---" + Thread.currentThread().getName());
        }

    }
}
