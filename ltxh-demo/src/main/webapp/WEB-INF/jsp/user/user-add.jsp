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
	   <!--多图上传插件css  -->	
		<link href="<%=basePath%>resources/common/css/globle.css" />
		<style type="text/css">
		.img-upload img{
		width:100%;
		
		height:100%;
		}
		.img-upload {
		width:150px;
		height:120px;
		}
		</style>
		   <style type="text/css">  
           #allmap {width: 100%;height: 80%;overflow: hidden;margin:0;font-family:"微软雅黑";}  
        </style>  
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<title>农业协会管理系统</title><link rel="shortcut icon" href="<%=basePath%>resources/common/images/icon.ico"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3d6b1dd144584af6a2bb5d0fd1511dc3"></script>  
	</head>

	<body>
	<div>
		<div class="pd-20" style="display: inline-block;width:50%;vertical-align: top;">
			<form  method="post" class="form form-horizontal" id="form-articl-add">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>用户手机：</label>
					<div class="formControls col-4">
						<input type="text" class="input-text" value="" placeholder="请输入用户手机" id="u_phone" name="u_phone" datatype="/^1[3,4,5,7,8]\d{9}$/" nullmsg="用户手机不能为空！" errormsg="请输入正确的手机号码">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>用户昵称：</label>
					<div class="formControls col-4">
						<input type="text" class="input-text" value="" placeholder="请输入用户昵称" id="u_nick_name" name="u_nick_name"  datatype="*" nullmsg="用户昵称不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>公司名称：</label>
					<div class="formControls col-4">
						<input type="text" class="input-text" value="" placeholder="请输入公司名称" id="u_company_name" name="u_company_name"  datatype="*" nullmsg="公司名称不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>公司职称：</label>
					<div class="formControls col-4">
						<input type="text" class="input-text" value="" placeholder="请输入公司职称" id="u_post" name="u_post"  datatype="*" nullmsg="公司职称不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
						<label class="form-label col-2"><span class="c-red">*</span>用户类型：</label>
						<div class="formControls col-3">
						<span class="select-box">
						<select name="u_type" id="u_type" class="select" onchange="showPageDetail();">
									<option value="0">个人</option>
									<option value="1">企业</option>
									<option value="2">协会</option>
						</select>
						</span>
						</div>
							<div class="col-2"></div>
				</div>
				<div class="row cl" id="role"  >
						<label class="form-label col-2"><span class="c-red">*</span>用户角色：</label>
						<div class="formControls col-3">
						<span class="select-box">
						<select name="u_role_id" id="u_role" class="select">
							<option value="-1" selected="selected">请选择</option>
						<c:forEach items="${roles}" var="role">
							<option value="${role.ur_id}">${role.ur_role_name}</option>
						</c:forEach>
						</select>
						</span>
						</div>
							<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>所在地区：</label>
					<div class="formControls col-2">
					<span class="select-box">
					<select name="u_province_id" id="g_place_province_id" class="select">
					</select>
					</span> 
					</div> 
					<div class="formControls col-2">
					<span class="select-box">
					<select name="u_city_id" id="g_place_city_id" class="select">
					
					</select>
					</span> 
					</div> 
					<div class="formControls col-2">
					<span class="select-box">
					<select name="u_area_id" id="g_place_area_id" class="select">
					
					</select>
					</span>
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
						<label class="form-label col-2"><span class="c-red">*</span>用户性别：</label>
						<div class="formControls col-3">
						<span class="select-box">
						<select name="u_sex" id="u_sex" class="select">
									<option value="1">男</option>
									<option value="2">女</option>
									<option value="0">不详</option>
						</select>
						</span>
						</div>
							<div class="col-2"></div>
				</div>
				<div class="row cl cate-img articl-cate" id="imageDiv"  >
					<label class="form-label col-2"><span class="c-red">*</span>用户头像(<font color="red">双击移除</font>)：</label>
					<div class="formControls col-10">
						<span class="img-upload" id="img-upload">
							<img  src="<%=basePath%>resources/common/images/icon-add.png" class="thumbnail" id="artImg">
						</span>
						<span class="btn-upload">
						  <a href="javascript:void();" class="btn btn-primary radius" style="margin-left:5px;"><i class="iconfont">&#xf0020;</i> 浏览文件</a>
						  <input type="file" id="input-file" multiple name="file_0" class="input-file" onchange="return check_file(2*1024*1024,'input-file',this)" nullimg="分享图片不能为空" errorimg="上传的附件文件不能超过2M！！！">
						  <span class="Validform_checktip"></span>
						</span>
						<div class="c-red">图片规格:750*400且大小不能大于2MB</div>
					</div>
				</div>
	<!--如果添加的用户是企业,那么该部分就显示否则隐藏  start  -->
	<!-- 显示所在位置 -->
		<div class="showAndhide">
		 	<div class="row cl" id="location">
					<label class="form-label col-2"><span class="c-red">*</span>所在位置：</label>
					<div class="formControls col-3">
						<input type="text" class="input-text" value="" placeholder="请选择所在位置" id="u_address_detail" name="u_address_detail" readonly="readonly" onclick="choseAddress(this)">
					</div>
	
					<div class="col-2"></div>
				</div>
				<input type="hidden" class="u_longitude" placeholder="当前位置经度" name="u_longitude" id="u_longitude" value=""/> <input type="hidden" class="u_latitude" placeholder="当前位置纬度" name="u_latitude" id="u_latitude" value=""/>
	<!-- 显示所在位置 end-->			
				<div class="row cl" id="business_license">
					<label class="form-label col-2"><span class="c-red">*</span>组织机构代码：</label>
					<div class="formControls col-3">
						<input type="text" class="input-text" value="" placeholder="请输入" id="uca_business_license" name="uca_business_license"  datatype="*" nullmsg="请输入企业的组织机构代码">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl" id="companytel">
					<label class="form-label col-2"><span class="c-red">*</span>企业电话：</label>
					<div class="formControls col-3">
						<input type="text" class="input-text" value=""  id="u_company_phone" name="u_company_phone" placeholder="请输入企业电话">
					</div>
					<div class="col-2"></div>
				</div>
	<!-- 封面图start -->                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
				
				<div class="row cl cate-img articl-cate" id="imageDiv2">
					<label class="form-label col-2"><span class="c-red">*</span>封面图(<font color="red">双击移除</font>)：</label>
					<div class="formControls col-10">
					   <div id="cover-box">
						<span class="img-upload">
							<img  src="<%=basePath%>resources/common/images/icon-add.png" class="img-cover">
						</span>
						</div>
						<span class="btn-upload">
						  <a href="javascript:void();" class="btn btn-primary radius" style="margin-left:5px;"><i class="iconfont">&#xf0020;</i> 添加图片</a>
						  <input type="file" id="input-file2" multiple name="file_0" class="input-file" onchange="return check_file2(2*1024*1024,'input-file2',this)" nullimg="分享图片不能为空" errorimg="上传的附件文件不能超过2M！！！">
						  <span class="Validform_checktip"></span>
						</span>
						<div class="c-red">图片规格:750*350且大小不能大于2MB </div>
					</div>
				</div>
						
						
	<!-- 封面图end -->
				
	<!-- 添加对公司的描述start -->
				<div class="row cl" id="describtion">
					<label class="form-label col-2"><span class="c-red">*</span>公司介绍：</label>
					<div class="formControls col-10">
						<script id="editor" name="editor" datatype="editor" style="width:auto;height:400px;"></script>
					</div>
					<div class="col-2"></div>
				</div>  
	<!-- 添加对公司的描述end -->
		</div>
	<!--如果添加的用户是企业,那么该部分就显示否则隐藏  end -->
	
			
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<button onClick="saveUser();" class="btn btn-primary radius" type="button">
							<i class="Hui-iconfont">&#xe632;</i> 保存
						</button>
					</div>
				</div>
			</form>
		</div>

	  		 <div class="baidumap"  style="display: inline-block;width:45%;height:400px;vertical-align: top;padding-top: 20px; id="showmap" >	 
		  		<div id="r-result" style="width:auto; height: 50px;font-size: 25px;">请输入:&nbsp;&nbsp;&nbsp;<input type="text" id="suggestId" size="20" value="百度" name="u_address_detail" style="width:200px;height:30px;" /></div>
				<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>	 
				<div id="allmap"></div>
			 </div> 	
		
		</div>
			 <!--引入地图包，地图包网址的ak属性是你在百度地图开放平台上申请的秘钥-->
		 	<script type="text/javascript" src="<%=basePath%>resources/common/ueditor1-43/1.4.3/ueditor.config.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/ueditor1-43/1.4.3/ueditor.all.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/ueditor1-43/1.4.3/lang/zh-cn/zh-cn.js"></script>  
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
		<script src="<%=basePath %>resources/util/yeUtil.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.admin.js"></script>		
		<script src="<%=basePath%>resources/common/js/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/baidumap.js"></script>
		 <script type="text/javascript" src="<%=basePath%>resources/util/area.js"></script>
	
	
	</body>
