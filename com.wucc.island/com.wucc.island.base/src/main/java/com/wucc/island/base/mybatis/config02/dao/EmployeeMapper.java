package com.wucc.island.base.mybatis.config02.dao;


import com.wucc.island.base.mybatis.config02.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);

}
