var curWwwPath=window.document.location.href;
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
var localhostPaht=curWwwPath.substring(0,pos);
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
/**网站地址**/
var web_path = localObj.protocol+"//"+localObj.host+"/"+contextPath;
/**图片上传地址**/
var pic_upload_path=web_path+"/upload/";

function isnotnull(str){
	if((typeof(str) != "undefined") && (null != str) && ('' != str) ){
		return true;
	}else {
		return false;
	}
}

/**
 * 附件上传
 */
function common_Docupload(inputfile,maxSize,tarDir,callBack){
	var file = $(inputfile).val();
	var patrn = /.(sh|exe|bat)$/;
	if (patrn.test(file)) {
		layer.alert('禁止上传此格式,请重新选择');
		return false;
	}
	
	//图片大小和尺寸验证
	var filesize = 0;
	filesize = inputfile.files[0].size;

	if(isnotnull(maxSize) && maxSize!=0){
		if (filesize/1024/1024 > maxSize) {
			layer.alert('图片过大，请选择大小小于'+maxSize+'M的图片！');
			return false;
		}
	}
	
    var id=$(inputfile).attr("id");
	
	var v_url=web_path+"/fileserver/web/uploadDoc.action";
	var param={maxSize:maxSize,tarDir:tarDir};
	layer.msg("文件上传中.....",{time:9999999});
	$.ajaxFileUpload({
		url:v_url,
		data:param,
		secureuri: false, //一般设置为false
		fileElementId: id, //文件上传空间的id属性  <input type="file" id="file" name="file" />
		type: 'post',
		dataType: 'HTML', //返回值类型 一般设置为json
		success: function(d, status){ //服务器成功响应处理函数
			layer.closeAll();
			var data = eval("("+d+")");
		    if(data.code=="304"){
				layer.msg("请上传小于"+maxSize+"M的图片");
				return;
			}else if(data.code=="500"){
				layer.msg("服务异常");
				return;
			}
			callBack(data);
			},
		error: function(d, status, e) //服务器响应失败处理函数
			{
			layer.closeAll();
			layer.msg("图片上传失败！");
			}
	});
}


/**
 * inputfile:inputfile表单组件对象;width,height:宽高限制;maxSize:大小限制;needConpress:是否需要压缩;needWater:是否需要水印;tarDir:目标文件夹;callBack:回调函数;
 * web端图片上传通用函数
 */
function common_fileupload(inputfile,width,height,maxSize,needCompress,needWater,tarDir,callBack){
	var file = $(inputfile).val();
	var patrn = /.(gif|jpg|jpeg|png)$/;
	//图片格式验证
	if (!patrn.test(file)) {
		layer.alert('图片格式不正确,请重新选择');
		return false;
	}
	//图片大小和尺寸验证
	var filesize = 0;
	filesize = inputfile.files[0].size;

	if(isnotnull(maxSize) && maxSize!=0){
		if (filesize/1024/1024 > maxSize) {
			layer.alert('图片过大，请选择大小小于'+maxSize+'M的图片！');
			return false;
		}
	}
	var id=$(inputfile).attr("id");
	
	var v_url=web_path+"/fileserver/web/upload.action";
	
	
	var param={w:width,h:height,maxSize:maxSize,needCompress:needCompress,needWater:needWater,tarDir:tarDir};
	
	$.ajaxFileUpload({
		url:v_url,
		data:param,
		secureuri: false, //一般设置为false
		fileElementId: id, //文件上传空间的id属性  <input type="file" id="file" name="file" />
		type: 'post',
		dataType: 'HTML', //返回值类型 一般设置为json
		success: function(data, status){ //服务器成功响应处理函数
			if(data=="302"){
				layer.msg("请上传大小为"+width+"px*"+height+"px的图片!");
				return;
			}else if(data=="304"){
				layer.msg("请上传小于"+maxSize+"M的图片");
				return;
			}
			callBack(data);
			},
		error: function(data, status, e) //服务器响应失败处理函数
			{
			    layer.msg("图片上传失败！");
			}
	});
  }

/**视频上传**/
	function common_videoupload(inputfile,width,height,maxSize,needWater,tarDir,callBack){
		var file = $(inputfile).val();
		var patrn = /.(mp4|avi|flv|dvd|MP4)$/;
		//视频格式验证
		if (!patrn.test(file)) {
			layer.alert('视频格式不正确,请重新选择');
			return false;      
		}
		
		//视频大小验证
		var filesize = 0;
		filesize = inputfile.files[0].size;
		if(isnotnull(maxSize) && maxSize!=0){
			if (filesize/1024/1024 > maxSize) {
				layer.alert('视频过大，请选择大小小于'+maxSize+'M的视频！');
				return false;
			}
		}
		
		var id=$(inputfile).attr("id");
		var v_url=web_path+"/fileserver/uploadVideo.action";
		var param={filePath:tarDir,maxSize:maxSize};
	    console.log(param);
	    layer.msg("文件上传中.....",{time:9999999});
		$.ajaxFileUpload({
			url:v_url,
			data:param,
			secureuri: false, //一般设置为false
			fileElementId: id, //文件上传空间的id属性  <input type="file" id="file" name="file" />
			type: 'post',
			dataType: 'HTML', //返回值类型 一般设置为json
			success: function(data){ //服务器成功响应处理函数
				layer.closeAll();
				if(data=="300"){
					layer.msg("视频格式不正确,请重新选择");
					return;
				}else if(data=="304"){
					layer.msg("请上传小于"+maxSize+"M的视频");
					return;
				}
				callBack(data);
				},
			error: function(data, e) //服务器响应失败处理函数
				{
				
				layer.closeAll();
				    layer.msg("图片上传失败！");
				}
		});
		
	}

	/**
	 * 验证表单
	 * @param formId
	 * @returns {Boolean}
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
			 			if(0<gets&&gets<=999999){
			 				$(obj).val(parseFloat(gets).toFixed(2));
			 				return true;
			 			}else{
			 				return "*价格输入不正确";
			 			}
			 		}else{
			 			return "*价格输入不正确,只能输入包含两位小数的数字";
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
	
	/**
	 * ajax通用回调
	 * @param data
	 * @param fn
	 */
	function ajaxCallBack(data,fn){
		if(data=="500"){
		   	layer.msg("操作失败!");
		   	return;
		   	}
		   	layer.msg(data,{time:1000},function(){
		   	fn();
		   	});
	}
	
	/**
	 * 刷新自己
	 */
	function reloadThis(){
		location.reload();
		layer.closeAll();
	}
	
	/**
	 * 刷新父页面
	 */
	function reloadParent(){
		parent.location.reload();
		parent.layer.closeAll();
	}

   
	