package com.agc.model;


/**
 * 政策附件表
 * @author Administrator
 */
public class News_Attachment {
	
	private Long na_id;
	private Long na_news_id;	//政策ID
	private String na_name;	//文件名
	private String na_attachment_path;	//路径
	private Integer na_order;	//回复评论ID
	private Long na_create_time;		//时间
	private Integer na_is_del;		//默认0
	
	
	
	public Long getNa_id() {
		return na_id;
	}
	public void setNa_id(Long na_id) {
		this.na_id = na_id;
	}
	public Long getNa_news_id() {
		return na_news_id;
	}
	public void setNa_news_id(Long na_news_id) {
		this.na_news_id = na_news_id;
	}
	public String getNa_attachment_path() {
		return na_attachment_path;
	}
	public void setNa_attachment_path(String na_attachment_path) {
		this.na_attachment_path = na_attachment_path;
	}
	public Integer getNa_order() {
		return na_order;
	}
	public void setNa_order(Integer na_order) {
		this.na_order = na_order;
	}
	public Long getNa_create_time() {
		return na_create_time;
	}
	public void setNa_create_time(Long na_create_time) {
		this.na_create_time = na_create_time;
	}
	public Integer getNa_is_del() {
		return na_is_del;
	}
	public void setNa_is_del(Integer na_is_del) {
		this.na_is_del = na_is_del;
	}
	public String getNa_name() {
		return na_name;
	}
	public void setNa_name(String na_name) {
		this.na_name = na_name;
	}
}
