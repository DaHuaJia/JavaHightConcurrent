package com.krt.demo1;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description 锁定某个对象o, 如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 * @date 2019/6/12 10:24
 */
public class Test16 {
    public Object o = 2;

    public void fun2() {
        /**
         * synchronized锁定的是Object = 2，当object发生改变是，该锁依然指向原对象，
         * Object=3没有被锁定，所以在Object发生改变之后，两个线程不再同步，而是并行执行。
         *
         * 该对象锁锁定的是堆内存中的具体的对象上，而不是栈内存中的对象的引用
         */
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "=====" + o.toString());
            }
        }
    }

    public static void main(String[] args) {
        Test16 t = new Test16();

        new Thread(() -> t.fun2(), "fun2a").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.o = 3;
        new Thread(() -> t.fun2(), "fun2b").start();
    }

}
