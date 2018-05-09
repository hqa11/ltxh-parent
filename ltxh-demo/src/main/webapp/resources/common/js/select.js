	$(function(){
		//初始化省份数据
		tm_init_pronince();
	});

	//省份数据
	var proninceDatas = [
		{"id":1001,"name":"湖南省"},
		{"id":1002,"name":"湖北省"}
	];

	//城市数据
	var cityDatas = {
		"1001":"100101#长沙,100102#郴州,100103#株洲",
		"1002":"100201#武汉,100202#太远,10023#十堰"
	};

	//区域数据
	var areasDatas = {
		"100101" :"10010101#雨花区,10010102#芙蓉区",
		"100102" :"10010201#桂阳县,10010202#永兴区",
		"100201" :"10020101#汉口,10020102#汉阳,10020103#武昌"
	};

	function tm_init_pronince(){
		var $province = $("#province");
		var html = "<option value=''>请选择</option>";
		for(var i=0;i<proninceDatas.length;i++){
			html += "<option value='"+proninceDatas[i].id+"'>"+proninceDatas[i].name+"</option>";
		}
		$province.html(html);
	};

	//根据省份获取城市
	function tm_city_change(obj){
		var value = $(obj).val();
		//根据id获取对应的城市数据
		var datas = cityDatas[value];
		var city = $("#city");
		var option = "<option value=''>请选择</option>";
		city.html(option);
		if(datas){
			var citys = datas.split(",");
			for(var i=0;i<citys.length;i++){
				var v = citys[i].split("#");
				var html = "<option value='"+v[0]+"'>"+v[1]+"</option>";
				city.append(html);
			}
		}

	};


	function tm_areas_change(obj){
		var value = $(obj).val();
		var datas = areasDatas[value];
		var area = $("#area");
		var option = "<option value=''>请选择</option>";
		area.html(option);
		if(datas){
			var areas =areasDatas[value].split(",");
			for(var i=0;i<areas.length;i++){
				var v = areas[i].split("#");
				var html = "<option value='"+v[0]+"'>"+v[1]+"</option>";
				area.append(html);
			}
		}
	};