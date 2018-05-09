package com.agc.dubbo.dto;

import java.io.Serializable;

public class AnswerDto implements Serializable {

 private String q_name;//问卷标题
 private String qs_name;//问题
 private Integer a_num;//选A的人数
 private Integer b_num;
 private Integer c_num;
 private Integer d_num;
 private Integer e_num;
 private Integer sum_num;
public String getQ_name() {
	return q_name;
}
public void setQ_name(String q_name) {
	this.q_name = q_name;
}
public String getQs_name() {
	return qs_name;
}
public void setQs_name(String qs_name) {
	this.qs_name = qs_name;
}
public Integer getA_num() {
	return a_num;
}
public void setA_num(Integer a_num) {
	this.a_num = a_num;
}
public Integer getB_num() {
	return b_num;
}
public void setB_num(Integer b_num) {
	this.b_num = b_num;
}
public Integer getC_num() {
	return c_num;
}
public void setC_num(Integer c_num) {
	this.c_num = c_num;
}
public Integer getD_num() {
	return d_num;
}
public void setD_num(Integer d_num) {
	this.d_num = d_num;
}
public Integer getE_num() {
	return e_num;
}
public void setE_num(Integer e_num) {
	this.e_num = e_num;
}
public Integer getSum_num() {
	return a_num + b_num + c_num +d_num +e_num;
}
public void setSum_num(Integer sum_num) {
	this.sum_num = sum_num;
}
	
}
