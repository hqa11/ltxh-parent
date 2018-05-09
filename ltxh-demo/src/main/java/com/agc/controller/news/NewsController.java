package com.agc.controller.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agc.controller.BaseController;
import com.agc.model.News;
import com.agc.model.News_Attachment;
import com.agc.model.News_Img;
import com.agc.service.news.NewsService;

@Controller
@RequestMapping("news")
public class NewsController extends BaseController{
	
	@Resource
	private NewsService ns;
	/**
	 * 去往新闻列表
	 * @return
	 */
	@RequestMapping("goNewsList")
	public String goNewsList(@RequestParam(defaultValue = "1") Integer pageCurrent,String news_title,
			Integer news_type,Model model){
		//参数回显
		model.addAttribute("news_title", news_title);
		model.addAttribute("news_type", news_type);
		Map<String, Object> map = getMap();
		if(isNotEmpty(news_title))map.put("news_title", news_title);
		if(isNotEmpty(news_type))map.put("news_type", news_type);
		Page<News> page = ns.getNewsPage(map,pageCurrent);
		model.addAttribute("pageBean",page);
		return "news/news-list";
	}
	
	/**
	 *  删除新闻
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="delNews",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String delNews(@RequestParam Map<String, Object> params){
		
        return ns.delNews(params);
	}
	
	
	/**
	 *  发布新闻
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="releaseNews",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String releaseNews(@RequestParam Map<String, Object> params){
		
        return ns.releaseNews(params);
	}


	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping("addNews")
	public String addNews(){
		
		return "news/news-add";
	}
	
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping("editNews")
	public String editNews(Model model,Integer newsId){
		//获取新闻
		News news = ns.getNewsById(newsId);
		//获取新闻图片
		List<News_Img> imgs = ns.getImgsByNewsId(newsId);
		//获取附件
		List<News_Attachment> fjs = ns.getAttachmentsByNewsId(newsId);
		model.addAttribute("news", news);
		model.addAttribute("imgs", imgs);
		model.addAttribute("fjs", fjs);
		return "news/news-edit";
	}
	
	
	
	/**
	 *  新增新闻
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="saveNews",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String saveNews(@RequestParam Map<String, Object> params){
		try {
			return ns.addNews(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "500";
	}
	
	/**
	 *  编辑新闻
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="updateNews",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String updateNews(@RequestParam Map<String, Object> params){
		try {
			return ns.updateNews(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "500";
	}

	
	
}
