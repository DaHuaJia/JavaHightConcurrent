package com.krt.demo4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 郭明德
 * @description Collections.synchronizedList 给List加锁
 * @date 2019/6/14 14:47
 */
public class T03_SynchronizedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        /**
         * 原来的list是没有锁的，不具有原子性
         * Collections.synchronizedList(list) 的返回结果依然是一个List
         * 返回的新的List是加锁的。
         *
         * 类似于 List<String> lists = new Vector();
         */
        List<String> listSync = Collections.synchronizedList(list);
    }
}
