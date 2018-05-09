package com.agc.controller.goods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.agc.core.common.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agc.controller.BaseController;
import com.agc.model.Goods;
import com.agc.model.Goods_Img;
import com.agc.model.User;
import com.agc.service.goods.GoodsService;

@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController{
	
	@Resource
	private GoodsService gs;
	
	/**
	 * 去往供求列表
	 * @return
	 */
	@RequestMapping("goGoodsList")
	public String goGoodsList(@RequestParam(defaultValue = "1") Integer pageCurrent,String g_title,
			Integer g_type,Model model){
		//参数回显
		model.addAttribute("g_title", g_title);
		model.addAttribute("g_type", g_type);
		Map<String, Object> map = getMap();
		if(isNotEmpty(g_title))map.put("g_title", g_title);
		if(isNotEmpty(g_type))map.put("g_type", g_type);
		Page<Goods> page = gs.getGoodsPage(map,pageCurrent);
		model.addAttribute("pageBean",page);
		return "goods/goods-list";
	}

	/**
	 * 跳转至发布供求页面
	 * @return
	 */
	@RequestMapping("addGoods")
	public String addGoods(){
		
		return "goods/goods-add";
	}
	
	/**
	 * 跳转至编辑供求页面
	 * @return
	 */
	@RequestMapping("editGoods")
	public String editGoods(Model model,Integer g_id){
		Goods goods = gs.getGoodsById(g_id);
        List<Goods_Img> imgs = gs.getGoodsImgs(g_id);
        model.addAttribute("goods",goods);
        model.addAttribute("imgs",imgs);
		return "goods/goods-edit";
	}
	
	/**
	 *  删除供求
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="delNews",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String delGoods(@RequestParam Map<String, Object> params){
		
        return gs.delGoods(params);
	}
	
	
	/**
	 *  新增供求
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="saveGoods",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String saveGoods(@RequestParam Map<String, Object> params){
		try {
			params.put("g_release_person", ((User)currentSession().getAttribute("user")).getU_id());
			return gs.saveGoods(params,currentRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "500";
	}
	
	
	/**
	 *  编辑供求
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="updateGoods",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String updateGoods(@RequestParam Map<String, Object> params){
		try {
			return gs.updateGoods(params,currentRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "500";
	}
	
	
	
}
