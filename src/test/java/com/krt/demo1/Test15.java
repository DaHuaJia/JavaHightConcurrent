package com.krt.demo1;

import java.util.concurrent.TimeUnit;

/**
 * @author 郭明德
 * @description synchronized优化  同步代码块中的语句越少越好  fun3的效率要比fun2高
 * @date 2019/6/12 10:11
 */
public class Test15 {
    int count = 0;

    public synchronized void fun2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);

        count++;

        TimeUnit.SECONDS.sleep(2);
    }

    public void fun3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);

        /**
         * 业务逻辑中只有下面这句需要sync,这时不应该给整个方法都加上锁
         * 采用细粒度的锁，可以使线程争用时间变短，从而提高效率
         */
        synchronized (this) {
            count++;
        }

        TimeUnit.SECONDS.sleep(2);
    }

}
