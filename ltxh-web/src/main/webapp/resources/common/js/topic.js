/**
 +----------------------------------------------------------
 * 话题列表-列表排序
 +----------------------------------------------------------
 */
	table_sort('topic-list-sort',1,'0,2,3,4,5,6,8');

/**
 +----------------------------------------------------------
 * 话题列表-查看详情弹出层
 +----------------------------------------------------------
 */
	function topic_detail(title,url,id){
		var index = layer.open({
			type: 2,
			title: title,
			content: url
		});
		layer.full(index);
	}

/**
 +----------------------------------------------------------
 * 话题列表-编辑弹出层
 +----------------------------------------------------------
 */
	function topic_edit(title,url,id){
		var index = layer.open({
			type: 2,
			title: title,
			content: url
		});
		layer.full(index);
	}

/**
 +----------------------------------------------------------
 * 话题列表-编辑-图片和视频切换
 +----------------------------------------------------------
 */
	$('#topic-img').click(function(){
		if($(this).prop('checked')){
			$('.topic-img').show();
			$('.topic-video').hide();
		}else{
			$('.topic-img').hide();
		}
	})
	$('#topic-video').click(function(){
		if($(this).prop('checked')){
			$('.topic-video').show();
			$('.topic-img').hide();
		}else{
			$('.topic-video').hide();
		}
	})

/**
 +----------------------------------------------------------
 * 话题详情-阅读、点赞、评价、转发、投诉弹出层
 +----------------------------------------------------------
 */
	function count_check(title,url,id,w,h){
		layer_show(title,url,w,h);
	}


/**
 +----------------------------------------------------------
 * 话题详情-查看圈子弹出层
 +----------------------------------------------------------
 */
	function group_check_detail(title,url,id){
		var index = layer.open({
			type: 2,
			title: title,
			content: url
		});
		layer.full(index);
	}



/**
 +----------------------------------------------------------
 * 话题列表-删除
 +----------------------------------------------------------
 */
	function topic_del(obj,id){
		layer.confirm('确认要删除该话题吗？',function(index){
			//此处请求后台程序，下方是成功后的前台处理……
			$(obj).parents("tr").remove();
			layer.msg('已删除!',{icon:1,time:1000});
		});
	}

/**
 +----------------------------------------------------------
 * 话题列表-推精
 +----------------------------------------------------------
 */
	function topic_recom(obj,id){
		layer.confirm('确认要设置为精华话题吗？',function(index){
			//此处请求后台程序，下方是成功后的前台处理……
			$(obj).parents('tr').find('.td-manage').append('<a class="pd-5" title="取消推精" href="javascript:;" onclick="topic_recom_cancel(this,1)" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i>取消推精</a>');
			$(obj).parents('tr').find('.td-recomm').append('<span class="label label-warning radius ml-5">精</span>');
			$(obj).remove();
			layer.msg('已推精!',{icon:1,time:1000});
		});
	}

/**
 +----------------------------------------------------------
 * 话题列表-取消推精
 +----------------------------------------------------------
 */
	function topic_recom_cancel(obj,id){
		layer.confirm('确认要取消精华话题吗？',function(index){
			//此处请求后台程序，下方是成功后的前台处理……
			$(obj).parents('tr').find('.td-manage').append('<a class="pd-5" title="推精" href="javascript:;" onclick="topic_recom(this,1)" style="text-decoration:none"><i class="Hui-iconfont">&#xe697;</i>推精</a>');
			$(obj).parents('tr').find('.td-recomm span').remove();
			$(obj).remove();
			layer.msg('已取消!',{icon:1,time:1000});
		});
	}

/**
 +----------------------------------------------------------
 * 话题列表-审核
 +----------------------------------------------------------
 */
	/*扩展方法propem依赖于layer.ext.js*/
	layer.config({
	  extend: 'extend/layer.ext.js'
	})

	function topic_check_no(obj,id){
		layer.confirm('确认要取消话题吗？',function(index){
			//此处请求后台程序，下方是成功后的前台处理……

			$(obj).parents("tr").find(".td-status").html('<span class="label label-warning radius">话题已取消</span>');
			$(obj).parents('tr').find('.td-manage').prepend('<a class="pd-5" title="通过" href="javascript:;" onclick="topic_check_yes(this,1)" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e1;</i>话题通过</a>');
			$(obj).remove();
			layer.msg('已取消!',{icon:1,time:1000});
		});
	}

	function topic_check_yes(obj,id){
		layer.confirm('确认要重新通过话题吗？',function(index){
			//此处请求后台程序，下方是成功后的前台处理……

			$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">话题已通过</span>');
			$(obj).parents('tr').find('.td-manage').prepend('<a class="pd-5" title="取消" href="javascript:;" onclick="topic_check_no(this,1)" style="text-decoration:none"><i class="Hui-iconfont">&#xe6dd;</i>取消通过</a>');
			$(obj).remove();
			layer.msg('已取消!',{icon:1,time:1000});
		});
	}

