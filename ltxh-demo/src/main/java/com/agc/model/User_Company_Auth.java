package com.agc.model;


import com.agc.core.utils.DataUtil;

/**
 * 用户
 * @author Administrator
 */
public class User_Company_Auth {
	
	private Long uca_id;
	private String uca_company_name;	//公司名称
	private String uca_business_license;	//营业执照编号
	private Long uca_state;		//状态	默认0.代表待审核，1代表认证通过 2代表认证失败
	private String uca_failure_reason;	//认证失败原因
	private Long uca_auth_time;	//
	private String uca_auth_timef;	//
	private Long uca_create_time;	//
	private String uca_create_timef;	//
	private Integer uca_is_del;
	private Long uca_apply_userid;
	
	//申请人
	private String username;
	public void setUca_apply_userid(Long uca_apply_userid) {
		this.uca_apply_userid = uca_apply_userid;
	}
	public Long getUca_apply_userid() {
		return uca_apply_userid;
	}
	
	public Long getUca_id() {
		return uca_id;
	}
	public void setUca_id(Long uca_id) {
		this.uca_id = uca_id;
	}
	public String getUca_company_name() {
		return uca_company_name;
	}
	public void setUca_company_name(String uca_company_name) {
		this.uca_company_name = uca_company_name;
	}
	public String getUca_business_license() {
		return uca_business_license;
	}
	public void setUca_business_license(String uca_business_license) {
		this.uca_business_license = uca_business_license;
	}
	public Long getUca_state() {
		return uca_state;
	}
	public void setUca_state(Long uca_state) {
		this.uca_state = uca_state;
	}
	public String getUca_failure_reason() {
		return uca_failure_reason;
	}
	public void setUca_failure_reason(String uca_failure_reason) {
		this.uca_failure_reason = uca_failure_reason;
	}
	public Long getUca_auth_time() {
		return uca_auth_time;
	}
	public void setUca_auth_time(Long uca_auth_time) {
		this.uca_auth_time = uca_auth_time;
	}
	public Integer getUca_is_del() {
		return uca_is_del;
	}
	public void setUca_is_del(Integer uca_is_del) {
		this.uca_is_del = uca_is_del;
	}
	public Long getUca_create_time() {
		return uca_create_time;
	}
	public void setUca_create_time(Long uca_create_time) {
		this.uca_create_time = uca_create_time;
	}
	public String getUca_auth_timef() {
		return DataUtil.timeMilionsFormat(uca_auth_time, "yyyy-MM-dd HH:mm:ss");
	}
	public void setUca_auth_timef(String uca_auth_timef) {
		this.uca_auth_timef = uca_auth_timef;
	}
	public String getUca_create_timef() {
		return DataUtil.timeMilionsFormat(uca_create_time, "yyyy-MM-dd HH:mm:ss");
	}
	public void setUca_create_timef(String uca_create_timef) {
		this.uca_create_timef = uca_create_timef;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
