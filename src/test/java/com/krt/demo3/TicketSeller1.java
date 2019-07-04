package com.krt.demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author 郭明德
 * @description 有N张火车票，每张票都有一个编号，同时有10个窗口对外售票
 * @date 2019/6/14 9:34
 */
public class TicketSeller1 {
    /**
     * List方法操作不具有原子性，容易导致一个元素被删除两次
     */
    public static List<String> tickets = new ArrayList<>();

    /**
     * Vector容器具有原子性
     */
    //public static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 20; i++) {
            tickets.add("Number is " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    /**
                     * 在判断时候有票和取票的过程中没有加锁，可能会被其他线程打断
                     * 导致程序错误 或者 卖出重复的票
                     */
                    System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                }
            }, "t" + i).start();
        }

    }

}
