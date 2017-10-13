package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.Employee;

public interface IEmployeeDao {

	// ����Ա��
	public boolean addEmp(Employee	 employee);

	// ��������Ա��
	public List<Employee> getEmpList();
	
	//ɾ������
	public boolean deleteEmployee(Employee employee);
	
	// ģ������Ա��
	public List<Employee> getEmpListByLike(String jobid,String name,String strcardid,String sex,String phone,String departid);
	
	//ͨ�����֤�ż����Ƿ����
	public boolean IsHavEmpByCardId(String cardID);
}
