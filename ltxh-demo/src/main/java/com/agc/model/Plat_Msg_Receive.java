package com.agc.model;


/**
 * 站内消息阅读表
 * @author Administrator
 */
public class Plat_Msg_Receive {
	
	private Long pmr_id;
	private Long pmr_pm_id;		//消息ID
	private Long pmr_receive_userid;	//接收人ID
	private Long pmr_create_time;		//时间
	private Long pmr_read_time;		//时间
	private Integer pmr_is_read;		//默认0	0代表未阅读，1代表已阅读
	private Integer pmr_is_del;		//默认0
	
	
	private String u_nick_name;
	
	
	public Long getPmr_id() {
		return pmr_id;
	}
	public void setPmr_id(Long pmr_id) {
		this.pmr_id = pmr_id;
	}
	public Long getPmr_pm_id() {
		return pmr_pm_id;
	}
	public void setPmr_pm_id(Long pmr_pm_id) {
		this.pmr_pm_id = pmr_pm_id;
	}
	public Long getPmr_read_time() {
		return pmr_read_time;
	}
	public void setPmr_read_time(Long pmr_read_time) {
		this.pmr_read_time = pmr_read_time;
	}
	public Integer getPmr_is_del() {
		return pmr_is_del;
	}
	public void setPmr_is_del(Integer pmr_is_del) {
		this.pmr_is_del = pmr_is_del;
	}
	public Long getPmr_receive_userid() {
		return pmr_receive_userid;
	}
	public void setPmr_receive_userid(Long pmr_receive_userid) {
		this.pmr_receive_userid = pmr_receive_userid;
	}
	public Long getPmr_create_time() {
		return pmr_create_time;
	}
	public void setPmr_create_time(Long pmr_create_time) {
		this.pmr_create_time = pmr_create_time;
	}
	public Integer getPmr_is_read() {
		return pmr_is_read;
	}
	public void setPmr_is_read(Integer pmr_is_read) {
		this.pmr_is_read = pmr_is_read;
	}
	public String getU_nick_name() {
		return u_nick_name;
	}
	public void setU_nick_name(String u_nick_name) {
		this.u_nick_name = u_nick_name;
	}
}
