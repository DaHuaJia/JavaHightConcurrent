package com.krt.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description 实现一个容器，提供两个方法，add 和 size
 * 写两个线程，线程1添加10个元素到容器中，线程2监控元素的个数，当元素的个数到5个时，线程2给出提示并结束
 * <p>
 * 这里使用wait 和 notify来避免线程2的死循环，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法是，必须要保证线程2先执行，也就是首先要让线程2监听才可以
 * @date 2019/6/12 15:03
 */
public class MyContainer2 {
    volatile List<Integer> myList = new ArrayList();

    public void add(Integer ans) {
        myList.add(ans);
    }

    public int size() {
        return myList.size();
    }

    public static void main(String[] args) {
        MyContainer2 demo3 = new MyContainer2();

        new Thread(() -> {
            synchronized (demo3.myList) {
                if (demo3.size() != 5) {
                    try {
                        demo3.myList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("myList size is 5.");

                /*demo3.myList.notify();*/
            }
        }, "t2").start();

        /**
         * 刚开始t2处于wait状态，当size=5时，虽然t1通过notify通知了t2，但是并没有释放锁
         * t2虽然被唤醒，但是没有myList的锁，依然没办法立即执行。
         *
         * 可以在t1 notify的时候，同时wait，释放锁。t2得到锁，执行完毕之后，通知t1，让t1继续执行
         */
        new Thread(() -> {
            synchronized (demo3.myList) {
                for (int i = 0; i < 10; i++) {
                    demo3.add(i);
                    System.out.println("add = " + i);
                    if (demo3.size() == 5) {
                        demo3.myList.notify();

                        try {
                            demo3.myList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    /*try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }, "t1").start();

    }

}
