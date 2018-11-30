package com.sakura.designmode.impl;

import com.sakura.designmode.MyPredicate;
import com.sakura.pojo.Employee;

/**
 * @Author: Sakura
 * @Description: 年龄大于35
 * @Date: 2018/11/29 16:30
 */
public class FilterEmpByAge implements MyPredicate<Employee> {

    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() > 35;
    }
}
