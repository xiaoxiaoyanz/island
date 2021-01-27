package com.wucc.island.base.mybatis.helloworld01.dao;


import com.wucc.island.base.mybatis.helloworld01.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);

}
