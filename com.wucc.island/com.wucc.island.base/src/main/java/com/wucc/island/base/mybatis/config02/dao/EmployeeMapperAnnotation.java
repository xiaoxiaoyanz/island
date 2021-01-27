package com.wucc.island.base.mybatis.config02.dao;

import com.wucc.island.base.mybatis.config02.bean.Employee;
import org.apache.ibatis.annotations.Select;



public interface EmployeeMapperAnnotation {
	
	@Select("select * from tbl_employee where id=#{id}")
	public Employee getEmpById(Integer id);
}
