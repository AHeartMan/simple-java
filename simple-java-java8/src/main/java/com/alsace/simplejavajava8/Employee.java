package com.alsace.simplejavajava8;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author sangmingming
 * @since 2019/11/4 0004
 */
public class Employee {

    private int age;
    private String name;
    private double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, salary);
    }

    public Employee(int age, String name, double salary) {
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
