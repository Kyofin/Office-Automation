package com.gec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gec.Interfdao.IEmployeeDao;
import com.gec.bean.Apart;
import com.gec.bean.Employee;
import com.gec.bean.Job;

public class EmployeeDao extends BaseDao implements IEmployeeDao{

	@Override
	public boolean addEmp(Employee employee) 
	{


		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			String sql ="insert into employee_inf values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prstm = conn.prepareStatement(sql);
			
			//填入参数
			prstm.setInt(1, employee.getDepartid());
			prstm.setInt(2, employee.getJobid())	;
			prstm.setString(3, employee.getName());
			prstm.setString(4, employee.getCardid());
			prstm.setString(5, employee.getAddress());
			prstm.setString(6, employee.getPostcode());
			prstm.setString(7, employee.getTel());
			prstm.setString(8, employee.getPhone());
			prstm.setString(9, employee.getQq());
			prstm.setString(10, employee.getEmail());
			prstm.setString(11, employee.getSex());
			prstm.setString(12, employee.getParty());
			prstm.setDate(13, new java.sql.Date(employee.getBirthday().getTime()) );
			prstm.setString(14, employee.getRace());
			prstm.setString(15, employee.getEducation());
			prstm.setString(16, employee.getSpeciality());
			prstm.setString(17, employee.getHobby());
			prstm.setString(18, employee.getRemark());
			prstm.setTimestamp(19, new Timestamp(new Date().getTime()));
			
			
			//执行语句
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("添加成功"+pos+"条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;
	
	
	}

	@Override
	public List<Employee> getEmpList() 
	{
		List<Employee> employeeList = new ArrayList<>();
		Connection conn = null;
		Statement stam = null;
		try {
			conn = getConnection();
			stam = conn.createStatement();
			String sql = "SELECT\r\n" + 
					"e.ID,\r\n" + 
					"e.DEPT_ID,\r\n" + 
					"e.JOB_ID,\r\n" + 
					"e.`NAME`,\r\n" + 
					"e.CARD_ID,\r\n" + 
					"e.ADDRESS,\r\n" + 
					"e.POST_CODE,\r\n" + 
					"e.TEL,\r\n" + 
					"e.PHONE,\r\n" + 
					"e.QQ_NUM,\r\n" + 
					"e.EMAIL,\r\n" + 
					"e.SEX,\r\n" + 
					"e.PARTY,\r\n" + 
					"e.BIRTHDAY,\r\n" + 
					"e.RACE,\r\n" + 
					"e.EDUCATION,\r\n" + 
					"e.SPECIALITY,\r\n" + 
					"e.HOBBY,\r\n" + 
					"e.REMARK,\r\n" + 
					"e.CREATE_DATE,\r\n" + 
					"j.`NAME` AS jobname,\r\n" + 
					"d.`NAME` AS deptname\r\n" + 
					"from employee_inf e,job_inf j,dept_inf d\r\n" + 
					"where e.job_id = j.id and e.DEPT_ID = d.ID";
			ResultSet resultSet = stam.executeQuery(sql);
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setDepartid(resultSet.getInt(2));
				employee.setJobid(resultSet.getInt(3));
				employee.setName(resultSet.getString(4));
				employee.setCardid(resultSet.getString(5));
				employee.setAddress(resultSet.getString(6));
				employee.setPostcode(resultSet.getString(7));
				employee.setTel(resultSet.getString(8));
				employee.setPhone(resultSet.getString(9));
				employee.setQq(resultSet.getString("QQ_NUM"));
				employee.setEmail(resultSet.getString("EMAIL"));
				employee.setSex(resultSet.getString("SEX"));
				employee.setParty(resultSet.getString("PARTY"));
				employee.setBirthday(resultSet.getDate("BIRTHDAY"));
				employee.setRace(resultSet.getString("RACE"));
				employee.setEducation(resultSet.getString("EDUCATION"));
				employee.setSpeciality(resultSet.getString("SPECIALITY"));
				employee.setHobby(resultSet.getString("HOBBY"));
				employee.setRemark(resultSet.getString("REMARK"));
				employee.setCreatedate(resultSet.getDate("CREATE_DATE"));
				employee.setDeptname(resultSet.getString("deptname"));
				employee.setJobname(resultSet.getString("jobname"));
				
				
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return employeeList;
	}

	@Override
	public boolean IsHavEmpByCardId(String	cardId) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn= getConnection();
			pre =conn.prepareStatement("select * from employee_inf where card_id = ?");
			pre.setString(1, cardId);
			ResultSet resultSet = pre.executeQuery();
			while(resultSet.next())
			{
				flag=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return flag;
	}

	@Override
	public List<Employee> getEmpListByLike(String jobid,String name,String strcardid,String sex,String phone,String departid) 
	{

		List<Employee> employeeList = new ArrayList<>();
		Connection conn = null;
		Statement stam = null;
		try {
			conn = getConnection();
			stam = conn.createStatement();
			StringBuffer sqlBuf = new StringBuffer("select * from employee_inf where 1=1 ");
			
			if (!jobid.equals(""))
			{
				sqlBuf.append(" and job_id like '%"+jobid+"%'");
			}
			if (!name.equals(""))
			{
				sqlBuf.append(" and name like '%"+name+"%'");
			}
			if (!strcardid.equals(""))
			{
				sqlBuf.append(" and card_id like '%"+strcardid+"%'");
			}
			if (!sex.equals(""))
			{
				sqlBuf.append(" and sex like '%"+sex+"%'");
			}
			if (!phone.equals(""))
			{
				sqlBuf.append(" and phone like '%"+phone+"%'");
			}
			if (!departid.equals(""))
			{
				sqlBuf.append(" and dept_id like '%"+departid+"%'");
			}
			System.out.println(sqlBuf.toString());
			ResultSet resultSet = stam.executeQuery(sqlBuf.toString());
			while(resultSet.next())
			{
				Employee employee = new Employee();
				employee.setId(resultSet.getInt(1));
				employee.setDepartid(resultSet.getInt(2));
				employee.setJobid(resultSet.getInt(3));
				employee.setName(resultSet.getString(4));
				employee.setCardid(resultSet.getString(5));
				employee.setAddress(resultSet.getString(6));
				employee.setPostcode(resultSet.getString(7));
				employee.setTel(resultSet.getString(8));
				employee.setPhone(resultSet.getString(9));
				employee.setQq(resultSet.getString("QQ_NUM"));
				employee.setEmail(resultSet.getString("EMAIL"));
				employee.setSex(resultSet.getString("SEX"));
				employee.setParty(resultSet.getString("PARTY"));
				employee.setBirthday(resultSet.getDate("BIRTHDAY"));
				employee.setRace(resultSet.getString("RACE"));
				employee.setEducation(resultSet.getString("EDUCATION"));
				employee.setSpeciality(resultSet.getString("SPECIALITY"));
				employee.setHobby(resultSet.getString("HOBBY"));
				employee.setRemark(resultSet.getString("REMARK"));
				employee.setCreatedate(resultSet.getDate("CREATE_DATE"));
				//获取员工对应的部门名称
				Apart apart = new Apart();
				apart.setId(resultSet.getInt(2));
				employee.setDeptname(DaoFactory.getApartDao().getApart(apart).getName());
				//获取员工对应的职位
				Job job = new Job();
				job.setId(resultSet.getInt(3));
				employee.setJobname(DaoFactory.getJobDao().getJob(job).getName());
				
				System.out.println(employee);
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return employeeList;
	
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		String strid =String.valueOf(employee.getId());
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("delete from employee_inf where ID = ?");
			
			//填入参数
			prstm.setInt(1, Integer.valueOf(strid));
			
			//执行语句
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("删除成功"+pos+"条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;	
	
	}

}
