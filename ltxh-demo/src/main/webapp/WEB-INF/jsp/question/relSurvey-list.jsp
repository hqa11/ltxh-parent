<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<!--[if lt IE 9]>
		<script type="text/javascript" src="lib/html5.js"></script>
		<script type="text/javascript" src="lib/respond.min.js"></script>
		<script type="text/javascript" src="lib/PIE_IE678.js"></script>
		<![endif]-->
		<link href="<%=basePath%>resources/common/css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resources/common/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resources/common/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resources/common/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/common/css/style.css"/>
		<!--[if IE 6]>
		<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<title>农业协会管理系统</title><link rel="shortcut icon" href="<%=basePath%>resources/web/images/icon.ico"/>
	</head>

	<body>
		<!-- <nav class="breadcrumb">
			<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新">
				<i class="Hui-iconfont btn-refresh">&#xe68f;</i>
			</a>
		</nav> -->
        <div style="height:20px;"></div>
		<div class="pd-20">
			<table class="table table-border table-bordered table-bg" id="table">
				<thead>
					 <tr>
						<th scope="col" colspan="8" style="background-color: #FFFFFF;">
							<div class="text-c pd-20 cl f-14 font-w100">
								<form action="" method="post" id="search-box">
								<input type="hidden" name="q_id" value="${q_id }">
									<div class="row cl">
										<div class="col-2">
										问题:
										<input type="text" value="${qs_subject}"  name="qs_subject" id="qs_subject" class="input-text"  style="width: 120px;">
										</div>
										<div class="col-1">
											<span class="select-box">
											<select name="exist" id="exist" class="select">
									            <option value="">全部</option>
												<option value="0">未关联</option>
												<option value="1">已关联</option>
											</select>
											</span>
										</div>
										<div class="col-3">
											<button type="button" class="btn btn-success" onclick="search()" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
										</div>
									</div>
								</form>
							</div>
						</th>
					</tr> 
					<tr>
						<th colspan="6">问卷列表</th>
					</tr>
					<tr class="text-c">
					    <th width="5"> </th>
						<th width="40">ID</th>
						<th width="300">问题</th>
						<th width="50">关联状态</th>
						<th width="100">创建时间</th>
						<th width="50">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageBean.content }" varStatus="i" var="one">
				<tr class="text-c">
				        <td><input type="checkbox" name="rela" value="${one.exist}" disabled <c:if test="${one.exist == 1 }"> checked="checked" </c:if> /></td>
						<td>${i.index+1+pageBean.pageable.pageSize*(pageBean.pageable.pageNumber-1) }</td>
						<td>${one.qs_subject }</td>
						<td>${one.exist == 1 ? '已关联':'未关联' }</td>
						<td>${one.qs_create_timef }</td>
						<td class="td-manage">
							<c:if test="${one.exist == 1 }">
						    <a title="取消关联" href="javascript:;" onClick="rela('${one.qs_id}',0)" class="pd-5"  style="text-decoration:none" >
								<i class="Hui-iconfont">&#xe60b;</i>取消关联
							</a>
							</c:if>
							<c:if test="${one.exist != 1 }">
						    <a title="关联" href="javascript:;" onClick="rela('${one.qs_id}',1)" class="pd-5"  style="text-decoration:none" >
								<i class="Hui-iconfont">&#xe62b;</i>关联
							</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		<!--分页开始-->
		<jsp:include page="../common/pager.jsp"></jsp:include>
		 <!--分页结束-->
		</div>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
		<script src="<%=basePath %>resources/util/yeUtil.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/icheck/jquery.icheck.min.js"></script>
		<script src="<%=basePath%>resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.admin.js"></script>
		<!--商家管理js-->
	</body>
   <script type="text/javascript">
   $(function(){
	   $("#exist").val('${exist}');
   })
   
   function rela(id,state){
	   layer.confirm(state == 1 ? "确定要关联吗?":"确定要取消关联吗?",function(){
		  	$.post("question/relSurvey.action",{qs_id:id,q_id:'${q_id}',op:state},function(data){
		  	   	ajaxCallBack(data,reloadThis);
		  	   	},"text"); 
	      });  
   }
   </script>
   <script type="text/javascript" src="<%=basePath%>resources/util/pager.js"></script>
</html>