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
			<span class="c-gray en">&gt;</span> 认证申请
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
									<%-- <div class="col-3">
											活动时间:
											<input type="text" value="${start }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end-date\')||\'%y-%M-%d\'}'})" name="star_date" id="star-date" class="input-text Wdate" placeholder="开始时间" readonly="readonly" style="width: 160px;">
													-
											<input type="text" value="${end }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'2200-10-01',minDate:'#F{$dp.$D(\'star-date\')}'})" name="end_date" id="end-date" class="input-text Wdate" placeholder="结束时间" readonly="readonly" style="width: 160px;">
									</div> --%>
										<div class="col-2">
											公司名称:
											<input type="text" id="uca_company_name" name="uca_company_name" class="input-text" style="width: 150px;" value="${uca_company_name}" />
										</div>
										<div class="col-3">
							<!-- 根据状态搜索 -->
										
										<div class="row cl"  style="padding-left: 10px;">
											<label class="form-label col-3">认证状态：</label>
											<div class="formControls col-4">
											<span class="select-box">
											<select name="uca_state" id="uca_state" class="select">
												<option value="-1" selected="selected">请选择</option>
												<option  value="0">待审核</option>
												<option  value="1">认证通过</option>
												<option  value="2">认证失败</option>
											</select>
											</span>
											</div>
												<div class="col-2"></div>
										</div>
							<!-- 根据状态搜索 -->			
											<button type="button" class="btn btn-success" onclick="search()" name="" style="margin-left: 210px;"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
										</div>
									</div>
								</form>
							</div>
						</th>
					</tr>
					<tr>
						<th colspan="8">活动模板列表</th>
					</tr>
					<tr class="text-c">
						<th width="40">ID</th>
						<th width="100">公司名称</th>
						<th width="100">用户名称</th>
						<th width="100">营业执照</th>
						<th width="80">状态</th>
						<th width="50">申请时间</th>
						<th width="100">认证时间</th>
						<th width="200">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageBean.content }" varStatus="i" var="one">
				<tr class="text-c">
						<td>${i.index+1+pageBean.pageable.pageSize*(pageBean.pageable.pageNumber-1) }</td>
						<td>${one.uca_company_name }</td>
							<td>${one.username }</td>
						<td>${one.uca_business_license }</td>
						<td>
						<c:if test="${one.uca_state == 0 }">待审核</c:if>
						<c:if test="${one.uca_state == 1 }">已通过</c:if>
						<c:if test="${one.uca_state == 2 }">未通过</c:if>
						</td>
						<td>${one.uca_create_timef }</td>
						<td>
						<c:if test="${one.uca_state != 0 }">${one.uca_auth_timef }</c:if>
						</td>
						<td class="td-manage">
						<c:if test="${one.uca_state == 0 }">
						    <a class="pd-5" title="查看图片" href="javascript:;" onclick="look_pic('${one.uca_id}')" class="ml-5" style="text-decoration:none">
								<i class="Hui-iconfont">&#xe6d3;</i>查看图片
							</a>
							<a title="审核" href="javascript:;" onClick="check_auth('${one.uca_id}')" class="pd-5"  style="text-decoration:none" >
								<i class="Hui-iconfont">&#xe6df;</i>审核
							</a>
						</c:if>
							<a class="pd-5" title="删除" href="javascript:;" onclick="one_del(this,'${one.uca_id}')" class="ml-5" style="text-decoration:none">
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
   $(function(){
   $("#uca_state").val('${uca_state}');
   });
   	//删除活动--------------------------------------------start------------------------------------------------------
   	function one_del(obj,id){
   	layer.confirm("确定要删除吗?",function(){  	
   	$.post("user/delAuth.action",{uca_id:id},function(data){
   	ajaxCallBack(data,reloadThis);
   	},"text");
   	});
   	}
   	//--------------------------------------------------end--------------------------------------------------------
   	
   	function check_auth(uca_id){
   	var index = layer.confirm("您确定要通过审核吗?",{btn:['通过','拒绝'],closeBtn:1},function(){
   		doAgree(uca_id);
   		layer.close(index);
   	},function(){
   		doRefuse(uca_id);   
   		layer.close(index);
   	});	
   	}
   	
   	//同意审核
   	function doAgree(uca_id){
   		$.post("user/handleAuth.action",{uca_id:uca_id,uca_state:1},function(data){
   		   	ajaxCallBack(data,reloadThis);
   		   	},"text");
   	}
   	
	
	/*扩展方法propem依赖于layer.ext.js*/
	layer.config({
	  extend: 'extend/layer.ext.js'
	})
   	
	//拒绝审核
   	function doRefuse(uca_id){
   	 layer.prompt({title: '请输入拒绝原因',formType: 2}, function(pass, index){
   		$.post("user/handleAuth.action",{uca_id:uca_id,uca_state:2,uca_failure_reason:pass},function(data){
   		   	ajaxCallBack(data,reloadThis);
   		   	},"text");
 	  });
   	}
   	
	//查看图片
	function look_pic(uca_id){
		$.post("user/lookAuthPic.action",{uca_id:uca_id,web_path:pic_upload_path+"/"},function(data){
			 layer.photos({
				    shade: [0.1, '#eee'],
				    photos: data //格式见API文档手册页
				    ,shift:0//0-6的选择，指定弹出图片动画类型，默认随机
				  });
   		   	},"json");
	}
   	
   </script>
   <script type="text/javascript" src="<%=basePath%>resources/util/pager.js"></script>
</html>