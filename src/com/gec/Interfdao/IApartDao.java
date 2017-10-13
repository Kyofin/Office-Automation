package com.gec.Interfdao;

import java.util.List;

import com.gec.bean.Apart;
import com.gec.bean.PageModle;


public interface IApartDao {

		//���Ӳ���
		public boolean addApart(Apart apart);
		
		//ɾ������
		public boolean deleteApart(Apart apart);
		
		//���²�����Ϣ
		public boolean updateApart(Apart apart);
		
		
		//�������в���
		public List<Apart> getApartList();
		//�������в���ͨ����ҳ
		public List<Apart> getApartListByPage(PageModle pageModle);
		
		//ģ����ѯ����
		public List<Apart> queryApartListByLike(String apart);
		
		//��ѯ������Ϣ
		public Apart getApart(Apart apart);
		
		//��ѯ�������ݵ�������
		public int getTotalNum();
	
}
