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
	 * ��ѯ�û�(���)
	 */
	public String queryUser()
	{
		//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
		String name= getUsername();
		String status = getUserStatus();
		
		//Ĭ����ʾ�����û�
		List<User> userlist = UserService.getUsersByLike(name, status);
		
		//���û����ϱ��浽request��ת����ҳ��
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		
		return SUCCESS;
		
	}
	/**
	 * ɾ���û�(���)
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
	 * ����û�+�����ϢУ��(���)
	 * @return
	 */
	public String addUser()
	{
		System.out.println(insertUser);
		
		//����userService
		UserService.insertUserToDB(insertUser);
		
		
		ServletActionContext.getRequest().setAttribute("info", "�ɹ����");
		
		return SUCCESS;
	}
	
	/**
	 * ��ʼ���༭����
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
	 * �����û�
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
