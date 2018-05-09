package com.agc.dubbo.service.user;

import com.agc.core.common.Page;
import com.agc.dubbo.model.User;
import com.agc.dubbo.model.User_Company_Auth;
import com.agc.dubbo.model.User_Role;

import java.util.List;
import java.util.Map;

public interface UserService{

	/**
	 * 分页获取用户
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<User> getUserPage(Map<String, Object> map, Integer pageCurrent);

	public String updateUser(User user,String uca_business_license,String csi_img_path);

	/**
	 * 获取用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password);

	/**
	 *  删除用户
	 * @return
	 */
	public String delUser(Integer u_id);

	/**
	 * 新增用户
	 * @param user
	 * @param csi_img_path 
	 * @param u_company_desc 
	 * @param u_company_desc
	 * @return
	 */
	public String saveUser(User user, String csi_img_path,String u_address_detail,String u_company_desc , String uca_business_license) ;


	/**
	 * 根据主键获取用户
	 * @param u_id
	 * @return
	 */
	public User getUser(Integer u_id);


	/**
	 * 认证请求列表
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<User_Company_Auth> goAuthPage(Map<String, Object> map, Integer pageCurrent);

	/**
	 * 删除认证请求
	 * @param uca_id
	 * @return
	 */
	public String delAuth(Integer uca_id);

	/**
	 * 处理用户认证申请
	 * @param params
	 * @return
	 */
	public String handleAuth(Map<String, Object> params);

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<User_Role> getAllRoles();

	public Map<?, ?> lookAuthPic(Integer uca_id,String web_path);

}
