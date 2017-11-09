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
//		//获取uri以确认请求的路径
//		String uri = request.getRequestURI();
//		if (uri.endsWith("queryApart"))
//		{
//			//获取页数参数
//			String pageIndex = request.getParameter("pageIndex");
//			//设置分页对象
//			PageModle pageModle = new PageModle();
//			//没页码参数时默认为第一页
//			pageModle.setPageIndex(pageIndex!=null&&!pageIndex.equals("")?Integer.valueOf(pageIndex):1);
//			pageModle.setTotalRows(new ApartDao().getTotalNum());
//			
//			//默认显示所有用户
//			List<Apart> apartlist = ApartService.getApartListByPage(pageModle);
//			
//			
//			//获取搜索或删除的参数(搜素和删除的逻辑)
//			String name= request.getParameter("name");
//			
//			//url中文参数乱码
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
//				//将搜索结果放入显示集合中
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
//			//获取参数
//			String apartname = request.getParameter("apartname");
//			String remark = request.getParameter("remark");
//			
//			if (remark!=null&&apartname!=null)
//			{
//				//组装insertApart
//				Apart insertApart = new Apart();
//				insertApart.setName(apartname);
//				insertApart.setRemark(remark);
//				
//				//调用ApartService
//				ApartService.addApart(insertApart);
//				
//				//将添加成功的反馈记录到request的属性中
//				request.setAttribute("info", "成功添加");
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
