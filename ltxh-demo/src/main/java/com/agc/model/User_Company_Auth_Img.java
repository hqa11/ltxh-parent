package com.agc.model;


/**
 * 用户
 * @author Administrator
 */
public class User_Company_Auth_Img {
	
	private Long ucai_id;
	private Long ucai_uca_id;	//认证表
	private String ucai_img_path;	//公司名称
	private Integer ucai_order;	//默认0	排序
	private Long ucai_auth_time;		//
	private Integer ucai_is_del;
	
	
	public Long getUcai_id() {
		return ucai_id;
	}
	public void setUcai_id(Long ucai_id) {
		this.ucai_id = ucai_id;
	}
	public Long getUcai_uca_id() {
		return ucai_uca_id;
	}
	public void setUcai_uca_id(Long ucai_uca_id) {
		this.ucai_uca_id = ucai_uca_id;
	}
	public String getUcai_img_path() {
		return ucai_img_path;
	}
	public void setUcai_img_path(String ucai_img_path) {
		this.ucai_img_path = ucai_img_path;
	}
	public Integer getUcai_order() {
		return ucai_order;
	}
	public void setUcai_order(Integer ucai_order) {
		this.ucai_order = ucai_order;
	}
	public Long getUcai_auth_time() {
		return ucai_auth_time;
	}
	public void setUcai_auth_time(Long ucai_auth_time) {
		this.ucai_auth_time = ucai_auth_time;
	}
	public Integer getUcai_is_del() {
		return ucai_is_del;
	}
	public void setUcai_is_del(Integer ucai_is_del) {
		this.ucai_is_del = ucai_is_del;
	}
}
