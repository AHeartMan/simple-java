package com.alsace.simplejavajava8.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * <p>
 * 测试类
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/28 0028
 */
public class Main {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTest test = new ForkJoinTest(0, 100000000L);
        Long submit = pool.invoke(test);
        System.out.println(submit);
    }
}
