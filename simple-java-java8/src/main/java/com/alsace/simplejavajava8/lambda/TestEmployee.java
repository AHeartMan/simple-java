package com.alsace.simplejavajava8.lambda;

import com.alsace.simplejavajava8.Employee;
import com.alsace.simplejavajava8.lambda.filter.FilterEmployee;
import com.alsace.simplejavajava8.lambda.impl.FilterByAge;
import com.alsace.simplejavajava8.lambda.impl.FilterBySalary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/4 0004
 */
public class TestEmployee {

    //初始化员工集合信息
    List<Employee> emps = Arrays.asList(
            new Employee(18, "张三", 9999.99),
            new Employee(59, "李四", 6666.66),
            new Employee(28, "王五", 3333.33),
            new Employee(8, "赵六", 7777.77),
            new Employee(10, "田七", 5555.55)
    );

    public static void main(String[] args) {
        TestEmployee testEmployee = new TestEmployee();
//        testEmployee.test1();
//        testEmployee.test2();
//        testEmployee.test3();
//        testEmployee.test4();
//        testEmployee.test5();
        testEmployee.test6();
    }

    //获取年龄大于35的员工 and 获取工资大于5000的员工
    //传统方式
    public void test1() {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getAge() < 35) {
                list.add(emp);
            }
        }
        list.forEach(System.out::println);

        System.out.println("-----------------------");

        list.clear();
        for (Employee emp : emps) {
            if (emp.getSalary() > 5000) {
                list.add(emp);
            }
        }
        list.forEach(System.out::println);
    }

    //优化方式一：策略模式
    public List<Employee> myFilter(FilterEmployee<Employee> filterEmployee) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (filterEmployee.test(emp)) {
                list.add(emp);
            }
        }
        return list;
    }

    public void test2() {
        List<Employee> list1 = myFilter(new FilterByAge());
        list1.forEach(System.out::println);
        System.out.println("--------------------");
        List<Employee> list2 = myFilter(new FilterBySalary());
        list2.forEach(System.out::println);
    }

    //优化方式二：匿名内部类
    public void test3() {
        List<Employee> list = myFilter(new FilterEmployee<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 35;
            }
        });
        list.forEach(System.out::println);
    }

    //优化方式三：lambda表达式
    public void test4() {
        List<Employee> list = myFilter((e) -> e.getAge() > 35);
        list.forEach(System.out::println);
    }

    //优化方式四：StreamApi
    public void test5() {
        emps.stream()
                .filter(employee -> employee.getAge() > 35)
                .forEach(System.out::println);
    }

    //获取所有员工姓名
    public void test6() {
        emps.stream()
                .map(Employee::getAge)
                .limit(5)
                .sorted()
                .forEach(System.out::println);
    }
}
