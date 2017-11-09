<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户编辑</title>
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

</head>
<body>

<s:form action="updateUser" method="post">
	<table style="width:500px; margin:44px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
		<tr>
				<td>id</td>
				<td>用户名</td>
				<td>登录账户</td>
				<td>登录密码</td>
				<td>状态</td>
				
			</tr>
			<tr>
				<td><s:property value="#request.editorUser.id"/><input type="hidden" name="insertUser.Id" value="<s:property value="#request.editorUser.id"/>"></td>
				<td><input type="text" value="<s:property value="#request.editorUser.username"/>" name="insertUser.username"></td>
				<td><input type="text" value="<s:property value="#request.editorUser.loginname"/>" name="insertUser.loginname"></td>
				<td><input type="text" value="<s:property value="#request.editorUser.password"/>" name="insertUser.PASSWORD"></td>
				<td><input type="text" value="<s:property value="#request.editorUser.status"/>" name="insertUser.STATUS"></td>
			</tr>
			<tr ><td colspan="5"><input class="btn btn-primary form-control"  type="submit" value="更新"></td></tr>
	</table>
</s:form>	
</body>
</html>