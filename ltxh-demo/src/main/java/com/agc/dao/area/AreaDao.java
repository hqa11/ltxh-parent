package com.agc.dao.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.agc.dao.BaseDao;
import com.agc.model.Area;

@Repository
public class AreaDao extends BaseDao<Area, Integer>{

	public List<Area> getAreaByParentId(Integer parentId) {
		String sql = "SELECT * FROM area WHERE area_parent_id = ?";
		List<Area> areas = getList(sql, Area.class, parentId);
		return areas;
	}

}
