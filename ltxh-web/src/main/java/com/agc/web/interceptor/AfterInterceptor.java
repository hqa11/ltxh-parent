package com.agc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agc.core.utils.DataUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AfterInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
					throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object obj) throws Exception {
		if(DataUtil.isAjax(req))return true;
		//放行
		if(obj instanceof HandlerMethod ){
			HandlerMethod hm = (HandlerMethod)obj;
			//获取类上的注解
			String url = null;
			RequestMapping rqm = hm.getBeanType().getAnnotation(RequestMapping.class);
			if(rqm != null)url = rqm.value()[0];			
			RequestMapping rqmm = hm.getMethodAnnotation(RequestMapping.class);
			if(rqmm != null)url+="/"+rqmm.value()[0];
			req.setAttribute("url", url);
		}

		return true;
	}

}
