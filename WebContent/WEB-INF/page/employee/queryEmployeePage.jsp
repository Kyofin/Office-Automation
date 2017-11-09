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
		$("<%="#collapseFour"%>").addClass("in");
		
		<!--触发搜索按钮-->
		$("#searchbtn").click(function(){
			alert("搜索");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/queryEmployee");
			
		});
		
		<!--触发删除按钮-->
		$("#deletebtn").click(function(){
			alert("删除");
			$("#inputform").attr("action","${pageContext.servletContext.contextPath }/deleteEmployee");
			
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
	<jsp:include page="/WEB-INF/page/headerAndNav.jsp"></jsp:include>
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
			<s:form id="inputform" method="post" action="deleteEmployee">
			
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="job">职位:</label> 
					<%-- <select id="job" name="job" class="form-control">
						<option value="">--请选择--</option>
						<c:forEach items="${jobs }" var="job">
							<option value="${job.id }">${job.name }</option>
						</c:forEach>
					</select> --%>
					<s:select id="job" name="job" class="form-control" list="jobs" listKey="id" listValue="name" headerKey="" headerValue="--请选择--" />
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="name">姓名</label> 
					<s:textfield name="name" class="form-control"  placeholder="请输入名称"/>	
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="cardid">身份证号码</label> 
					<s:textfield name="cardid" class="form-control"  placeholder="请输入身份证号"/>	
				</div>
			</div>
			<br>
			<div class="form-inline" style="width: 100%" role="form">
				<div class="form-group">
					<label for="sex">性别</label> 
					<%-- <select id="sex" class="form-control">
						<option value="">--请选择--</option>
						<option  value="男" >男</option>
						<option value="女">女</option>
					</select> --%>
					<s:select id="sex" name="sex" class="form-control" list="#{'男':'男','女':'女' }"  />
						
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="phone">手机</label> 
					<s:textfield name="phone" class="form-control"  placeholder="请输入手机"/>
				</div>
				&nbsp;&nbsp;
				<div class="form-group">
					<label for="depart">所属部门</label> 
					<%-- <select id="depart" class="form-control">
						<option value="">--请选择--</option>
						<c:forEach items="${departs }" var="depart">
							<option value="${depart.id }">${depart.name }</option>
						</c:forEach>
					</select> --%>
					<s:select id="depart" name="depart" class="form-control" list="departs" listKey="id" listValue="name" headerKey="" headerValue="--请选择--" />
				</div>
				
				<button class="btn btn-info" id="searchbtn">搜索</button>
			</div>
				<br>


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
					
					<s:iterator value="#request.employeeslist">
						<tr>
							<td>
								<s:checkbox name="EmpsId" fieldValue="%{id}"/>
							</td>
							<td><s:property value="name"/> </td>
							<td><s:property value="sex"/></td>
							<td><s:property value="phone"/></td>
							<td><s:property value="email"/></td>
							<td><s:property value="jobname"/></td>
							<td><s:property value="education"/></td>
							<td><s:property value="cardid"/></td>
							<td><s:property value="deptname"/></td>
							<td><s:property value="address"/></td>
							<td><s:property value="createdate"/></td>
							<td>操作</td>
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