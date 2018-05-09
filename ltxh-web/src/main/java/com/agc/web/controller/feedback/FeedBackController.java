package com.agc.web.controller.feedback;

import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.dubbo.model.Feed_Back;
import com.agc.dubbo.service.feedback.FeedBackService;
import com.agc.web.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("feedback")
public class FeedBackController extends BaseController {

	@Resource
	private FeedBackService fbs;

	@RequestMapping("getFeedBackList")
	public String getFeedBackList(Model model,@RequestParam(defaultValue = "1") Integer pageCurrent
			,String start,String end){
		//参数回显.
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		Map<String, Object> map = getMap();
		if(isNotEmpty(start))map.put("start", getTimeMillions(start));
		if(isNotEmpty(end))map.put("end",  getTimeMillions(end));
		Page<Feed_Back> page = fbs.getFeedBackPage(map,pageCurrent);
		model.addAttribute("pageBean",page);
		return "feedback/feedback-list";
	}
	
	/**
	 *  标记为阅读/处理反馈/删除反馈
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="handleFeedBack",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String handleFeedBack(@RequestParam Map<String, Object> params){

		return fbs.handleFeedBack(params);
	}
	

}
