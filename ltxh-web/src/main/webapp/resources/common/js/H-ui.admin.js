/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = $(".acrossTab"),
		$tabNavWp = $(".Hui-tabNav-wp"),
		$tabNavitem = $(".acrossTab li"),
		$tabNavmore =$(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60))});
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})}
}

/*左侧菜单响应式*/
function Huiasidedisplay(){
	if($(window).width()>=768){
		$(".Hui-aside").show()
	}
}
function getskincookie(){
	var v = getCookie("Huiskin");
	if(v==null||v==""){
		v="default";
	}
	$("#skin").attr("href","skin/"+v+"/skin.css");
}
$(function(){
	getskincookie();
	//layer.config({extend: 'extend/layer.ext.js'});
	Huiasidedisplay();
	var resizeID;
	$(window).resize(function(){
		clearTimeout(resizeID);
		resizeID = setTimeout(function(){
			Huiasidedisplay();
		},500);
	});

	$(".Hui-nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();
	});
	$(".Hui-aside").on("click",".menu_dropdown dd li a",function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	/*左侧菜单*/
	$.Huifold(".menu_dropdown dl dt",".menu_dropdown dl dd","fast",1,"click");
	/*选项卡导航*/

	$(".Hui-aside,.Hui-userbar,.news-aaa").on("click",".menu_dropdown a,#Hui-msg a",function(){
		if($(this).attr('_href')){
			var bStop=false;
			var bStopIndex=0;
			var _href = $(this).attr('_href');
			if($(this).has('i').length>0){
				var _titleName=$(this).attr('data-to');
			}else{
				var _titleName=$(this).html();
			}

			var topWindow=$(window.parent.document);
			var show_navLi=topWindow.find("#min_title_list li");
			show_navLi.each(function() {
				if($(this).find('span').attr("data-href")==_href){
					bStop=true;
					bStopIndex=show_navLi.index($(this));
					return false;
				}
			});
			if(!bStop){
				creatIframe(_href,_titleName);
				min_titleList();
			}
			else{
				show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
				var iframe_box=topWindow.find("#iframe_box");
				iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src",_href);
			}
		}
	});

	function min_titleList(){
		var topWindow=$(window.parent.document);
		var show_nav=topWindow.find("#min_title_list");
		var aLi=show_nav.find("li");
	};
	function creatIframe(href,titleName){
		var topWindow=$(window.parent.document);
		var show_nav=topWindow.find('#min_title_list');
		show_nav.find('li').removeClass("active");
		var iframe_box=topWindow.find('#iframe_box');
		show_nav.append('<li class="active"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
		tabNavallwidth();
		var iframeBox=iframe_box.find('.show_iframe');
		iframeBox.hide();
		iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src='+href+'></iframe></div>');
		var showBox=iframe_box.find('.show_iframe:visible');
		showBox.find('iframe').attr("src",href).load(function(){
			showBox.find('.loading').hide();
		});
	}

	var num=0;
	var oUl=$("#min_title_list");
	var hide_nav=$("#Hui-tabNav");
	$(document).on("click","#min_title_list li",function(){
		var bStopIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
	});
	$(document).on("click","#min_title_list li i",function(){
		var aCloseIndex=$(this).parents("li").index();
		$(this).parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
		num==0?num=0:num--;
		tabNavallwidth();
	});
	$(document).on("dblclick","#min_title_list li",function(){
		var aCloseIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		if(aCloseIndex>0){
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
			num==0?num=0:num--;
			$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
			tabNavallwidth();
		}else{
			return false;
		}
	});
	tabNavallwidth();

	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos();
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--;
		toNavPos();
	});

	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100);
	}

	/*换肤*/
	$("#Hui-skin .dropDown-menu a").click(function(){
		var v = $(this).attr("data-val");
		setCookie("Huiskin", v);
		$("#skin").attr("href","skin/"+v+"/skin.css");
	});
});
/*弹出层*/
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title,url,w,h){
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w+'px', h +'px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: title,
		content: url
	});
}
/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

/*关闭弹出框口并刷新父窗口*/
function layer_close_refresh(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.$('.btn-refresh').click();
	parent.layer.close(index);
}


/*checkbox、radio样式框*/
function check_skin(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '15%'
	});
}

/*显示加载动画、禁止button事件*/
function submit_loading(){
	$('.form-submit').attr("disabled",true).html('<img src="../../images/loading_072.gif" width="25" height="25"/>');
}

/*页面排序*/
/*
 * 参数说明
 * table 需要排序的table id
 * d     默认第几个排序
 * n     制定不参与排序的列
 */
