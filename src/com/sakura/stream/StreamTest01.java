package com.sakura.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: Sakura
 * @Date: 2018/12/3 9:35
 * @Description:
 * 1.创建Stream
 *
 * 2.中间操作
 *
 * 3.终止操作
 */
public class StreamTest01 {

    @Test
    public void createStream() {
        // 1.通过Collection系列提供的Stream()或者parallelStream()
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2.通过Arrays的静态方法获取数组流
        String[] strings = new String[20];
        Stream<String> stream1 = Arrays.stream(strings);

        // 3.通过Stream类的静态方法of()
        Stream<String> stream2 = Stream.of("a", "b", "c");

        // 4.创建无限流
        // 1)、迭代创建
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);

        // 2)、生成
        Stream<Double> stream4 = Stream.generate(() -> Math.random());


    }

}
