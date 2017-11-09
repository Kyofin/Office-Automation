<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-2.2.4.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
<style type="text/css">
	<!--设置表格内容 -->
.table th, .table td {  
	text-align: center;  
	vertical-align: middle!important;  
} 
</style>
<script type="text/javascript">

	$(function () {
		<!--添加导航样式-->
		$("<%="#collapseOne"%>").addClass("in");
		
		<!--触发搜索按钮-->
		$("#searchbtn").click(function(){
			alert("搜索");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/QueryUser");
			
		});
		
		<!--触发删除按钮-->
		$("#deletebtn").click(function(){
			alert("删除");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/deleteUser");
			
		});
		
		<!--全选功能-->
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
	<jsp:include page="/WEB-INF/page/headerAndNav.jsp"></jsp:include>
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
			<s:form  id="inputform" method="post" action="deleteUser">
			
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="name">用户名</label> <s:textfield
						class="form-control" id="name" placeholder="请输入名称" name="username"/>
				</div>
				<div class="form-group">
					<label for="status">用户状态</label> 
					<s:select name="userStatus" list="#{'':'请选择','1':'1','2':'2'}"   class="form-control"/>	
				</div>
				<button class="btn btn-info" id="searchbtn">搜索</button>
			</div>

			<!--表格-->
			<table class="table table-hover">

				<thead>
					<tr>
						<th><input  type="checkbox" id="checkAll"></th>
						<th>登陆账户</th>
						<th>密码</th>
						<th>用户名</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator  value="#request.userlist" >
						<tr>
							<td><s:checkbox name="userid" fieldValue="%{id}"/></td>
							<td><s:property value="loginname" /></td>
							<td><s:property value="PASSWORD" /></td>
							<td><s:property value="username" /></td>
							<td><s:property value="STATUS" /></td>
							<td><s:date name="createdate" format="yyyy-MM-dd"/> </td>
							<td>
								<s:if test="#session.loginUser.STATUS==2">
									<a class="btn btn-info" href="${pageContext.servletContext.contextPath }/editorUser?id=<s:property value="id" />">编辑</a>
								</s:if>
							</td>
						</tr>
					</s:iterator>
					
					
				</tbody>
			</table>
				<s:if test="#session.loginUser.STATUS==2">
					<button id="deletebtn" type="submit" class="btn btn-warning">删除</button>
				</s:if>
			</s:form>
			<br>
		</div>
	</div>
	</div>
</body>
</html>