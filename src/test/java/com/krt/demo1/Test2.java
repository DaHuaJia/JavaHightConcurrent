package com.krt.demo1;

import org.junit.Test;

import java.util.concurrent.TimeUnit;


/**
 * @author 郭明德
 * @description Test1的简单写法
 * @date 2019/6/4 9:26
 */
public class Test2 {
    private int count = 10;

    public void fun() {
        synchronized (this) { // this表示锁定自身
            // 锁定的是该实例化对象
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }


    // 静态方法和静态对象可以总结通过类名进行访问，无需实例化出一个对象，因此不能使用this进行锁定
    // 可以采用Test3中的方法
    /*private static int num = 10;

    public static void fun2(){
        synchronized(this){ // 此处不能使用this
            num --;
            System.out.println(Thread.currentThread().getName()+" num="+num);
        }
    }*/

}
