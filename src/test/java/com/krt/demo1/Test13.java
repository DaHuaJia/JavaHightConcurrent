package com.krt.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭明德
 * @description 对比于上一个程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 * @date 2019/6/11 21:00
 */
public class Test13 {

    public int count = 0;

    public synchronized void fun() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        Test13 test13 = new Test13();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> test13.fun(), "fun" + i));
        }

        threads.forEach((o) -> o.start());

        for (int i = 0; i < 10; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("count= " + test13.count);
    }

}
