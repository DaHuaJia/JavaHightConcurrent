package com.krt.demo3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭明德
 * @description 有N张火车票，每张票都有一个编号，同时有10个窗口对外售票
 * @date 2019/6/14 9:34
 */
public class TicketSeller2 {
    /**
     * List方法操作不具有原子性，容易导致一个元素被删除两次
     */
    public static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 20; i++) {
            tickets.add("Number is " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    /**
                     * 加锁可以保证程序的原子性
                     */
                    synchronized (tickets) {
                        if (tickets.size() > 0) {
                            System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                        }
                    }
                }
            }, "t" + i).start();
        }

    }

}
