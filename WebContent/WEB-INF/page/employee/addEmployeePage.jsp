<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	$(function (){
		$('<%="#collapseFour"%>').addClass("in");
		
		
		$("#cardid").blur(function(){
			$.ajax("${pageContext.request.contextPath}/checkcard",// 发送请求的URL字符串。
					{
					dataType : "json", // 预期服务器返回的数据类型。
		   			type : "post", //  请求方式 POST或GET
				    contentType:"application/json", //  发送信息至服务器时的内容编码类型
				   // 发送到服务器的数据。
				   data:JSON.stringify({'card' : $("#cardid").val()}),
				   async:  true , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
				   // 请求成功后的回调函数。
				   success :function(data){
					   alert(data['pos']);
				   },
				   // 请求出错时调用的函数
				   error:function(){
					   alert("数据发送失败");
				   }
			}); 
		});
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
			<li><a href="#">员工管理</a></li>
			<li class="active">添加员工</li>
		</ol>


		<!--表单-->
		<s:form style="margin-bottom:20px"  action="addEmployee" method="post">
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 姓名：</span>
						<s:textfield class="form-control" id="name" name="insertEmployee.name"/>
					</div>
				</div>
				<div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
					<div class="input-group">
						<span class="input-group-addon">身份证号码：</span> 
						<s:textfield class="form-control" id="cardid" name="insertEmployee.cardid"/>
					</div>
				</div>
			</div>
			
			
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 性别：</span> 
						<%-- <select name="sex" class="form-control">
							<option value="男" >男</option>
							<option value="女">女</option>
						</select> --%>
						<s:select name="insertEmployee.sex" class="form-control" list="#{'男':'男','女':'女' }"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">职位：</span> 
					<%-- 	<select name="job" class="form-control">
							<c:forEach items="${jobs }" var="job">
								<option value="${job.id }">${job.name }</option>
							</c:forEach>
						</select> --%>
						<s:select name="insertEmployee.jobid" class="form-control" list="jobs" listKey="id" listValue="name"></s:select>
					</div>
				</div>
			</div>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 学历：</span>
						<s:textfield class="form-control"  name="insertEmployee.education"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">邮箱：</span>
						<s:textfield class="form-control"  name="insertEmployee.email"/>
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 手机：</span>
						<s:textfield class="form-control"  name="insertEmployee.phone"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">电话：</span>
						<s:textfield class="form-control"  name="insertEmployee.tel"/>
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 政治面貌：</span> 
						<s:textfield class="form-control"  name="insertEmployee.party"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">QQ号码：</span>
						<s:textfield class="form-control"  name="insertEmployee.qq"/>
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 联系地址：</span> 
						<s:textfield class="form-control"  name="insertEmployee.address"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">邮政编码：</span>
						<s:textfield class="form-control"  name="insertEmployee.postcode"/>
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 出生日期：</span>
						<input type="date" class="form-control" name="insertEmployee.birthday" />
						<%-- <s:datetextfield class="form-control" format="yyyy-MM-dd" name="birthday"/> --%>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">民族：</span>
						<s:textfield class="form-control"  name="insertEmployee.race"/>
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon"> 所学专业：</span>
						<s:textfield class="form-control"  name="insertEmployee.major"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">爱好：</span> 
						<s:textfield class="form-control"  name="insertEmployee.hobby"/>
					</div>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-bottom:20px">
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">备注：</span>
						<s:textfield class="form-control"  name="insertEmployee.remark"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-5 col-xs-6">
					<div class="input-group">
						<span class="input-group-addon">所属部门：</span> 
						<%-- <select name="depart" class="form-control">
							<c:forEach items="${departs }" var="depart">
								<option value="${depart.id }">${depart.name }</option>
							</c:forEach>
						</select> --%>
						<s:select name="insertEmployee.departid" class="form-control" list="departs" listKey="id" listValue="name"></s:select>
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