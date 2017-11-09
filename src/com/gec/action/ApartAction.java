package com.gec.action;

import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gec.bean.Apart;
import com.gec.bean.PageModle;
import com.gec.dao.ApartDao;
import com.gec.service.ApartService;
import com.opensymphony.xwork2.ActionSupport;

public class ApartAction extends ActionSupport
{
	private String pageIndex;
	private String apartname;
	private String remark;
	private String[] apartid;
	


	public String[] getApartid() {
		return apartid;
	}

	public void setApartid(String[] apartid) {
		this.apartid = apartid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getApartname() {
		return apartname;
	}

	public void setApartname(String apartname) {
		this.apartname = apartname;
	}

	/**
	 * 查找部门(分页未处理)
	 * @return
	 */
	public String queryApart()
	{
		//获取页数参数
		String pageIndex = getPageIndex();
		//设置分页对象
		PageModle pageModle = new PageModle();
		//没页码参数时默认为第一页
		pageModle.setPageIndex(pageIndex!=null&&!pageIndex.equals("")?Integer.valueOf(pageIndex):1);
		pageModle.setTotalRows(new ApartDao().getTotalNum());
		
		//默认显示所有用户
		List<Apart> apartlist = ApartService.getApartListByPage(pageModle);
		
		String apartName = getApartname();
		
		if (apartName!=null)
		{
			List<Apart> qAparts= ApartService.getAparsByLike(apartName);
			//将搜索结果放入显示集合中
			apartlist = qAparts;
		}
		
		ServletActionContext.getRequest().setAttribute("pageModle", pageModle);
		ServletActionContext.getRequest().setAttribute("apartlist", apartlist);
		
		return SUCCESS;
	}
	
	/**
	 * 添加部门(完成)
	 * @return
	 */
	public String addApart()
	{
		
		//组装insertApart
		Apart insertApart = new Apart();
		insertApart.setName(apartname);
		insertApart.setRemark(remark);
		
		//调用ApartService
		ApartService.addApart(insertApart);
		
		//将添加成功的反馈记录到request的属性中
		ServletActionContext.getRequest().setAttribute("info", "成功添加");
		return SUCCESS;
	}
	
	
	/**
	 * 删除部门（完成）
	 * @return
	 */
	public String deleteApart()
	{
		String[] apartsId = getApartid();
		System.out.println(apartsId);
		for (String string : apartsId) {
			ApartService.deleteApartById(string);
		}
		
		return SUCCESS;
	}
}
