package com.agc.controller.platnews;

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
import com.agc.model.Plat_Msg;
import com.agc.model.Plat_Msg_Attachment;
import com.agc.model.User;
import com.agc.service.platnews.PlatNewsService;

@Controller
@RequestMapping("platNews")
public class PlatNewsController extends BaseController{

	@Resource
	private PlatNewsService ps;

	/**
	 * 站内消息分页
	 * @param model
	 * @param pageCurrent
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping("getPlatMsgList")
	public String getPlatMsgList(Model model,@RequestParam(defaultValue = "1") Integer pageCurrent
			,String start,String end){
		//参数回显
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		Map<String, Object> map = getMap();
		if(isNotEmpty(start))map.put("start", getTimeMillions(start));
		if(isNotEmpty(end))map.put("end",  getTimeMillions(end));
		Page<Plat_Msg> page = ps.getPlatMsgPage(map,pageCurrent);
		model.addAttribute("pageBean",page);
		return "platmsg/msg-list";
	}


	/**
	 *  发布/取消发布/删除系统消息
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="handleMsg",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String handleMsg(@RequestParam Map<String, Object> params){

		return ps.handleFeedBack(params);
	}

	/**
	 * 跳转至消息添加页面
	 * @return
	 */
	@RequestMapping("addMsg")
	public String addMsg(){

		return "platmsg/msg-add";
	}
	
	/**
	 * 跳转至消息编辑页面
	 * @return
	 */
	@RequestMapping("editMsg")
	public String editMsg(Model model,Integer pm_id){

		Plat_Msg pm = ps.getMsgById(pm_id);
		List<Plat_Msg_Attachment> attachments = ps.getAttachmentsByMsgId(pm_id);
		model.addAttribute("msg", pm);
		model.addAttribute("fjs", attachments);
		return "platmsg/msg-edit";
	}
	
	
	
	/**
	 *  新增短消息
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="saveMsg",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String saveMsg(@RequestParam Map<String, Object> params){
		try {
			params.put("pm_sender_userid",((User)currentSession().getAttribute("user")).getU_id());
			params.put("pm_msg_type", 1);
			params.put("pm_system_msg_status", 0);
			return ps.saveMsg(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "500";
	}

	

	/**
	 *  编辑短消息
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="updateMsg",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String updateMsg(@RequestParam Map<String, Object> params){
		try {
			return ps.updateMsg(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "500";
	}
}
