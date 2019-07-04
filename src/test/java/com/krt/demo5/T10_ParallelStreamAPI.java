package com.krt.demo5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/17 16:01
 */
public class T10_ParallelStreamAPI {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        /**
         * 初始化该nums，大小为10000，每个元素为一个随机数 1000000到2000000之间
         */
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }

        /**
         * 第一种方法是采用forEach循环遍历 lambda
         */
        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        /**
         * 第二种方式是采用parallelStream()  多线程 + 工作窃取
         * parallelStream是一个并行执行的流(Stream)，通过默认的ForkJoinPool，可以提高多线程的任务速度
         * Stream具有平行处理能力，处理的过程会分而治之，也就是将一个大任务切分成多个小任务，这表示每个任务都是一个操作
         */
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T10_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    // 判断一个数是否是素数
    static Boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % 2 == 0) {
                return false;
            }
        }
        return true;
    }

}
