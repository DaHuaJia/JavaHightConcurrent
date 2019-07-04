package com.krt.demo3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 郭明德
 * @description 有N张火车票，每张票都有一个编号，同时有10个窗口对外售票
 * @date 2019/6/14 9:34
 */
public class TicketSeller3 {
    /**
     * 队列容器
     */
    public static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 20; i++) {
            tickets.add("Number is " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    String str = tickets.poll();
                    if (str == null) {
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "---" + str);
                    }
                }
            }, "t" + i).start();
        }

    }

}
