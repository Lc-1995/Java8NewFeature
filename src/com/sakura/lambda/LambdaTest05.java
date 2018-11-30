package com.sakura.lambda;

import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

/**
 * @Author: Sakura
 * @Date: 2018/11/30 15:45
 * @Description:
 * 一、方法引用：如果Lambda体中的内容有方法实现了，就可以使用方法引用（Lambda体的另一种形式）
 *
 *  主要的三种语法格式：
 *          1、对象::实例方法名
 *          2、类::静态方法名
 *          3、类::实例方法名
 *
 *  使用注意项：
 *          1、Lambda体中调用的方法的参数列表和返回值类型要和函数式接口中的抽象方法一致
 *          2、如果Lambda参数列表的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用类::实例方法名
 *
 * 二、构造器引用
 *          ClassName::new
 *  使用注意项：
 *          调用的构造器的参数列表必须和函数式接口实现方法的参数列表一致
 *
 * 三、数组引用
 *          Type::new
 */
public class LambdaTest05 {

    // ============================方法引用Start============================
    // 对象::实例方法名
    @Test
    public void test1() {
        Consumer<Integer> consumer = (x) -> System.out.println(x);

        Consumer<String> methodRef = System.out::println;
        methodRef.accept("sakura");
    }

    // 类::静态方法名
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> methodRef = Integer::compare;
    }

    // 类::实例方法名
    @Test
    public void test3() {
        BiPredicate<String, String> predicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> methodRef = String::equals;
        System.out.println(methodRef.test("sakura", "Sakura"));
    }
    // ============================方法引用End============================

    // ============================构造器引用Start============================
    @Test
    public void test4() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();
        System.out.println(employee);
        // --------------------构造器引用-------------------
        Supplier<Employee> constructorRef = Employee::new;
        System.out.println(constructorRef.get());
    }

    // 带一个参数的构造器引用
    @Test
    public void test5() {
        Function<Integer, Employee> function = (x) -> new Employee(x);
        System.out.println(function.apply(30));
        // --------------------构造器引用-------------------
        Function<Integer, Employee> constructorRef = Employee::new;
        System.out.println(constructorRef.apply(20));
    }
    // ============================构造器引用End============================

    @Test
    public void test6() {
        Function<Integer, String[]> function = (x) -> new String[x];
        System.out.println(function.apply(20).length);
        // --------------------数组引用-------------------
        Function<Integer, Integer[]> arrayRef = Integer[]::new;
        System.out.println(arrayRef.apply(10).length);
    }


    // ============================练习=============================

    /**
     * 2、
     * 1)声明函数式接口，声明抽象方法String getValue(String s)
     * 2)使用接口作为参数，将一个字符串转换成大写，并返回
     * 3)再将一个字符串的第二个和第四个索引位置进行截取
     */
    @Test
    public void test7() {
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.apply("sakura"));
    }

    /**
     * 3、
     * 1)声明一个两个泛型的函数式接口<T, R>,T为参数，R为返回值
     * 2)计算两个long型参数的和
     * 3)计算两个long型参数的乘积
     */
    @Test
    public void test8() {
        BiFunction<Long, Long, Long> function = Long::sum;
        System.out.println(function.apply(20L,30L));
    }
}
