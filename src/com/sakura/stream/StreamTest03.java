package com.sakura.stream;

import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
            new Employee("温蕾萨", 19, 88888, Employee.Status.BUYS),
            new Employee("温蕾萨", 19, 88888, Employee.Status.VOCATION)
    );

    /**
     * 查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是否至少匹配一个元素
     * nonematch--检查是否没有匹配所有元素
     * findFirst--返回第一个元素
     * findAny--返回当前流中任意元素
     * count--返回流中元素的总个数
     * max--返回流中最大值
     * min--返回流中最小值
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


    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)--将流中元素反复结合得到一个新流
     */
    @Test
    public void test9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final Integer reduce = list.stream()
                // 第一个参数是起始值传递给x，将元素中的第一个传给y，然后执行Lambda体得到新值作为参数又传递给x，以此类推
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        System.out.println("---------------------------------------");
        final Optional<Integer> sum = employeeList.stream()
                .map(Employee::getSalary)
                .reduce(Integer::sum);
        System.out.println(sum.get());
    }

    /**
     * 收集
     * collect--将流转化为其他形式。接受一个Collector接口的实现，用于Stream中元素做汇总方法
     */
    @Test
    public void test10() {
        final List<String> list = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(list);

        final Set<String> set = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println(set);

        final HashSet<String> hashSet = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSet);
    }

    // 求平均值，最大最小值，总个数
    @Test
    public void test11() {
        final Long collect = employeeList.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        System.out.println("-----------------------");

        final Double avg = employeeList.stream()
                .collect(Collectors.averagingInt(Employee::getAge));
        System.out.println(avg);

        System.out.println("-----------------------");

        final Optional<Integer> min = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Integer::compare));
        System.out.println(min.get());

        System.out.println("-----------------------");

        final Optional<Integer> max = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(max.get());
    }

    // 分组
    @Test
    public void test12() {
        final Map<Employee.Status, List<Employee>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    // 多级分组
    @Test
    public void test13() {
        final Map<Employee.Status, Map<String, List<Employee>>> map = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() < 100) {
                        return "青年";
                    } else if (e.getAge() < 4000) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }

    // 分区
    @Test
    public void test14() {
        final Map<Boolean, List<Employee>> collect = employeeList.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
        System.out.println(collect);
    }

    // 运算的主函数
    @Test
    public void test15() {
        final IntSummaryStatistics collect = employeeList.stream()
                .collect(Collectors.summarizingInt(Employee::getSalary));
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getMax());
    }

    // 收集为字符串
    @Test
    public void test16() {
        final String collect = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
