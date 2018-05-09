package com.agc.dubbo.service.platnews;


import com.agc.core.common.Page;
import com.agc.dubbo.model.Plat_Msg;
import com.agc.dubbo.model.Plat_Msg_Attachment;

import java.util.List;
import java.util.Map;

public interface PlatNewsService {

	/**
	 * 站内消息分页
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<Plat_Msg> getPlatMsgPage(Map<String, Object> map,
										 Integer pageCurrent);

	/**
	 * 发布/取消发布/删除站内信
	 * @param params
	 * @return
	 */
	public String handleFeedBack(Map<String, Object> params);

	/**
	 * 新增短消息
	 * @param params
	 * @return
	 */
	public String saveMsg(Map<String, Object> params);


	/**
	 * 获取消息
	 * @param pm_id
	 * @return
	 */
	public Plat_Msg getMsgById(Integer pm_id) ;

	/**
	 * 获取附件
	 * @param pm_id
	 * @return
	 */
	public List<Plat_Msg_Attachment> getAttachmentsByMsgId(Integer pm_id);

	/**
	 * 编辑短消息
	 * @param params
	 * @return
	 */
	public String updateMsg(Map<String, Object> params);

}
