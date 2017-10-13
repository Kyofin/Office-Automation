package com.gec.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gec.bean.User;
import com.gec.dao.DaoFactory;
import com.gec.service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/dologin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		//获取参数
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		
		//组装userbean
		User loginUser = new User();
		loginUser.setLoginname(loginName);
		loginUser.setPASSWORD(password);
		
		//验证登录信息
		boolean isPass = LoginService.validate(loginUser);
		
		//转发试图
		if (isPass)
		{	
			//获得登录用户的全部信息组织成user对象
			loginUser = DaoFactory.getUserDao().getUser(loginUser);
			//将该LoginUser放入session中
			request.getSession().setAttribute("loginUser", loginUser);
			//跳转到QueryUser
			response.sendRedirect("QueryUser");
		}else
		{
			response.sendRedirect("index.jsp");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
