package com.gec.service;

import java.util.List;

import com.gec.bean.Job;
import com.gec.dao.DaoFactory;

public class JobService {

	public static List<Job> getJobList()
	{
		return DaoFactory.getJobDao().getJobList();
	}
	
	public static void addJob(Job job)
	{
		DaoFactory.getJobDao().addJob(job);
	}
	
	public static void deleteJobById(String id)
	{
		Job job = new Job();
		job.setId(Integer.parseInt(id));
		DaoFactory.getJobDao().deleteJob(job);
	}
	
	public static List<Job> getJobListByLike(String jobName)
	{
		return DaoFactory.getJobDao().queryJobListByLike(jobName);
	}
}
