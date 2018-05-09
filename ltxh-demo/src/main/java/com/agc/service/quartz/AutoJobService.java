package com.agc.service.quartz;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agc.utils.ye.YeRedisUtil;

@Component("autoJobService")
@Transactional
public class AutoJobService   {
	

	@Resource
	private YeRedisUtil redisUtil;
	public void testRedis() {
		
		System.out.println("============================================>>>>");
		System.out.println(redisUtil.getValue("x"));
		System.out.println("============================================>>>>");
		
	}

	
}
