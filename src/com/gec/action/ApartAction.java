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
	 * ���Ҳ���(��ҳδ����)
	 * @return
	 */
	public String queryApart()
	{
		//��ȡҳ������
		String pageIndex = getPageIndex();
		//���÷�ҳ����
		PageModle pageModle = new PageModle();
		//ûҳ�����ʱĬ��Ϊ��һҳ
		pageModle.setPageIndex(pageIndex!=null&&!pageIndex.equals("")?Integer.valueOf(pageIndex):1);
		pageModle.setTotalRows(new ApartDao().getTotalNum());
		
		//Ĭ����ʾ�����û�
		List<Apart> apartlist = ApartService.getApartListByPage(pageModle);
		
		String apartName = getApartname();
		
		if (apartName!=null)
		{
			List<Apart> qAparts= ApartService.getAparsByLike(apartName);
			//���������������ʾ������
			apartlist = qAparts;
		}
		
		ServletActionContext.getRequest().setAttribute("pageModle", pageModle);
		ServletActionContext.getRequest().setAttribute("apartlist", apartlist);
		
		return SUCCESS;
	}
	
	/**
	 * ��Ӳ���(���)
	 * @return
	 */
	public String addApart()
	{
		
		//��װinsertApart
		Apart insertApart = new Apart();
		insertApart.setName(apartname);
		insertApart.setRemark(remark);
		
		//����ApartService
		ApartService.addApart(insertApart);
		
		//����ӳɹ��ķ�����¼��request��������
		ServletActionContext.getRequest().setAttribute("info", "�ɹ����");
		return SUCCESS;
	}
	
	
	/**
	 * ɾ�����ţ���ɣ�
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
