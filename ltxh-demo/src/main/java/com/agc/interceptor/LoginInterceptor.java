package com.agc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agc.core.annotion.Pass;
import com.agc.core.utils.DataUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.agc.model.User;

public class LoginInterceptor implements HandlerInterceptor{

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
			Pass typeP = hm.getBeanType().getAnnotation(Pass.class);
			if(typeP != null)return true;			
			Pass p = hm.getMethodAnnotation(Pass.class);
			if(p != null)return true;
		}
		//校验
		User user = (User) req.getSession().getAttribute("user");
		if(user != null){
			return true;
		}else{
			req.getSession().setAttribute("loginFailed", "请登录!");
			res.sendRedirect(req.getContextPath() + "/login/loginPage.action");
			return false;
		}

	}

}
