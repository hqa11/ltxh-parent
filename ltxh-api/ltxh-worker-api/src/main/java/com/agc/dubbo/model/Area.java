package com.agc.dubbo.model;


import java.io.Serializable;

public class Area implements Serializable {
    private Integer area_id;

    private String area_name;

    private Integer area_parentId;

    private Integer area_sort;

    private Integer area_deep;

    private String area_region;

	public Integer getArea_id() {
		return area_id;
	}

	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public Integer getArea_parentId() {
		return area_parentId;
	}

	public void setArea_parentId(Integer area_parentId) {
		this.area_parentId = area_parentId;
	}

	public Integer getArea_sort() {
		return area_sort;
	}

	public void setArea_sort(Integer area_sort) {
		this.area_sort = area_sort;
	}

	public Integer getArea_deep() {
		return area_deep;
	}

	public void setArea_deep(Integer area_deep) {
		this.area_deep = area_deep;
	}

	public String getArea_region() {
		return area_region;
	}

	public void setArea_region(String area_region) {
		this.area_region = area_region;
	}

}