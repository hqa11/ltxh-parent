package com.agc.dao;

import java.util.Map;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Repository;

import com.agc.model.Feed_Back;
	
@Repository
public class FeedBackDao extends BaseDao<Feed_Back, Integer>{

	public Page<Feed_Back> getNewsPage(Pageable pb) {
		String sql = "SELECT f.* FROM feed_back f WHERE f.fd_is_del = 0";
		Map<String,Object> o = pb.getQmp();
		if(o != null){
			if(o.get("start") != null){
				sql += " AND f.fd_time >= ?";
				pb.addQueryVal(o.get("start"));
			}
			if(o.get("end") != null){
				sql += " AND f.fd_time <= ?";
				pb.addQueryVal(o.get("end"));
			}
		}
		sql += " order by f.fd_time desc ";
		Page<Feed_Back> page = getPage(sql, pb, Feed_Back.class);
		return page;
	}

}
