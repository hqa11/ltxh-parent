package com.agc.model;


/**
 * 新闻图片
 * @author Administrator
 */
public class News_Img {
	
	private Long ni_id;
	private Long ni_news_id;	
	private String ni_path;		//图片路径
	private Integer ni_order;	//默认0,排序
	private Long ni_create_time;		//创建时间
	private Long ni_edit_time;		//修改时间
	private Integer ni_is_del;		//默认0
	
	
	public Long getNi_id() {
		return ni_id;
	}
	public void setNi_id(Long ni_id) {
		this.ni_id = ni_id;
	}
	public String getNi_path() {
		return ni_path;
	}
	public void setNi_path(String ni_path) {
		this.ni_path = ni_path;
	}
	public Integer getNi_order() {
		return ni_order;
	}
	public void setNi_order(Integer ni_order) {
		this.ni_order = ni_order;
	}
	public Long getNi_create_time() {
		return ni_create_time;
	}
	public void setNi_create_time(Long ni_create_time) {
		this.ni_create_time = ni_create_time;
	}
	public Long getNi_edit_time() {
		return ni_edit_time;
	}
	public void setNi_edit_time(Long ni_edit_time) {
		this.ni_edit_time = ni_edit_time;
	}
	public Integer getNi_is_del() {
		return ni_is_del;
	}
	public void setNi_is_del(Integer ni_is_del) {
		this.ni_is_del = ni_is_del;
	}
	public Long getNi_news_id() {
		return ni_news_id;
	}
	public void setNi_news_id(Long ni_news_id) {
		this.ni_news_id = ni_news_id;
	}
}
