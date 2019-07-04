package com.krt.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭明德
 * @description
 * @date 2019/6/11 20:32
 */
public class Test12 {
    /**
     * volatile并不能保证多个线程共同修改flag变量是所带来的不一致的问题，volatile不能替代synchronized
     * volatile的效率比synchronized高的多
     * volatile只保证了可见性，synchronized既保证可见性有保证了原子性
     * volatile虽然可以让每个线程都可见该变量，但是在该变量自增时确不一定能及时同步
     * 并行运算不一定能保证正确的运行结果，串行运算可以保证结果
     */
    public volatile int count = 0;

    /**
     * 定义一个方法，自增10000
     */
    public void fun() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        Test12 test12 = new Test12();

        /**
         * 定义一个线程数组
         */
        List<Thread> threads = new ArrayList<>();

        /**
         * 初始化该线程数组
         */
        for (int j = 0; j < 10; j++) {
            threads.add(new Thread(() -> test12.fun(), "fun" + j));
        }

        /**
         * 开启线程数据
         */
        /*for(int i=0; i<10; i++){
            threads.get(i).start();
        }*/
        threads.forEach((o) -> o.start());

        /**
         * 等待所有数组结束
         */
        for (int i = 0; i < 10; i++) {
            /**
             * join 和 isAlive 方法
             * isAline方法由线程调用，有返回值，如果线程还存活返回true，如果线程消亡返回false
             * join方法也是由线程调用，如果线程结束则能成功完成join方法，如果线程存活则程序或阻塞在join方法中
             */
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 10个线程，每个线程自增10000，结果应该是100000
        System.out.println("count= " + test12.count);
    }

}
