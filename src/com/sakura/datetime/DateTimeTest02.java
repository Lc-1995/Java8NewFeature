package com.sakura.datetime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @Author: Sakura
 * @Description: jdk1.8的时间是静态线程安全的，每一个都是新的实例
 * @Date: 2018/12/4 15:00
 */
public class DateTimeTest02 {

    // 本地时间
    // LocalDate  LocalTime  LocalDateTime
    @Test
    public void test1() {
        // 获取实例
        final LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        // 构造实例
        final LocalDateTime dateTime1 = LocalDateTime.of(2017, 12, 12, 12, 12);
        System.out.println(dateTime1);
        // 操作时间
        System.out.println(dateTime.plusYears(3));
        // 获取年月日时分秒
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getHour());
    }

    // Instant:时间戳（以Unix元年：1970-1-1 00:00:00到指定时间之间的毫秒数）
    @Test
    public void test2() {
        final Instant dateTime = Instant.now();// 默认获取的UTC时区
        System.out.println(dateTime);
        final OffsetDateTime offsetDateTime = dateTime.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println("获取毫秒值:" + dateTime.toEpochMilli());
    }

    // Duration：计算两个时间的间隔
    // Period：计算两个日期的间隔
    @Test
    public void test3() {
        final Instant dateTime = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Instant dateTime2 = Instant.now();
        final Duration duration = Duration.between(dateTime, dateTime2);
        System.out.println(duration.toMillis());

        System.out.println("------------------------");

        final LocalDate localDate = LocalDate.now();
        final LocalDate date = LocalDate.of(2016, 12, 12);
        final Period period = Period.between(date, localDate);
        System.out.println(period.getYears());
    }

    // TemporalAdjuster：时间校正器
    @Test
    public void test4() {
        final LocalDateTime now = LocalDateTime.now();
        // 指定当前时间的日期
        final LocalDateTime localDateTime = now.withDayOfMonth(1);
        System.out.println(localDateTime);
        // 下一个周日的日期
        final LocalDateTime dateTime = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(dateTime);

        // 自定义校正器：查找下一个工作日日期
        final LocalDateTime nextDateTime = now.with((day) -> {
            // 需要转换类型
            LocalDateTime time = (LocalDateTime) day;
            // 获取当天是周几
            final DayOfWeek dayOfWeek = time.getDayOfWeek();
            // 如果是周五则下个工作是三天后
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return time.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return time.plusDays(2);
            } else {
                return time.plusDays(1);
            }
        });
        System.out.println("下个工作日:" + nextDateTime);
    }

    // DateTimeFormatter：格式化日期时间
    @Test
    public void test5() {
        final LocalDateTime dateTime = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        final String format = dateTime.format(formatter);
        System.out.println(format);

        // 自定义格式化
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        final String format1 = dateTimeFormatter.format(dateTime);
        System.out.println(format1);
    }
}
