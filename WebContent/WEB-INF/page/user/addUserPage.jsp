<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function () {
		$("<%="#collapseOne"%>").addClass("in");
	});
</script>
<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/page/headerAndNav.jsp"></jsp:include>
	<!--右边内容-->
	<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10" style="padding: 0">
		<!-- 面包屑导航-->
		<ol class="breadcrumb">
			<li><a href="#">当前位置：</a></li>
			<li><a href="#">用户管理</a></li>
			<li class="active">添加用户</li>
		</ol>


		<!--表单-->
		<form action="${pageContext.servletContext.contextPath }/AddUser" method="post">
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<s:fielderror  fieldName="insertUser.username"/>
					<div class="input-group">
						<span class="input-group-addon"> 用户名： </span> 
						<s:textfield class="form-control" name="insertUser.username"/>
					</div>
					
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">状态：</span> 
						<s:select name="insertUser.STATUS" list="#{'1':'普通用户','2':'管理员'}"   class="form-control"/>
					</div>
				</div>
			</div>
			
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<s:fielderror  fieldName="insertUser.loginname"/>
					<div class="input-group">
						<span class="input-group-addon"> 登录账户：</span> 
						<s:textfield class="form-control" name="insertUser.loginname"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
				<s:fielderror  fieldName="insertUser.PASSWORD"/>
					<div class="input-group">
						<span class="input-group-addon"> 密码： </span> 
						<s:textfield class="form-control" name="insertUser.PASSWORD"/>
					</div>
				</div>
			</div> 
			<h4 class = "text-success">${info }</h4>
			<hr>
			<input type="submit" value="添加" class="btn btn-success">
			&nbsp;&nbsp;
			<input type="reset" value="重置" class="btn btn-default">
			
		</form>
		

	</div>
	</div>
</body>
</html>