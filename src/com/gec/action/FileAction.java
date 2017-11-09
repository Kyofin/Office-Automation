package com.gec.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gec.bean.FileBean;
import com.gec.bean.User;
import com.gec.service.FileService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport
{
	private String name;
	private String[] filesId;
	private File input    ;      //�ϴ��ļ��������ֱ����ļ�nameһ�£�
	private String inputContentType; //��ȡ�ϴ��ļ������ͣ�����ǰ׺�ͱ����ļ�nameһ�£�
	private String inputFileName; //��ȡ�ļ���������ǰ׺�ͱ����ļ�nameһ�£�
	private static final String SAVE_PATH = ServletActionContext.getServletContext().getRealPath("/WEB-INF/"+"upload");
	private String title;
	private String fileinfo;
	private InputStream targetFile ;
	private String fileid;
	private String fileName;
	
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setTargetFile(InputStream targetFile) {
		this.targetFile = targetFile;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileinfo() {
		return fileinfo;
	}

	public void setFileinfo(String fileinfo) {
		this.fileinfo = fileinfo;
	}

	public File getInput() {
		return input;
	}

	public void setInput(File input) {
		this.input = input;
	}

	public String getInputContentType() {
		return inputContentType;
	}

	public void setInputContentType(String inputContentType) {
		this.inputContentType = inputContentType;
	}

	public String getInputFileName() {
		return inputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public static String getSavePath() {
		return SAVE_PATH;
	}

	public String[] getFilesId() {
		return filesId;
	}

	public void setFilesId(String[] filesId) {
		this.filesId = filesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * ��ѯ�ĵ�
	 * @return
	 */
	public String queryFile()
	{
		// Ĭ����ʾ�����û�
		List<FileBean> filelist = FileService.getFileList();

		//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
		String name= getName();
		
		if (name!=null)
		{
			List<FileBean> qfiles= FileService.getFileListByLike(name); 
			//���������������ʾ������
			filelist = qfiles;
		}		
		
		
		ServletActionContext.getRequest().setAttribute("filelist", filelist);

		return SUCCESS;
	}
	
	
	/**
	 * ɾ���ĵ�
	 * @return
	 */
	public String deleteFile()
	{
		
		String[] filesId = getFilesId();
		for (String string : filesId) {
			FileService.deleteFileById(string);
		}
		
		return SUCCESS;
	}
	
	/**
	 * �ļ��ϴ�(δ���)
	 * @return
	 */
	public String uploadFile()
	{
		System.out.println("�ļ�����"+getInputFileName());
		System.out.println("�ļ����ͣ�"+getInputContentType());
		
		//�ļ�����
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			 fis = new FileInputStream(getInput());
			 byte[] buffer= new byte[1024];
			 int len =0;
			 while((len =fis.read(buffer))!=-1)
			 {
				 baos.write(buffer,0,len);
			 }
			// ���ļ�������װ��fileBean����
			FileBean fileBean = new FileBean();
			fileBean.setFileName(getInputFileName());
			fileBean.setRemark(getFileinfo());
			fileBean.setTitle(getTitle());
			fileBean.setCreatedate(new Date());
			fileBean.setFileBytes(baos.toByteArray());
			User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
			System.out.println(loginUser);
			fileBean.setUserid(loginUser.getId());
			// ����FileService
			boolean flag= FileService.addFile(fileBean);
			if (flag)
			{
				// ����ӳɹ��ķ�����¼��request��������
				ServletActionContext.getRequest().setAttribute("info", "�ɹ����");
			}
			 
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fis!= null)
			{
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			if (baos!= null) 
			{
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * ��ȡ�����ļ���
	 * @return
	 */
	public InputStream getTargetFile() {
		
		//��ȡ���ص��ļ�id
		String fileid = getFileid();
		FileBean searchBean = new FileBean();
		searchBean.setId(Integer.parseInt(fileid));
		
		//����fileService��ȡ���ص��ļ�����
		FileBean outBean= FileService.getFileBeanByID(searchBean);
		byte[] buffer =  outBean.getFileBytes();
		//���������ļ�name,������������ļ�������
		String uft8FileName=null;
		try {
			uft8FileName = new String(outBean.getFileName().getBytes("UTF-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFileName(uft8FileName);
		
		return new ByteArrayInputStream(buffer);
	}
	
	public String downloadFile()
	{
		return SUCCESS;
	}

	
}
