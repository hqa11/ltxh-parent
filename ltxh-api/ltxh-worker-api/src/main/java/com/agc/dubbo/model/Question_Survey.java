package com.agc.dubbo.model;


import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;

import java.io.Serializable;

/**
 * 问卷调查题目表
 * @author Administrator
 */
public class Question_Survey implements Serializable {
	
	private Long qs_id;
	private String qs_subject;		//题目
	private String qs_answer_a;		//答案A
	private String qs_answer_b;		//答案b
	private String qs_answer_c;		//答案c
	private String qs_answer_d;		//答案d
	private String qs_answer_e;		//答案e
	private String qs_right_answer;		//正确答案
	private Long qs_create_time;	//接收人ID
	private Integer qs_is_del;		//默认0
	
	@Except
	private String qs_create_timef;
	@Except
	private Integer exist;
	public Long getQs_id() {
		return qs_id;
	}
	public void setQs_id(Long qs_id) {
		this.qs_id = qs_id;
	}
	public String getQs_subject() {
		return qs_subject;
	}
	public void setQs_subject(String qs_subject) {
		this.qs_subject = qs_subject;
	}
	public String getQs_answer_a() {
		return qs_answer_a;
	}
	public void setQs_answer_a(String qs_answer_a) {
		this.qs_answer_a = qs_answer_a;
	}
	public String getQs_answer_b() {
		return qs_answer_b;
	}
	public void setQs_answer_b(String qs_answer_b) {
		this.qs_answer_b = qs_answer_b;
	}
	public String getQs_answer_c() {
		return qs_answer_c;
	}
	public void setQs_answer_c(String qs_answer_c) {
		this.qs_answer_c = qs_answer_c;
	}
	public String getQs_answer_d() {
		return qs_answer_d;
	}
	public void setQs_answer_d(String qs_answer_d) {
		this.qs_answer_d = qs_answer_d;
	}
	public String getQs_answer_e() {
		return qs_answer_e;
	}
	public void setQs_answer_e(String qs_answer_e) {
		this.qs_answer_e = qs_answer_e;
	}
	public String getQs_right_answer() {
		return qs_right_answer;
	}
	public void setQs_right_answer(String qs_right_answer) {
		this.qs_right_answer = qs_right_answer;
	}
	public Long getQs_create_time() {
		return qs_create_time;
	}
	public void setQs_create_time(Long qs_create_time) {
		this.qs_create_time = qs_create_time;
	}
	public Integer getQs_is_del() {
		return qs_is_del;
	}
	public void setQs_is_del(Integer qs_is_del) {
		this.qs_is_del = qs_is_del;
	}
	public String getQs_create_timef() {
		return DataUtil.timeMilionsFormat(qs_create_time,"yyyy-MM-dd HH:mm:ss");
	}
	public void setQs_create_timef(String qs_create_timef) {
		this.qs_create_timef = qs_create_timef;
	}
	public Integer getExist() {
		return exist;
	}
	public void setExist(Integer exist) {
		this.exist = exist;
	}
}
