package com.agc.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Service;

import com.agc.constant.C;
import com.agc.dao.user.UserDao;
import com.agc.model.User;
import com.agc.model.User_Company_Auth;
import com.agc.model.User_Company_Auth_Img;
import com.agc.model.User_Role;
import com.agc.service.BaseService;

@Service
public class UserService  extends BaseService{

	@Resource
	private UserDao userDao;

	/**
	 * 分页获取用户
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<User> getUserPage(Map<String, Object> map, Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<User> page = userDao.getUserPage(pageable);
		return page;
	}

	/**
	 * 获取用户
	 * @param username
	 * @param md5
	 * @return
	 */
	public User getUser(String username, String password) {

		return userDao.getUser(username,password);
	}

	/**
	 *  删除用户
	 * @return
	 */
	public String delUser(Integer u_id) {
		Map<String, Object> params = getMap();
		params.put("u_id", u_id);
		params.put("u_is_del", 1);
		int ret = userDao.updateById(params, User.class, "u_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

	/**
	 * 新增用户
	 * @param user
	 * @param csi_img_path 
	 * @param u_company_desc 
	 * @param u_role 
	 * @return
	 */
	public String saveUser(User user, String csi_img_path,String u_address_detail,String u_company_desc , String uca_business_license) {
		List<User> us = userDao.getUserByPhone(user.getU_phone());
		//重复注册
		if(us != null && us.size() > 0)return "400";
			int pk = userDao.insertBean(user);
			insertUserImgs(csi_img_path, pk);
			Map<String,Object> user_company_auth=getMap();
			user_company_auth.put("uca_apply_userid", pk);
			user_company_auth.put("uca_business_license", uca_business_license);
			user_company_auth.put("uca_state", 1);
			user_company_auth.put("uca_company_name", user.getU_company_name());
			user_company_auth.put("uca_auth_time", now());
			user_company_auth.put("uca_create_time", now());
			userDao.insertBean(user_company_auth,"user_company_auth");					
			return C.ADD_OK;
	}

	/**
	 * 插入用户图片
	 * @param csi_img_path
	 * @param pk
	 */
	private void insertUserImgs(String csi_img_path, int pk) {
		if(csi_img_path!=null){
			String[] pathArray=csi_img_path.split(",");
			for (String path : pathArray) {
				Map<String,Object> user_company_show_img=getMap();
				user_company_show_img.put("csi_userid", pk);
				user_company_show_img.put("csi_img_path", path);
				user_company_show_img.put("csi_create_time", now());
				user_company_show_img.put("csi_edit_time",  now());
				userDao.insertBean(user_company_show_img,"user_company_show_img");
				
			}
		}
	}

	/**
	 * 根据主键获取用户
	 * @param u_id
	 * @return
	 */
	public User getUser(Integer u_id) {
		User user = userDao.getUserById(u_id);
        List<Map<String,Object>> imgs = userDao.getCoImgs(u_id);
        user.setImgs(imgs);
		return user;
	}


	/**
	 * 更新用户
	 * @param params
	 * @return
	 */
	//public String updateUser(@RequestParam Map<String, Object> params) {
		public String updateUser(User user,String uca_business_license,String csi_img_path) {
		//1.user 	
		int ret = userDao.updateById(user,"u_id");
		//2.img
		if(ret < 1)throw new RuntimeException();
		//2.1 移除原先的图片关联(is_del 字段改为1)
		userDao.deleteAllUserImgs(user.getU_id());
		//2.2将新的图片存入数据库
		insertUserImgs(csi_img_path,user.getU_id().intValue());
		if(ret < 1)throw new RuntimeException();
		//3.auth
		ret = userDao.updateAuthByUserId(user.getU_id(),uca_business_license);
		/*if(ret < 1)throw new RuntimeException();
		return "500";*/
		/*if(ret == 1)*/
			return C.UPDATE_OK;
	}

	/**
	 * 认证请求列表
	 * @param map
	 * @param pageCurrent
	 * @return
	 */
	public Page<User_Company_Auth> goAuthPage(Map<String, Object> map, Integer pageCurrent) {
		Pageable pageable = getPageable(pageCurrent, map);
		Page<User_Company_Auth> page = userDao.getAuthPage(pageable);
		return page;
	}

	/**
	 * 删除认证请求
	 * @param uca_id
	 * @return
	 */
	public String delAuth(Integer uca_id) {
		Map<String, Object> params = getMap();
		params.put("uca_id", uca_id);
		params.put("uca_is_del", 1);
		int ret = userDao.updateById(params, User_Company_Auth.class, "uca_id");
		if(ret == 1)return C.OP_OK;
		return "500";
	}

	/**
	 * 处理用户认证申请
	 * @param params
	 * @return
	 */
	public String handleAuth(Map<String, Object> params) {
		int ret = userDao.updateById(params,User_Company_Auth.class,"uca_id");
		if(ret == 0)return "500";
		//将申请人升级
		String  state = params.get("uca_state") + "";
		if("1".equals(state)){
			User_Company_Auth auth = userDao.getById(Integer.parseInt(params.get("uca_id") + ""), User_Company_Auth.class,"uca_id");
			Long u_id = auth.getUca_apply_userid();
			Map<String, Object> map = getMap();
			map.put("u_id", u_id);
			map.put("u_type", 1);
			int r = userDao.updateById(map, User.class, "u_id");
			if(r == 1)return C.OP_OK;
		}
		return C.OP_OK;
	}

	/**
	 * 查看图片
	 * @param uca_id
	 * @return
	 */
	public Map<?, ?> lookAuthPic(Integer uca_id,String web_path) {
		List<User_Company_Auth_Img> imgs = userDao.getAuthImgs(uca_id);
		//构造json格式
		Map<String,Object> map_out = getMap();
		map_out.put("title", "认证图片");
		map_out.put("id", uca_id);
		map_out.put("start", 0);
		List<Map<String,Object>> data_list=new ArrayList();
		if(imgs!=null && imgs.size() > 0){
			int index=1;
			for (User_Company_Auth_Img img : imgs) {
				Map<String,Object> map_inner = getMap();
				map_inner.put("alt","图片"+index );
				map_inner.put("pid",img.getUcai_id());
				map_inner.put("src",web_path+img.getUcai_img_path());
				map_inner.put("thumb",web_path+img.getUcai_img_path());
				data_list.add(map_inner);
				index ++;
			}
		}
		map_out.put("data",data_list);
		return map_out;
	}

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<User_Role> getAllRoles() {
		
		return userDao.getRoles();
	}

}
