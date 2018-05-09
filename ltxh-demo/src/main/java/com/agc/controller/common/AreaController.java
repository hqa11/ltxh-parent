package com.agc.controller.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agc.model.Area;
import com.agc.service.area.AreaService;

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
