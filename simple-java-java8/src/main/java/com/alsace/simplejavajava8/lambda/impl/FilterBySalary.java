package com.alsace.simplejavajava8.lambda.impl;

import com.alsace.simplejavajava8.Employee;
import com.alsace.simplejavajava8.lambda.filter.FilterEmployee;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/6 0006
 */
public class FilterBySalary implements FilterEmployee<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 5000;
    }
}
