<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri ="/struts-tags" prefix="s" %>
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
<style type="text/css">
	<!--设置表格内容 -->
.table th, .table td {  
	text-align: center;  
	vertical-align: middle!important;  
} 
</style>	
<script type="text/javascript">
	$(function () {
		
		$("<%="#collapseThree"%>").addClass("in");
		
		<!--触发搜索按钮-->
		$("#searchbtn").click(function(){
			alert("搜索");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/queryJob");
			
		});
		
		<!--触发删除按钮-->
		$("#deletebtn").click(function(){
			alert("删除");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/deleteJob");
			
		});
		
		
		$("#all").click(function () {
		    if(this.checked){
		        $("[name=jobid]:checkbox").prop('checked',true)
		    }else {
		        $("[name=jobid]:checkbox").prop('checked',false)
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
			<li><a href="#">职位管理</a></li>
			<li class="active">职位查询</li>
		</ol>
		<!-- 操作栏-->
		<div >
			<s:form  id="inputform" method="post" action="deleteJob">
			
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="name">职位名称</label>
					<s:textfield name="jobname" class="form-control" id="jobname" placeholder="请输入名称"/>
				</div>
				<button class="btn btn-info" id="searchbtn">搜索</button>
			</div>

			<!--表格-->
			<table class="table table-hover">

				<thead>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>职位名称</th>
						<th>详细信息</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.jobslist" >
						<tr>
							<td><s:checkbox name="jobid" fieldValue="%{id}"/></td>
							<td><s:property value="name"/></td>
							<td><s:property value="mark"/></td>
							<td>
								<s:if test="#session.loginUser.STATUS==2">
									<a class="btn btn-info" href="${pageContext.servletContext.contextPath }/editorJob?id=<s:property value="id" />">编辑</a>
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
			<br><br>
			
		</div>
	</div>
	</div>
</body>
</html>