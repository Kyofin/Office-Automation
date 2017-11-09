//package com.gec.web;
//
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.gec.bean.Apart;
//import com.gec.bean.PageModle;
//import com.gec.bean.User;
//import com.gec.dao.ApartDao;
//import com.gec.dao.DaoFactory;
//import com.gec.service.ApartService;
//import com.gec.service.UserService;
//
///**
// * Servlet implementation class ApartController
// */
//@WebServlet({"/queryApart","/addApart","/deleteApart"})
//public class ApartController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ApartController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//
//		request.setCharacterEncoding("utf-8");
//		//��ȡuri��ȷ�������·��
//		String uri = request.getRequestURI();
//		if (uri.endsWith("queryApart"))
//		{
//			//��ȡҳ������
//			String pageIndex = request.getParameter("pageIndex");
//			//���÷�ҳ����
//			PageModle pageModle = new PageModle();
//			//ûҳ�����ʱĬ��Ϊ��һҳ
//			pageModle.setPageIndex(pageIndex!=null&&!pageIndex.equals("")?Integer.valueOf(pageIndex):1);
//			pageModle.setTotalRows(new ApartDao().getTotalNum());
//			
//			//Ĭ����ʾ�����û�
//			List<Apart> apartlist = ApartService.getApartListByPage(pageModle);
//			
//			
//			//��ȡ������ɾ���Ĳ���(���غ�ɾ�����߼�)
//			String name= request.getParameter("name");
//			
//			//url���Ĳ�������
//			if (name!=null) 
//			{
//				name = URLDecoder.decode(name, "utf-8");
//			}
//			
//			String search = request.getParameter("search");
//			String delete = request.getParameter("delete");
//			if (search!=null)
//			{
//				List<Apart> qAparts= ApartService.getAparsByLike(name);
//				//���������������ʾ������
//				apartlist = qAparts;
//			}else if (delete!=null) 
//			{
//				//
//			}
//			
//			request.setAttribute("pageModle", pageModle);
//			request.setAttribute("apartlist", apartlist);
//			request.getRequestDispatcher("WEB-INF/page/queryApartPage.jsp").forward(request, response);;
//			
//		}else if (uri.endsWith("addApart"))
//		{
//			//��ȡ����
//			String apartname = request.getParameter("apartname");
//			String remark = request.getParameter("remark");
//			
//			if (remark!=null&&apartname!=null)
//			{
//				//��װinsertApart
//				Apart insertApart = new Apart();
//				insertApart.setName(apartname);
//				insertApart.setRemark(remark);
//				
//				//����ApartService
//				ApartService.addApart(insertApart);
//				
//				//����ӳɹ��ķ�����¼��request��������
//				request.setAttribute("info", "�ɹ����");
//			}
//			
//			request.getRequestDispatcher("WEB-INF/page/addApartPage.jsp").forward(request, response);;
//			
//		}else if (uri.endsWith("deleteApart"))
//		{
//			String[] apartsId = request.getParameterValues("apartid");
//			for (String string : apartsId) {
//				ApartService.deleteApartById(string);
//			}
//			
//			response.sendRedirect("/oasystem/queryApart");
//		}
//		
//		
//	
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
