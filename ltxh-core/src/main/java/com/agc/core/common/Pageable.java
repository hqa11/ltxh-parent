package com.agc.core.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页信息
 * 
 * @version 3.0
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录�?*/
	private static final int DEFAULT_PAGE_SIZE = 20;

	/** �?��每页记录�?*/
	private static final int MAX_PAGE_SIZE = 1000;

	/** 页码 */
	private int pageNumber = DEFAULT_PAGE_NUMBER;

	/** 每页记录�?*/
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private Object o;
	
	private Map<String,Object> qmp;
	
	private List<Object> queryVal = new ArrayList<Object>();
	
	public List<Object> getQueryVal() {
		return queryVal;
	}
	
	public void addQueryVal(Object o){
		queryVal.add(o);
	}
	
	/** limit ?,*/
	private int start;
	
	/** limit ,?*/
	private int end;
	/**定义一个pageBar*/
    private Integer[] pageBar;

	/**
	 * 初始化一个新创建的Pageable对象
	 */
	public Pageable() {
	}

	/**
	 * 初始化一个新创建的Pageable对象
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录�?
	 */
	public Pageable(Integer pageNumber, Integer pageSize) {
		if (pageNumber != null && pageNumber >= 1) {
			this.pageNumber = pageNumber;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置页码
	 * 
	 * @param pageNumber
	 *            页码
	 */
	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * 获取每页记录�?
	 * 
	 * @return 每页记录�?
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录�?
	 * 
	 * @param pageSize
	 *            每页记录�?
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

/**
 * Hqa添加的查询参数
 * @return
 */
	public int getStart() {
		this.start=this.pageSize*(this.pageNumber-1);
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		this.end=pageSize;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Integer[] getPageBar() {
		return pageBar;
	}

	public void setPageBar(Integer[] pageBar) {
		this.pageBar = pageBar;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}


	public Map<String,Object> getQmp() {
		return qmp;
	}

	public void setQmp(Map<String,Object> qmp) {
		this.qmp = qmp;
	}



	
}