package com.agc.dubbo.model;


import java.io.Serializable;

/**
 * 新闻点赞
 * @author Administrator
 */
public class News_Agree implements Serializable {
	
	private Long ng_id;
	private Long ng_news_id;	//新闻ID
	private Long ng_agree_userid;	//用户ID
	private Integer ng_agree_state;		//点赞状态	1-无		1-点赞		2-踩
	private Long ng_agree_time;		//点赞时间
	private Long ng_cancel_agree_time;	//取消时间
	private Integer ng_is_del;		//默认0
	
	
	public Long getNg_id() {
		return ng_id;
	}
	public void setNg_id(Long ng_id) {
		this.ng_id = ng_id;
	}
	public Long getNg_news_id() {
		return ng_news_id;
	}
	public void setNg_news_id(Long ng_news_id) {
		this.ng_news_id = ng_news_id;
	}
	public Long getNg_agree_userid() {
		return ng_agree_userid;
	}
	public void setNg_agree_userid(Long ng_agree_userid) {
		this.ng_agree_userid = ng_agree_userid;
	}
	public Integer getNg_agree_state() {
		return ng_agree_state;
	}
	public void setNg_agree_state(Integer ng_agree_state) {
		this.ng_agree_state = ng_agree_state;
	}
	public Long getNg_agree_time() {
		return ng_agree_time;
	}
	public void setNg_agree_time(Long ng_agree_time) {
		this.ng_agree_time = ng_agree_time;
	}
	public Long getNg_cancel_agree_time() {
		return ng_cancel_agree_time;
	}
	public void setNg_cancel_agree_time(Long ng_cancel_agree_time) {
		this.ng_cancel_agree_time = ng_cancel_agree_time;
	}
	public Integer getNg_is_del() {
		return ng_is_del;
	}
	public void setNg_is_del(Integer ng_is_del) {
		this.ng_is_del = ng_is_del;
	}
}
