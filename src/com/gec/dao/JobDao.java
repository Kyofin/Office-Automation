package com.gec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gec.Interfdao.IJobDao;
import com.gec.bean.Apart;
import com.gec.bean.Job;

public class JobDao extends BaseDao implements IJobDao{

	@Override
	public boolean addJob(Job job) {

		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("insert into job_inf(name,remark) values(?,?)");
			
			//填入参数
			prstm.setString(1, job.getName());
			prstm.setString(2, job.getMark());
		
			
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
	public boolean deleteJob(Job job) {
		String strid =String.valueOf(job.getId());
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("delete from job_inf where ID = ?");
			
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

	@Override
	public boolean updateJob(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Job> getJobList() {

		List<Job> jobList = new ArrayList<>();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select NAME,REMARK,ID from job_inf ");
			
			ResultSet resultSet= prstm.executeQuery();
			while(resultSet.next())
			{
				Job job = new Job();
				job.setName(resultSet.getString(1));
				job.setMark(resultSet.getString(2));
				job.setId(resultSet.getInt(3));
				jobList.add(job);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return jobList;
	}

	@Override
	public List<Job> queryJobListByLike(String jobname) 
	{
		List<Job> jobList = new ArrayList<>();
		Connection conn=null;
		Statement stm = null;
		try {
			conn = getConnection();
			stm = conn.createStatement();
			StringBuffer sqlBuf = new StringBuffer("select NAME,REMARK,ID from job_inf where 1=1 ");
			
			if (!jobname.equals(""))
			{
				sqlBuf.append("and name like '%"+jobname+"%'");
			}
			
			ResultSet resultSet= stm.executeQuery(sqlBuf.toString());
			while(resultSet.next())
			{
				Job job = new Job();
				job.setName(resultSet.getString(1));
				job.setMark(resultSet.getString(2));
				job.setId(resultSet.getInt(3));
				jobList.add(job);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return jobList;
	
	}

	@Override
	public Job getJob(Job job) 
	{

		Job outJob = new  Job();
		Connection conn=null;
		PreparedStatement pre =null;
		conn=getConnection();
		try {
			pre = conn.prepareStatement("select * from job_inf where id = ? ");
			pre.setInt(1, job.getId());
			ResultSet resultSet = pre.executeQuery();
			while(resultSet.next())
			{
				outJob.setId(resultSet.getInt(1));
				outJob.setName(resultSet.getString(2));
				outJob.setMark(resultSet.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return outJob;
	
	}
	

}
