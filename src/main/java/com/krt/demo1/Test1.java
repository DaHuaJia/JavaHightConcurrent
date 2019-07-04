package com.krt.demo1;

/**
 * @author 郭明德
 * @description synchronized关键字 对某个对象加锁
 * @date 2019/6/4 9:04
 */
public class Test1 {

    private int count = 10;
    private Object object = new Object();

    public void fun() {
        synchronized (object) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}

