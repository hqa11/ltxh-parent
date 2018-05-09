<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<%=basePath%>resources/common/lib/html5.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/common/lib/respond.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/common/lib/PIE_IE678.js"></script>
    <![endif]-->
    <link href="<%=basePath%>resources/common/css/H-ui.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>resources/common/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/common/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/common/skin/default/skin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/common/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/common/css/style.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>农业协会管理系统</title><link rel="shortcut icon" href=""/>
</head>

<body>
<header class="Hui-header cl"> <a class="Hui-logo l" title="H-ui.admin v2.3" href="/">农业协会平台管理</a> <a class="Hui-logo-m l" href="/" title="H-ui.admin">H-ui</a>
    <ul class="Hui-userbar">
        <li>协会</li>
        <li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${user.u_nick_name}<i class="Hui-iconfont">&#xe6d5;</i></a>
            <ul class="dropDown-menu radius box-shadow">
                <li><a href="#">个人信息</a></li>
				<li><a href="javascript:;" >修改密码</a></li>
				<li><a href="javascript:;" onclick="exit()">退出</a></li>
            </ul>
        </li>
        <li id="Hui-msg"> <a href="#" title="消息"  _href="#" class="news-aaa" data-to="消息中心"><span id="news-a"></span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
    </ul>
    <a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
</header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
        <dl id="1">
       <dt><i class="Hui-iconfont">&#xe66a;</i> 基础管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
       <dd>
       <ul>
       <li><a _href="<%=basePath %>user/goUserList.action" href="javascript:void(0)">用户列表</a></li>
       <li><a _href="<%=basePath %>user/goAuthList.action" href="javascript:void(0)">认证管理</a></li>
       <li><a _href="<%=basePath %>news/goNewsList.action" href="javascript:void(0)">新闻管理</a></li>
       <li><a _href="<%=basePath %>goods/goGoodsList.action" href="javascript:void(0)">供求管理</a></li>
       <li><a _href="<%=basePath %>feedback/getFeedBackList.action" href="javascript:void(0)">意见反馈</a></li>
       <li><a _href="<%=basePath %>platNews/getPlatMsgList.action" href="javascript:void(0)">站内消息</a></li>
       <li><a _href="<%=basePath %>question/getQuestionList.action" href="javascript:void(0)">问卷调查</a></li>
       <li><a _href="<%=basePath %>question/getSurveyList.action" href="javascript:void(0)">题库管理</a></li>
       </ul>
       </dd>
       </dl>
        <%--<dl id="2">--%>
        <%--<dt><i class="Hui-iconfont">&#xe636;</i> 内容管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>--%>
        <%--<dd>--%>
        <%--<ul>--%>
        <%--<li><a _href="<%=basePath %>admin/content/contentCateManage/toContentCatePage.action" href="javascript:void(0)">文章分类管理</a></li>--%>
        <%--<li><a _href="<%=basePath %>admin/content/articleList/toContentListPage.action" href="javascript:void(0)">文章列表</a></li>--%>
        <%--<li><a _href="<%=basePath %>admin/content/areaNews/toAreaNewsPage.action" href="javascript:void(0)">区域咨询</a></li>--%>
        <%--</ul>--%>
        <%--</dd>--%>
        <%--</dl>--%>

        <%--<dl id="3">--%>
        <%--<dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>--%>
        <%--<dd>--%>
        <%--<ul>--%>
        <%--<li><a _href="<%=basePath %>admin/system/admin/systemAdminList.action" href="javascript:void(0)">管理员列表</a></li>--%>
        <%--<li><a _href="<%=basePath %>admin/system/adminRole/systemRoleManageList.action" href="javascript:void(0)">角色管理</a></li>--%>
        <%--<li><a _href="<%=basePath %>admin/system/adminUrl/systemAdminUrlList.action" href="javascript:void(0)">权限管理</a></li>--%>
        <%--<li><a _href="system/system-msg-manage.html" href="javascript:void(0)">消息管理</a></li>--%>
        <%--<li><a _href="system/system-website-related.html" href="javascript:void(0)">网站相关</a></li>--%>
        <%--</ul>--%>
        <%--</dd>--%>
        <%--</dl>--%>
    </div>
</aside>
<div class="dislpayArrow">
    <a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
</div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="首页" data-href="welcome.html">首页</span><em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src=""></iframe>
        </div>
    </div>
</section>
<div class="footer">Copyright 江苏农牧人电子商务股份有限公司</div>
<script type="text/javascript" src="<%=basePath%>resources/common/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/common/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/common/js/H-ui.admin.js"></script>
<script type="text/javascript">
function exit(){
location.href = "<%=basePath%>login/logout.action";
}
</script>
</body>

</html>