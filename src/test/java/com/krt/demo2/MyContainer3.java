package com.krt.demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description 实现一个容器，提供两个方法，add 和 size
 * 写两个线程，线程1添加10个元素到容器中，线程2监控元素的个数，当元素的个数到5个时，线程2给出提示并结束
 * <p>
 * MyContainer2的整个通信过程还是比较繁琐的
 * <p>
 * 使用Latch(门栓)替代wait notify 来进行通知，好处是通信方式简单，同时也可以指定等待时间
 * 使用await 和 countdown 方法替代wait 和 notify
 * Coun6tDownLatch不涉及锁定，当count的为零时当前线程可以继续运行
 * 当不涉及同步，只涉及线程通信的时候，使用synchronized + wait/notify 就显得太重了
 * 这时候应该考虑 countdownlatch/cylicbarrier/semaphore
 * @date 2019/6/12 15:03
 */
public class MyContainer3 {
    volatile List<Integer> myList = new ArrayList();

    public void add(Integer ans) {
        myList.add(ans);
    }

    public int size() {
        return myList.size();
    }

    public static void main(String[] args) {
        MyContainer3 demo3 = new MyContainer3();

        /**
         * 定义一个门栓
         * new CountDownLatch(1) 表示只有一个门栓，latch.countDown()一次就可以唤醒
         * new CountDownLatch(n) 则需要 latch.countDown() n次
         */
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            if (demo3.size() != 5) {
                try {
                    latch.await();
                    // 也可以自动等待时间
                    //latch.await(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("myList size is 5.");
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo3.add(i);
                System.out.println("add = " + i);

                if (demo3.size() == 5) {
                    // 打开门栓，让t2可以执行
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();

    }

}
