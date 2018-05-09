package com.agc.dubbo.model;


import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;

import java.io.Serializable;

/**
 * 商品表
 * @author Administrator
 */
public class Goods implements Serializable {
	
	private Long g_id;
	private Long g_release_person;		//发布人
	private Long g_release_time;	//发布时间
	private Integer g_type;		//默认1，1代表供应信息 ，2代表求购信息 3协会发布商品
	private String g_title;
	private Integer g_read_num;	//阅读数
	private Long g_place_province_id;	//省市区
	private Long g_place_city_id;	//省市区
	private Long g_place_area_id;	//省市区
	private String g_other_name;	//别名
	private String g_goods_type;	//品种
	private String g_contact_person;	//联系人
	private String g_contact_phone;	//联系电话
	private String g_goods_msg;	//产品信息（需求信息）
	private String g_goods_price;	//供应价格（求购价格）
	private Integer g_is_del;	// 
	private String g_unit;//单位
	private String g_goods_ewm_path;
	
	@Except
	private String defaultImg;	//默认图片
	@Except
	private String username;
	@Except
	private String g_release_timef;	//发布时间
	
	
	public String getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	public Long getG_id() {
		return g_id;
	}
	public void setG_id(Long g_id) {
		this.g_id = g_id;
	}
	public Long getG_release_person() {
		return g_release_person;
	}
	public void setG_release_person(Long g_release_person) {
		this.g_release_person = g_release_person;
	}
	public Long getG_release_time() {
		return g_release_time;
	}
	public void setG_release_time(Long g_release_time) {
		this.g_release_time = g_release_time;
	}
	public Integer getG_type() {
		return g_type;
	}
	public void setG_type(Integer g_type) {
		this.g_type = g_type;
	}
	public String getG_title() {
		return g_title;
	}
	public void setG_title(String g_title) {
		this.g_title = g_title;
	}
	public Integer getG_read_num() {
		return g_read_num;
	}
	public void setG_read_num(Integer g_read_num) {
		this.g_read_num = g_read_num;
	}
	public Long getG_place_province_id() {
		return g_place_province_id;
	}
	public void setG_place_province_id(Long g_place_province_id) {
		this.g_place_province_id = g_place_province_id;
	}
	public Long getG_place_city_id() {
		return g_place_city_id;
	}
	public void setG_place_city_id(Long g_place_city_id) {
		this.g_place_city_id = g_place_city_id;
	}
	public Long getG_place_area_id() {
		return g_place_area_id;
	}
	public void setG_place_area_id(Long g_place_area_id) {
		this.g_place_area_id = g_place_area_id;
	}
	public String getG_other_name() {
		return g_other_name;
	}
	public void setG_other_name(String g_other_name) {
		this.g_other_name = g_other_name;
	}
	public String getG_goods_type() {
		return g_goods_type;
	}
	public void setG_goods_type(String g_goods_type) {
		this.g_goods_type = g_goods_type;
	}
	public String getG_contact_person() {
		return g_contact_person;
	}
	public void setG_contact_person(String g_contact_person) {
		this.g_contact_person = g_contact_person;
	}
	public String getG_contact_phone() {
		return g_contact_phone;
	}
	public void setG_contact_phone(String g_contact_phone) {
		this.g_contact_phone = g_contact_phone;
	}
	public String getG_goods_msg() {
		return g_goods_msg;
	}
	public void setG_goods_msg(String g_goods_msg) {
		this.g_goods_msg = g_goods_msg;
	}
	public String getG_goods_price() {
		return g_goods_price;
	}
	public void setG_goods_price(String g_goods_price) {
		this.g_goods_price = g_goods_price;
	}
	public Integer getG_is_del() {
		return g_is_del;
	}
	public void setG_is_del(Integer g_is_del) {
		this.g_is_del = g_is_del;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getG_release_timef() {
		return DataUtil.timeMilionsFormat(g_release_time, "yyyy-MM-dd HH:mm:ss");
	}
	public void setG_release_timef(String g_release_timef) {
		this.g_release_timef = g_release_timef;
	}
	public String getG_unit() {
		return g_unit;
	}
	public void setG_unit(String g_unit) {
		this.g_unit = g_unit;
	}
	public String getG_goods_ewm_path() {
		return g_goods_ewm_path;
	}
	public void setG_goods_ewm_path(String g_goods_ewm_path) {
		this.g_goods_ewm_path = g_goods_ewm_path;
	}
}
