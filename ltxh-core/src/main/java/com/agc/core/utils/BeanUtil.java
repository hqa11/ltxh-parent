package com.agc.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.agc.core.annotion.Except;
import org.apache.commons.beanutils.BeanUtils;


public class BeanUtil {

	@SuppressWarnings("unchecked")
	public static Map<String,Object> beanToMap(Object obj){
		if(obj==null)return null;
		if(obj instanceof Map){
			return (Map<String, Object>) obj;
		}
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) { 
				Except exc = field.getAnnotation(Except.class);
				if(exc != null)continue;
				// 得到property对应的getter方法  
				String fname = field.getName();
				String name = "get" + fname.substring(0,1).toUpperCase() + fname.substring(1);
				Method getter = obj.getClass().getDeclaredMethod(name);
				Object value = getter.invoke(obj);  
				map.put(fname, value);  
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <E> E mapToBean(Map<String,Object> map,Class<E> clazz){
		E obj = null;
		try {
			obj = clazz.newInstance();
			BeanUtils.populate(obj, map);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return  obj;
	}


}
