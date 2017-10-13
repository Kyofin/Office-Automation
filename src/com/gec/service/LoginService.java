package com.gec.service;

import com.gec.bean.User;
import com.gec.dao.DaoFactory;
import com.gec.dao.UserDao;

public class LoginService {

	/**
	 * 验证登录用户
	 * @param loginUser
	 * @return
	 */
	public static boolean validate(User loginUser)
	{
		boolean isPass = DaoFactory.getUserDao().queryUserLogin(loginUser);
		return isPass;
	}
	
}
