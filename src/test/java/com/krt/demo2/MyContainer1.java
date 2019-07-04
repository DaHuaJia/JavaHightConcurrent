package com.krt.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description 实现一个容器，提供两个方法，add 和 size
 * 写两个线程，线程1添加10个元素到容器中，线程2监控元素的个数，当元素的个数到5个时，线程2给出提示并结束
 * @date 2019/6/12 15:03
 */
public class MyContainer1 {
    volatile List<Integer> myList = new ArrayList();

    public void add(Integer ans) {
        myList.add(ans);
    }

    public int size() {
        return myList.size();
    }

    public static void main(String[] args) {
        MyContainer1 demo3 = new MyContainer1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo3.add(i);
                    System.out.println("add=" + i);
                }
            }
        }, "t1").start();

        // 线程死循环浪费CPU，不断的探测demo3.size()浪费资源
        new Thread(() -> {
            while (true) {
                if (demo3.size() == 5) {
                    break;
                }
            }
            System.out.println("myList size is 5.");
        }, "t2").start();
    }

}
