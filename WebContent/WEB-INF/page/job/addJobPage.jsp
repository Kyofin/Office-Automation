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
		$("<%="#collapseThree"%>").addClass("in");
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
			<li><a href="#">职位管理</a></li>
			<li class="active">添加职位</li>
		</ol>


		<!--表单-->
		<s:form action="addJob" method="post">
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<s:fielderror fieldName="jobname"/>
					<div class="input-group">
						<span class="input-group-addon"> 职位名称：</span> 
						<s:textfield name="jobname" class="form-control" />	
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<s:fielderror fieldName="remark"/>
					<div class="input-group">
						<span class="input-group-addon">详细描述：</span> 
						<s:textfield name="remark" class="form-control" />
					</div>
				</div>
			</div>
			
		
			<h4 class = "text-success">${info }</h4>
			<hr>
			<s:submit value="添加" class="btn btn-success"/>
			&nbsp;&nbsp;
			<s:reset value="重置" class="btn btn-default"/>
			
		</s:form>
		

	</div>
	</div>
</body>
</html>