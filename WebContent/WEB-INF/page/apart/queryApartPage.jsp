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
		$("<%="#collapseTwo"%>").addClass("in");
		
		<!--触发搜索按钮-->
		$("#searchbtn").click(function(){
			alert("搜索");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/queryApart");
			
		});
		
		<!--触发删除按钮-->
		$("#deletebtn").click(function(){
			alert("删除");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/deleteApart");
			
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
	<jsp:include page="/WEB-INF/page/headerAndNav.jsp"></jsp:include>
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
			<s:form  id="inputform" method="post" action="deleteApart">
			
			<div class="form-inline" style="width: 100%" >
				<div class="form-group">
					<label for="name">部门名称</label> 
					<s:textfield id="name" class="form-control" name="apartname" placeholder="请输入名称"/>
				</div>
				
				<button class="btn btn-info" id="searchbtn">搜索</button>
			</div>


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
					<s:iterator value="#request.apartlist">
						<tr>
							<td><s:checkbox name="apartid" fieldValue="%{id}"/></td>
							<td><s:property  value="name"/></td>
							<td><s:property value="remark"/></td>
							<td>操作</td>
						</tr>
					</s:iterator>
					
					
				</tbody>
			</table>
			<s:if test="#session.loginUser.STATUS==2">
					<button id="deletebtn" type="submit" class="btn btn-warning">删除</button>
			</s:if>
			
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
			</s:form>
			
		</div>
	</div>
	</div>
</body>
</html>