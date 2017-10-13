package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.User;

public interface IUserDao {
	
	//�����û�
	public boolean addUser(User user);
	
	//ɾ���û�
	public  boolean deleteUserById(String strid);
	
	//�����û���Ϣ
	public boolean updateUser(User user);
	
	//���������û�
	public List<User> getUserList();
	
	//��֤�û������ݿ��м�¼
	public boolean queryUserLogin(User user);
	
	//ģ����ѯ�û�
	public List<User> queryUserListByLike(String name, String status);
	
	//��ѯ�û�������Ϣ
	public User getUser(User user);
	
	//��ѯ�û�������Ϣͨ����ID
	public User getUserById(User user);
	
	
	public List<User> getUserListByMybatis(String name,Integer status) ;

	
}
