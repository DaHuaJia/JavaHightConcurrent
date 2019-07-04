package com.krt.demo5;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author 郭明德
 * @description 任务分叉线程池
 * @date 2019/6/17 15:16
 */
public class T09_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        // 初始化数组，给每一个数组赋一个初始值100之内
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        /**
         * 输出总和
         * stream.api jdk1.8中的内容
         * stream(nums) 循环遍历对象中的内一个值
         */
        System.out.println(Arrays.stream(nums).sum());
    }

    /**
     * 当任务量太大时，该线程池会把大任务切割成小任务，如果小任务还是太大，还可以继续切割
     * 任务的最大大小可以自己指定
     * <p>
     * 继承 RecursiveAction没有返回值
     * 继承RecursiveTask有返回值
     */
    /*static class AddTask extends RecursiveAction {
        int start, end;
        AddTask(int s, int e){
            this.start = s;
            this.end = e;
        }

        @Override
        protected void compute() {
            if(end - start <= MAX_NUM){
                long sum = 0L;
                for(int i=start; i<end; i++){
                    sum += nums[i];
                }
                System.out.println("from: "+start+" to: "+end+" = "+sum);
            }else{
                int middle = start + (end - start) / 2;

                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                // 启动线程
                subTask1.fork();
                subTask2.fork();
            }
        }
    }*/

    // 有返回值，
    static class AddTask extends RecursiveTask<Long> {
        int start, end;

        AddTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = start + (end - start) / 2;

                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                // 启动线程
                subTask1.fork();
                subTask2.fork();

                return subTask1.join() + subTask2.join();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        /**
         * 定义一个任务分叉线程池
         */
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 初始化任务大小
        AddTask task = new AddTask(0, nums.length);
        // 启动线程池
        forkJoinPool.execute(task);

        /**
         * 有返回值时使用
         * 使用join()方法可以不需要 System.in.read(), 因为join本身就是阻塞的
         */
        long result = task.join();
        System.out.println(result);

        // 阻塞主线程，任务分叉线程池产生的也是后台线程
        //System.in.read();
    }

}
