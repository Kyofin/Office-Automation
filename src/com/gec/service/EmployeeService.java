package com.gec.service;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.Employee;
import com.gec.dao.DaoFactory;

public class EmployeeService {
	public static boolean addEmployee(Employee employee)
	{
		 return DaoFactory.getEmpDao().addEmp(employee);
	}
	
	public static boolean IsHavEmpByCardId(String cardid) {
		return DaoFactory.getEmpDao().IsHavEmpByCardId(cardid);
	}
	
	public static void deleteApartById(String id)
	{
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(id));
		DaoFactory.getEmpDao().deleteEmployee(employee);
	}
	
	public static List<Employee> getEmployeeList()
	{
		return DaoFactory.getEmpDao().getEmpList();
	}
	
	public static List<Employee> getEmployeeListByLike(String jobid,String name,String strcardid,String sex,String phone,String departid)
	{
		return DaoFactory.getEmpDao().getEmpListByLike(jobid, name, strcardid, sex, phone, departid);
	}
}
