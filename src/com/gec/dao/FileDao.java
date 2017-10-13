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

import com.gec.Interfdao.IFileDao;
import com.gec.bean.Apart;
import com.gec.bean.FileBean;
import com.gec.bean.User;

public class FileDao extends BaseDao implements IFileDao{

	@Override
	public boolean addFile(FileBean fileBean) 
	{

		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("insert into document_inf(title,filename,remark,create_date,user_id,file) values(?,?,?,?,?,?)");
			
			//填入参数
			prstm.setString(1, fileBean.getTitle());
			prstm.setString(2,fileBean.getFileName());
			prstm.setString(3, fileBean.getRemark());
			prstm.setTimestamp(4, new Timestamp(new Date().getTime()));
			prstm.setInt(5, fileBean.getUserid());
			prstm.setBytes(6, fileBean.getFileBytes());
			
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
	public List<FileBean> getFileList() 
	{
		List<FileBean> fileList = new ArrayList<>();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select * from document_inf ");
			
			ResultSet resultSet= prstm.executeQuery();
			while(resultSet.next())
			{
				FileBean fileBean = new FileBean();
				fileBean.setId(resultSet.getInt(1));
				fileBean.setTitle(resultSet.getString(2));
				fileBean.setFileName(resultSet.getString(3));
				fileBean.setRemark(resultSet.getString(4));
				fileBean.setCreatedate(resultSet.getDate(5));
				fileBean.setUserid(resultSet.getInt(6));
				fileBean.setFileBytes(resultSet.getBytes(7));
				//获取文件创建人名
				User user = new User();
				user.setId(resultSet.getInt(6));
				fileBean.setUpusername(DaoFactory.getUserDao().getUserById(user).getUsername());
				fileList.add(fileBean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return fileList;
	}

	@Override
	public FileBean getFileBeanByID(FileBean fileBean) {

		FileBean outFileBean = new FileBean();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select * from document_inf where id = ?");
			
			//填入参数
			prstm.setInt(1, fileBean.getId());
			
			//执行语句
			ResultSet resultSet = prstm.executeQuery();
			while(resultSet.next())
			{
				outFileBean.setId(resultSet.getInt(1));
				outFileBean.setTitle(resultSet.getString(2));
				outFileBean.setFileName(resultSet.getString(3));
				outFileBean.setRemark(resultSet.getString(4));
				outFileBean.setCreatedate(resultSet.getDate(5));
				outFileBean.setUserid(resultSet.getInt(6));
				outFileBean.setFileBytes(resultSet.getBytes(7));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return outFileBean;	
	
	}

	@Override
	public List<FileBean> getFileListByLike(String name) 
	{

		List<FileBean> fileList = new ArrayList<>();
		Connection conn=null;
		Statement stm = null;
		try {
			conn = getConnection();
			stm = conn.createStatement();
			StringBuffer sqlBuf = new StringBuffer("select * from document_inf where 1=1 ");
			
			if (name!=null)
			{
				sqlBuf.append("and title like '%"+name+"%'");
			}
			
			ResultSet resultSet= stm.executeQuery(sqlBuf.toString());
			while(resultSet.next())
			{
				FileBean fileBean = new FileBean();
				fileBean.setId(resultSet.getInt(1));
				fileBean.setTitle(resultSet.getString(2));
				fileBean.setFileName(resultSet.getString(3));
				fileBean.setRemark(resultSet.getString(4));
				fileBean.setCreatedate(resultSet.getDate(5));
				fileBean.setUserid(resultSet.getInt(6));
				fileBean.setFileBytes(resultSet.getBytes(7));
				//获取文件创建人名
				User user = new User();
				user.setId(resultSet.getInt(6));
				fileBean.setUpusername(DaoFactory.getUserDao().getUserById(user).getUsername());
				fileList.add(fileBean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return fileList;
	
	}

	@Override
	public boolean deleteFile(FileBean fileBean) {
		String strid =String.valueOf(fileBean.getId());
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("delete from document_inf where ID = ?");
			
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
