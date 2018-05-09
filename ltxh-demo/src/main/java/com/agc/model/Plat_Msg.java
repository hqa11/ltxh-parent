package com.agc.model;

import com.agc.core.annotion.Except;
import com.agc.core.utils.DataUtil;

import java.util.List;


/**
 * 站内消息
 * @author Administrator
 */
public class Plat_Msg {
	
	private Long pm_id;
	private Long pm_sender_userid;	//发送人ID
	private Long pm_send_time;	//时间
	private String pm_msg_title;		//标题
	private String pm_msg_content;		//内容
	private Integer pm_is_del;		//默认0
	private Integer pmr_is_read;	//是否已阅
	private Integer pm_msg_type;
	private Integer pm_system_msg_status;
	
    @Except
	private String username;
	@Except
	private String pm_send_timef;
	@Except
	private List<Plat_Msg_Attachment> attList;	//附件列表
	@Except
	private List<Plat_Msg_Receive> recList;	//收件人列表
	
	
	public Long getPm_id() {
		return pm_id;
	}
	public void setPm_id(Long pm_id) {
		this.pm_id = pm_id;
	}
	public Long getPm_sender_userid() {
		return pm_sender_userid;
	}
	public void setPm_sender_userid(Long pm_sender_userid) {
		this.pm_sender_userid = pm_sender_userid;
	}
	public Long getPm_send_time() {
		return pm_send_time;
	}
	public void setPm_send_time(Long pm_send_time) {
		this.pm_send_time = pm_send_time;
	}
	public String getPm_msg_title() {
		return pm_msg_title;
	}
	public void setPm_msg_title(String pm_msg_title) {
		this.pm_msg_title = pm_msg_title;
	}
	public String getPm_msg_content() {
		return pm_msg_content;
	}
	public void setPm_msg_content(String pm_msg_content) {
		this.pm_msg_content = pm_msg_content;
	}
	public Integer getPm_is_del() {
		return pm_is_del;
	}
	public void setPm_is_del(Integer pm_is_del) {
		this.pm_is_del = pm_is_del;
	}
	public Integer getPmr_is_read() {
		return pmr_is_read;
	}
	public void setPmr_is_read(Integer pmr_is_read) {
		this.pmr_is_read = pmr_is_read;
	}
	public List<Plat_Msg_Attachment> getAttList() {
		return attList;
	}
	public void setAttList(List<Plat_Msg_Attachment> attList) {
		this.attList = attList;
	}
	public List<Plat_Msg_Receive> getRecList() {
		return recList;
	}
	public void setRecList(List<Plat_Msg_Receive> recList) {
		this.recList = recList;
	}
	public String getPm_send_timef() {
		return DataUtil.timeMilionsFormat(pm_send_time, "yyyy-MM-dd HH:mm:ss");
	}
	public void setPm_send_timef(String pm_send_timef) {
		this.pm_send_timef = pm_send_timef;
	}
	public Integer getPm_msg_type() {
		return pm_msg_type;
	}
	public void setPm_msg_type(Integer pm_msg_type) {
		this.pm_msg_type = pm_msg_type;
	}
	public Integer getPm_system_msg_status() {
		return pm_system_msg_status;
	}
	public void setPm_system_msg_status(Integer pm_system_msg_status) {
		this.pm_system_msg_status = pm_system_msg_status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
