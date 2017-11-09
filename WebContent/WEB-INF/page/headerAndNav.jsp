<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 巨幕-->
	<h4 class="pull-right">
		<a class="btn btn-danger" href="${pageContext.servletContext.contextPath }/LoginOut">注销</a>&nbsp;&nbsp;&nbsp;
	</h4>
	<div class="jumbotron" style="background: lightblue">
		<div class="container">
			<h4 class="pull-right"><span class="glyphicon glyphicon-user" style="color: rgb(217, 140, 71);" >${loginUser.username}</span></h4>
			<h3>人事管理系统</h3>
			<h4 class="text-right"><%=new SimpleDateFormat("yyyy年MM月dd日HH时mm分").format(new Date())%></h4>
		</div>
	</div>
	<!-- 内容显示-->
	<div class="container-fluid">
		<!--左边导航-->
		<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2">
			<div class="panel-group" id="accordion">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"
								style="font-size: 20px; text-decoration: none;"> 用户管理 </a>
						</h3>
					</div>
					<div id="collapseOne" class="panel-collapse collapse ">
						<div class="panel-body" style="padding-left: 0; padding-right: 0;"
							style="padding-left: 0;padding-right:0; ">

							<a href="${pageContext.servletContext.contextPath }/QueryUser" class="list-group-item text-center"
								style="border: none; font-size: 18px;">用户查询</a> <a href="${pageContext.servletContext.contextPath }/toAddUserPage"
								class="list-group-item text-center "
								style="border: none; font-size: 18px">添加用户</a>

						</div>
					</div>
				</div>
				<div class="panel panel-success">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"
								style="font-size: 20px; text-decoration: none;"> 部门管理 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse ">
						<div class="panel-body" style="padding-left: 0; padding-right: 0;">
							<a href="${pageContext.servletContext.contextPath }/queryApart" class="list-group-item text-center"
								style="border: none; font-size: 18px">部门查询</a> 
								<a href="${pageContext.servletContext.contextPath }/toaddapart"
								class="list-group-item text-center "
								style="border: none; font-size: 18px">添加部门</a>
						</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"
								style="font-size: 20px; text-decoration: none;"> 职位管理 </a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body" style="padding-left: 0; padding-right: 0;">
							<a href="${pageContext.servletContext.contextPath }/queryJob" class="list-group-item text-center"
								style="border: none; font-size: 18px">职位查询</a> <a href="${pageContext.servletContext.contextPath }/toaddjob"
								class="list-group-item text-center "
								style="border: none; font-size: 18px">添加职位</a>
						</div>
					</div>
				</div>
				<div class="panel panel-warning">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseFour"
								style="font-size: 20px; text-decoration: none;"> 员工管理 </a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse">
						<div class="panel-body" style="padding-left: 0; padding-right: 0;">
							<a href="${pageContext.servletContext.contextPath }/queryEmployee" class="list-group-item text-center"
								style="border: none; font-size: 18px">员工查询</a> <a href="${pageContext.servletContext.contextPath }/toAddEmployeePage"
								class="list-group-item text-center "
								style="border: none; font-size: 18px">添加员工</a>
						</div>
					</div>
				</div>
			<!-- 	<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseFive"
								style="font-size: 20px; text-decoration: none;"> 公告管理 </a>
						</h4>
					</div>
					<div id="collapseFive" class="panel-collapse collapse">
						<div class="panel-body" style="padding-left: 0; padding-right: 0;">
							dd
						</div>
					</div>
				</div> -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseSix"
								style="font-size: 20px; text-decoration: none;"> 下载中心 </a>
						</h4>
					</div>
					<div id="collapseSix" class="panel-collapse collapse">
						<div class="panel-body" style="padding-left: 0; padding-right: 0;">
							<a href="${pageContext.servletContext.contextPath }/queryFile" class="list-group-item text-center"
								style="border: none; font-size: 18px">文档查询</a> <a href="${pageContext.servletContext.contextPath }/toAddFilePage"
								class="list-group-item text-center "
								style="border: none; font-size: 18px">上传文档</a>	
						</div>
					</div>
				</div>
			</div>
		</div>