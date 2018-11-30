package com.sakura.designmode;

/**
 * @Author: Sakura
 * @Description: 设计过滤条件接口
 * @Date: 2018/11/29 16:25
 */
@FunctionalInterface
public interface MyPredicate<T> {

    boolean filter(T t);
}
