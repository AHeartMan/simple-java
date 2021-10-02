package com.alsace.simplejavajava8.forkjoin;

import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * <p>
 * java8之后
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/28 0028
 */
public class ForkJoinTest2 {

    /**
     * 并行流，底层是fork/join
     *
     * @param args
     */
    public static void main(String[] args) {
        OptionalLong reduce = LongStream.rangeClosed(0, 100000000L)
                .parallel()
                .reduce(Long::sum);
        System.out.println(reduce.getAsLong());
    }
}
