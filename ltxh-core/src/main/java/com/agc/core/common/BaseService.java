package com.agc.core.common;

import java.util.Map;

public class BaseService extends Base {
	//默认
	protected final  Integer default_pageSize = 10;

	protected Pageable getPageable(Integer pageNumber, Map<String, Object>  o){
		Pageable pageable = new Pageable(pageNumber, default_pageSize);
		pageable.setQmp(o);
		return pageable;
	}
	
}
