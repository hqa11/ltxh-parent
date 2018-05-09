package com.agc.dubbo.service.news;


import com.agc.core.common.Page;
import com.agc.dubbo.model.News;
import com.agc.dubbo.model.News_Attachment;
import com.agc.dubbo.model.News_Img;

import java.util.List;
import java.util.Map;

public interface NewsService  {


	/**
	 * 分页查询新闻
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<News> getNewsPage(Map<String, Object> map, Integer pageCurrent);

	/**
	 * 删除新闻
	 * @param
	 * @return
	 */
	public String delNews(Map<String, Object> params);

	/**
	 * 发布新闻
	 * @param params
	 * @return
	 */
	public String releaseNews(Map<String, Object> params);
	/**
	 * 新增新闻
	 * @param params
	 * @return
	 */
	public String addNews(Map<String, Object> params) ;


	/**
	 * 获取新闻
	 * @param newsId
	 * @return
	 */
	public News getNewsById(Integer newsId);

	/**
	 * 获取新闻图片
	 * @param newsId
	 * @return
	 */
	public List<News_Img> getImgsByNewsId(Integer newsId);

	/**
	 * 获取新闻附件
	 * @param newsId
	 * @return
	 */
	public List<News_Attachment> getAttachmentsByNewsId(Integer newsId);

	/**
	 * 编辑新闻
	 * @param params
	 * @return
	 */
	public String updateNews(Map<String, Object> params);

}
