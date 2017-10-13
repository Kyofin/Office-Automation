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
		$("<%="#collapseFour"%>").addClass("in");
		$("#search").mousedown(function(){
			$("#search").attr("href",function(){
				return $("#search").attr("href")+"&job="+encodeURI($("#job").val())+"&name="+encodeURI($("#name").val())+"&cardid="+encodeURI($("#cardid").val())+"&sex="+encodeURI($("#sex").val())+"&phone="+encodeURI($("#phone").val())+"&depart="+encodeURI($("#depart").val());
			});
		});
		$("#all").click(function () {
		    if(this.checked){
		        $("[name=EmpsId]:checkbox").prop('checked',true)
		    }else {
		        $("[name=EmpsId]:checkbox").prop('checked',false)
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
			<li><a href="#">员工管理</a></li>
			<li class="active">员工查询</li>
		</ol>
		<!-- 操作栏-->
		<div >
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="job">职位:</label> 
					<select id="job" name="job" class="form-control">
							<option value="">--请选择--</option>
							<c:forEach items="${jobs }" var="job">
								<option value="${job.id }">${job.name }</option>
							</c:forEach>
						</select>
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="name">姓名</label> <input type="text"
						class="form-control" id="name" placeholder="请输入名称" >
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="cardid">身份证号码</label> <input type="text"
						class="form-control" id="cardid" placeholder="请输入名称" >
				</div>
			</div>
			<br>
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="sex">性别</label> 
						<select id="sex" class="form-control">
							<option value="">--请选择--</option>
							<option  value="男" >男</option>
							<option value="女">女</option>
						</select>
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="phone">手机</label> <input type="text"
						class="form-control" id="phone" placeholder="请输入名称" >
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="depart">所属部门</label> 
					<select id="depart" class="form-control">
							<option value="">--请选择--</option>
							<c:forEach items="${departs }" var="depart">
								<option value="${depart.id }">${depart.name }</option>
							</c:forEach>
						</select>
				</div>
				
				&nbsp;&nbsp;
				<a href="${pageContext.servletContext.contextPath }/queryEmployee?search=true" id="search" class="btn btn-info">搜索</a>
				 &nbsp;&nbsp;
				 
			</div>
				<br>


			<form method="post" action="${pageContext.servletContext.contextPath }/deleteEmployee">
			<!--表格-->
			<table class="table table-hover">

				<thead>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>姓名</th>
						<th>性别</th>
						<th>手机号码</th>
						<th>邮箱</th>
						<th>职位</th>
						<th>学历</th>
						<th>身份证号码</th>
						<th>部门</th>
						<th>联系地址</th>
						<th>建档日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${employeeslist }" var="emp">
						<tr>
							<td><input type="checkbox" name="EmpsId" value="${emp.id }"></td>
							<td>${emp.name }</td>
							<td>${emp.sex }</td>
							<td>${emp.phone }</td>
							<td>${emp.email }</td>
							<td>${emp.jobname }</td>
							<td>${emp.education }</td>
							<td>${emp.cardid }</td>
							<td>${emp.deptname }</td>
							<td>${emp.address }</td>
							<td>${emp.createdate }</td>
							<td>操作</td>
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