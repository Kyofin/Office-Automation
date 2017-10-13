package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.FileBean;
import com.gec.bean.Job;


public interface IFileDao {


	// �����ļ�
	public boolean addFile(FileBean	 fileBean);
	
	//ɾ���ļ�
	public boolean deleteFile(FileBean fileBean);

	// ���������ļ�����
	public List<FileBean> getFileList();
	
	public FileBean getFileBeanByID(FileBean fileBean);
	
	//ģ����ѯ�ļ�
	public List<FileBean> getFileListByLike(String name);
	
}
