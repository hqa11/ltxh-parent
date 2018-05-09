$(function(){
	//绑定搜索事件
	$("#pageBox a").each(function()  {
		$(this).click(function() {
			//1.获取搜索参数
			var param = getParam();
			//3.获取a标签的href属性
			var href = $(this).attr("href");
			//4.拼接请求url
			var v_url = href + param;
			//5.发送请求
			window.location.href = v_url;
			//6.取消默认行为
			return false;
		});
	});

});

/**
 * 搜索方法
 */
function search(){
	var v_param=getParam();
	window.location.href=url + ".action?pageCurrent=1"+v_param;
}

/**
 * 获取请求参数
 */
function getParam(){
	var v_param="&" + $("#search-box").serialize();
	return v_param;
}   	
