package com.gec.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gec.bean.User;
import com.gec.dao.DaoFactory;
import com.gec.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String loginName;
	private String password;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 进行登录
	 */
	public String dologin() {
		System.out.println("执行dologin");
		// 获取参数
		String loginName = getLoginName();
		String password = getPassword();

		// 组装userbean
		User loginUser = new User();
		loginUser.setLoginname(loginName);
		loginUser.setPASSWORD(password);

		// 验证登录信息
		boolean isPass = LoginService.validate(loginUser);

		// 转发试图
		if (isPass) {
			// 获得登录用户的全部信息组织成user对象
			loginUser = DaoFactory.getUserDao().getUser(loginUser);
			// 将该LoginUser放入session中
			ActionContext.getContext().getSession().put("loginUser", loginUser);
			// 跳转到QueryUser
			return SUCCESS;
		} else {
			addFieldError("password", "用户或密码错误");
			return INPUT;
		}
	}
	
	
	
	public String doLogout() 
	{
		System.out.println("doLogout");
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if (session!=null)
		{
			//注销会话
			session.invalidate();
		}
		return SUCCESS;
		
	}
	
	

	
}
