package com.agc.service.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Service;

import com.agc.constant.C;
import com.agc.dao.news.NewsDao;
import com.agc.model.News;
import com.agc.model.News_Attachment;
import com.agc.model.News_Img;
import com.agc.service.BaseService;
import com.agc.utils.JsonUtils;

@Service
public class NewsService extends BaseService{

	@Resource
	private NewsDao newsDao;

	/**
	 * 分页查询新闻
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<News> getNewsPage(Map<String, Object> map, Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<News> page = newsDao.getNewsPage(pageable);
		return page;
	}

	/**
	 * 删除新闻
	 * @param news_id
	 * @return
	 */
	public String delNews(Map<String, Object> params) {
		params.put("news_is_del", 1);
		int ret = newsDao.updateById(params, News.class, "news_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

	/**
	 * 发布新闻
	 * @param params
	 * @return
	 */
	public String releaseNews(Map<String, Object> params) {
		String news_state = params.get("news_state") + "";
		if("1".equals(news_state))params.put("news_release_time",System.currentTimeMillis());
		int ret = newsDao.updateById(params, News.class, "news_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

	/**
	 * 新增新闻
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addNews(Map<String, Object> params) {
		params.remove("editorValue");
		String picStr = params.get("picStr") == null ? "" : params.get("picStr")+ "";
		String fjStr = params.get("fjStr") == null ? "" : params.get("fjStr")+ "";
		params.remove("picStr");
		params.remove("fjStr");
		params.put("news_order", 0);
		params.put("news_state", 0);
		params.put("news_create_time", System.currentTimeMillis());
		//保存新闻,返回主键
		int pk = newsDao.insertBean(params, News.class);
		// 保存图片
		if(isNotEmpty(picStr)){
			addNewsImgs(picStr, pk);
		}
		//保存附件
		if(isNotEmpty(fjStr)){
			addNewsAttachments(fjStr, pk);
		}
		return C.ADD_OK;
	}

	/**
	 * 新增附件
	 * @param fjStr
	 * @param pk
	 */
	private void addNewsAttachments(String fjStr, int pk) {
		if(isEmpty(fjStr))return;
		List<Map<String,Object>> fjList = JsonUtils.toObject(fjStr, List.class);
		int index = 0;
		for (Map<String, Object> fjMap : fjList) {
			fjMap.put("na_create_time",System.currentTimeMillis());
			fjMap.put("na_news_id",pk);
			fjMap.put("na_order",index);
			newsDao.insertBean(fjMap, News_Attachment.class);
			index ++;
		}
	}

	/**
	 * 新增图片
	 * @param picStr
	 * @param pk
	 */
	private void addNewsImgs(String picStr, int pk) {
		if(isEmpty(picStr))return;
		List<Map<String,Object>> picList = JsonUtils.toObject(picStr, List.class);
		int index = 0;
		for (Map<String, Object> picMap : picList) {
			picMap.put("ni_create_time",System.currentTimeMillis());
			picMap.put("ni_news_id",pk);
			picMap.put("ni_order",index);
			newsDao.insertBean(picMap, News_Img.class);
			index ++;
		}
	}

	/**
	 * 获取新闻
	 * @param newsId
	 * @return
	 */
	public News getNewsById(Integer newsId) {
		News news = newsDao.getById(newsId, News.class, "news_id");
		return news;
	}

	/**
	 * 获取新闻图片
	 * @param newsId
	 * @return
	 */
	public List<News_Img> getImgsByNewsId(Integer newsId) {
		List<News_Img> imgs = newsDao.getImgsByNewsId(newsId);
		return imgs;
	}

	/**
	 * 获取新闻附件
	 * @param newsId
	 * @return
	 */
	public List<News_Attachment> getAttachmentsByNewsId(Integer newsId) {
		List<News_Attachment> atts = newsDao.getAttachmentsByNewsId(newsId);
		return atts;
	}

	/**
	 * 编辑新闻
	 * @param params
	 * @return
	 */
	public String updateNews(Map<String, Object> params) {
		params.remove("editorValue");
		String picStr = params.get("picStr") == null ? "" : params.get("picStr")+ "";
		String fjStr = params.get("fjStr") == null ? "" : params.get("fjStr")+ "";
		params.remove("picStr");
		params.remove("fjStr");
		int ret = newsDao.updateById(params, News.class, "news_id");
		if(ret < 0)return "500";
		Object pk = params.get("news_id");
		//删除原图
		newsDao.deleteOldImgs(pk);
		// 保存图片
		if(isNotEmpty(picStr)){
			addNewsImgs(picStr,Integer.parseInt(pk.toString()));
		}
		//删除原附件
		newsDao.deleteOldIAttachments(pk);	
		//保存附件
		if(isNotEmpty(fjStr)){
			addNewsAttachments(fjStr,Integer.parseInt(pk.toString()));
		}
		return C.UPDATE_OK;
	}

}
