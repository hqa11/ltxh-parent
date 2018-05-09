package com.agc.model;


import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;

/**
 * 意见反馈表
 * @author Administrator
 */
public class Feed_Back {
	
	private Long fd_id;
	private String fd_name;	//姓名
	private String fd_phone;	//手机号
	private String fd_content;	//内容
	private Long fd_time;		//时间
	private Integer fd_state;	//默认0.待处理，1代表已查看 2代表已反馈
	private String fd_backup;	//处理备注
	private Integer fd_is_del;
	@Except
	private String fd_timef;
	public Long getFd_id() {
		return fd_id;
	}
	public void setFd_id(Long fd_id) {
		this.fd_id = fd_id;
	}
	public String getFd_name() {
		return fd_name;
	}
	public void setFd_name(String fd_name) {
		this.fd_name = fd_name;
	}
	public String getFd_phone() {
		return fd_phone;
	}
	public void setFd_phone(String fd_phone) {
		this.fd_phone = fd_phone;
	}
	public String getFd_content() {
		return fd_content;
	}
	public void setFd_content(String fd_content) {
		this.fd_content = fd_content;
	}
	public Long getFd_time() {
		return fd_time;
	}
	public void setFd_time(Long fd_time) {
		this.fd_time = fd_time;
	}
	public Integer getFd_state() {
		return fd_state;
	}
	public void setFd_state(Integer fd_state) {
		this.fd_state = fd_state;
	}
	public String getFd_backup() {
		return fd_backup;
	}
	public void setFd_backup(String fd_backup) {
		this.fd_backup = fd_backup;
	}
	public Integer getFd_is_del() {
		return fd_is_del;
	}
	public void setFd_is_del(Integer fd_is_del) {
		this.fd_is_del = fd_is_del;
	}
	public String getFd_timef() {
		return DataUtil.timeMilionsFormat(fd_time, "yyyy-MM-dd HH:mm:ss");
	}
	public void setFd_timef(String fd_timef) {
		this.fd_timef = fd_timef;
	}
}
