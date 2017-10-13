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
		$("<%="#collapseTwo"%>").addClass("in");
		$("#search").mousedown(function(){
			$("#search").attr("href",function(){
				return $("#search").attr("href")+"&name="+encodeURI($("#name").val());
			});
		});
		$("#all").click(function () {
		    if(this.checked){
		        $("[name=apartid]:checkbox").prop('checked',true)
		    }else {
		        $("[name=apartid]:checkbox").prop('checked',false)
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
			<li><a href="#">部门管理</a></li>
			<li class="active">部门查询</li>
		</ol>
		<!-- 操作栏-->
		<div >
			<div class="form-inline" style="width: 100%" >
				<div class="form-group">
					<label for="name">部门名称</label> <input type="text"
						class="form-control" id="name" placeholder="请输入名称" name="apartname">
				</div>
				
				<a href="${pageContext.servletContext.contextPath }/queryApart?search=true" id="search" class="btn btn-info">搜索</a>
				
			</div>

			<form method="post" action="${pageContext.servletContext.contextPath }/deleteApart">

			<!--表格-->
			<table class="table table-hover">

				<thead>
					<tr>
						<th><input type="checkbox" id="all"></th>
						<th>部门名称</th>
						<th>详细信息</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${apartlist }" var="apart">
						<tr>
							<td><input type="checkbox" name="apartid" value="${apart.id }"></td>
							<td>${apart.name }</td>
							<td>${apart.remark }</td>
							<td>操作</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${loginUser.STATUS==2 }">
					<button type="submit" class="btn btn-warning">删除</button>
				</c:if> 
			
			<!-- 分页 -->	
			<nav style="text-align: center">  
				<ul   class="pagination">
					<li><a href="${pageContext.servletContext.contextPath }/queryApart?pageIndex=${pageModle.pageIndex-1}">&laquo;</a></li>
					<c:forEach begin="1" var="i" end="${pageModle.totalPages }">
						<c:choose>
							<c:when test="${pageModle.pageIndex==i }">
								<li class="active"><a href="${pageContext.servletContext.contextPath }/queryApart?pageIndex=${i}">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li ><a href="${pageContext.servletContext.contextPath }/queryApart?pageIndex=${i}">${i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<li><a href="${pageContext.servletContext.contextPath }/queryApart?pageIndex=${pageModle.pageIndex+1}">&raquo;</a></li>
				</ul>	
			</nav>	
			</form>
			
		</div>
	</div>
	</div>
</body>
</html>