package com.gec.service;

import java.util.Date;
import java.util.List;

import com.gec.bean.User;
import com.gec.dao.DaoFactory;
import com.gec.dao.UserDao;

public class UserService {

	public static void insertUserToDB(User user) {
		DaoFactory.getUserDao().addUser(user);
	}
	
	public static void deleteUser(String id)
	{
		DaoFactory.getUserDao().deleteUserById(id);
	}
	
	public static List<User> getUsersByLike(String name,String status)
	{
		List<User> users =null;
		
		users= DaoFactory.getUserDao().getUserListByMybatis(name, null);
		
		if (status!=null&&!status.trim().equals(""))
		{
			//users= DaoFactory.getUserDao().queryUserListByLike(name,status);
			users= DaoFactory.getUserDao().getUserListByMybatis(name, Integer.valueOf(status));
		}
	
		
		return users;
	}
	
	public static User getUserById(User user)
	{
		return DaoFactory.getUserDao().getUserById(user);
	}
}
