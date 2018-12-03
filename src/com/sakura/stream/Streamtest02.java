package com.sakura.stream;

import com.sakura.pojo.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Sakura
 * @Description: 中间操作
 * @Date: 2018/12/3 11:14
 */
public class Streamtest02 {

    // 员工列表
    List<Employee> employeeList = Arrays.asList(
            new Employee("泰兰德", 5000, 10000),
            new Employee("伊利丹", 10000, 666666),
            new Employee("奥蕾莉亚", 19, 55555),
            new Employee("希尔瓦娜斯", 30, 77777),
            new Employee("卡尔", 100, 22222),
            new Employee("温蕾萨", 19, 88888),
            new Employee("温蕾萨", 19, 88888)
    );

    /**
     * 筛选与切片
     * filter--接收Lambda，过滤。
     * limit--截断流，使元素不超过指定的数量。
     * skip(n)--跳过元素，跳过前n个元素，返回之后的元素，如果总元素不足n个，返回空流。
     * distinct--筛选，通过流所生成的元素的hashCode()和equals()去除重复元素。
     */
    @Test
    public void test1() {
        employeeList.stream()
                .filter((x) -> x.getAge() > 90)
                .forEach(System.out::println);
    }

    @Test
    public void test2() {
        employeeList.stream()
                .limit(4)
                .forEach(System.out::println);
    }

    @Test
    public void test3() {
        employeeList.stream()
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        employeeList.stream()
                .distinct()
                .forEach(System.out::println);
    }

    // --------------------------------华丽丽的分割线--------------------------------

    /**
     * 映射
     * map--接收Lambda，将元素转化为其他形式或者提取信息，接收一个函数作为参数，该函数会应用到每个元素上，并映射成一个新的元素
     * flatMap--就收一个函数作为参数，将流中的每一个值都转化成另一个流，然后把所有流连接成一个流
     * map和flatMap像List类的add()和addAll()方法的区别，map是一个流中包含多个小流，flatMap是将所有流连接为一个流
     */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream()
                .map((x) -> x.toUpperCase())  // 每一个元素都会被作用到toUpperCase()函数上
                .forEach(System.out::println);

        employeeList.stream()
                .map(Employee::getName)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        Stream<Stream<Character>> stream = list.stream()
                .map(Streamtest02::filterCharater);
        // stream的数据格式大致是{{"a","a"}, {"b","b"}, {"c","c"}}的形式，一个大流包含许多小流
        // 所以需要两次遍历
        stream.forEach((x) -> x.forEach(System.out::println));

        System.out.println("----------------------------------");

        // 使用flatMap可以直接将多个小流连接为一个流{"a", "a", "b", "b", "c", "c"}
        list.stream()
                .flatMap(Streamtest02::filterCharater)
                .forEach(System.out::println);
    }

    // 需求：传入一个字符串，将字符提取出来放入List，并创建一个Stream流
    public static Stream<Character> filterCharater(String s) {
        List<Character> list = new ArrayList<>();
        for (Character character : s.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    // --------------------------------华丽丽的分割线--------------------------------

    /**
     * 排序
     * sorted()--自然排序(Comparable)
     * sorted()--定制排序(Comparator)
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("b", "f", "a", "c", "h");
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test8() {
        employeeList.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }

}
