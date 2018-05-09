package com.agc.web.controller.common;


import javax.annotation.Resource;

import com.agc.dubbo.model.Area;
import com.agc.dubbo.service.area.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("area")
public class AreaController {

	@Resource
	private AreaService as;
	
	@RequestMapping("getChild")
	@ResponseBody
	public List<Area> getAreaByParentId(Integer parentId){
		List<Area> areas = as.getAreaByParentId(parentId);
		return areas;
	}
	
}
