package com.agc.worker.service.area;

import com.agc.core.common.BaseService;
import com.agc.dubbo.model.Area;
import com.agc.dubbo.service.area.AreaService;
import com.agc.worker.dao.area.AreaDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AreaServiceImpl extends BaseService implements AreaService{
	
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
