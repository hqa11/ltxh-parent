package com.agc.dubbo.service.goods;


import com.agc.core.common.Page;
import com.agc.dubbo.model.Goods;
import com.agc.dubbo.model.Goods_Img;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface GoodsService {

	/**
	 * 获取供求分页列表
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<Goods> getGoodsPage(Map<String, Object> map, Integer pageCurrent);

	/**
	 * 删除供求
	 * @param params
	 * @return
	 */
	public String delGoods(Map<String, Object> params) ;

	/**
	 * 新增供求
	 * @param params
	 * @param request 
	 * @return
	 */
	public String saveGoods(Map<String, Object> params, HttpServletRequest request);

	/**
	 * 获取商品
	 * @param
	 * @return
	 */
	public Goods getGoodsById(Integer g_id);

	/**
	 * 获取商品图片
	 * @param
	 * @return
	 */
	public List<Goods_Img> getGoodsImgs(Integer g_id);

	/**
	 * 编辑供求
	 * @param params
	 * @param
	 * @return
	 */
	public String updateGoods(Map<String, Object> params, HttpServletRequest request);

}
