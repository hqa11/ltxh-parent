package com.agc.web.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.agc.core.annotion.Pass;
import com.agc.core.utils.DataUtil;
import com.agc.dubbo.model.User;
import com.agc.dubbo.service.user.UserService;
import com.agc.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 登录控制器
 * @author Administrator
 *
 */
@RequestMapping("/login")
@Controller
public class LoginController extends BaseController {

	@Resource
	private UserService us;

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("loginPage")
	@Pass
	public String goLoginPage(){
		return "login";
	}


	/**
	 * 登录验证
	 * @param password
	 * @param username
	 * @param model
	 * @return
	 */
	@RequestMapping("goLogin")
	@Pass
	public String login(String password,String username,Model model,HttpServletResponse response){

		String md5 = DataUtil.MD5(password);
		
		System.out.println(md5);
		
		User user = us.getUser(username,md5);
		if(user == null){
			currentSession().setAttribute("loginFailed", "用户名或者密码不正确!");
			return "redirect:loginPage.action";
		}
		if(user.getU_state() == 1){
			currentSession().setAttribute("loginFailed", "该用户已被禁用!");
			return "redirect:loginPage.action";
		}
		currentSession().removeAttribute("loginFailed");
		//写入session
		currentSession().setAttribute("user", user);
		//写入cookie
		Cookie cookie=new Cookie("userId", user.getU_id().toString());
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:goIndex.action";
	}


	/**
	 * 去往首页
	 * @return
	 */
	@RequestMapping("goIndex")
	public String toIndex(){

		return "index";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(){
        currentSession().removeAttribute("user");
        currentSession().invalidate();
        Cookie[] cookies = currentRequest().getCookies();
        for (Cookie cookie : cookies) {
			if("userId".equals(cookie.getName())){
				//立即失效
				cookie.setMaxAge(0);
				break;
			}
		}
		return "redirect:loginPage.action";
	}

}
