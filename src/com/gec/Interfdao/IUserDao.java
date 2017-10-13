package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.User;

public interface IUserDao {
	
	//增加用户
	public boolean addUser(User user);
	
	//删除用户
	public  boolean deleteUserById(String strid);
	
	//更新用户信息
	public boolean updateUser(User user);
	
	//返回所有用户
	public List<User> getUserList();
	
	//验证用户在数据库有记录
	public boolean queryUserLogin(User user);
	
	//模糊查询用户
	public List<User> queryUserListByLike(String name, String status);
	
	//查询用户个人信息
	public User getUser(User user);
	
	//查询用户个人信息通过其ID
	public User getUserById(User user);
	
	
	public List<User> getUserListByMybatis(String name,Integer status) ;

	
}
