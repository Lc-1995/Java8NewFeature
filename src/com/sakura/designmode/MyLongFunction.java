package com.sakura.designmode;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/30 11:25
 */
@FunctionalInterface
public interface MyLongFunction<T, R> {

    R getValue(T t1, T t2);
}
