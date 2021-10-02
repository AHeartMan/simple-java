package com.alsace.simplejavajava8.lambda;

import com.alsace.simplejavajava8.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/7 0007
 */
public class TestEmployee1 {

    public static void main(String[] args) {
        TestEmployee1 testEmployee1 = new TestEmployee1();
        testEmployee1.test1();
    }

    List<Employee> emps = Arrays.asList(
            new Employee(18, "1", 9999.99),
            new Employee(59, "2", 6666.66),
            new Employee(28, "3", 3333.33),
            new Employee(18, "4", 7777.77),
            new Employee(10, "5", 5555.55)
    );

    //对员工进行年龄排序，年龄相同对姓名排序
    public void test1() {
        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return -e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        emps.forEach(System.out::println);
    }
}
