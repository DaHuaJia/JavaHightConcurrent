package com.krt.demo4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/14 16:38
 */
public class DelayQueue {
    /**
     * DelayQueue中的每一个元素，都需要在加入后等待一段时间才能被取出，每一个元素都有记载着自己的等待时间
     * DelayQueue默认是按等待时间排序的，等待时间最长的排在前面
     * 使用DelayQueue需要实现它的接口才能被使用
     * <p>
     * 主要用于执行定时任务
     */
    public static BlockingQueue<MyTask> tasks = new java.util.concurrent.DelayQueue<>();

    public Random r = new Random();

    // 使用该容器必须实现的接口
    public static class MyTask implements Delayed {
        long runningTime;
        long now = System.currentTimeMillis();

        MyTask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        // 还有多长时间可以取出
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime + now - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "" + runningTime;
        }

    }

    public static void main(String[] args) throws Exception {
        long now = System.currentTimeMillis();

        // 定义一个对象t1，该对象在加入后等待1s才能被取出
        MyTask t1 = new MyTask(1000);
        MyTask t2 = new MyTask(2000);
        MyTask t3 = new MyTask(1500);
        MyTask t4 = new MyTask(2500);
        MyTask t5 = new MyTask(500);

        // 将每个对象放到tasks容器中
        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);

        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            // 取出tasks中的值
            System.out.println(tasks.take());
        }
    }

}
