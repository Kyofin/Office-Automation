package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.Employee;

public interface IEmployeeDao {

	// 增加员工
	public boolean addEmp(Employee	 employee);

	// 返回所有员工
	public List<Employee> getEmpList();
	
	//删除部门
	public boolean deleteEmployee(Employee employee);
	
	// 模糊搜索员工
	public List<Employee> getEmpListByLike(String jobid,String name,String strcardid,String sex,String phone,String departid);
	
	//通过身份证号检验是否存在
	public boolean IsHavEmpByCardId(String cardID);
}
