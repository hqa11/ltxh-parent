package com.agc.core.common;

import com.agc.core.utils.DataUtil;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Base {

	/**
	 * 判断空
	 * @param o
	 * @return
	 */
	protected static boolean isEmpty(Object o) {
		if (o == null) return true;
		if ((o instanceof String)) {
			if (((String) o).trim().length() == 0) {
				return true;
			}
		} else if ((o instanceof Collection)) {
			if (((Collection<?>) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (((Object[]) o).length == 0) {
				return true;
			}
		} else if ((o instanceof Map)) {
			if (((Map<?,?>) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 判断非空
	 * @param o
	 * @return
	 */
	protected static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
	
	/**
	 * 获取一个hashMap
	 * @return
	 */
	protected Map<String,Object> getMap(){
		
		return new HashMap<String,Object>();
	}
	
	/**
	 * 时间转化
	 * @param time
	 * @return
	 */
	protected Long getTimeMillions(String time){
		
		try {
			return DataUtil.stringFormatToTimeMilions("yyyy-MM-dd HH:mm:ss", time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 时间转化
	 * @param time
	 * @return
	 */
	protected Long getTimeMillionsYmd(String time){
		
		try {
			return DataUtil.stringFormatToTimeMilions("yyyy-MM-dd", time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	protected Long now(){
		
		return System.currentTimeMillis();
	}
	
}
