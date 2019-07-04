package com.krt.demo5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 郭明德
 * @description 并行计算  找出1到200000中的质数
 * @date 2019/6/17 11:10
 */
public class T04_ParallelComputing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 先使用一个主线程去处理
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // 使用四个线程处理
        ExecutorService service = Executors.newFixedThreadPool(4);
        // 前面的数字比较小，后面数字较大，所以分配不均匀
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        // 阻塞，直到所有的方法执行完
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }
    }


    // 判断一个数是否是质数
    static Boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    //找出start到end之间的所有的质数
    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

}
