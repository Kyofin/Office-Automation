package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.PageModle;


public interface IApartDao {

		//增加部门
		public boolean addApart(Apart apart);
		
		//删除部门
		public boolean deleteApart(Apart apart);
		
		//更新部门信息
		public boolean updateApart(Apart apart);
		
		
		//返回所有部门
		public List<Apart> getApartList();
		//返回所有部门通过分页
		public List<Apart> getApartListByPage(PageModle pageModle);
		
		//模糊查询部门
		public List<Apart> queryApartListByLike(String apart);
		
		//查询部门信息
		public Apart getApart(Apart apart);
		
		//查询部门数据的总条数
		public int getTotalNum();
	
}
