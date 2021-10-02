package com.alsace.simplejavajava8.lambda;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/4 0004
 */
public class TestLambda {

    public static void main(String[] args) {
        TestLambda lambda = new TestLambda();
        lambda.test1();
    }

    /**
     * 不使用lambda表达式
     */
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
        ts.add(54);
        ts.add(3);
        ts.add(1);
        ts.add(100);

        Iterator<Integer> iterator = ts.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 使用lambda表达式
     */
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }
}
