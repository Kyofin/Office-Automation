package com.gec.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gec.bean.User;

/**
 * Servlet Filter implementation class myFilter
 */
@WebFilter("/*")
public class requestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public requestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//强制转换request,response
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String[] passUriStr = {"/index.jsp","/dologin","/error.jsp","/resources/css/normalize.css"}; 
		
		//放行以下请求
		String uri = httpServletRequest.getServletPath();
		for (String string : passUriStr) {
			if (uri.equals(string))
			{
				chain.doFilter(request, response);
				return;
			}
		}
		
		
		
		//判断session中是否有用户信息
		HttpSession session = httpServletRequest.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser!=null)
		{
			chain.doFilter(request, response);
		}else
		{
			httpServletResponse.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
