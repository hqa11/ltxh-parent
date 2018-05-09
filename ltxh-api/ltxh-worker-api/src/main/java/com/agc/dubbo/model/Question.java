package com.agc.dubbo.model;


import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;

import java.io.Serializable;

/**
 * 问卷调查
 * @author Administrator
 */
public class Question implements Serializable {
	
	private Long q_id;
	private String q_name;		//标题
	private String q_desc;		//描述
	private Long q_create_time;	//时间
	private Integer q_is_del;		//默认0
	
	@Except
	private String q_create_timef;
	@Except
	private Integer answer_num = 0;
	public Long getQ_id() {
		return q_id;
	}
	public void setQ_id(Long q_id) {
		this.q_id = q_id;
	}
	public String getQ_name() {
		return q_name;
	}
	public void setQ_name(String q_name) {
		this.q_name = q_name;
	}
	public String getQ_desc() {
		return q_desc;
	}
	public void setQ_desc(String q_desc) {
		this.q_desc = q_desc;
	}
	public Long getQ_create_time() {
		return q_create_time;
	}
	public void setQ_create_time(Long q_create_time) {
		this.q_create_time = q_create_time;
	}
	public Integer getQ_is_del() {
		return q_is_del;
	}
	public void setQ_is_del(Integer q_is_del) {
		this.q_is_del = q_is_del;
	}
	public String getQ_create_timef() {
		return DataUtil.timeMilionsFormat(q_create_time,"yyyy-MM-dd HH:mm:ss");
	}
	public void setQ_create_timef(String q_create_timef) {
		this.q_create_timef = q_create_timef;
	}
	public Integer getAnswer_num() {
		return answer_num;
	}
	public void setAnswer_num(Integer answer_num) {
		this.answer_num = answer_num;
	}
}
