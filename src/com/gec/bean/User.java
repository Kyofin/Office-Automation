package com.gec.bean;
/*#创建表user_inf（用户表）STATUS=1，用户是管理员，否则是普通用户
CREATE TABLE user_inf (
  ID INT(11) NOT NULL AUTO_INCREMENT,	//用户id
  loginname VARCHAR(20) NOT NULL, //登录账户名（必须）
  PASSWORD VARCHAR(16) NOT NULL,	//登录密码（必须）
  STATUS INT(11) NOT NULL DEFAULT '1',	//用户权限（必须）
  createdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,//创建时间
  username VARCHAR(20) DEFAULT NULL,	//用户名（必须）
  PRIMARY KEY (ID)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;*/

import java.util.Date;

public class User {
	private Integer ID;
	private String loginname;
	private String PASSWORD;
	private Integer STATUS;
	private Date createdate;
	private String username;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer id, String loginname, String pASSWORD, Integer sTATUS, Date createdate, String username) {
		super();
		this.ID = id;
		this.loginname = loginname;
		PASSWORD = pASSWORD;
		STATUS = sTATUS;
		this.createdate = createdate;
		this.username = username;
	}
	
	
	
	@Override
	public String toString() {
		return "User [ID=" + ID + ", loginname=" + loginname + ", PASSWORD=" + PASSWORD + ", STATUS=" + STATUS
				+ ", createdate=" + createdate + ", username=" + username + "]";
	}

	public Integer getId() {
		return ID;
	}
	public void setId(Integer id) {
		this.ID = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
