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
		<script type="text/javascript" src="<%=basePath%>resources/admin/common/lib/html5.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/admin/common/lib/respond.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/admin/common/lib/PIE_IE678.js"></script>
		<![endif]-->
		<link href="<%=basePath%>resources/common/css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resources/common/css/H-ui.login.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resources/common/css/style.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resources/common/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<title>农业协会管理系统</title><link rel="shortcut icon" href="<%=basePath%>resources/web/images/icon.ico"/>
	</head>

	<body class="login-body">
		<div class="container">
			<form class="form-signin" action="goLogin.action" method="post" onsubmit="return check()" >
				<h3 class="text-c font-write">欢迎登录农业协会管理系统</h3>
				<div class="login-wrap text-c">
					<div class="login-logo">
						<%-- <img src="<%=basePath%>resources/common/images/nmrlogolv.png" /> --%>
					</div> 
					<input type="text" class="input-text radius size-L" id="adminName" name="username" placeholder="请输入用户名" />
					<input type="password" class="input-text radius size-L" id="adminPassword" name="password" placeholder="请输入密码" />
					<div class="login-forgot text-r">
						<a href="#">忘记密码？</a>
					</div>
					 <div class="error"></div>
					<button class="btn btn-success radius btn-block size-L" type="submit">登 录</button>
				</div>
			</form>
		</div>
		<div class="footer"></div>
		<script type="text/javascript" src="<%=basePath%>resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.js"></script>
	</body>

</html>

<script type="text/javascript">
    $(function(){
        if (top.location != self.location) {
            top.location = self.location;
        }
    })

    var msg='${loginFailed}';
    if(msg!=null && msg!=""){
		$('.error').html(msg);
	}
	
	function check(){
		var user = $('#adminName');//获取用户名
		var pwd = $('#adminPassword');//获取用户密码
		if(user.val().length<1||pwd.val().length<1){
			$('.error').html('*用户名或密码不能为空');
		}
	}
</script>