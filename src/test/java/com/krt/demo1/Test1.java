package com.krt.demo1;

import org.junit.Test;

/**
 * @author 郭明德
 * @description synchronized关键字 对某个对象加锁
 * @date 2019/6/4 9:04
 */
public class Test1 {
    // synchronized是一种互斥锁

    private int count = 10;
    private Object object = new Object(); // new出来的对象报存在堆内存中

    @Test
    public void fun() {
        // 对象的锁信息也保存在相应的堆内存中，当对象(object)的引用发生改变时，对象的锁也会发生改变
        synchronized (object) { // 任何线程想要执行下面的代码都必须拿到object的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }
}

