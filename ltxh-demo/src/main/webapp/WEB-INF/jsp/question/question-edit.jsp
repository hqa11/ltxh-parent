<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		<link href="<%=basePath%>resources/common/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/common/css/style.css" />
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<title>农业协会管理系统</title><link rel="shortcut icon" href="<%=basePath%>resources/common/images/icon.ico"/>
	</head>

	<body>
		<div class="pd-20">
			<form action="" method="post" class="form form-horizontal" id="form-articl-add">
			<input type="hidden" value="${question.q_id }" name="q_id">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>问卷标题：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${question.q_name }" placeholder="请输入消息标题" maxlength="50" id="q_name" name="q_name" datatype="*" nullmsg="问卷标题不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>问卷描述：</label>
					<div class="formControls col-4">
						<textarea value=""  class="textarea" placeholder="请输入问卷描述...." rows="12" cols="60"  maxlength="500" id="q_desc" name="q_desc" datatype="*" nullmsg="问卷描述不能为空！">${question.q_desc }</textarea>
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<button onClick="saveNews();" class="btn btn-primary radius" type="button">
							<i class="Hui-iconfont">&#xe632;</i> 保存
						</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
		<script src="<%=basePath %>resources/util/yeUtil.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.admin.js"></script>	
		<script type="text/javascript" src="<%=basePath%>resources/common/ueditor1-43/1.4.3/ueditor.config.js"></script>
	    <script type="text/javascript" src="<%=basePath%>resources/common/ueditor1-43/1.4.3/ueditor.all.min.js"></script>
	    <script type="text/javascript" src="<%=basePath%>resources/common/ueditor1-43/1.4.3/lang/zh-cn/zh-cn.js"></script>	
		<script src="<%=basePath%>resources/common/js/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
	</body>
    <script type="text/javascript">

//保存事件----------------------------------------------start----------------------------------------------
function saveNews(){
//验证表单
var form_result = check_form("#form-articl-add");
if(!form_result){
return;
}
//验证通过
//1.准备请求参数
var params = $("#form-articl-add").serialize();
//2.发送请求
$.post("updateQuestion.action",params,function(data){
ajaxCallBack(data,reloadParent);
},"text");
}
</script>
</html>