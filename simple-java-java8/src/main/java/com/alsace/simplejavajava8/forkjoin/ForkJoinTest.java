package com.alsace.simplejavajava8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * <p>
 * fork/join 采用“工作窃取”模式
 * java8之前
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/27 0027
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long THRESHOLD = 10000;

    public ForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long lenth = end - start;

        if (lenth <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinTest left = new ForkJoinTest(start, middle);
            left.fork();
            ForkJoinTest right = new ForkJoinTest(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
