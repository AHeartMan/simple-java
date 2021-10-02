package com.alsace.simplejavajava8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>
 * 一、Stream 的三个操作步骤
 * 1. 创建stream
 * 2. 中间操作
 * 3. 终止操作
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/18 0018
 */
public class StreamTest1 {

    /**
     * 创建stream
     */
    public void test1() {
        //1.可以通过collection系列集合的stream()或parallelStream()(并行流)方法获取
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        int[] a = new int[10];
        IntStream stream2 = Arrays.stream(a);

        //3.通过Stream类中的静态方法of()方法获取
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.forEach(System.out::println);
        //生成
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
    }
}
