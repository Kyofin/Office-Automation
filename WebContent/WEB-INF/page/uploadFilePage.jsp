<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		$("#collapseSix").addClass("in");
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
			<li><a href="#">下载中心</a></li>
			<li class="active">上传文档</li>
		</ol>


		<!--表单-->
		<form action="${pageContext.servletContext.contextPath }/uploadFile"
			enctype="multipart/form-data" method="post">
			<div class="row" style="margin-bottom: 20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 文档标题：</span> <input type="text"
							class="form-control" name="title">
					</div>
				</div>
			</div>

			<div class="form-group">
				<label>文档描述：</label>
				<textarea rows="5" name="fileinfo" cols="20" class="form-control"></textarea>
			</div>




			<h4 class="text-success">${info }</h4>
			<hr>
			<div class="form-group">
				<label for="inputfile">文件输入</label> <input type="file"
					id="inputfile" name="uploadfile">
			</div>
			<hr>
			<input type="submit" value="添加" class="btn btn-success">
			&nbsp;&nbsp; <input type="reset" value="重置" class="btn btn-default">

		</form>


	</div>
	</div>
</body>
</html>