package com.gec.service;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.FileBean;
import com.gec.dao.DaoFactory;

public class FileService {
	public static boolean addFile(FileBean fileBean)
	{
		return DaoFactory.getFileDao().addFile(fileBean);
	}
	
	public static List<FileBean> getFileList()
	{
		return DaoFactory.getFileDao().getFileList();
	}
	
	public static List<FileBean> getFileListByLike(String name)
	{
		return DaoFactory.getFileDao().getFileListByLike(name);
	}
	
	public static FileBean getFileBeanByID(FileBean fileBean)
	{
		return  DaoFactory.getFileDao().getFileBeanByID(fileBean);
	}
	
	public static void deleteFileById(String id)
	{
		FileBean fileBean = new FileBean();
		fileBean.setId(Integer.parseInt(id));
		DaoFactory.getFileDao().deleteFile(fileBean);
	}
}
