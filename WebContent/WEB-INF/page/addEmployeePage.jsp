<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		$("#cardid").blur(function(){
			$.post("/oasystem/checkcard",{card:$("#cardid").val()},function(data,status){
				alert(data);
			})
		});
	});
</script>
<style type="text/css">

</style>
</head>
<body>
	<jsp:include page="headerAndNav.jsp"></jsp:include>
	<!--右边内容-->
	<div class="col-md-10 col-xs-10 col-sm-10 col-lg-10" style="padding: 0">
		<!-- 面包屑导航-->
		<ol class="breadcrumb">
			<li><a href="#">当前位置：</a></li>
			<li><a href="#">员工管理</a></li>
			<li class="active">添加员工</li>
		</ol>


		<!--表单-->
		<form style="margin-bottom:20px"  action="${pageContext.servletContext.contextPath }/addEmployee" method="post">
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 姓名：</span> <input type="text"
							class="form-control" name="name">
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<div class="input-group">
						<span class="input-group-addon">身份证号码：</span> <input type="text"
							class="form-control" id="cardid" name="creditid">
					</div>
				</div>
			</div>
			
			
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 性别：</span> 
						<select name="sex" class="form-control">
							<option value="男" >男</option>
							<option value="女">女</option>
						</select>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">职位：</span> 
						<select name="job" class="form-control">
							<c:forEach items="${jobs }" var="job">
								<option value="${job.id }">${job.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 学历：</span> <input type="text"
							class="form-control" name="education">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">邮箱：</span> <input type="text"
							class="form-control" name="email">
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 手机：</span> <input type="text"
							class="form-control" name="phone">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">电话：</span> <input type="text"
							class="form-control" name="tel">
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 政治面貌：</span> <input type="text"
							class="form-control" name="party">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">QQ号码：</span> <input type="text"
							class="form-control" name="qq">
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 联系地址：</span> <input type="text"
							class="form-control" name="address">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">邮政编码：</span> <input type="text"
							class="form-control" name="postcode">
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 出生日期：</span> <input type="date"
							class="form-control" name="birthday">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">民族：</span> <input type="text"
							class="form-control" name="race">
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 所学专业：</span> <input type="text"
							class="form-control" name="major">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">爱好：</span> <input type="text"
							class="form-control" name="hobby">
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">备注：</span> <input type="text"
							class="form-control" name="remark">
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">所属部门：</span> 
						<select name="depart" class="form-control">
							<c:forEach items="${departs }" var="depart">
								<option value="${depart.id }">${depart.name }</option>
							</c:forEach>
						</select>
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