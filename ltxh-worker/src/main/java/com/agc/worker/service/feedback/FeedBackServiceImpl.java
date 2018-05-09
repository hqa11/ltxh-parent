package com.agc.worker.service.feedback;

import com.agc.core.common.BaseService;
import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import com.agc.core.constant.C;
import com.agc.dubbo.model.Feed_Back;
import com.agc.dubbo.service.feedback.FeedBackService;
import com.agc.worker.dao.FeedBackDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
public class FeedBackServiceImpl extends BaseService implements FeedBackService{

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
