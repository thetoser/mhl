package com.zhljava.mhl.service;

import com.zhljava.mhl.dao.EmployeeDAO;
import com.zhljava.mhl.domain.Employee;

/**
 * 完成对employee表的各种操作(通过调用EmployeeDAO对象完成)
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //根据empId 和 pwd 返回一个Employee对象
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {

        String sql = "select * from employee where empId = ? and pwd = md5(?)";
        return employeeDAO.querySingle(sql, Employee.class, empId, pwd);
    }

}
