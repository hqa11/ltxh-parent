package com.agc.service.area;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.agc.dao.area.AreaDao;
import com.agc.model.Area;
import com.agc.service.BaseService;

@Service
public class AreaService  extends BaseService{
	
	@Resource
	private AreaDao areaDao;

	/**
	 * 获取子地区
	 * @param parentId
	 * @return
	 */
	public List<Area> getAreaByParentId(Integer parentId) {
		List<Area> areas = areaDao.getAreaByParentId(parentId);
		return areas;
	}

}