function table_sort(table, d, n){
	var arr = new Array();
	var t = n.split(',');
	for (var i=0;i<t.length;i++) {
		arr.push(parseInt(t[i]));
	}
	$('#'+table).dataTable({
		"order": [[ d, "asc" ]],//默认第几个排序
		"paging": false,//不显示本地分页
		"searching": false,//不进行本地搜索
		"info": false,  //不显示表格信息
		"language": {
		      "emptyTable": "没有找到相关数据！"
		   },
		"columnDefs": [
		  {"orderable":false,"targets":arr}// 制定列不参与排序
		]
	});
}


/*
 * validform表单提交验证方法
 * 参数说明
 * formId 当前提交表单的form id
 */
function check_form(formId){
	var form_cate_add = $(formId).Validform({
		tiptype: 2,
		ajaxPost:true,
		ignoreHidden:true,   //当为true时对:hidden的表单元素将不做验证;
		datatype:{
		 	"level-price":function(gets,obj,curform,regxp){//价格验证
		 		var regx = /^((\d+(\.\d+)?))$/;
		 		if (regx.test(gets)) {
		 			if(0<=gets&&gets<=999999){
		 				$(obj).val(parseFloat(gets).toFixed(2));
		 				return true;
		 			}else{
		 				return "*价格输入不正确";
		 			}
		 		}else{
		 			return "*价格输入不正确,只能输入包含两位小数的数字";
		 		}
		 	},
		 	"apply-date":function(){
		 		if($('#apply-start').val()!=''&&$('#apply-end').val()!=''){
					return true;
		 		}else{
		 			return "请选择时间";
		 		}
		 	},
		 	"helpMoney":function(gets,obj,curform,regxp){
		 		var regx = /^((\d+(\.\d+)?))$/;
		 		if (regx.test(gets)) {
		 			if(0<gets){
		 				$(obj).val(parseFloat(gets).toFixed(2));
		 				return true;
		 			}else{
		 				return false;
		 			}
		 		}else{
		 			return false;
		 		}
		 	},
		 	"vote-date":function(){
		 		if($('#vote-start').val()!=''&&$('#vote-end').val()!=''){
					return true;
		 		}else{
		 			return "请选择时间";
		 		}
		 	}
		 },
		beforeSubmit:function(curform){
			//这里明确return false的话表单将不会提交;
			return false;
		}
	});
	if(form_cate_add.check()){
		return true;
	}else{
		return false;
	}
}

/*
 * ajax返回状态（可选择性使用）
 * 参数说明
 * flag 返回状态
 * info 提示信息
 */
function ajax_result(flag,info){
	if(flag){ //返回成功
		layer.msg(info,{ //弹出返回信息，显示1s
			icon:1,
			time:1000
		},function(){ //关闭弹出框，刷新页面
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		})
	}else{ //返回失败
		layer.msg(info,{ //弹出返回信息，显示1s
			icon:2,
			time:1000
		},function(){ //刷新当前弹出框
			window.location.reload();
		});
	}
}

/*
 * ajax执行 error错误提示（可选择性使用）
 */
function error_info(){
	layer.msg('系统出错！',{//弹出返回信息，显示1s
		icon:2,
		time:2000
	},function(){ //刷新当前弹出框
		window.location.reload();
	});
}
/*
 * 全选操所方法
 * 只适用于table列表
 * 全选name = check-all,单选name=check-alone
 */
var $all = $('input[name="check-all"]');
var $sig = $('input[name="check-alone"]');
	$all.click(function(){
		if($(this).prop("checked")){
			//循环取出列表id并选中
			$sig.prop("checked", true);
		}else{
			$sig.prop("checked", false);
		}
	})
	$sig.click(function(){
		//查找每个s的类的值
		//找到当前元素下的所有checkbox的长度  判断全选
		var sigcheck = $('input[name="check-alone"]:checked').length;  //选中长度
		if (sigcheck==$sig.length) {
			$all.prop("checked",true);
		}else{
			$all.prop("checked",false);
		}
	});

/*全选checkbox 数据判断*/
function get_check_ids(){
	var ids = '';  //获取选中的文章id，多个id用","隔开
	var checks = $('input[name="check-alone"]:checked');
	var length = checks.length;
	checks.each(function(index){
		if(index==(length-1)){
			ids += $(this).val();
		}else{
			ids += $(this).val()+',';
		}
	})
	//判断ids的长度
	if(ids.length==0){
		layer.msg('请至少选择一条数据进行操作!', {
			icon: 2,
			time: 2000
		});
		return false;
	}else{
		return ids;
	}

}

function nullTable(){
	var trs = '<tr class="odd"><td valign="top" colspan="5" class="dataTables_empty">没有找到相关数据！</td></tr>';
	$('tbody').html(trs);
}



