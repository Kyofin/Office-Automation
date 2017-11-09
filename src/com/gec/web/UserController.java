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
//import com.gec.bean.User;
//import com.gec.dao.DaoFactory;
//import com.gec.service.UserService;
//
///**
// * Servlet implementation class QueryUser
// */
//@WebServlet({"/updateUser","/editorUser","/QueryUser","/AddUser","/deleteUser"})
//public class UserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public UserController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//		request.setCharacterEncoding("utf-8");
//		//获取uri以确认请求的路径
//		String uri = request.getRequestURI();
//		if (uri.endsWith("QueryUser"))
//		{
//			
//			
//			//获取搜索或删除的参数(搜素和删除的逻辑)
//			String name= request.getParameter("name");
//			String status = request.getParameter("status");
//			
//			
//			//解决URL中文参数乱码
//			if (name!=null)
//			{
//				name= URLDecoder.decode(name, "utf-8");
//			}
//			
//			if (status!=null)
//			{
//				status=URLDecoder.decode(status, "utf-8");
//			}
//			//默认显示所有用户
//			List<User> userlist = UserService.getUsersByLike(name, status);
//			
//			
//			
//			
//			request.setAttribute("userlist", userlist);
//			request.getRequestDispatcher("WEB-INF/page/queryUserPage.jsp").forward(request, response);;
//			
//		}else if (uri.endsWith("AddUser"))
//		{
//			//获取参数
//			String loginName = request.getParameter("loginname");
//			String status = request.getParameter("status");
//			String userName = request.getParameter("username");
//			String password = request.getParameter("password");
//			
//			if (loginName!=null&&status!=null&&status.length()>0&&userName!=null&&password!=null)
//			{
//				//组装insertUser
//				User insertUser = new User();
//				insertUser.setLoginname(loginName);
//				insertUser.setSTATUS(Integer.parseInt(status));
//				insertUser.setUsername(userName);
//				insertUser.setPASSWORD(password);
//				
//				//调用userService
//				UserService.insertUserToDB(insertUser);
//				
//				//将添加成功的反馈记录到request的属性中
//				request.setAttribute("info", "成功添加");
//			}
//			
//			request.getRequestDispatcher("WEB-INF/page/addUserPage.jsp").forward(request, response);;
//			
//		}else if (uri.endsWith("deleteUser"))
//		{
//			String[] usersId = request.getParameterValues("userid");
//			for (String string : usersId) {
//				UserService.deleteUser(string);
//			}
//			
//			response.sendRedirect("/oasystem/QueryUser");
//		}else if (uri.endsWith("editorUser"))
//		{
//			String id = request.getParameter("id");
//			User user = new User();
//			user.setId(Integer.parseInt(id));
//			User editorUser = DaoFactory.getUserDao().getUserById(user);
//			request.setAttribute("editorUser", editorUser);
//			
//			request.getRequestDispatcher("WEB-INF/page/editorUserPage.jsp").forward(request, response);
//		}else if (uri.endsWith("updateUser")) 
//		{
//			String id = request.getParameter("id");
//			String username= request.getParameter("username");
//			String loginname= request.getParameter("loginname");
//			String password= request.getParameter("password");
//			String status = request.getParameter("status");
//			User newUser = new User();
//			newUser.setId(Integer.parseInt(id));
//			newUser.setLoginname(loginname);
//			newUser.setUsername(username);
//			newUser.setPASSWORD(password);
//			newUser.setSTATUS(Integer.parseInt(status));
//			DaoFactory.getUserDao().updateUser(newUser);
//			
//			
//			response.sendRedirect("/oasystem/QueryUser");
//
//		}
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
