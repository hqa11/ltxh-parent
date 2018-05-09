package com.agc.worker.dao.area;


import com.agc.dubbo.model.Area;
import com.agc.worker.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaDao extends BaseDao<Area, Integer> {

	public List<Area> getAreaByParentId(Integer parentId) {
		String sql = "SELECT * FROM area WHERE area_parent_id = ?";
		List<Area> areas = getList(sql, Area.class, parentId);
		return areas;
	}

}
