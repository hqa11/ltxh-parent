package com.agc.worker.dao.news;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import com.agc.dubbo.model.News;
import com.agc.dubbo.model.News_Attachment;
import com.agc.dubbo.model.News_Img;
import com.agc.worker.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NewsDao  extends BaseDao<News, Integer> {

	public Page<News> getNewsPage(Pageable pb) {
		String sql = "SELECT s.*,"
				+ "(SELECT COUNT(1) FROM news_comment c WHERE c.nc_news_id = s.news_id AND  c.nc_is_del = 0)agreeNum,"
				+ "(SELECT COUNT(1) FROM news_agree a WHERE a.ng_news_id = s.news_id AND  a.ng_is_del = 0)cpNum "
				+ "FROM news s  WHERE s.news_is_del = 0";
		Map<String,Object> o = pb.getQmp();
		if(o != null){
			if(o.get("news_title") != null){
				sql += " AND s.news_title like ?";
				pb.addQueryVal("%"+o.get("news_title")+"%");
			}
			if(o.get("news_type") != null){
				sql += " AND s.news_type = ?";
				pb.addQueryVal(o.get("news_type"));
			}
		}
		sql +=" order by s.news_create_time desc ";
		Page<News> page = getPage(sql, pb, News.class);
		return page;
	}

	public List<News_Img> getImgsByNewsId(Integer newsId) {
		String sql = "SELECT * FROM news_img  WHERE ni_is_del = 0 AND ni_news_id = ? order by ni_create_time desc";
		List<News_Img> list = getList(sql, News_Img.class, newsId);
		return list;
	}

	public List<News_Attachment> getAttachmentsByNewsId(Integer newsId) {
		String sql = "SELECT * FROM news_attachment  WHERE na_is_del = 0 AND na_news_id = ? order by na_create_time desc";
		List<News_Attachment> list = getList(sql, News_Attachment.class, newsId);
		return list;
	}

	public void deleteOldImgs(Object pk) {
		String sql = "UPDATE news_img SET ni_is_del = 1  WHERE ni_news_id = ?";
		jtp.update(sql, pk);
	}

	public void deleteOldIAttachments(Object pk) {
		String sql = "UPDATE news_attachment SET na_is_del = 1  WHERE na_news_id = ?";
		jtp.update(sql, pk);
	}

}
