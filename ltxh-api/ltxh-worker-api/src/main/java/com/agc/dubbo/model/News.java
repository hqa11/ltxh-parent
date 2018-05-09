package com.agc.dubbo.model;

import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;
import com.agc.core.utils.ResourceUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 新闻
 * @author Administrator
 */
public class News implements Serializable {
	
	private Long news_id;
	private String news_title;	//标题
	private String news_desc;	//简介
	private String news_detail;	//新闻详情
	private String news_release_person;		//发布人
	private Integer news_order;		//默认0	排序
	private Long news_create_time;	//创建时间
	private String news_create_timef;	//创建时间
	private Long news_release_time;	//发布时间
	private String news_release_timef;	//发布时间
	private Integer news_state;		//默认0
	private Integer news_is_del;		//默认0
	private Integer news_type;
	@Except
	private String defaultImg;	//默认图片
	@Except
	private Integer agreeNum;	//点赞数	
	@Except
	private Integer disAgreeNum;	//点踩数
	@Except
	private Integer cpNum;
	public void setCpNum(Integer cpNum) {
		this.cpNum = cpNum;
	}
	public Integer getCpNum() {
		return cpNum;
	}
	public Long getNews_id() {
		return news_id;
	}
	public void setNews_id(Long news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_desc() {
		return news_desc;
	}
	public void setNews_desc(String news_desc) {
		this.news_desc = news_desc;
	}
	public String getNews_detail() {
		return news_detail;
	}
	public void setNews_detail(String news_detail) {
		this.news_detail = news_detail;
	}
	public String getNews_release_person() {
		return news_release_person;
	}
	public void setNews_release_person(String news_release_person) {
		this.news_release_person = news_release_person;
	}
	public Integer getNews_order() {
		return news_order;
	}
	public void setNews_order(Integer news_order) {
		this.news_order = news_order;
	}
	public Long getNews_create_time() {
		return news_create_time;
	}
	public void setNews_create_time(Long news_create_time) {
		this.news_create_time = news_create_time;
	}
	public Long getNews_release_time() {
		return news_release_time;
	}
	public void setNews_release_time(Long news_release_time) {
		this.news_release_time = news_release_time;
	}
	public Integer getNews_state() {
		return news_state;
	}
	public void setNews_state(Integer news_state) {
		this.news_state = news_state;
	}
	public Integer getNews_is_del() {
		return news_is_del;
	}
	public void setNews_is_del(Integer news_is_del) {
		this.news_is_del = news_is_del;
	}
	public String getDefaultImg() {
		return StringUtils.isNotBlank(defaultImg)? ResourceUtil.getString("fileServerHost")+defaultImg:null;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	public Integer getAgreeNum() {
		return agreeNum;
	}
	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}
	public Integer getDisAgreeNum() {
		return disAgreeNum;
	}
	public void setDisAgreeNum(Integer disAgreeNum) {
		this.disAgreeNum = disAgreeNum;
	}

	public String getNews_create_timef() {
		return DataUtil.timeMilionsFormat(news_create_time, "yyyy-MM-dd HH:mm:ss");
	}

	public void setNews_create_timef(String news_create_timef) {
		this.news_create_timef = news_create_timef;
	}

	public String getNews_release_timef() {
		return DataUtil.timeMilionsFormat(news_release_time, "yyyy-MM-dd HH:mm:ss");
	}

	public void setNews_release_timef(String news_release_timef) {
		this.news_release_timef = news_release_timef;
	}
	public Integer getNews_type() {
		return news_type;
	}
	public void setNews_type(Integer news_type) {
		this.news_type = news_type;
	}
}
