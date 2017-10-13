package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.Job;

public interface IJobDao {


		//����ְλ
		public boolean addJob(Job job);
		
		//ɾ��ְλ
		public boolean deleteJob(Job job);
		
		//�����û���Ϣ
		public boolean updateJob(Job job);
		
		//��������ְλ
		public List<Job> getJobList();
		
		
		//ģ����ѯְλ
		public List<Job> queryJobListByLike(String job);
		
		//��ѯְλ��Ϣ
		public Job getJob(Job job);
}
