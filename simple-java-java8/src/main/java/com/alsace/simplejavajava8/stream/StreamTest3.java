package com.alsace.simplejavajava8.stream;

import com.alsace.simplejavajava8.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 终止操作
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/25 0025
 */
public class StreamTest3 {

    List<Employee> emps = Arrays.asList(
            new Employee(18, "张三", 9999.99),
            new Employee(59, "李四", 6666.66),
            new Employee(28, "王五", 3333.33),
            new Employee(8, "赵六", 7777.77),
            new Employee(8, "赵六", 7777.77),
            new Employee(8, "赵六", 7777.77),
            new Employee(8, "赵六", 7777.77),
            new Employee(10, "田七", 5555.55)
    );

    /**
     * 查找与匹配
     * allMatch -- 检查是否匹配所有元素
     * angMatch -- 检查是否至少匹配一个元素
     * noneMatch -- 检查是否没有匹配的元素
     * findFirst -- 返回第一个元素
     * findAny -- 返回当前流中的任意元素
     * count -- 返回元素中的总个数
     * max -- 返回流中的最大值
     * min -- 返回流中的最小值
     */
    public void test1() {
        boolean flag = emps.stream()
                .allMatch(e -> e.getAge() == 8);
        Optional<Employee> first = emps.stream()
                .findFirst();
        //并行流
        Optional<Employee> any = emps.parallelStream()
                .filter(e -> e.getAge() > 8)
                .findAny();
        long count = emps.stream()
                .count();
        Optional<Employee> max = emps.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
    }

    /**
     * 规约 -- 可以将流中元素反复结合起来，得到一个值
     * reduce(T identity(起始值), BinaryOperator(二分操作函数))
     * reduce(BinaryOperator)
     */
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //原理：将初始值赋值给x, 从流中取出第一个元素1， 0 + 1 = 1, 将1赋值给x,再取出第二个元素赋值给y
        //1 + 2 = 3,依次计算
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        Optional<Double> reduce = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
    }

    /**
     * 收集
     * collect -- 将流转换为其他形式，接受一个Collector接口的实现，用于给Stream中的元素做汇总的方法
     * 分组
     * 分区
     * 组函数
     * 连接 join
     */
    public void test3() {
        List<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        Set<Double> collect1 = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.toSet());
        HashSet<String> collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        //获取总数
        Long collect3 = emps.stream()
                .collect(Collectors.counting());
        //获取工资平均值
        Double collect4 = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        //工资总和
        Double collect5 = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        //工资最大值
        Optional<Double> collect6 = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
    }
}
