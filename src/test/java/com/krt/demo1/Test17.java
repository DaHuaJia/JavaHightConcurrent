package com.krt.demo1;

/**
 * @author 郭明德
 * @description 不要以字符串常量作为锁定对象
 * @date 2019/6/12 14:37
 */
public class Test17 {
    /**
     * 在这个例子中，fun1 和 fun2其实锁定的是同一个对象，有可能会发生死锁阻塞
     * 程序和所使用的类库不经意间使用了同一把锁
     */
    public String str1 = "Hello";
    public String str2 = "Hello";

    public void fun1() {
        synchronized (str1) {

        }
    }

    public void fun2() {
        synchronized (str2) {

        }
    }

}
