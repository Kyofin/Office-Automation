package com.gec.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gec.bean.Apart;
import com.gec.bean.Employee;
import com.gec.bean.Job;
import com.gec.service.ApartService;
import com.gec.service.EmployeeService;
import com.gec.service.JobService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport
{
	private List<Job> jobs;
	private List<Apart> departs;
	private Employee insertEmployee;
	private String job;
	private String name;
	private String cardid;
	private String sex;
	private String phone;
	private String depart;
	private String card;
	private String pos;
	private String[] EmpsId;
	
	
	
	
	
	
	

	public String[] getEmpsId() {
		return EmpsId;
	}

	public void setEmpsId(String[] empsId) {
		EmpsId = empsId;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Employee getInsertEmployee() {
		return insertEmployee;
	}

	public void setInsertEmployee(Employee insertEmployee) {
		this.insertEmployee = insertEmployee;
	}

	public List<Apart> getDeparts() {
		return departs;
	}

	public void setDeparts(List<Apart> departs) {
		this.departs = departs;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	/**
	 * 打开添加员工页面
	 * @return
	 */
	public String toAddEmployeePage()
	{
		//初始化表单数据
		//获取全部职位
		List<Job> jobs = JobService.getJobList();
		setJobs(jobs);
		//获取全部部门
		List<Apart> departs = ApartService.getApartList();
		setDeparts(departs);
		return SUCCESS;
	}
	
	/**
	 * 添加员工
	 * @return
	 * 身份证，出生，数据持久化未完成
	 */
	public String addEmployee()
	{
		System.out.println(getInsertEmployee());
		
		//调用EmployeeService
		if(EmployeeService.addEmployee(insertEmployee))
		{
			//将添加成功的反馈记录到request的属性中
			ServletActionContext.getRequest().setAttribute("info", "成功添加");
			
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询员工
	 * @return
	 */
	public String queryEmployee()
	{
		//默认显示所有员工
		List<Employee> employeeslist = EmployeeService.getEmployeeList();
		//获取全部职位
		List<Job> jobs = JobService.getJobList();
		setJobs(jobs);
		//获取全部部门
		List<Apart> departs = ApartService.getApartList();
		setDeparts(departs);
		
		//获取搜索或删除的参数(搜素和删除的逻辑)
		String jobid= getJob();
		String name = getName();
		String strcardid = getCardid();
		String sex = getSex();
		String phone = getPhone();
		String departid =getDepart();
		
		if(jobid!=null&&name!=null&&strcardid!=null&&sex!=null&&phone!=null&&departid!=null) 
		{
			List<Employee> qEmployees= EmployeeService.getEmployeeListByLike(jobid, name, strcardid, sex, phone, departid);
			//将搜索结果放入显示集合中
			employeeslist = qEmployees;
		}
		
		
		ServletActionContext.getRequest().setAttribute("employeeslist", employeeslist);
		return SUCCESS;
	}
	
	
	
	public String deleteEmployee()
	{
		String[] EmpsId = getEmpsId();
		for (String string : EmpsId) {
			EmployeeService.deleteApartById(string);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 验证身份证
	 * @return
	 */
	public String checkcard()
	{
		//通过AJAX检验身份证号是否已存在
		String card = getCard();//提交的身份证号参数
		boolean pos = EmployeeService.IsHavEmpByCardId(card);
		
		if (pos)
		{
			setPos("身份证已存在，请重新输入");//发送响应
		}else 
		{
			setPos("身份证验证通过");//发送响应
		}
		
		return SUCCESS;
	}
}
