package com.agc.web.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.utils.DataUtil;
import com.agc.core.utils.ResourceUtil;
import com.agc.dubbo.model.User;
import com.agc.dubbo.model.User_Company_Auth;
import com.agc.dubbo.model.User_Role;
import com.agc.dubbo.service.user.UserService;
import com.agc.web.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Resource
	private UserService us;
	
	/**
	 * 去往用户列表
	 * @return
	 */
	@RequestMapping("goUserList")
	public String goUserList(@RequestParam(defaultValue = "1") Integer pageCurrent,String u_nick_name,
			String u_phone,Model model){
		//参数回显
		model.addAttribute("u_nick_name", u_nick_name);
		model.addAttribute("u_phone", u_phone);
		Map<String, Object> map = getMap();
		if(isNotEmpty(u_nick_name))map.put("u_nick_name", u_nick_name);
		if(isNotEmpty(u_phone))map.put("u_phone", u_phone);
		Page<User> page = us.getUserPage(map,pageCurrent);
		model.addAttribute("pageBean",page);
		return "user/user-list";
	}
	
	
	/**
	 *  删除用户
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="delUser",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String delUser(Integer u_id){
		
        return us.delUser(u_id);
	}
	
	/**
	 *  删除认证请求
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="delAuth",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String delAuth(Integer uca_id){
		
		return us.delAuth(uca_id);
	}
	
	/**
	 *  新增用户
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="saveUser",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String saveUser(User user,Integer u_role,String csi_img_path,String u_company_desc,
			String u_address_detail,String uca_business_license){
		user.setU_reg_time(System.currentTimeMillis());
		user.setU_state(0);
		user.setU_is_del(0);
		//会员初始化密码
		user.setU_pwd(DataUtil.MD5(ResourceUtil.getString("init_user_pwd")));
		return us.saveUser(user,csi_img_path,u_company_desc,u_address_detail,uca_business_license);
	}
	
	/**
	 *  更新用户
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="updateUser",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String updateUser(User user,String uca_business_license,String csi_img_path){
		return us.updateUser(user,uca_business_license,csi_img_path);
	}
	
	/**
	 * 处理用户认证申请
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="handleAuth",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String handleAuth(@RequestParam Map<String, Object> params){
		
		return us.handleAuth(params);
	}
	
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping("addUser")
	public String addUser(Model model){
		List<User_Role> roleList = us.getAllRoles();
		model.addAttribute("roles", roleList);
		return "user/user-add";
	}
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping("editUser")
	public String editUser(Model model,Integer u_id){
		User user = us.getUser(u_id);
		model.addAttribute("user",user);
		List<User_Role> roleList = us.getAllRoles();
		model.addAttribute("roles", roleList);
		return "user/user-edit";
	}
	
	
	/**
	 * 认证申请列表
	 * @return
	 */
	@RequestMapping("goAuthList")
	public String goAuthList(@RequestParam(defaultValue = "1") Integer pageCurrent,String uca_company_name,
			Model model,Integer uca_state){
		//参数回显
		model.addAttribute("uca_company_name", uca_company_name);
		model.addAttribute("uca_state", uca_state);
		Map<String, Object> map = getMap();
		if(isNotEmpty(uca_company_name))map.put("uca_company_name", uca_company_name);
		if(isNotEmpty(uca_state) && -1 != uca_state)map.put("uca_state", uca_state);
		Page<User_Company_Auth> page = us.goAuthPage(map,pageCurrent);
		model.addAttribute("pageBean",page);
		return "user/auth-list";
	}
	
	/**
	 * 查看认证申请图片
	 * @param u_id
	 * @return
	 */
	@RequestMapping(value="lookAuthPic",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public Map<?,?> lookAuthPic(Integer uca_id,String web_path){
		
		return us.lookAuthPic(uca_id,web_path);
	}
	
	
}