<script type="text/javascript">

$(function(){  
    showPageDetail();
	initArea("g_place_province_id","g_place_city_id","g_place_area_id");
});


var ue = UE.getEditor('editor');


//为图片添加双击事件-----------------------------------start-----------------------------------------------
$("#artImg").dblclick(function(){
  if(!isnotnull( $("#artImg").attr("img-url"))){
  return;
  }
  //重置src
  $("#artImg").attr("src","");
  //移除属性
  $("#artImg").removeAttr("img-url");
  //移除文件域的值
  $("#input-file").val("");
  
  layer.msg("已移除!");
});
//-----------------------------------------------end------------------------------------------------
//----为上传的多图添加双击事件----
function rmPic(obj){
	//移除属性
	$(obj).remove();
	 layer.msg("已移除!");
		
}

//图片上传------------------------------------------start---------------------------------------------

	function check_file(size,id,obj){
		common_fileupload(obj,0,0,size/1024/1024,0,0,"/user",function(data){	
			$("#img-upload").find("img").attr('src',pic_upload_path + data).attr('img-url',data);
     });
	}
	
	function check_file2(size,id,obj){
		common_fileupload(obj,0,0,size/1024/1024,0,0,"/user/cover",function(data){
		//$("#cover-box").find("img").attr('src',pic_upload_path + data).attr('img-url',data);
		var $img = '<span class="img-upload" ondblclick="rmPic(this)" ><img  src="'+pic_upload_path + data+'" class="img-cover" img-url="'+data+'" ></span>';
		$("#cover-box").append($img);
     });
	}
	
