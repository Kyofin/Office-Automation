package com.gec.action;

import java.applet.AppletContext;
import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gec.bean.User;
import com.gec.dao.DaoFactory;
import com.gec.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport
{
	private String username;
	private String userStatus;
	private String[] userid;
	private User insertUser;
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(User insertUser) {
		this.insertUser = insertUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	public String[] getUserid() {
		return userid;
	}

	public void setUserid(String[] userid) {
		this.userid = userid;
	}

	/**
	 * 查询用户(完成)
	 */
	public String queryUser()
	{
		//获取搜索或删除的参数(搜素和删除的逻辑)
		String name= getUsername();
		String status = getUserStatus();
		
		//默认显示所有用户
		List<User> userlist = UserService.getUsersByLike(name, status);
		
		//把用户集合保存到request中转发到页面
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		
		return SUCCESS;
		
	}
	/**
	 * 删除用户(完成)
	 */
	public String deleteUser()
	{
		String[] usersId = getUserid();
		
		for (String string : usersId) {
			System.out.println(string);
			UserService.deleteUser(string);
		}
			
		return SUCCESS;
		
	}
	
	/**
	 * 添加用户+添加信息校验(完成)
	 * @return
	 */
	public String addUser()
	{
		System.out.println(insertUser);
		
		//调用userService
		UserService.insertUserToDB(insertUser);
		
		
		ServletActionContext.getRequest().setAttribute("info", "成功添加");
		
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑界面
	 * @return
	 */
	public String editorUser()
	{
		
		User user = new User();
		user.setId(getId());
		User editorUser = DaoFactory.getUserDao().getUserById(user);
		ServletActionContext.getRequest().setAttribute("editorUser", editorUser);;
		
		return SUCCESS;
	}
	
	/**
	 * 更新用户
	 * @return
	 */
	public String updateUser()
	{
	
		System.out.println(getInsertUser());
		
		User newUser = getInsertUser();
		
		DaoFactory.getUserDao().updateUser(newUser);
		
		
		return SUCCESS;
	}
	
	
}
