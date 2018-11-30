package com.sakura.lambda;

import com.sakura.designmode.MyFunction;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Author: Sakura
 * @Date: 2018/11/29 17:21
 * @Description:
 * 一、Lambda表达式基础语法:
 *      Java8中引入一个新的操作符"->" Lambda操作符,该操作符将Lambda表达式拆分为两部分:
 *      左侧:Lambda表达式的参数列表
 *      右侧:Lambda表达式需要执行的功能,即Lambda体
 *
 *  语法格式一:无参数,无返回值
 *          () -> System.out.println("Hello Lombda!");
 *
 *  语法格式二:有一个参数,并且无返回值
 *          (x) -> System.out.println(x);
 *          也可以不加括号
 *          x -> System.out.println(x);
 *
 *  语法格式三:有多个参数,并且有返回值,Lombda体中有多条语句
 *          Comparator<Integer> comparator = (x, y) -> {
 *             System.out.println("Hello Lombda!");
 *             return Integer.compare(x, y);
 *         };
 *         如果Lombda体中只有一条语句,可以省略{}和return
 *         Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 *
 *  语法格式四:Lombda表达式的参数列表的参数类型可以省略不写,因为JVM编译器可以通过上下文环境推断出类型
 *         (Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 二、Lambda表达式需要函数式接口
 *      函数式接口:接口中只有一个抽象方法的接口,可以使用 @FunctionalInterface 注解修饰检查是否是函数式接口
 */
public class LambdaTest02 {

    @Test
    public void test1() {
        Runnable runnable = () -> System.out.println("Hello Lombda!");
        runnable.run();
    }

    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hello Lombda!");
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("Hello Lombda!");
            return Integer.compare(x, y);
        };
        int compare = comparator.compare(1, 2);
        System.out.println(compare);
    }

    // ==============================================================

    // 1.需求:对一个数进行运算
    @Test
    public void test4() {
        Integer integer = operation(100, (x) -> x * x);
        System.out.println(integer);

        System.out.println(operation(20, (x) -> x + 10));
    }

    public Integer operation(Integer num, MyFunction myFunction) {
        return myFunction.getValue(num);
    }
}
