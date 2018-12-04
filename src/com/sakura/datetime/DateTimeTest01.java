package com.sakura.datetime;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: Sakura
 * @Description: jdk1.7的时间是变量导致线程不安全
 * @Date: 2018/12/4 14:47
 */
public class DateTimeTest01 {

    public static void main(String[] args) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return dateFormat.parse("2018124");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add(pool.submit(callable));
        }
        for (Future<Date> future : result) {
            System.out.println(future);
        }
        pool.shutdown();
    }

}
