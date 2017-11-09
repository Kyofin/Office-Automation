<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		$("<%="#collapseSix"%>").addClass("in");

		<!--触发搜索按钮-->
		$("#searchbtn").click(function(){
			alert("搜索");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/queryFile");
			
		});
		
		<!--触发删除按钮-->
		$("#deletebtn").click(function(){
			alert("删除");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/deleteFile");
			
		});
		
		
		$("#all").click(function () {
		    if(this.checked){
		        $("[name=filesId]:checkbox").prop('checked',true)
		    }else {
		        $("[name=filesId]:checkbox").prop('checked',false)
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
			<li><a href="#">文档管理</a></li>
			<li class="active">文档查询</li>
		</ol>
		<!-- 操作栏-->
		<div >
			<s:form  id="inputform" method="post" action="deleteFile">
		
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="name">标题</label> 
					<s:textfield class="form-control"  placeholder="请输入文件标题" name="name"/>
				</div>
				
				<button class="btn btn-info" id="searchbtn">搜索</button>
				
			</div>

			<!--表格-->
			<table class="table table-hover">

				<thead>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>标题</th>
						<th>创建时间</th>
						<th>创建人</th>
						<th>描述</th>
						<th>操作</th>
						<th>下载</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.filelist">
						<tr>
							<td><s:checkbox name="filesId" fieldValue="%{id}"/></td>
							<td><s:property  value="title"/></td>
							<td><s:date name="createdate" format="yyyy-MM-dd"/> </td>
							<td><s:property value="upusername"/></td>
							<td><s:property value="remark"/></td>
							<td>操作</td>
							<td><a href="${pageContext.servletContext.contextPath }/downloadFile?fileid=<s:property value="id"/>">下载</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<s:if test="#session.loginUser.STATUS==2">
				<button id="deletebtn" type="submit" class="btn btn-warning">删除</button>
			</s:if>
			</s:form>
			
		</div>
	</div>
	</div>
</body>
</html>