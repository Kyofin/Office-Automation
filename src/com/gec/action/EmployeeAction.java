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
	 * �����Ա��ҳ��
	 * @return
	 */
	public String toAddEmployeePage()
	{
		//��ʼ��������
		//��ȡȫ��ְλ
		List<Job> jobs = JobService.getJobList();
		setJobs(jobs);
		//��ȡȫ������
		List<Apart> departs = ApartService.getApartList();
		setDeparts(departs);
		return SUCCESS;
	}
	
	/**
	 * ���Ա��
	 * @return
	 * ���֤�����������ݳ־û�δ���
	 */
	public String addEmployee()
	{
		System.out.println(getInsertEmployee());
		
		//����EmployeeService
		if(EmployeeService.addEmployee(insertEmployee))
		{
			//����ӳɹ��ķ�����¼��request��������
			ServletActionContext.getRequest().setAttribute("info", "�ɹ����");
			
		}
		return SUCCESS;
	}
	
	
	/**
	 * ��ѯԱ��
	 * @return
	 */
	public String queryEmployee()
	{
		//Ĭ����ʾ����Ա��
		List<Employee> employeeslist = EmployeeService.getEmployeeList();
		//��ȡȫ��ְλ
		List<Job> jobs = JobService.getJobList();
		setJobs(jobs);
		//��ȡȫ������
		List<Apart> departs = ApartService.getApartList();
		setDeparts(departs);
		
		//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
		String jobid= getJob();
		String name = getName();
		String strcardid = getCardid();
		String sex = getSex();
		String phone = getPhone();
		String departid =getDepart();
		
		if(jobid!=null&&name!=null&&strcardid!=null&&sex!=null&&phone!=null&&departid!=null) 
		{
			List<Employee> qEmployees= EmployeeService.getEmployeeListByLike(jobid, name, strcardid, sex, phone, departid);
			//���������������ʾ������
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
	 * ��֤���֤
	 * @return
	 */
	public String checkcard()
	{
		//ͨ��AJAX�������֤���Ƿ��Ѵ���
		String card = getCard();//�ύ�����֤�Ų���
		boolean pos = EmployeeService.IsHavEmpByCardId(card);
		
		if (pos)
		{
			setPos("���֤�Ѵ��ڣ�����������");//������Ӧ
		}else 
		{
			setPos("���֤��֤ͨ��");//������Ӧ
		}
		
		return SUCCESS;
	}
}
