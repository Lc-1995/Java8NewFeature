package com.sakura.pojo;

import java.util.Objects;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/29 15:59
 */
public class Employee {

    private String name;

    private Integer age;

    private Integer salary;

    private Status Status;

    public Employee() {
    }

    public Employee(String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, Integer age, Integer salary, Employee.Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        Status = status;
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Employee.Status getStatus() {
        return Status;
    }

    public void setStatus(Employee.Status status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", Status=" + Status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, salary);
    }

    public enum Status {
        FREE,
        BUYS,
        VOCATION
    }
}
