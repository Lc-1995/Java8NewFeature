package com.sakura.stream;

import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Sakura
 * @Description: 终止操作
 * @Date: 2018/12/3 16:55
 */
public class StreamTest03 {

    // 员工列表
    List<Employee> employeeList = Arrays.asList(
            new Employee("泰兰德", 5000, 10000, Employee.Status.BUYS),
            new Employee("伊利丹", 10000, 666666, Employee.Status.FREE),
            new Employee("奥蕾莉亚", 19, 55555, Employee.Status.VOCATION),
            new Employee("希尔瓦娜斯", 30, 77777, Employee.Status.FREE),
            new Employee("卡尔", 100, 22222, Employee.Status.VOCATION),
            new Employee("温蕾萨", 19, 88888,Employee.Status.BUYS),
            new Employee("温蕾萨", 19, 88888,Employee.Status.VOCATION)
    );
    
    /**
     *  查找与匹配
     *  allMatch--检查是否匹配所有元素
     *  anyMatch--检查是否至少匹配一个元素
     *  nonematch--检查是否没有匹配所有元素
     *  findFirst--返回第一个元素
     *  findAny--返回当前流中任意元素
     *  count--返回流中元素的总个数
     *  max--返回流中最大值
     *  min--返回流中最小值
     */
    @Test
    public void test1() {
        final boolean b = employeeList.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUYS));
        System.out.println(b);
    }

    @Test
    public void test2() {
        final boolean b = employeeList.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b);
    }

    @Test
    public void test3() {
        final boolean b = employeeList.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b);
    }

    @Test
    public void test4() {
        final Optional<Employee> first = employeeList.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .findFirst();
        System.out.println(first.get());
    }

    @Test
    public void test5() {
        final Optional<Employee> any = employeeList.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.VOCATION))
                .findAny();
        System.out.println(any.get());
    }

    @Test
    public void test6() {
        final long count = employeeList.stream()
                .count();
        System.out.println(count);
    }

    @Test
    public void test7() {
        final Optional<Employee> max = employeeList.stream()
                .max(Comparator.comparing(Employee::getAge));
        System.out.println(max.get());
    }

    @Test
    public void test8() {
        final Optional<Integer> min = employeeList.stream()
                .map(Employee::getAge)
                .min(Integer::compareTo);
        System.out.println(min.get());
    }
}