/**
 +----------------------------------------------------------
 * 话题列表-修改保存
 +----------------------------------------------------------
 */
	function save_topic(){
		var check_result = check_form('#topic-form');
		//获取选中的商品
		if(!chose_goods()){
			return false;
		}
		//图片和内容验证结果
		var check_contant = check_articl();

		if(check_result){
			if(check_contant){
				//此处请求后台程序，下方是成功后的前台处理……

				layer_close_refresh();
			}
		}
	}

/**
 +----------------------------------------------------------
 * 话题列表-修改-选择商品
 +----------------------------------------------------------
 */
	function chose_goods(){
		var r = $('.change-con table input[type="radio"]:checked');
		if(r.length<1){
			$('.all-goods').next().addClass('Validform_wrong').html('请选择商品！');
			return false;
		}else{
			$('.all-goods').next().addClass('Validform_right').html('');
			return true;
		}
	}

/**
 +----------------------------------------------------------
 * 话题列表-修改-删除商品
 +----------------------------------------------------------
 */
	 function deleteImg(obj,id){
	 	$(obj).parents('.feedback-img').remove();
	 }

/**
 +----------------------------------------------------------
 * 话题详情-评价删除
 +----------------------------------------------------------
 */
	function topic_evealuate_del(obj,id){
		layer.confirm('确认要删除该评论吗？',function(index){
			//此处请求后台程序，下方是成功后的前台处理……
			$(obj).parents(".row").remove();
			layer.msg('已删除!',{icon:1,time:1000},function(){
				//获取评论列表长度
				var evas = $('.row').length;
				if(evas==0){
					$('.null-box').show();
				}
			});
		});
	}

/**
 +----------------------------------------------------------
 * 话题详情-评价审核
 +----------------------------------------------------------
 */
	function topic_audit(obj,id){
		layer.msg('是否通过审核？', {
		  time: 0, //不自动关闭
		  btn: ['通过', '不通过'],
		  yes: function(index){
		  	//此处请求"通过审核"后台程序，下方是成功后的前台处理……

		  	layer.close(index);
		  	$(obj).parents(".row").find(".e-state").html('<span class="label label-success radius">已通过</span>');
		  	$(obj).remove();
		    layer.msg('已通过!', {
				icon: 6,
				time: 1000
			});
		  },
		  cancel:function(){
			//此处请求后台程序，下方是成功后的前台处理……

			$(obj).parents(".row").find(".e-state").html('<span class="label label-warning radius">未通过</span>');
			$(obj).remove();
			layer.msg('未通过!', {
				icon: 5,
				time: 1000
			});
			return false;
		  }
		});
	}






/**
 +----------------------------------------------------------
 * 话题-修改图片和内容校验
 +----------------------------------------------------------
 */
	/*验证上传图片*/
	function check_file(size,id,obj){
		var num = $('.image-items .feedback-img').length;
	    if(num==9){
	    	layer.alert('最多上传9张图片!');
   			return false;
	    }
	 	var patrn = /.(gif|jpg|jpeg|png)$/;
	 	var inputFile = document.getElementById(id);
		//图片格式验证
   		if(!patrn.test($(obj).val())){
   			layer.msg('只能上传gif、jpg、jpeg、png格式的图片');
			return false;
   		}
   		//图片大小和尺寸验证
		var filesize = 0;
		filesize = inputFile.files[0].size;
	    if (filesize>size) {
	    	layer.msg($(obj).attr('errorimg'));
	    	return false;
   		}
	    //非空验证
	    if($(obj).val()!=''){
			$(obj).next().addClass('Validform_right').html('');
	    }
	    var img='<p class="feedback-img mt-10 pos-r">'
					+'<img src="../../../images/login-bg.jpg" height="100" />'
					+'<a class="pd-5 c-red img-del" title="删除" href="javascript:;" onclick="deleteImg(this,1)" style="text-decoration:none">'
						+'<i class="Hui-iconfont">&#xe60b;</i>'
					+'</a>'
				+'</p>'
		$('.image-items').append(img);
	    return true;
	}


	/*表单图片及内容验证*/
	function check_articl(){
		//图片验证
		if($('#topic-img').prop('checked')){
			var num = $('.image-items .feedback-img').length;
		    if(num==0){
		    	layer.alert('请上传图片!');
	   			return false;
		    }
		}

	   //详情内容验证
	   var txt = $('#topic-info').val();
		if(txt.length<1){
			$('#topic-info').next().addClass('Validform_wrong').html('话题详情不能为空!');
			return false;
		}else{
			$('#topic-info').next().addClass('Validform_right').html('');
		}
		return true;
	}