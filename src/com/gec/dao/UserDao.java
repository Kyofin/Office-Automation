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
 * @author 公子
 *
 */
public class UserDao extends BaseDao implements IUserDao {
	
	
	
	/**
	 * 添加用户
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
			
			//填入参数
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			prstm.setString(3, user.getUsername());
			prstm.setInt(4, user.getSTATUS());
			
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

	/**
	 * 删除用户
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

	/**
	 * 更改用户信息
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
			
			//填入参数
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			prstm.setString(3, user.getUsername());
			prstm.setInt(4, user.getSTATUS());
			prstm.setInt(5, user.getId());
			
			//执行语句
			int pos = prstm.executeUpdate();
			if (pos>0)
			{
				flag=true;
				System.out.println("更新成功"+pos+"条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		
		return flag;	
	}

	/**
	 * 获取所有用户资料
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
				outUser.setCreatedate(resultSet.getTimestamp(5)); //有问题？？？
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
	 * 验证登录用户
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
			
			//填入参数
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			
			//执行语句
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
			
			//填入参数
			prstm.setString(1, user.getLoginname());
			prstm.setString(2, user.getPASSWORD());
			
			//执行语句
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
	 * 通过mybatis配置文件获取用户集合
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
	
	
	
	
	
	
	
	
	//通过检索条件获取list
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
				outUser.setCreatedate(resultSet.getTimestamp(5)); //有问题？？？
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
			
			//填入参数
			prstm.setInt(1, user.getId());

			
			//执行语句
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
