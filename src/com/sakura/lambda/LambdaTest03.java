package com.sakura.lambda;

import com.sakura.designmode.MyLongFunction;
import com.sakura.designmode.MyStrFunction;
import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Sakura
 * @Description: Lambda表达式练习
 * @Date: 2018/11/30 10:37
 */
public class LambdaTest03 {

    // 员工列表
    List<Employee> employeeList = Arrays.asList(
            new Employee("泰兰德", 5000, 10000),
            new Employee("伊利丹", 10000, 666666),
            new Employee("奥蕾莉亚", 19, 55555),
            new Employee("希尔瓦娜斯", 30, 77777),
            new Employee("卡尔", 100, 22222),
            new Employee("温蕾萨", 19, 88888)
    );

    /**
     * 1.调用Collections.sort()方法，通过定制排序比较两个Employee，
     * 先按年龄比，年龄相同按姓名比
     */
    @Test
    public void test1() {
        Collections.sort(employeeList, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    /**
     * 2、
     * 1)声明函数式接口，声明抽象方法String getValue(String s)
     * 2)使用接口作为参数，将一个字符串转换成大写，并返回
     * 3)再将一个字符串的第二个和第四个索引位置进行截取
     */
    @Test
    public void test2() {
        String s1 = operation("sakura", (s) -> s.toUpperCase());
        System.out.println(s1);
        System.out.println(operation("sakura", (s -> s.substring(2, 4))));
    }

    public String operation(String s, MyStrFunction strFunction) {
        return strFunction.getValue(s);
    }

    /**
     * 3、
     * 1)声明一个两个泛型的函数式接口<T, R>,T为参数，R为返回值
     * 2)计算两个long型参数的和
     * 3)计算两个long型参数的乘积
     */
    @Test
    public void test3() {
        System.out.println(operationT(30L, 20L, (x, y) -> x + y));
        System.out.println(operationT(20L, 20L, (x, y) -> x * y));
    }

    public Long operationT(Long l1, Long l2, MyLongFunction<Long, Long> longFunction) {
        return longFunction.getValue(l1, l2);
    }
}
