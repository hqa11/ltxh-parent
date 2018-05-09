package com.agc.dubbo.model;


import java.io.Serializable;

/**
 * 商品图片表
 * @author Administrator
 */
public class Goods_Img implements Serializable {
	
	private Long gi_id;
	private Long gi_g_id;		//商品ID
	private String gi_img_path;	//路径
	private Integer gi_order;		//默认0，	排序字段  越大越靠前
	private Long gi_create_time;
	private Integer gi_is_del;	//
	
	
	
	public Long getGi_id() {
		return gi_id;
	}
	public void setGi_id(Long gi_id) {
		this.gi_id = gi_id;
	}
	public Long getGi_g_id() {
		return gi_g_id;
	}
	public void setGi_g_id(Long gi_g_id) {
		this.gi_g_id = gi_g_id;
	}
	public String getGi_img_path() {
		return gi_img_path;
	}
	public void setGi_img_path(String gi_img_path) {
		this.gi_img_path = gi_img_path;
	}
	public Integer getGi_order() {
		return gi_order;
	}
	public void setGi_order(Integer gi_order) {
		this.gi_order = gi_order;
	}
	public Long getGi_create_time() {
		return gi_create_time;
	}
	public void setGi_create_time(Long gi_create_time) {
		this.gi_create_time = gi_create_time;
	}
	public Integer getGi_is_del() {
		return gi_is_del;
	}
	public void setGi_is_del(Integer gi_is_del) {
		this.gi_is_del = gi_is_del;
	}
}
