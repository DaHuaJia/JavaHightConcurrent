package com.krt.demo3;

import java.util.Arrays;

/**
 * @author 郭明德
 * @description 线程安全的单例模式
 * 阅读文章：http://www.cnblogs.com/xudong-bupt/p/3433643.html
 * <p>
 * 更好的是采用下面这种方式，既不用加锁，也能是实现懒加锁
 * @date 2019/6/14 8:49
 */
public class Singleton {

    private Singleton() {
        System.out.println("Single");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    private static Singleton getSingle() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < 200; i++) {
            threads[i] = new Thread(() -> {
                Singleton.getSingle();
            });
        }
        Arrays.asList(threads).forEach((o) -> {
            o.start();
        });
    }

}
