package com.sakura.lambda;

import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: Sakura
 * @Date: 2018/11/30 11:45
 * @Description: Java8 内置的四大核心函数式接口
 *          Consumer<T> : 消费型接口
 *              void accept(T t);
 *
 *          Supplier<T> : 供给型接口
 *              T get();
 *
 *          Function<T, R> : 函数型接口
 *              R apply(T t);
 *
 *          Predicate<T> : 断言型接口
 *              boolean test(T t);
 */
public class LambdaTest04 {

    /**
     *  Consumer 消费型接口
     *  用于有参数，没有返回值
     */
    @Test
    public void testConsumer() {
        buyNum(100, (n) -> System.out.println("购买数量:" + n));
    }
    public void buyNum(Integer num, Consumer<Integer> consumer) {
        consumer.accept(num);
    }

    /**
     *  Supplier 供给型接口
     *  无参数，有返回值
     *  需求：获取指定个数的整数
     */
    @Test
    public void testSupplier() {
        List<Integer> lists = getNum(10, () -> (int) (Math.random() * 100));
        lists.forEach(System.out::println);
    }

    public List<Integer> getNum(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i =0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }
    
    /**
     *  Function 函数型接口
     *  有参数，有返回值
     */
    @Test
    public void testFunction() {
        String str = strHandle("\t\t\t sakura ", (s) -> s.trim());
        System.out.println(str);
    }

    public String strHandle(String s, Function<String, String> function) {
        return function.apply(s);
    }

    /**
     *  Predicate 断言型接口
     *  做判断用
     */
    @Test
    public void testPredicate() {
        List<Employee> employees = filterEmp(employeeList, (e) -> e.getAge() > 100);
        employees.forEach(System.out::println);
    }

    public List<Employee> filterEmp(List<Employee> list, Predicate<Employee> predicate) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (predicate.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }
    // 员工列表
    List<Employee> employeeList = Arrays.asList(
            new Employee("泰兰德", 5000, 10000),
            new Employee("伊利丹", 10000, 666666),
            new Employee("奥蕾莉亚", 19, 55555),
            new Employee("希尔瓦娜斯", 30, 77777),
            new Employee("卡尔", 100, 22222),
            new Employee("温蕾萨", 19, 88888)
    );
}
