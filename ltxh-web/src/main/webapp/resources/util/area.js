/**
 * 加载区域
 * @param inputId 组件id
 * @param parentId 区域父id
 */
function loadArea(inputId,parentId){
	var areaDatas = getAreaData(parentId);
	for(var index in areaDatas){
		$('#'+inputId).append('<option value = "'+areaDatas[index].area_id+'">'+areaDatas[index].area_name+'</option>');
	}
}

/**
 * 获取区域信息
 * @param parentId
 */
function getAreaData(parentId){
	var result;
	$.ajax({
		url:web_path + '/area/getChild.action',
		type:'get',
		async:false,
		dataType:'json',
		data:{parentId:parentId},
		success:function(data){
			result = data;
		},
		error:function(data){
			alert('error');
		}
	});
	return result;
}

/**
 * 初始化区域信息
 * @param pId
 * @param cityId
 * @param areaId
 */
function initArea(pId,cityId,areaId){
	
  loadArea(pId,0); 
  $('#'+pId).change(function(){
  $('#'+cityId).empty();
  loadArea(cityId,$(this).val());
  $('#'+cityId).change();
 });
  $('#'+cityId).change(function(){
  $('#'+areaId).empty();
  loadArea(areaId,$(this).val());
 });
  //先change一下
 $('#'+pId).change();
	
}