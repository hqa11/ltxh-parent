package com.agc.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import com.agc.core.common.Page;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agc.model.User;
import com.agc.service.user.UserService;

/**
 * 测试类
 * @author Administrator
 *
 */
public class Test {

	static void testSpring(){
		ClassPathXmlApplicationContext  app = new ClassPathXmlApplicationContext("classpath:application-*.xml");
		UserService us = app.getBean(UserService.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u_phone", "18806278373");
		Page<User> uPage = us.getUserPage(map,1);
		List<User> content = uPage.getContent();
		for (User user : content) {
			System.out.println("\n=================================>"+user.getU_nick_name());
		}
	}
	
	public static void main(String[] args) throws Exception{
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {

				return new Random().nextInt(1000);
			}
		};
		
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
	//	Thread.sleep(5000);
		new Thread(future).start();
		System.out.println(future.get().toString());
	}
	
}
