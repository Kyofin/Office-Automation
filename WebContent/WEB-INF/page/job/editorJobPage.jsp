<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>职业编辑</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/resources/css/bootstrap.min.css">
<script src="${pageContext.servletContext.contextPath }/resources/js/jquery-2.2.4.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath }/resources/js/bootstrap.min.js"></script>
</head>
<body>

<s:form action="updateJob" method="post">
	<table style="width:500px; margin:44px auto" class="table table-striped table-bordered table-hover  table-condensed" align='center' border='1' cellspacing='0'>
		<tr>
				<td>id</td>
				<td>职业名称</td>
				<td>详细内容</td>
				
			</tr>
			<tr>
				<td>
					<s:property value="#request.editorJob.id"/>
					<s:hidden name="insertJob.id" value="%{request.editorJob.id}"/>
				</td>
				<td><s:textfield name="insertJob.jobname" value="%{#request.editorJob.name}"/></td>
				<td><s:textfield name="insertJob.remark" value="%{#request.editorJob.mark}"/></td>
			</tr>
			<tr ><td colspan="5"><input class="btn btn-primary form-control"  type="submit" value="更新"></td></tr>
	</table>
</s:form>	
</body>
</html>