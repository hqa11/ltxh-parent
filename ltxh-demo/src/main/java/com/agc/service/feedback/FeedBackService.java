package com.agc.service.feedback;

import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Service;

import com.agc.constant.C;
import com.agc.dao.FeedBackDao;
import com.agc.model.Feed_Back;
import com.agc.service.BaseService;

@Service
public class FeedBackService extends BaseService{

	@Resource
	private FeedBackDao fbDao;

	/**
	 * 获取意见反馈分页
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<Feed_Back> getFeedBackPage(Map<String, Object> map,
			Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<Feed_Back> page = fbDao.getNewsPage(pageable);
		return page;
	}
    
	/**
	 * 处理意见反馈
	 * @param params
	 * @return
	 */
	public String handleFeedBack(Map<String, Object> params) {
		int ret = fbDao.updateById(params, Feed_Back.class, "fd_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

}
