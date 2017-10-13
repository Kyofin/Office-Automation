<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		$("#search").mousedown(function(){
			$("#search").attr("href",function(){
				return $("#search").attr("href")+"&name="+encodeURI($("#name").val())+"&status="+encodeURI($("#status").val());
			});
		});
		$("#checkAll").click(function () {
		    if(this.checked){
		        $("[name=userid]:checkbox").prop('checked',true)
		    }else {
		        $("[name=userid]:checkbox").prop('checked',false)
		    }
		});
		
	});
</script>
</head>
<body>
	<jsp:include page="headerAndNav.jsp"></jsp:include>
	<!--右边内容-->
	<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10" style="padding: 0">
		<!-- 面包屑导航-->
		<ol class="breadcrumb">
			<li><a href="#">当前位置：</a></li>
			<li><a href="#">用户管理</a></li>
			<li class="active">用户查询</li>
		</ol>
		<!-- 操作栏-->
		<div >
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="name">用户名</label> <input type="text"
						class="form-control" id="name" placeholder="请输入名称" name="username">
				</div>
				<div class="form-group">
					<label for="status">用户状态</label> <input type="text"
						class="form-control" id="status" name="userStatus">
				</div>
				<a href="${pageContext.servletContext.contextPath }/QueryUser?search=true" id="search" class="btn btn-info">搜索</a>
				
			</div>

			<form method="post" action="${pageContext.servletContext.contextPath }/deleteUser">
			<!--表格-->
			<table class="table table-hover">

				<thead>
					<tr>
						<th><input type="checkbox" id="checkAll"></th>
						<th>登陆账户</th>
						<th>密码</th>
						<th>用户名</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userlist }" var="user">
						<tr>
							<td><input type="checkbox" name="userid" value="${user.id }"></td>
							<td>${user.loginname }</td>
							<td>${user.PASSWORD }</td>
							<td>${user.username }</td>
							<td>${user.STATUS }</td>
							<td>${user.createdate }</td>
							<td>
								<c:if test="${loginUser.STATUS==2 }">
									<a class="btn btn-info" href="${pageContext.servletContext.contextPath }/editorUser?id=${user.id }">编辑</a>
								</c:if> 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${loginUser.STATUS==2 }">
					<button type="submit" class="btn btn-warning">删除</button>
				</c:if> 
			</form>
			
		</div>
	</div>
	</div>
</body>
</html>