	$(function(){
		//初始化一级分类数据
		tm_init_cate1();
	});

//	var select_box = '<span class="select-box">'
//						+'<select name="" class="select" id="articl-cate">'
//							+'<option data-info="cate-img" value="1">生态农场</option>'
//							+'<option data-info="cate-video" value="2">中国好食品</option>'
//						+'</select>'
//					+'</span>';

	//一级分类
	var cate1Datas = [
		{"id":1001,"name":"蔬菜"},
		{"id":1002,"name":"水果"}
	];

	//二级数据
	var cate2Datas = {
		"1001":"100101#青菜,100102#萝卜",
		"1002":"100201#苹果,100202#香蕉,10023#葡萄"
	};


	function tm_init_cate1(){
		var $cate1 = $("#cate1");
		var html = "<option value=''>请选择</option>";
		for(var i=0;i<cate1Datas.length;i++){
			html += "<option value='"+cate1Datas[i].id+"'>"+cate1Datas[i].name+"</option>";
		}
		$cate1.html(html);
	};

	//根据一级分类获取二级分类
	function tm_cate2_change(obj){
		var value = $(obj).val();
		//根据id获取对应的城市数据
		var datas = cate2Datas[value];
		var cate2 = $("#cate2");
		var option = "<option value=''>请选择</option>";
		cate2.html(option);
		if(datas){
			var cate2s = datas.split(",");
			for(var i=0;i<cate2s.length;i++){
				var v = cate2s[i].split("#");
				var html = "<option value='"+v[0]+"'>"+v[1]+"</option>";
				cate2.append(html);
			}
		}

	};