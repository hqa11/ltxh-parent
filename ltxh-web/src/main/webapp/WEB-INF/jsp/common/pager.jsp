<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <div class="pager-box clearfix" id="pageBox">
		<div id="pager">
			<ul class="pages">
				<li class="pgNext " ><a class="pageQ" href="${url}.action?pageCurrent=1">首页</a></li>
				<li class="pgNext " ><a class="pageQ" href="${url}.action?pageCurrent=${pageBean.pageable.pageNumber==1?1:pageBean.pageable.pageNumber-1}">上一页</a></li>
				<c:forEach items="${pageBean.pageable.pageBar}" var="i">
				<c:choose>
				<c:when test="${pageBean.pageable.pageNumber==i}">
				<li class="page-number"><a class="pageQ" href="${url}.action?pageCurrent=${i}"><font color="#d40045">${i}</font></a></li>
				</c:when>
				<c:when test="${pageBean.pageable.pageNumber!=i}">
				<li class="page-number"><a class="pageQ" href="${url}.action?pageCurrent=${i}"><font>${i}</font></a></li>
				</c:when>
				</c:choose>														
				</c:forEach>										
				<li class="pgNext" ><a class="pageQ" href="${url}.action?pageCurrent=${pageBean.pageable.pageNumber==pageBean.totalPages?pageBean.totalPages:pageBean.pageable.pageNumber+1}">下一页</a></li>
				<li class="pgNext" ><a class="pageQ" href="${url}.action?pageCurrent=${pageBean.totalPages}">末页</a></li>
			</ul>
		</div>
	</div>
  </body>
  <script type="text/javascript">
   	var url = '${url}';
  </script>
</html>
