package com.agc.dubbo.model;

import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 用户
 * @author Administrator
 */
public class User implements Serializable {
	private Long u_id;
	private String u_phone;
	private String u_pwd;
	private String u_company_phone;
	private String u_company_desc;
	
	private String u_nick_name;
	private Integer u_type;		//会员类型		0-个人	1-企业	2-协会
	private String u_head_img;
	private Integer u_state;	//默认0，激活 ，1代表禁用
	private Long u_reg_time;
	private Integer u_sex;	//性别	0-不详	1-男	2-女
	private String u_company_name;
	private String u_post;	//所在公司职称
	private Integer u_is_del;
	private Integer u_role_id;   //角色ID
	private String u_address_detail; //具体地址
	private String u_longitude;  //经度
	private String u_latitude;  //纬度
	private Long u_province_id;
	private Long u_city_id;
	private Long u_area_id;
	
	//冗余字段
	@Except
	private String uca_business_license;
	@Except
    private List<Map<String,Object>> imgs = new ArrayList();
	
    public String getU_address_detail() {
		return u_address_detail;
	}
	public void setU_address_detail(String u_address_detail) {
		this.u_address_detail = u_address_detail;
	}
	public String getU_longitude() {
		return u_longitude;
	}
	public void setU_longitude(String u_longitude) {
		this.u_longitude = u_longitude;
	}
	public String getU_latitude() {
		return u_latitude;
	}
	public void setU_latitude(String u_latitude) {
		this.u_latitude = u_latitude;
	}
	@Except
	private String token;	//登录凭证
    @Except
    private String u_reg_timef;

	public Long getU_id() {
		return u_id;
	}
	public void setU_id(Long u_id) {
		this.u_id = u_id;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getU_nick_name() {
		return u_nick_name;
	}
	public void setU_nick_name(String u_nick_name) {
		this.u_nick_name = u_nick_name;
	}
	public Integer getU_type() {
		return u_type;
	}
	public void setU_type(Integer u_type) {
		this.u_type = u_type;
	}
	public String getU_head_img() {
		return u_head_img;
	}
	public void setU_head_img(String u_head_img) {
		this.u_head_img = u_head_img;
	}
	public Integer getU_state() {
		return u_state;
	}
	public void setU_state(Integer u_state) {
		this.u_state = u_state;
	}
	public Long getU_reg_time() {
		return u_reg_time;
	}
	public void setU_reg_time(Long u_reg_time) {
		this.u_reg_time = u_reg_time;
	}
	public Integer getU_sex() {
		return u_sex;
	}
	public void setU_sex(Integer u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_company_name() {
		return u_company_name;
	}
	public void setU_company_name(String u_company_name) {
		this.u_company_name = u_company_name;
	}
	public String getU_post() {
		return u_post;
	}
	public void setU_post(String u_post) {
		this.u_post = u_post;
	}
	public Integer getU_is_del() {
		return u_is_del;
	}
	public void setU_is_del(Integer u_is_del) {
		this.u_is_del = u_is_del;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getU_reg_timef() {
		
		return DataUtil.timeMilionsFormat(getU_reg_time(),"yyyy-MM-dd HH:mm:ss");
	}
	public void setU_reg_timef(String u_reg_timef) {
		this.u_reg_timef = u_reg_timef;
	}
	public Integer getU_role_id() {
		return u_role_id;
	}
	public void setU_role_id(Integer u_role_id) {
		this.u_role_id = u_role_id;
	}
	public String getU_company_phone() {
		return u_company_phone;
	}
	public void setU_company_phone(String u_company_phone) {
		this.u_company_phone = u_company_phone;
	}
	public String getU_company_desc() {
		return u_company_desc;
	}
	public void setU_company_desc(String u_company_desc) {
		this.u_company_desc = u_company_desc;
	}
	public Long getU_province_id() {
		return u_province_id;
	}
	public void setU_province_id(Long u_province_id) {
		this.u_province_id = u_province_id;
	}
	public Long getU_city_id() {
		return u_city_id;
	}
	public void setU_city_id(Long u_city_id) {
		this.u_city_id = u_city_id;
	}
	public Long getU_area_id() {
		return u_area_id;
	}
	public void setU_area_id(Long u_area_id) {
		this.u_area_id = u_area_id;
	}
	public String getUca_business_license() {
		return uca_business_license;
	}
	public void setUca_business_license(String uca_business_license) {
		this.uca_business_license = uca_business_license;
	}
	public List<Map<String,Object>> getImgs() {
		return imgs;
	}
	public void setImgs(List<Map<String,Object>> imgs) {
		this.imgs = imgs;
	}
}
