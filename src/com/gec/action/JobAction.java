package com.gec.action;

import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gec.bean.Job;
import com.gec.bean.User;
import com.gec.dao.DaoFactory;
import com.gec.service.JobService;
import com.opensymphony.xwork2.ActionSupport;

public class JobAction extends ActionSupport
{
	private String  jobname;
	private String remark;
	private String[] jobid;
	private Integer id;
	private Job insertJob;
	
	
	
	
	public Job getInsertJob() {
		return insertJob;
	}
	public void setInsertJob(Job insertJob) {
		this.insertJob = insertJob;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String[] getJobid() {
		return jobid;
	}
	public void setJobid(String[] jobid) {
		this.jobid = jobid;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * ��ѯְλ(���)
	 */
	public String queryJob()
	{
		//Ĭ����ʾ����ְҵ
		List<Job> jobslist = JobService.getJobList();
		
		//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
		String jobname= getJobname();
		
		if (jobname!=null) {
			List<Job> qJobs= JobService.getJobListByLike(jobname);
			//���������������ʾ������
			jobslist = qJobs;
		}
		
		ServletActionContext.getRequest().setAttribute("jobslist", jobslist);
		
		return SUCCESS;
		
	}
	/**
	 * ɾ��ְλ(���)
	 */
	public String deleteJob()
	{
	
		String[] jobsId =getJobid();
		for (String string : jobsId) {
			JobService.deleteJobById(string);
		}
		
		return SUCCESS;
		
	}
	
	/**
	 * ���ְλ(���)
	 * @return
	 */
	public String addJob()
	{
		//��ȡ����
		String jobname = getJobname();
		String remark = getRemark();
		
		//��װinsertJob
		Job insertJob = new Job();
		insertJob.setMark(remark);
		insertJob.setName(jobname);
		
		//����JobService
		JobService.addJob(insertJob);
		
		//����ӳɹ��ķ�����¼��request��������
		ServletActionContext.getRequest().setAttribute("info", "�ɹ����");
		
		return SUCCESS;
	}
	
	/**
	 * ��ʼ���༭����(���)
	 * @return
	 */
	public String editorJob()
	{
		
		/*User user = new User();
		user.setId(getId());
		User editorUser = DaoFactory.getUserDao().getUserById(user);*/
		Job job = new Job();
		job.setId(getId());
		Job editorJob = DaoFactory.getJobDao().getJob(job);
		System.out.println(editorJob);
		ServletActionContext.getRequest().setAttribute("editorJob", editorJob);
		
		return SUCCESS;
	}
	
	/**
	 * ����ְҵ
	 * @return
	 *//*
	public String updateJob()
	{
	
		System.out.println(getInsertUser());
		
		User newUser = getInsertUser();
		
		DaoFactory.getUserDao().updateUser(newUser);
		
		
		return SUCCESS;
	}*/
	
	
}
	
