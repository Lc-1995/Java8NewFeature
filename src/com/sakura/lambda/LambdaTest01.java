package com.sakura.lambda;


import com.sakura.designmode.MyPredicate;
import com.sakura.designmode.impl.FilterEmpByAge;
import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.*;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/29 15:15
 */
public class LambdaTest01 {

    // 以前的匿名内部类写法
    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> set = new TreeSet(comparator);
        set.add(2);
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // Lambda表达式
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> set = new TreeSet<>(comparator);
    }

    //=====================================================================


    // 员工列表
    List<Employee> employeeList = Arrays.asList(
            new Employee("泰兰德", 5000, 10000),
            new Employee("伊利丹", 10000, 666666),
            new Employee("奥蕾莉亚", 19, 55555),
            new Employee("希尔瓦娜斯", 30, 77777),
            new Employee("卡尔", 100, 22222)
    );

    // 1.需求:获取年龄大于35的员工
    // 2.需求:获取工资小于50000的员工
    // 3...
    @Test
    public void test3() {
        List<Employee> employees = getEmployeeList(this.employeeList);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // ========================传统的解决方式Start===========================

    // 获取年龄大于35的员工
    public List<Employee> getEmployeeList(List<Employee> list) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() > 35) {
                employees.add(employee);
            }
        }
        return employees;
    }

    // 获取工资小于50000的员工
    public List<Employee> getEmployeeList02(List<Employee> list) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getSalary() < 50000) {
                employees.add(employee);
            }
        }
        return employees;
    }
    // ========================传统的解决方式End===========================

    // 虽然需求增加了，但是代码基本不变，以上写法出现大量冗余代码
    // 以前的做法：1、抽取  2、使用设计模式

    // =============================优化代码Start=============================

    // 采用策略设计模式后只需要写一个方法，需要其他条件查询时只需要实现一个新的接口
    public List<Employee> getEmployeeList03(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            // 根据传入的不同实现类条件实现不同的过滤
            if (myPredicate.filter(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    // 优化方式一:设计模式 + 实现类
    @Test
    public void test4() {
        List<Employee> employees = getEmployeeList03(employeeList, new FilterEmpByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // 优化方式二:设计模式 + 匿名内部类(免除了频繁创建实现类过程)
    @Test
    public void test5() {
        List<Employee> employees = getEmployeeList03(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean filter(Employee employee) {
                return employee.getAge() <= 30;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // 优化方式三:设计模式 + Lambda表达式
    @Test
    public void test6() {
        List<Employee> employees = getEmployeeList03(employeeList, (e) -> e.getSalary() >= 50000);
        employees.forEach(System.out::println);
    }

    // 优化方式四:Stream API
    @Test
    public void test7() {
        employeeList.stream()
                .filter((e) -> e.getAge() > 50)
                .limit(2)    // 只获取前两个数据
                .forEach(System.out::println);
        System.out.println("---------------------------------");
        employeeList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    // =============================优化代码Start=============================
}