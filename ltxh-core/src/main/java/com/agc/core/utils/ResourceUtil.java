package com.agc.core.utils;

import java.util.ResourceBundle;

/**
 * @category  资源配置文件对象
 * @author JFCMS
 * @date  2016-01-06
 *
 */
public class ResourceUtil {
  
	private static String RESOURCE_PATH = "backlocation";
	private static ResourceBundle bundle = ResourceBundle
			.getBundle(RESOURCE_PATH);
    
	public static String getString(String key){
        return bundle.getString(key);
	}

	/**
	 * 指定资源文件
	 * 
	 * @param filePath
	 */
	public static void setConfigPath(String filePath){
		bundle = ResourceBundle.getBundle(filePath);
	}
}
