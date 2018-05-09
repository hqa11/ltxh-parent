/*
 *z-tree选择树形菜单
 */
		var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick
			}
		};


		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeMenu"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			id = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				id += nodes[i].id + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (id.length > 0 ) id = id.substring(0, id.length-1);
			var cityObj = $("#parent-name");
			cityObj.attr("value", v);
			cityObj.attr("data-id", id);
		}

		function showMenu() {
			var cityObj = $("#parent-name");
			var cityOffset = $("#parent-name").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			var zNodes  = get_zNodes();
			$.fn.zTree.init($("#treeMenu"), setting, zNodes);
		});