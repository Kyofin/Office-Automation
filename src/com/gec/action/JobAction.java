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
	 * 查询职位(完成)
	 */
	public String queryJob()
	{
		//默认显示所有职业
		List<Job> jobslist = JobService.getJobList();
		
		//获取搜索或删除的参数(搜素和删除的逻辑)
		String jobname= getJobname();
		
		if (jobname!=null) {
			List<Job> qJobs= JobService.getJobListByLike(jobname);
			//将搜索结果放入显示集合中
			jobslist = qJobs;
		}
		
		ServletActionContext.getRequest().setAttribute("jobslist", jobslist);
		
		return SUCCESS;
		
	}
	/**
	 * 删除职位(完成)
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
	 * 添加职位(完成)
	 * @return
	 */
	public String addJob()
	{
		//获取参数
		String jobname = getJobname();
		String remark = getRemark();
		
		//组装insertJob
		Job insertJob = new Job();
		insertJob.setMark(remark);
		insertJob.setName(jobname);
		
		//调用JobService
		JobService.addJob(insertJob);
		
		//将添加成功的反馈记录到request的属性中
		ServletActionContext.getRequest().setAttribute("info", "成功添加");
		
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑界面(完成)
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
	 * 更新职业
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
	
