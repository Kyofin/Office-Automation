package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.FileBean;
import com.gec.bean.Job;


public interface IFileDao {


	// 增加文件
	public boolean addFile(FileBean	 fileBean);
	
	//删除文件
	public boolean deleteFile(FileBean fileBean);

	// 返回所有文件对象
	public List<FileBean> getFileList();
	
	public FileBean getFileBeanByID(FileBean fileBean);
	
	//模糊查询文件
	public List<FileBean> getFileListByLike(String name);
	
}