//------------------------------------------------end-------------------------------------------------
//保存事件----------------------------------------------start----------------------------------------------
function saveUser(){
//验证表单
var form_result = check_form("#form-articl-add");
if(!form_result){
return;
}
if(!check_img("#artImg")){
return;
}
//验证通过
//1.准备请求参数
var params = $("#form-articl-add").serialize();
var su_img=$("#artImg").attr("img-url");
var text=ue.getContent();
var u_company_desc=text;
//封面图片数组 
var corver_img= new Array();
$("#cover-box img").not(":first").each(function(){
var url = $(this).attr("img-url");
corver_img.push(url);
});

params+="&u_head_img="+su_img+"&csi_img_path="+corver_img.join(",")+"&u_company_desc="+text;
//2.发送请求
$.post("saveUser.action",params,function(data){
if(data == "400"){
layer.msg("该号码已经存在!");
return;

}
ajaxCallBack(data,reloadParent);
},"text");
}

//----------------------------------------------------end-----------------------------------------------
function check_img(id){
if(!isnotnull($(id).attr('img-url'))){
layer.msg("请上传分享图片!");
return false;
} 
return true;
}
//-----------------------------------------------------end----------------------------------------------

//选择用户类型的时候显示不同的界面
function showPageDetail(){
$("#role,.showAndhide,.baidumap").hide();
var $chose=$("#u_type option:selected").val();
if($chose==2){
  $("#role").show();
}
if($chose==1){
  $(".showAndhide").show();
  $("#role").hide();
 // $(".baidumap").show();
 $("#u_address_detail").click(function(){
  $(".baidumap").show();

  /* //判断用户为企业经纬度不为空
	if($("#u_longitude").val()==null||$("#u_latitude").val()==null){
	layer.msg("所在企业的经纬度不能为空,请点击所选位置!");
	return;
	
} */
  
 });

}
}


</script>


</html>