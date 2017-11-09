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
	 * ���е�¼
	 */
	public String dologin() {
		System.out.println("ִ��dologin");
		// ��ȡ����
		String loginName = getLoginName();
		String password = getPassword();

		// ��װuserbean
		User loginUser = new User();
		loginUser.setLoginname(loginName);
		loginUser.setPASSWORD(password);

		// ��֤��¼��Ϣ
		boolean isPass = LoginService.validate(loginUser);

		// ת����ͼ
		if (isPass) {
			// ��õ�¼�û���ȫ����Ϣ��֯��user����
			loginUser = DaoFactory.getUserDao().getUser(loginUser);
			// ����LoginUser����session��
			ActionContext.getContext().getSession().put("loginUser", loginUser);
			// ��ת��QueryUser
			return SUCCESS;
		} else {
			addFieldError("password", "�û����������");
			return INPUT;
		}
	}
	
	
	
	public String doLogout() 
	{
		System.out.println("doLogout");
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if (session!=null)
		{
			//ע���Ự
			session.invalidate();
		}
		return SUCCESS;
		
	}
	
	

	
}
