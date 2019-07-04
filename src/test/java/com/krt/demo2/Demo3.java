package com.krt.demo2;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 郭明德
 * @description
 * @date 2019/6/13 10:53
 */
public class Demo3 {

    public static void main(String[] args) {
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm");
        Date date = new Date();
        String time = sf.format(date);
        System.out.println(time);
        System.out.println(" --\n|  |\n|  |\n --");

    }

}
