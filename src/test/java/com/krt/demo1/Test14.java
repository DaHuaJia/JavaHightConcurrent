package com.krt.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/12 8:45
 */
public class Test14 {
    /**
     * 解决同样的问题更高效的方法是使用AtomXXX类
     * AtomXXX类本身的方法都是原子性的，但不能保证多个方法连续调用是原子性的
     */
    AtomicInteger count = new AtomicInteger(0); // int count = 0;

    public void fun() {
        for (int i = 0; i < 10000; i++) {
            // incrementAndGet方法非常底层
            count.incrementAndGet(); // count++;
        }
    }

    public static void main(String[] args) {
        Test14 t = new Test14();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> t.fun(), "t" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("count= " + t.count);
    }

}
