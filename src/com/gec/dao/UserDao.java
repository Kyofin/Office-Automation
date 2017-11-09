package com.gec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.gec.Interfdao.IUserDao;
import com.gec.bean.User;
import com.gec.db.DataSourceUtil;

/**
 * @author ����
 *
 */
public class UserDao extends BaseDao implements IUserDao {
	
	
	
	/**
	 * ����û�
	 */
	@Override
	public boolean addUser(User user) 
	{
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("insert into user_inf(loginname,PASSWORD,username,STATUS) values(?,?,?,?)");
			
			//�������
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			prstm.setString(3, user.getUsername());
			prstm.setInt(4, user.getSTATUS());
			
			//ִ�����
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("��ӳɹ�"+pos+"������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;
	}

	/**
	 * ɾ���û�
	 */
	@Override
	public boolean deleteUserById(String strid) 
	{
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("delete from user_inf where ID = ?");
			
			//�������
			prstm.setInt(1, Integer.valueOf(strid));
			
			//ִ�����
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("ɾ���ɹ�"+pos+"������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;	
	}

	/**
	 * �����û���Ϣ
	 */
	@Override
	public boolean updateUser(User user)
	{
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("update  user_inf set loginname=?,PASSWORD=?,username=?,STATUS=? where ID = ?");
			
			//�������
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			prstm.setString(3, user.getUsername());
			prstm.setInt(4, user.getSTATUS());
			prstm.setInt(5, user.getId());
			
			//ִ�����
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("���³ɹ�"+pos+"������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;	
	}

	/**
	 * ��ȡ�����û�����
	 */
	@Override
	public List<User> getUserList() 
	{
		List<User> userList = new ArrayList<>();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select loginname,PASSWORD,username,STATUS,createdate,ID from user_inf ");
			
			ResultSet resultSet= prstm.executeQuery();
			while(resultSet.next())
			{
				User outUser = new User();
				outUser.setLoginname(resultSet.getString(1));
				outUser.setPASSWORD(resultSet.getString(2));
				outUser.setUsername(resultSet.getString(3));
				outUser.setSTATUS(resultSet.getInt(4));
				outUser.setCreatedate(resultSet.getTimestamp(5)); //�����⣿����
				outUser.setId(resultSet.getInt(6));
				userList.add(outUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return userList;
	}

	/**
	 * ��֤��¼�û�
	 */
	@Override
	public boolean queryUserLogin(User user) 
	{
		boolean flag = false;
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select loginname,PASSWORD from user_inf where loginname = ? and PASSWORD = ?");
			
			//�������
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			
			//ִ�����
			ResultSet resultSet = prstm.executeQuery();
			while(resultSet.next())
			{
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;	
	}

	@Override
	public User getUser(User user) 
	{
		User outUser = new User();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select * from user_inf where loginname = ? and PASSWORD = ?");
			
			//�������
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			
			//ִ�����
			ResultSet resultSet = prstm.executeQuery();
			while(resultSet.next())
			{
				outUser.setId(resultSet.getInt(1));
				outUser.setLoginname(resultSet.getString(2));
				outUser.setPASSWORD(resultSet.getString(3));
				outUser.setSTATUS(resultSet.getInt(4));
				outUser.setCreatedate(resultSet.getTimestamp(5));
				outUser.setUsername(resultSet.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return outUser;	
	}

	
	/**
	 * ͨ��mybatis�����ļ���ȡ�û�����
	 */
	@Override
	public List<User> getUserListByMybatis(String name,Integer status) 
	{
		List<User> userList = new ArrayList<>();
		User paramUser = new User();
		paramUser.setUsername(name);
		paramUser.setSTATUS(status);
		SqlSession sqlSession= DataSourceUtil.getSqlSession();
		userList = sqlSession.selectList("User.queryUserList",paramUser);
		return userList;
	}
	
	
	
	
	
	
	
	
	//ͨ������������ȡlist
	@Override
	public List<User> queryUserListByLike(String name,String status) {
		List<User> userList = new ArrayList<>();
		Connection conn=null;
		Statement stm = null;
		try {
			conn = getConnection();
			 stm = conn.createStatement();
			StringBuffer sqlBuf = new StringBuffer("select loginname,PASSWORD,username,STATUS,createdate,ID from user_inf where 1=1 ");
			if (!name.equals(""))
			{
				sqlBuf.append("and username like '%"+name+"%'");
			}
			
			if (!status.equals(""))
			{
				sqlBuf.append("and status like '%"+status+"%'");
			}

			ResultSet resultSet= stm.executeQuery(sqlBuf.toString());
			while(resultSet.next())
			{
				User outUser = new User();
				outUser.setLoginname(resultSet.getString(1));
				outUser.setPASSWORD(resultSet.getString(2));
				outUser.setUsername(resultSet.getString(3));
				outUser.setSTATUS(resultSet.getInt(4));
				outUser.setCreatedate(resultSet.getTimestamp(5)); //�����⣿����
				outUser.setId(resultSet.getInt(6));
				userList.add(outUser);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return userList;
	}

	@Override
	public User getUserById(User user) 
	{

		User outUser = new User();
		Connection conn=null;
		PreparedStatement prstm = null;
		try {
			conn = getConnection();
			prstm = conn.prepareStatement("select * from user_inf where id = ?");
			
			//�������
			prstm.setInt(1, user.getId());

			
			//ִ�����
			ResultSet resultSet = prstm.executeQuery();
			while(resultSet.next())
			{
				outUser.setId(resultSet.getInt(1));
				outUser.setLoginname(resultSet.getString(2));
				outUser.setPASSWORD(resultSet.getString(3));
				outUser.setSTATUS(resultSet.getInt(4));
				outUser.setCreatedate(resultSet.getTimestamp(5));
				outUser.setUsername(resultSet.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return outUser;	
	
	}

}
