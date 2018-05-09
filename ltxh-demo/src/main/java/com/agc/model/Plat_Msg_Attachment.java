package com.agc.model;


/**
 * 站内消息阅读表
 * @author Administrator
 */
public class Plat_Msg_Attachment {
	
	private Long pma_id;
	private Long pma_pm_id;		//消息ID
	private String pma_attach_name;	//资源文件名
	private String pma_attach_path;	//资源路径
	private Long pma_create_time;		//时间
	private Integer pma_order;		//默认0
	private Integer pma_is_del;		//默认0
	
	
	
	public Long getPma_id() {
		return pma_id;
	}
	public void setPma_id(Long pma_id) {
		this.pma_id = pma_id;
	}
	public Long getPma_pm_id() {
		return pma_pm_id;
	}
	public void setPma_pm_id(Long pma_pm_id) {
		this.pma_pm_id = pma_pm_id;
	}
	public String getPma_attach_path() {
		return pma_attach_path;
	}
	public void setPma_attach_path(String pma_attach_path) {
		this.pma_attach_path = pma_attach_path;
	}
	public Long getPma_create_time() {
		return pma_create_time;
	}
	public void setPma_create_time(Long pma_create_time) {
		this.pma_create_time = pma_create_time;
	}
	public Integer getPma_order() {
		return pma_order;
	}
	public void setPma_order(Integer pma_order) {
		this.pma_order = pma_order;
	}
	public Integer getPma_is_del() {
		return pma_is_del;
	}
	public void setPma_is_del(Integer pma_is_del) {
		this.pma_is_del = pma_is_del;
	}
	public String getPma_attach_name() {
		return pma_attach_name;
	}
	public void setPma_attach_name(String pma_attach_name) {
		this.pma_attach_name = pma_attach_name;
	}
}
