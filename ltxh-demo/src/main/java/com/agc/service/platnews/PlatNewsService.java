package com.agc.service.platnews;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Service;

import com.agc.constant.C;
import com.agc.dao.platnews.PlatNewsDao;
import com.agc.model.Plat_Msg;
import com.agc.model.Plat_Msg_Attachment;
import com.agc.service.BaseService;
import com.agc.utils.JsonUtils;

@Service
public class PlatNewsService extends BaseService{

	@Resource	
	private PlatNewsDao msgDao;

	/**
	 * 站内消息分页
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<Plat_Msg> getPlatMsgPage(Map<String, Object> map,
										 Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<Plat_Msg> page = msgDao.getPlatMsgPage(pageable);
		return page;
	}

	/**
	 * 发布/取消发布/删除站内信
	 * @param params
	 * @return
	 */
	public String handleFeedBack(Map<String, Object> params) {
		int ret = msgDao.updateById(params, Plat_Msg.class, "pm_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

	/**
	 * 新增短消息
	 * @param params
	 * @return
	 */
	public String saveMsg(Map<String, Object> params) {
		String fjStr = params.get("fjStr") == null ? "" : params.get("fjStr")+ "";
		params.remove("fjStr");
		//保存新闻
		int pk = msgDao.insertBean(params, Plat_Msg.class);
		//保存附件
		if(isNotEmpty(fjStr)){
			addMsgAttachments(fjStr, pk);
		}
		return C.ADD_OK;
	}

	/**
	 * 添加消息附件
	 * @param fjStr
	 * @param pk
	 */
	private void addMsgAttachments(String fjStr, Object pk) {
		List<Map<String,Object>> fjList = JsonUtils.toObject(fjStr, List.class);
		int index = 0;
		for (Map<String, Object> fjMap : fjList) {
			fjMap.put("pma_create_time",System.currentTimeMillis());
			fjMap.put("pma_pm_id",pk);
			fjMap.put("pma_order",index);
			msgDao.insertBean(fjMap, Plat_Msg_Attachment.class);
			index ++;
		}
	}

	/**
	 * 获取消息
	 * @param pm_id
	 * @return
	 */
	public Plat_Msg getMsgById(Integer pm_id) {
		Plat_Msg pm = msgDao.getById(pm_id, Plat_Msg.class, "pm_id");
		return pm;
	}

	/**
	 * 获取附件
	 * @param pm_id
	 * @return
	 */
	public List<Plat_Msg_Attachment> getAttachmentsByMsgId(Integer pm_id) {
		List<Plat_Msg_Attachment>  fjs = msgDao.getAttachmentsByMsgId(pm_id);
		return fjs;
	}

	/**
	 * 编辑短消息
	 * @param params
	 * @return
	 */
	public String updateMsg(Map<String, Object> params) {
		String fjStr = params.get("fjStr") == null ? "" : params.get("fjStr")+ "";
		params.remove("fjStr");
		//编辑新闻
		int ret = msgDao.updateById(params, Plat_Msg.class,"pm_id");
		if(ret < 1)return "500";
		Object pk = params.get("pm_id");
		//保存附件
		//删除原附件
		msgDao.deleteOldAttachments(pk);
		if(isNotEmpty(fjStr)){
			addMsgAttachments(fjStr, pk);
		}
		return C.UPDATE_OK;
	}

}
