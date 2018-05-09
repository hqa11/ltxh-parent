package com.agc.dubbo.model;


import java.io.Serializable;

/**
 * 问卷调查-题目关联表
 * @author Administrator
 */
public class Question_Relation implements Serializable {
	
	private Long qr_id;
	private Long qr_q_id;	//问卷表ID
	private Long qr_qs_id;	//关联题目表ID
	
	
	public Long getQr_id() {
		return qr_id;
	}
	public void setQr_id(Long qr_id) {
		this.qr_id = qr_id;
	}
	public Long getQr_q_id() {
		return qr_q_id;
	}
	public void setQr_q_id(Long qr_q_id) {
		this.qr_q_id = qr_q_id;
	}
	public Long getQr_qs_id() {
		return qr_qs_id;
	}
	public void setQr_qs_id(Long qr_qs_id) {
		this.qr_qs_id = qr_qs_id;
	}
}
