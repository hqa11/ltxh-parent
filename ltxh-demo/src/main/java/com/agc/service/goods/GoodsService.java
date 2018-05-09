package com.agc.service.goods;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import com.agc.core.utils.HttpUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.agc.constant.C;
import com.agc.dao.goods.GoodsDao;
import com.agc.model.Goods;
import com.agc.model.Goods_Img;
import com.agc.service.BaseService;
import com.agc.utils.JsonUtils;

@Service
public class GoodsService extends BaseService{

	@Resource
	private GoodsDao goodsDao;

	/**
	 * 获取供求分页列表
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<Goods> getGoodsPage(Map<String, Object> map, Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<Goods> page = goodsDao.getGoodsPage(pageable);
		return page;
	}

	/**
	 * 删除供求
	 * @param params
	 * @return
	 */
	public String delGoods(Map<String, Object> params) {
		params.put("g_is_del", 1);
		int ret = goodsDao.updateById(params, Goods.class, "g_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

	/**
	 * 新增供求
	 * @param params
	 * @param request 
	 * @return
	 */
	public String saveGoods(Map<String, Object> params, HttpServletRequest request) {
		String picStr = params.get("picStr") == null ? "" : params.get("picStr")+ "";
		params.remove("picStr");
		params.put("g_release_time",System.currentTimeMillis());
		params.put("g_read_num",0);
		int pk = goodsDao.insertBean(params, Goods.class);
		//插入图片表
		if(isNotEmpty(picStr)){
			addGoodsImgs(picStr, pk);
		}
		//新增二维码
		List<Map<String,Object>> picList = JsonUtils.toObject(picStr, List.class);
		genQrCode(pk,request,picList);
		return C.ADD_OK;
	}


	/**
	 * 获取项目的绝对路径
	 * @param request
	 * @return
	 */
	public String getProjectPath(HttpServletRequest request){
		String projectPath=request.getSession().getServletContext().getRealPath("");
		return projectPath;
	}

	/**
	 * 新增二维码
	 * @param pk
	 * @param request 
	 * @param picList 
	 */
	private void genQrCode(int pk, HttpServletRequest request, List<Map<String, Object>> picList) {
		try {
			//获取项目网络地址
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			//图片相对地址
			String relPath = "";
			if(!CollectionUtils.isEmpty(picList)){
				relPath=picList.get(0).get("gi_img_path")+"";
			}
			Map<String,Object> map_param=new HashMap();
			map_param.put("relPath", relPath);
			String path = "商品详情-" + pk;
			map_param.put("path", path);
			//目标文件夹
			String tarDir="ltxh/goods";
			File f=new File(this.getProjectPath(request)+"/upload/"+tarDir);
			if(!f.exists()){
				f.mkdirs();
			}	
			String tar = tarDir+"/"+System.currentTimeMillis()+".jpg";
			map_param.put("tar", tar);
			HttpUtil.httpPost(basePath+"fileserver/createGoodsQRCode.action",JsonUtils.toJson(map_param));
			Map<String, Object> params = getMap();
			params.put("g_id", pk);
			params.put("g_goods_ewm_path", tar);
			goodsDao.updateById(params, Goods.class, "g_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入图片
	 * @param picStr
	 * @param pk
	 */
	private void addGoodsImgs(String picStr, int pk) {
		List<Map<String,Object>> picList = JsonUtils.toObject(picStr, List.class);
		int index = 0;
		for (Map<String, Object> picMap : picList) {
			picMap.put("gi_create_time",System.currentTimeMillis());
			picMap.put("gi_g_id",pk);
			picMap.put("gi_order",index);
			goodsDao.insertBean(picMap, Goods_Img.class);
			index ++;
		}
	}

	/**
	 * 获取商品
	 * @param gi_id
	 * @return
	 */
	public Goods getGoodsById(Integer g_id) {
		return goodsDao.getById(g_id, Goods.class, "g_id");
	}

	/**
	 * 获取商品图片
	 * @param gi_id
	 * @return
	 */
	public List<Goods_Img> getGoodsImgs(Integer g_id) {
		List<Goods_Img> imgs = goodsDao.getGoodsImgs(g_id);
		return imgs;
	}

	/**
	 * 编辑供求
	 * @param params
	 * @param httpServletRequest 
	 * @return
	 */
	public String updateGoods(Map<String, Object> params, HttpServletRequest request) {
		String picStr = params.get("picStr") == null ? "" : params.get("picStr")+ "";
		params.remove("picStr");
		int ret = goodsDao.updateById(params, Goods.class, "g_id");
		if(ret < 0)return "500";
		Object pk = params.get("g_id");
		//删除原图
		goodsDao.deleteOldImgs(pk);
		// 保存图片
		if(isNotEmpty(picStr)){
			addGoodsImgs(picStr,Integer.parseInt(pk.toString()));
		}
		//新增二维码
		List<Map<String,Object>> picList = JsonUtils.toObject(picStr, List.class);
		genQrCode(Integer.parseInt(pk + ""),request,picList);
		return C.UPDATE_OK;
	}

}
