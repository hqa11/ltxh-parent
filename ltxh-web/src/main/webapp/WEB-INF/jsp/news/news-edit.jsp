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
				<input type="hidden"  value="${news.news_id}" id="news_id" name="news_id">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>新闻标题：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${news.news_title}" placeholder="请输入新闻标题" maxlength="50" id="news_title" name="news_title" datatype="*" nullmsg="新闻标题不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span> 新闻简介：</label>
					<div class="formControls col-4">
						<input type="text" class="input-text" value="${news.news_desc }" placeholder="请输入新闻简介" id="news_desc" name="news_desc"  datatype="*" nullmsg="新闻简介不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>发布人：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${news.news_release_person }" placeholder="请输入发布人" id="news_release_person" name="news_release_person"  datatype="*" nullmsg="发布人不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
						<label class="form-label col-2"><span class="c-red">*</span>新闻类型：</label>
						<div class="formControls col-1">
						<span class="select-box">
						<select name="news_type" id="news_type" class="select">
									<option value="0">普通新闻</option>
									<option value="1">政策解读</option>
						</select>
						</span>
						</div>
							<div class="col-2"></div>
				</div>
				<div class="row cl cate-img articl-cate" id="imageDiv">
					<label class="form-label col-2">新闻图片(<font color="red">双击移除</font>)：</label>
					<div class="formControls col-10" id="img-upload">
					<c:forEach items="${imgs}" var="img" >
					<span class="img-upload" ondblclick = "remove(this)">
							<img  src="<%=basePath %>upload/${img.ni_path}" img-url="${img.ni_path}" class="thumbnail">
					</span>
					</c:forEach>
						<%-- <span class="img-upload" >
							<img  src="<%=basePath%>resources/common/images/icon-add.png" class="thumbnail" id="artImg">
						</span>  --%>
						<span class="btn-upload">
						  <a href="javascript:void();" class="btn btn-primary radius" style="margin-left:5px;"><i class="iconfont">&#xe624;</i> 浏览图片</a>
						  <input type="file" id="input-file" multiple name="file_0" class="input-file" onchange="return check_file(2*1024*1024,'input-file',this)">
						  <span class="Validform_checktip"></span>
						</span>
						<div class="c-red">图片规格:750*400且大小不能大于2MB </div>
					</div>
				</div>
				<div class="row cl cate-img articl-cate" id="fjDiv">
					<label class="form-label col-2">新闻附件(<font color="red">双击移除</font>)：</label>
					<div class="formControls col-10" id="fj-upload">
					<c:forEach items="${fjs}" var="fj" >
                    <span class="fj-upload" fileurl="${fj.na_attachment_path }" ondblclick ="remove(this)">${fj.na_name }</span>					
					</c:forEach>
						<span class="btn-upload">
						  <a href="javascript:void();" class="btn btn-primary radius" style="margin-left:5px;"><i class="iconfont">&#xe624;</i> 浏览文件</a>
						  <input type="file" id="input-file2" multiple name="file_0" class="input-file" onchange="return check_file2(100*1024*1024,'input-file2',this)" >
						  <span class="Validform_checktip"></span>
						</span>
						<div class="c-red">附件规格:不能大于100MB </div>
					</div>
				</div>
				<div class="row cl">
						<label class="form-label col-2"><span class="c-red">*</span>详情：</label>
						<div class="formControls col-8">
							<script id="editor" type="text/plain" style="width:100%;height:400px;">${news.news_detail}</script>
							<span class="Validform_checktip"></span>
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
    $(function(){
    $("#news_type").val('${news.news_type}');
    });
    
    
    /*加载编辑框*/
			var ue = UE.getEditor('editor');
			/*获取编辑框内容*/
			function getContent(){
				var txt = ue.getContent();
				return txt;
			}
//为图片添加双击事件-----------------------------------start-----------------------------------------------
function remove(obj){
  $(obj).remove();
  layer.msg("已移除!");
}
//-----------------------------------------------end--------------------------------------------------

//图片上传------------------------------------------start------------------------------------------------

	function check_file(size,id,obj){
		common_fileupload(obj,0,0,size/1024/1024,0,0,"/news/pic",function(data){	
		$("#img-upload").prepend("<span class='img-upload' ondblclick ='remove(this)'>"+
							"<img  src='"+pic_upload_path + data+"' img-url='"+data+"' class='thumbnail'>"+
						"</span>");
        $("#input-file").val("");	
});
	}
//------------------------------------------------end-------------------------------------------------

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
//整理图片
var picArray = new Array();
$(".thumbnail").each(function(){
var obj = new Object();
obj.ni_path = $(this).attr("img-url");
picArray.push(obj);
});
var picStr = JSON.stringify(picArray);
//整理附件
var fjArray = new Array();
$(".fj-upload").each(function(){
var obj = new Object();
obj.na_name = $(this).html();
obj.na_attachment_path = $(this).attr("fileurl");
fjArray.push(obj);
});
var fjStr = JSON.stringify(fjArray);
var news_detail = getContent();
params+="&picStr="+picStr+"&fjStr="+fjStr+"&news_detail="+news_detail;
//2.发送请求
$.post("updateNews.action",params,function(data){
ajaxCallBack(data,reloadParent);
},"text");
}

//----------------------------------------------------end-----------------------------------------------
function check_img(id){
if(!isnotnull($(id).attr('img-url'))){
layer.msg("请上传图片!");
return false;
}
return true;
}
//-----------------------------------------------------end----------------------------------------------
</script>
</html>