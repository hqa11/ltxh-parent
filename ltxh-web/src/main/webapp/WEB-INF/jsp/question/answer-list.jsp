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
		<style type="text/css">
		.survey{
		height:170px;width:47%;border:1px solid #eee;border-radius:2px;display:inline-block;
		}
		.survey > .item{
		height:90%;width:40%;display:inline-block;overflow:hidden;padding:10px;margin-left:35px;position:relative;
		}
		.jdt{
		margin-left:5px;
		display:inline-block;
		height:15px;
		width:45%;
		background:black;
		border:1px solid #eee;
		}
		label{
		display:inline-block;
		width:20%;
		}
		.mf{
		position:absolute;
		right:0%;
		}
		</style>
	</head>

	<body>
		<!-- <nav class="breadcrumb">
			<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新">
				<i class="Hui-iconfont btn-refresh">&#xe68f;</i>
			</a>
		</nav> -->
		<div style="margin-top: 10px;">
		<div style="margin-left: 50px;"> 
        <legend>${as[0].q_name }</legend>
		<c:forEach items="${as }" var="s" varStatus="i">
		<div class="survey">
		<div class="item"  style="border-right:1px solid #eee;">
		${i.index + 1}.${s.qs_name }
		</div>
		<div class="item">
		<p><label>选 项 A:</label><span class="jdt" per="${s.sum_num == 0 ? 0 : (s.a_num/s.sum_num)}"></span ><span class="mf">&nbsp;&nbsp;占 比 : ${s.sum_num == 0 ? 0 : (s.a_num/s.sum_num*100)}%</span></p>
		<p><label>选 项 B:</label><span class="jdt" per="${s.sum_num == 0 ? 0 : (s.b_num/s.sum_num)}"></span ><span class="mf">&nbsp;&nbsp;占 比 : ${s.sum_num == 0 ? 0 : (s.b_num/s.sum_num*100)}%</span></p>
		<p><label>选 项 C:</label><span class="jdt" per="${s.sum_num == 0 ? 0 : (s.c_num/s.sum_num)}"></span ><span class="mf">&nbsp;&nbsp;占 比 : ${s.sum_num == 0 ? 0 : (s.c_num/s.sum_num*100)}%</span></p>
		<p><label>选 项 D:</label><span class="jdt" per="${s.sum_num == 0 ? 0 : (s.d_num/s.sum_num)}"></span ><span class="mf">&nbsp;&nbsp;占 比 : ${s.sum_num == 0 ? 0 : (s.d_num/s.sum_num*100)}%</span></p>
		<p><label>选 项 E:</label><span class="jdt" per="${s.sum_num == 0 ? 0 : (s.e_num/s.sum_num)}"></span ><span class="mf">&nbsp;&nbsp;占 比 : ${s.sum_num == 0 ? 0 : (s.e_num/s.sum_num*100)}%</span></p>
		</div>
		</div>
		</c:forEach>
		</div>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
		<script src="<%=basePath %>resources/util/yeUtil.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/icheck/jquery.icheck.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.admin.js"></script>
		<!--商家管理js-->
	</body>
   <script type="text/javascript">
    $(function(){
	   $('.jdt').each(function(){
		   var width = $(this).css('width');
		   var per = $(this).attr('per');
		   var pf = parseFloat(per);
		   var newW = parseInt(width) * pf;
		   if(newW <= 5)newW = 5;
		   $(this).css('width',newW);
		   if(pf <= 0.3){
			   color = 'red';
		   }else if(pf <= 0.6){
			   color = 'yellow';
		   }else{
			   color = 'green';
		   }
		   $(this).css('background',color);
	   });
   }); 
   
   </script>
</html>