package com.agc.dao.user;

import java.util.List;
import java.util.Map;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import org.springframework.stereotype.Repository;

import com.agc.dao.BaseDao;
import com.agc.model.User;
import com.agc.model.User_Company_Auth;
import com.agc.model.User_Company_Auth_Img;
import com.agc.model.User_Role;

@Repository
public class UserDao  extends BaseDao<User, Integer>{

	public Page<User> getUserPage(Pageable pb) {
		String sql = "SELECT * FROM user WHERE u_is_del = 0";
		Map<String,Object> o = pb.getQmp();
		if(o != null){
			if(o.get("u_nick_name") != null){
				sql += " AND u_nick_name like ?";
				pb.addQueryVal("%"+o.get("u_nick_name")+"%");
			}
			if(o.get("u_phone") != null){
				sql += " AND u_phone = ? ";
				pb.addQueryVal(o.get("u_phone"));
			}
		}
		sql +=" order by u_reg_time desc ";
		Page<User> page = getPage(sql, pb, User.class);
		return page;
	}

	public User getUser(String username, String password) {
		String sql = "SELECT * FROM user WHERE u_phone = ? AND u_pwd = ? AND u_is_del = 0 AND u_type = 3";
		User user = getMap(sql, User.class, username,password);
		return user;
	}

	public List<User> getUserByPhone(String phone) {
		String sql = "SELECT * FROM user WHERE u_phone = ?  AND u_is_del = 0";
		List<User> list = getList(sql, User.class, phone);
		return list;
	}

	public Page<User_Company_Auth> getAuthPage(Pageable pb) {
		String sql = "SELECT c.*,u.u_nick_name username FROM user_company_auth c left join user u on u.u_id = c.uca_apply_userid WHERE c.uca_is_del = 0";
		Map<String,Object> o = pb.getQmp();
		if(o != null){
			if(o.get("uca_company_name") != null){
				sql += " AND c.uca_company_name like ? ";
				pb.addQueryVal("%"+o.get("uca_company_name")+"%");
			}
			//根据认证状态筛选
			if(o.get("uca_state")!=null){
				sql += " AND uca_state= ? ";
				pb.addQueryVal(o.get("uca_state"));
			}

		}
		sql +=" order by uca_auth_time desc ";
		Page<User_Company_Auth> page = getPage(sql, pb, User_Company_Auth.class);
		return page;
	}

	public List<User_Company_Auth_Img> getAuthImgs(Integer uca_id) {
		String sql = "SELECT * FROM user_company_auth_img WHERE ucai_uca_id = ?  AND ucai_is_del = 0";
		List<User_Company_Auth_Img> list = getList(sql, User_Company_Auth_Img.class, uca_id);
		return list;
	}
	/**
	 * 获取角色列表
	 * @return
	 */
	public List<User_Role> getRoles() {
		String sql = "SELECT * FROM user_role where ur_is_del = 0";
		return getList(sql,User_Role.class,null);
	}
	/**
	 * 添加新用户当用户类型为企业时
	 * @return
	 */
	/*public List<User> insertUser(){
		String sql="INSERT INTO user(u_phone,u_nick_name,u_company_name,u_post,u_sex,u_head_img,u_address_detail,u_company_phone,) value(?,?,?,?,?,?,?,?)";
		return getList(sql,User.class);

	}*/

	/**
	 *查询用户 
	 */
	public User getUserById(Integer u_id) {
		String sql = "select u.*,c.uca_business_license from user u left join user_company_auth c on c.uca_apply_userid = u.u_id where u.u_id = ?";
		return getMap(sql, User.class, u_id);
	}

   /**
    * 查询用户图片
    * @param u_id
    * @return
    */
	public List<Map<String, Object>> getCoImgs(Integer u_id) {
		String sql = "select * from user_company_show_img where csi_userid = ? ";
		List<Map<String, Object>> imgList = jtp.queryForList(sql,u_id);
		return imgList;
	}

	/**
	 * 删除用户图片
	 * @param u_id
	 */
	public void deleteAllUserImgs(Long u_id) {
		String sql = "update user_company_show_img set csi_is_del = 1 where csi_userid = ? ";
		jtp.update(sql,u_id);
	}

	/**
	 * 
	 * @param u_id
	 * @param uca_business_license
	 * @return
	 */
	public int updateAuthByUserId(Long u_id, String uca_business_license) {
		String sql = "update user_company_auth set uca_business_license= ? where uca_apply_userid= ? ";
		return jtp.update(sql,uca_business_license,u_id);
	}
}
