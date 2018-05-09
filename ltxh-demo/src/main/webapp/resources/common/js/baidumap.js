// 百度地图API功能  
    var map = new BMap.Map("allmap");//创建百度地图实例，这里的allmap是地图容器的id  
    var point = new BMap.Point(113.721713, 34.774855);//创建一个点对象，这里的参数是地图上的经纬度  
    map.centerAndZoom(point, 20);//这里是将地图的中心移动到我们刚才创建的点；这里的12是地图的缩放界别；数值越大，地图看的越细  
	map.enableScrollWheelZoom();   //启用滚轮放大缩小
//添加点击地图监听事件，点击地图后显示当前经纬度
	function showInfo(e){
		alert(e.point.lng + ", " + e.point.lat);
		document.getElementById("u_longitude").value=e.point.lng;
		document.getElementById("u_latitude").value=e.point.lat;
	}
	map.addEventListener("click", showInfo);
// 用城市名设置地图中心点
function theLocation(){
		var city = document.getElementById("cityName").value;
		if(city != ""){
			map.centerAndZoom(city,11);      // 用城市名设置地图中心点
		}
	}
	
function myFun(result){
	var cityName = result.name;
	map.setCenter(cityName);
//	alert("当前定位城市:"+cityName);
}
var myCity = new BMap.LocalCity();
myCity.get(myFun);	
//添加控件
	 var navigationControl = new BMap.NavigationControl();//创建平移缩放控件  
    map.addControl(navigationControl);//添加到地图  
    var scaleControl = new BMap.ScaleControl();//这里创建比例尺控件  
    map.addControl(scaleControl);//添加到地图  
    var overviewMapControl = new BMap.OverviewMapControl();//创建缩略图控件，注意这个控件默认是在地图右下角，并且是缩着的  
    map.addControl(overviewMapControl);//添加到地图  
   /* var mapTypeControl = new BMap.MapTypeControl();//创建地图类型控件  
    map.addControl(mapTypeControl); */ 
    
//  模糊搜索时地图自动移动到搜索的目的地
    
   
   
// 百度地图API功能
	function G(id) {
		return document.getElementById(id);
	}

	
//	map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		$("#u_address_detail").val(myValue);
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}   
   
