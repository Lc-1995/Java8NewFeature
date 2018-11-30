package com.sakura.designmode.impl;

import com.sakura.designmode.MyPredicate;
import com.sakura.pojo.Employee;

/**
 * @Author: Sakura
 * @Description: 工资大于50000
 * @Date: 2018/11/29 16:38
 */
public class FilterEmpBySalary implements MyPredicate<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() > 50000;
    }
}
