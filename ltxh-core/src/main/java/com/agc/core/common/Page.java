/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.agc.core.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 * 
 * @version 3.0
 */
public class Page<T>  implements Serializable{

	/** 内容 */
	private final List<T> content = new ArrayList<T>();

	/** 总记录数 */
	private final long total;

	/** 分页信息 */
	private final Pageable pageable;

	/**
	 * 初始化一个新创建的Page对象
	 */
	public Page() {
		this.total = 0L;
		this.pageable = new Pageable();
	}

	/**
	 * @param content
	 *            内容
	 * @param total
	 *            总记录数
	 * @param pageable
	 *            分页信息
	 * @param autoGenPageBar 是否自动生成页码条
	 */
	public Page(List<T> content, long total, Pageable pageable,boolean autoGenPageBar) {
		this.content.addAll(content);
		this.total = total;
		this.pageable = pageable;
		if(autoGenPageBar)generatePageBar();
	}
	
	
	public Page(List<T> content, long total, Pageable pageable) {
		this.content.addAll(content);
		this.total = total;
		this.pageable = pageable;
		generatePageBar();
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageable.getPageNumber();
	}

	/**
	 * 获取每页记录�?
	 * 
	 * @return 每页记录?
	 */
	public int getPageSize() {
		return pageable.getPageSize();
	}



	/**
	 * 获取总页�?
	 * 
	 * @return 总页�?
	 */
	public int getTotalPages() {
		int t=(int) Math.ceil((double) getTotal() / (double) getPageSize());
		return t==0?1:t;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 获取分页信息
	 * 
	 * @return 分页信息
	 */
	public Pageable getPageable() {
		return pageable;
	}
	/**
	 * 生成pageBar
	 */
	private void generatePageBar() {
		Pageable pageable=this.getPageable();
		//总页数
		Integer totalPages=this.getTotalPages();
		if(totalPages==0){
			Integer pageBar[]=new Integer[0];
			pageable.setPageBar(pageBar);
			return;
		}
		//页码数目默认为5
		String pageBarNum="10";
		Integer pageBar[]=new Integer[Integer.parseInt(pageBarNum)];
		if(pageBar.length>totalPages){
			pageBar=new Integer[totalPages];
			for(int i=0;i<pageBar.length;i++){			
				pageBar[i]=i+1;
			}
		}else{
			for(int i=0;i<pageBar.length;i++){
				if(this.getPageNumber()+pageBar.length-1<totalPages || this.getPageNumber()+pageBar.length-1==totalPages ){
					pageBar[i]=this.getPageNumber()+i;
				}else{
					pageBar[i]=totalPages-pageBar.length+1+i;
				}
			}    
		}
		pageable.setPageBar(pageBar);			
	}

}