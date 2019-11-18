package com.alsace.simplejavajava8.stream;

import com.alsace.simplejavajava8.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 中间操作，中间操作不会执行任何操作，终止操作时，一次性执行全部内容，即“惰性求值”
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/18 0018
 */
public class StreamTest2 {

    List<Employee> emps = Arrays.asList(
            new Employee(18, "张三", 9999.99),
            new Employee(59, "李四", 6666.66),
            new Employee(28, "王五", 3333.33),
            new Employee(8, "赵六", 7777.77),
            new Employee(8, "赵六", 7777.77),
            new Employee(8, "赵六", 7777.77),
            new Employee(8, "赵六", 7777.77),
            new Employee(10, "田七",5555.55)
    );

    /**
     * 筛选与切片
     * filter --- 接受lambda，从流中排除某些元素
     * limit --- 截断流，使其元素不超过给定的数量
     * skip(n) --- 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit(n)互补
     * distinct --- 筛选，通过流所生成元素的hashcode()和equals()去除重复元素
     */
    public void test1(){
        emps.stream()
                .filter((e) -> e.getAge() > 30)
                .forEach(System.out::println);

        emps.stream()
                .limit(2)
                .forEach(System.out::println);

        emps.stream()
                .skip(2)
                .forEach(System.out::println);

        //依赖对象的hashcode()和equals()
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     *  map --- 接受lambda，将元素转换成其它形式的或提取消息，接受一个函数作为参数，该函数会被应用到每个元素上，
     *      并将其映射成为一个新的元素；
     *  flatMap --- 接受一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有的流连接成一个流
     */
    public void test2(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        Stream<Character> stream = list.stream()
                .flatMap(StreamTest2::getstream);
        stream.forEach(System.out::println);
    }

    private static Stream<Character> getstream(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
}
