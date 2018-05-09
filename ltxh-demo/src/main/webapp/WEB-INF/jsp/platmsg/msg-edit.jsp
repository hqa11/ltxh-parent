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
		<style type="text/css">
		.img-upload img{
		width:100%;
		height:100%;
		}
		.img-upload {
		width:100px;
		height:100px;
		}
		.fj-upload{
		padding:5px;
		height:50px;
		border:1px solid #eee;
		border-radius:2px;
		color: green;
		margin-right: 10px;
		}
		.fj-upload:hover{
		cursor: pointer;
		background:#eee;
		}
		</style>
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<title>农业协会管理系统</title><link rel="shortcut icon" href="<%=basePath%>resources/common/images/icon.ico"/>
	</head>

	<body>
		<div class="pd-20">
			<form action="" method="post" class="form form-horizontal" id="form-articl-add">
			<input type="hidden" name="pm_id" value="${msg.pm_id }">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>消息标题：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${msg.pm_msg_title }" placeholder="请输入消息标题" maxlength="50" id="pm_msg_title" name="pm_msg_title" datatype="*" nullmsg="消息标题不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>消息内容：</label>
					<div class="formControls col-4">
						<textarea value=""  class="textarea" placeholder="请输入消息内容...." rows="12" cols="60"  maxlength="500" id="pm_msg_content" name="pm_msg_content" datatype="*" nullmsg="消息内容不能为空！">${msg.pm_msg_content }</textarea>
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl cate-img articl-cate" id="fjDiv">
					<label class="form-label col-2"><span class="c-red">*</span>消息附件(<font color="red">双击移除</font>)：</label>
					<div class="formControls col-10" id="fj-upload">
						<%-- <span class="img-upload" >
							<img  src="<%=basePath%>resources/common/images/icon-add.png" class="thumbnail" id="artImg">
						</span>  --%>
						<c:forEach items="${fjs}" var="fj" >
                        <span class="fj-upload" fileurl="${fj.pma_attach_path }" ondblclick ="remove(this)">${fj.pma_attach_name }</span>					
					    </c:forEach>
						<span class="btn-upload">
						  <a href="javascript:void();" class="btn btn-primary radius" style="margin-left:5px;">+添加附件</a>
						  <input type="file" id="input-file2" multiple name="file_0" class="input-file" onchange="return check_file2(100*1024*1024,'input-file2',this)" >
						  <span class="Validform_checktip"></span>
						</span>
						<div class="c-red">附件规格:不能大于100MB </div>
					</div>
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
    function remove(obj){
  $(obj).remove();
  layer.msg("已移除!");
}
//附件上传------------------------------------------start------------------------------------------------

	function check_file2(size,id,obj){
		common_Docupload(obj,size/1024/1024,"/news/doc",function(data){	
		$("#fj-upload").prepend("<span class='fj-upload' fileurl='"+data.url+"' ondblclick ='remove(this)'>"+data.filename+"</span>");
        $("#input-file2").val("");	
});
	}
//------------------------------------------------end-------------------------------------------------

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
//整理附件
var fjArray = new Array();
$(".fj-upload").each(function(){
var obj = new Object();
obj.pma_attach_name = $(this).html();
obj.pma_attach_path = $(this).attr("fileurl");
fjArray.push(obj);
});
var fjStr = JSON.stringify(fjArray);
params+="&fjStr="+fjStr;
//2.发送请求
$.post("updateMsg.action",params,function(data){
ajaxCallBack(data,reloadParent);
},"text");
}
</script>
</html>