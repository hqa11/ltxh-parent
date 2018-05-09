package com.agc.dubbo.service.area;

import com.agc.dubbo.model.Area;

import java.util.List;

public interface AreaService   {

	/**
	 * 获取子地区
	 * @param parentId
	 * @return
	 */
	public List<Area> getAreaByParentId(Integer parentId);

}
