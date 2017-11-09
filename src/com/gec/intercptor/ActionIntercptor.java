package com.gec.intercptor;

import com.gec.bean.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionIntercptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("执行拦截器");
		
		//加载登陆成功后保存在session的用户信息
		User loginUser = (User) ActionContext.getContext().getSession().get("loginUser");
		
		String result = "";
		if (loginUser!=null)
		{
			result = invocation.invoke();
			System.out.println(result);
		}else {
			result = "login";
		}
		
		return result;
	}

}
