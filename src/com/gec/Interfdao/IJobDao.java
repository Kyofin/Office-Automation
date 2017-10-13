package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.Job;

public interface IJobDao {


		//增加职位
		public boolean addJob(Job job);
		
		//删除职位
		public boolean deleteJob(Job job);
		
		//更新用户信息
		public boolean updateJob(Job job);
		
		//返回所有职位
		public List<Job> getJobList();
		
		
		//模糊查询职位
		public List<Job> queryJobListByLike(String job);
		
		//查询职位信息
		public Job getJob(Job job);
}
