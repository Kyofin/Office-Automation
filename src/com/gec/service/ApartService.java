package com.gec.service;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.PageModle;
import com.gec.dao.DaoFactory;

public class ApartService {

	public static List<Apart> getApartList()
	{
		return DaoFactory.getApartDao().getApartList();

	}
	
	//Í¨¹ý·ÖÒ³
	public static List<Apart> getApartListByPage(PageModle pageModle)
	{
		return DaoFactory.getApartDao().getApartListByPage(pageModle);

	}
	
	public static void deleteApartById(String id)
	{
		Apart apart = new Apart();
		apart.setId(Integer.parseInt(id));
		DaoFactory.getApartDao().deleteApart(apart);
	}
	
	public static void addApart(Apart apart)
	{
		DaoFactory.getApartDao().addApart(apart);
	}
	
	public static List<Apart> getAparsByLike(String apartname)
	{
		return DaoFactory.getApartDao().queryApartListByLike(apartname);
	}
}
