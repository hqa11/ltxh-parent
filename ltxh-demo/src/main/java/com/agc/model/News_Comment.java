package com.agc.model;


/**
 * 新闻评论
 * @author Administrator
 */
public class News_Comment {
	
	private Long nc_id;
	private Long nc_news_id;	//新闻ID
	private Long nc_comment_userid;	//用户ID
	private String nc_comment_content;		//评论内容
	private Long nc_comment_time;		//时间
	private Integer nc_is_del;		//默认0
	
	
	public Long getNc_id() {
		return nc_id;
	}
	public void setNc_id(Long nc_id) {
		this.nc_id = nc_id;
	}
	public Long getNc_news_id() {
		return nc_news_id;
	}
	public void setNc_news_id(Long nc_news_id) {
		this.nc_news_id = nc_news_id;
	}
	public Long getNc_comment_userid() {
		return nc_comment_userid;
	}
	public void setNc_comment_userid(Long nc_comment_userid) {
		this.nc_comment_userid = nc_comment_userid;
	}
	public String getNc_comment_content() {
		return nc_comment_content;
	}
	public void setNc_comment_content(String nc_comment_content) {
		this.nc_comment_content = nc_comment_content;
	}
	public Long getNc_comment_time() {
		return nc_comment_time;
	}
	public void setNc_comment_time(Long nc_comment_time) {
		this.nc_comment_time = nc_comment_time;
	}
	public Integer getNc_is_del() {
		return nc_is_del;
	}
	public void setNc_is_del(Integer nc_is_del) {
		this.nc_is_del = nc_is_del;
	}
}
