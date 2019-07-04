package com.krt.demo1;

import java.util.concurrent.TimeUnit;


/**
 * @author 郭明德
 * @description volatile 关键字，使一个变量在多个线程之间可见
 * @date 2019/6/11 19:33
 */
public class Test11 {

    /**
     * test11线程 和 main主线程都使用了flag这个变量，java默认给test11线程复制了一个副本在私有的工作内存中
     * 这样的话，当主线程修改了flag的值，虽然主内存中的值已经改变，可test11线程未必知道
     * 使用volatile关键字，将会强制所有的线程重新去读取到变量的修改值
     * <p>
     * volatile原理：
     * volatile所修饰的变量并不是说让其他线程不复制该变量的副本，也不是说，让其他线程定时读取主内存中的值。
     * 而是，当有线程修改该变量的值时，volatile会通知其他使用了该变量的线程，强制它们刷新该变量的副本。
     */

    // volatile并不能保证多个线程共同修改flag变量是所带来的不一致的问题，volatile不能替代synchronized，volatile的效率比synchronized高的多

    // volatile只保证了可见性，synchronized既保证可见性有保证了原子性
    volatile Boolean flag = true;

    public void fun() {
        System.out.println("fun start.....");
        while (flag) {
            /**
             * 在没有加volatile时，并不代表着其他线程不是更新变量副本
             * 当CPU有空闲的时候，会去主动更新线程的变量副本的值，存在不确定性
             * 通过volatile可以使CPu强制刷新该变量副本
             *
             * 例如，在这个while循环中使用sleep(30)，主动让出空闲，那么CPU就有可能会去刷新该变量的值
             */
        }
        System.out.println("fun end!");
    }


    public static void main(String[] args) {
        Test11 test11 = new Test11();
        new Thread(() -> test11.fun(), "test11").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test11.flag = false;
    }

}
