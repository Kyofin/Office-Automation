package com.gec.bean;

public class PageModle {
	
	//��ǰҳ��
	private int pageIndex;
	//���ݵ�ȫ������
	private int totalRows;
	//ÿҳ��ʾ������
	private static final int pageSize=3;
	//��ҳ��
	private int totalPages;
	
	
	//��ȡ��ǰҳ��
	public int getPageIndex() {
		
		this.pageIndex=this.pageIndex<=1?1:this.pageIndex;
		this.pageIndex=this.pageIndex>=getTotalPages()?getTotalPages():this.pageIndex;
		
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	//��ȡ��ҳ��
	public int getTotalPages() {
		this.totalPages = (getTotalRows()+getPageSize()-1)/getPageSize();
		
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	//��ȡ���ݱ��ѯ����ʼλ��
	public int  getStartRow()
	{
		return (getPageIndex()-1)*getPageSize();
	}
}
