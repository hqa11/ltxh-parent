package com.agc.dubbo.service.feedback;


import com.agc.core.common.Page;
import com.agc.dubbo.model.Feed_Back;

import java.util.Map;

public interface FeedBackService  {


	/**
	 * 获取意见反馈分页
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<Feed_Back> getFeedBackPage(Map<String, Object> map,
										   Integer pageCurrent);
    
	/**
	 * 处理意见反馈
	 * @param params
	 * @return
	 */
	public String handleFeedBack(Map<String, Object> params);

}
