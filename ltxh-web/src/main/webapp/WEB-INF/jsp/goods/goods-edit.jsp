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
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>供求标题：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${goods.g_title }" placeholder="请输入供求标题" maxlength="50" id="g_title" name="g_title" datatype="*" nullmsg="供求标题不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>供求别名：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${goods.g_other_name }" placeholder="请输入供求别名" id="g_other_name" name="g_other_name"  datatype="*" nullmsg="供求别名不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>供求品种：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${goods.g_goods_type }" placeholder="请输入供求品种" id="g_goods_type" name="g_goods_type"  datatype="*" nullmsg="供求品种不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>联系人：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${goods.g_contact_person }" placeholder="请输入联系人" id="g_contact_person" name="g_contact_person"  datatype="*" nullmsg="联系人不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>联系电话：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${goods.g_contact_phone }" placeholder="请输入联系电话" id="g_contact_phone" name="g_contact_phone"  datatype="/^1[3,4,5,7,8]\d{9}$/" nullmsg="联系电话不能为空！"  errormsg="请输入正确的电话号码!">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>产地：</label>
					<div class="formControls col-1">
					<span class="select-box">
					<select name="g_place_province_id" id="g_place_province_id" class="select">
					</select>
					</span> 
					</div> 
					<div class="formControls col-1">
					<span class="select-box">
					<select name="g_place_city_id" id="g_place_city_id" class="select">
					
					</select>
					</span> 
					</div> 
					<div class="formControls col-1">
					<span class="select-box">
					<select name="g_place_area_id" id="g_place_area_id" class="select">
					
					</select>
					</span>
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>供求类型：</label>
					<div class="formControls col-1">
					<span class="select-box">
					<select name="g_type" id="g_type" class="select">
						<option value="1">供应信息</option>
						<option value="2">求购信息</option>
						<option value="3">协会发布</option>
					</select>
					</span>
					</div>
					<div class="col-2"></div>
				</div>
					<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>单位：</label>
					<div class="formControls col-1">
						<input type="text" class="input-text" value="${goods.g_unit }" placeholder="请输入供求单位" id="g_unit" name="g_unit"  datatype="*" nullmsg="供求单位不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl cate-img articl-cate" id="imageDiv">
					<label class="form-label col-2"><span class="c-red">*</span>供求图片(<font color="red">双击移除</font>)：</label>
					<div class="formControls col-10" id="img-upload">
						<%-- <span class="img-upload" >
							<img  src="<%=basePath%>resources/common/images/icon-add.png" class="thumbnail" id="artImg">
						</span>  --%>
						<c:forEach items="${imgs}" var="img" >
						<span class="img-upload" ondblclick = "remove(this)">
								<img  src="<%=basePath %>upload/${img.gi_img_path}" img-url="${img.gi_img_path}" class="thumbnail">
						</span>
					    </c:forEach>
						<span class="btn-upload">
						  <a href="javascript:void();" class="btn btn-primary radius" style="margin-left:5px;"><i class="iconfont">&#xe624;</i> 浏览图片</a>
						  <input type="file" id="input-file" multiple name="file_0" class="input-file" onchange="return check_file(2*1024*1024,'input-file',this)">
						  <span class="Validform_checktip"></span>
						</span>
						<div class="c-red">图片规格:750*400且大小不能大于2MB </div>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>产品信息：</label>
					<div class="formControls col-4">
						<input type="text" class="input-text" value="${goods.g_goods_msg }" placeholder="请输入产品信息" id="g_goods_msg" name="g_goods_msg"  datatype="*" nullmsg="产品信息不能为空！">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>供应价格：</label>
					<div class="formControls col-2">
						<input type="text" class="input-text" value="${goods.g_goods_price }" placeholder="请输入供应价格" id="g_goods_price" name="g_goods_price"  datatype="/^[1-9]\d*(\.\d{1,2})?$|0\.\d{1,2}$/" nullmsg="供应价格不能为空！" errormsg="请输入正确的金额!">
					</div>
					<div class="col-2"></div>
				</div>
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<button onClick="updateGoods();" class="btn btn-primary radius" type="button">
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
		<script src="<%=basePath%>resources/common/js/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
		 <script type="text/javascript" src="<%=basePath%>resources/util/area.js"></script>
	</body>
	  <script type="text/javascript">
	 $(function(){
	  initArea("g_place_province_id","g_place_city_id","g_place_area_id");
      //回显一下
      $("#g_type").val('${goods.g_type}');
      //处理省市区
      $("#g_place_province_id").val('${goods.g_place_province_id}').change();
      $("#g_place_city_id").val('${goods.g_place_city_id}').change();
      $("#g_place_area_id").val('${goods.g_place_area_id}');
	 });
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

	
	//保存事件----------------------------------------------start----------------------------------------------
	function updateGoods(){
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
	obj.gi_img_path = $(this).attr("img-url");
	picArray.push(obj);
	});
	var picStr = JSON.stringify(picArray);
	params+="&picStr="+picStr+"&g_id="+'${goods.g_id}';
	//2.发送请求
	$.post("updateGoods.action",params,function(data){
	ajaxCallBack(data,reloadParent);
	},"text");
	}

</script>
</html>