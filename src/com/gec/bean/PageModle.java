package com.gec.bean;

public class PageModle {
	
	//当前页码
	private int pageIndex;
	//数据的全部数量
	private int totalRows;
	//每页显示的数量
	private static final int pageSize=3;
	//总页数
	private int totalPages;
	
	
	//获取当前页码
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
	
	//获取总页数
	public int getTotalPages() {
		this.totalPages = (getTotalRows()+getPageSize()-1)/getPageSize();
		
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	//获取数据表查询的起始位置
	public int  getStartRow()
	{
		return (getPageIndex()-1)*getPageSize();
	}
}
