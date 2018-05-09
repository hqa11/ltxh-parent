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
		<nav class="breadcrumb">
			<i class="Hui-iconfont">&#xe67f;</i> 首页
			<span class="c-gray en">&gt;</span> 基础管理
			<span class="c-gray en">&gt;</span> 意见反馈列表
			<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新">
				<i class="Hui-iconfont btn-refresh">&#xe68f;</i>
			</a>
		</nav>
        <div style="height:20px;"></div>
		<div class="pd-20">
			<table class="table table-border table-bordered table-bg" id="table">
				<thead>
					<tr>
						<th scope="col" colspan="8" style="background-color: #FFFFFF;">
							<div class="text-c pd-20 cl f-14 font-w100">
								<form action="" method="post" id="search-box">
									<div class="row cl">
										<div class="col-4">
											反馈时间:
											<input type="text" value="${start }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'end\')||\'%y-%M-%d\'}'})" name="start" id="start" class="input-text Wdate" placeholder="开始时间" readonly="readonly" style="width: 160px;">
													-
											<input type="text" value="${end }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2200-10-01',minDate:'#F{$dp.$D(\'start\')}'})" name="end" id="end" class="input-text Wdate" placeholder="结束时间" readonly="readonly" style="width: 160px;">
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
						<th colspan="8">意见反馈列表</th>
					</tr>
					<tr class="text-c">
						<th width="40">ID</th>
						<th width="50">反馈人</th>
						<th width="100">手机号码</th>
						<th width="80">反馈时间</th>
						<th width="50">反馈内容</th>
						<th width="50">处理状态</th>
						<th width="150">处理备注</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageBean.content }" varStatus="i" var="one">
				<tr class="text-c">
						<td>${i.index+1+pageBean.pageable.pageSize*(pageBean.pageable.pageNumber-1) }</td>
						<td>${one.fd_name }</td>
						<td>${one.fd_phone}</td>
						<td>${one.fd_timef }</td>
						<td><a href="javascript:void(0);" onClick="lookContent('${one.fd_content}')">查看</a></td>
						<td>
						<c:if test="${one.fd_state == 0 }">待处理</c:if>
						<c:if test="${one.fd_state == 1 }">已查看</c:if>
						<c:if test="${one.fd_state == 2 }">已反馈</c:if>
						</td>
						<td>
						<c:if test="${one.fd_state == 2 }">${one.fd_backup }</c:if>
						</td>
						<td class="td-manage">
							<c:if test="${one.fd_state == 0 }">
								<a title="标记阅读" href="javascript:;" onClick="markRead('${one.fd_id}')" class="pd-5"  style="text-decoration:none" >
									<i class="Hui-iconfont">&#xe6df;</i>标记阅读
								</a>
							</c:if>	
							<c:if test="${one.fd_state != 2 }">
							<a title="处理反馈" href="javascript:;" onClick="reBack('${one.fd_id}')" class="pd-5"  style="text-decoration:none" >
								<i class="Hui-iconfont">&#xe6e1;</i>处理反馈
							</a>
							</c:if>
							<a class="pd-5" title="删除" href="javascript:;" onclick="del('${one.fd_id}')"" class="ml-5" style="text-decoration:none">
								<i class="Hui-iconfont">&#xe6e2;</i>删除
							</a>
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
   function lookContent(ct){
	   layer.confirm(ct);
   }
   
   /*扩展方法propem依赖于layer.ext.js*/
	layer.config({
	  extend: 'extend/layer.ext.js'
	})
   
   function reBack(id){
	 layer.prompt({title: '请输入反馈意见',formType: 2}, function(pass, index){
	   var param = new Object();
	   param.fd_id = id;
	   param.fd_state = 2;
	   param.fd_backup = pass;
	   postHandle(param);
	 	  });
    }
   
   
   function markRead(id){
	   layer.confirm("确定要标记为阅读吗?",function(){
	   var param = new Object();
	   param.fd_id = id;
	   param.fd_state = 1;
	   postHandle(param);
	   });
   }
   
   function del(id){
	   layer.confirm("确定要删除吗?",function(){
	   var param = new Object();
	   param.fd_id = id;
	   param.fd_is_del = 1;
	   postHandle(param);
	   });
   }
   
   function postHandle(param){
	   $.post("feedback/handleFeedBack.action",param,function(data){
  		   	ajaxCallBack(data,reloadThis);
  		   	},"text");
   }
   
   </script>
   <script type="text/javascript" src="<%=basePath%>resources/util/pager.js"></script>
</html>