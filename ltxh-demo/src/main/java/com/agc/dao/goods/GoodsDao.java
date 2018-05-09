package com.agc.dao.goods;

import java.util.List;
import java.util.Map;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Repository;

import com.agc.dao.BaseDao;
import com.agc.model.Goods;
import com.agc.model.Goods_Img;
@Repository
public class GoodsDao extends BaseDao<Goods,Integer>{

	public Page<Goods> getGoodsPage(Pageable pb) {
		String sql = "SELECT s.*,u.u_nick_name username FROM goods s LEFT JOIN  user u ON s.g_release_person = u.u_id"
				+ " WHERE  s.g_is_del = 0 ";
		
		Map<String,Object> o = pb.getQmp();
		if(o != null){
			if(o.get("g_title") != null){
				sql += " AND s.g_title like ?";
				pb.addQueryVal("%"+o.get("g_title")+"%");
			}
			if(o.get("g_type") != null){
				sql += " AND s.g_type = ?";
				pb.addQueryVal(o.get("g_type"));
			}
		}
		sql +=" order by s.g_release_time desc ";
		Page<Goods> page = getPage(sql, pb, Goods.class);
		return page;
	}

	/**
	 * 获取商品图片
	 * @param gi_id
	 * @return
	 */
	public List<Goods_Img> getGoodsImgs(Integer gi_id) {
		String sql = "SELECT * FROM goods_img WHERE gi_is_del = 0 AND gi_g_id = ?";
		 List<Goods_Img> imgs = getList(sql, Goods_Img.class, gi_id);
		 return imgs;
	}

	/**
	 * 删除旧图
	 * @param pk
	 */
	public void deleteOldImgs(Object pk) {
		String sql = "UPDATE goods_img SET gi_is_del = 1 WHERE  gi_g_id = ?";
		jtp.update(sql, pk);
	}

}
