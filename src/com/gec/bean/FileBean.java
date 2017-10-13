package com.gec.bean;

import java.util.Arrays;
import java.util.Date;

public class FileBean {
	private Integer id;
	private String title;
	private String fileName;
	private String remark;
	private Date createdate;
	private byte[] fileBytes;
	private Integer userid;
	private String upusername;
	
	
	
	public String getUpusername() {
		return upusername;
	}

	public void setUpusername(String upusername) {
		this.upusername = upusername;
	}

	@Override
	public String toString() {
		return "FileBean [id=" + id + ", title=" + title + ", fileName=" + fileName + ", remark=" + remark
				+ ", createdate=" + createdate + ", fileBytes=" + Arrays.toString(fileBytes) + ", userid=" + userid
				+ "]";
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public FileBean() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
