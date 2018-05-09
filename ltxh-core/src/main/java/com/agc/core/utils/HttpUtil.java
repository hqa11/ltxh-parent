package com.agc.core.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * http工具
 * @author Hqa
 * @date 2017-2-13 
 * @email 18806278373@sina.cn
 * @to TODO
 */
public class HttpUtil {

	public static Object httpPost(String add,String json_param) throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();
		URI url = null;
		Map<String,Object> map=JsonUtils.toObject(json_param, Map.class);
		add=add+"?"+"relPath="+map.get("relPath")+"&path="+map.get("path")+"&tar="+map.get("tar");
		try {
			
			url=new URI(add);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		 HttpPost post = new HttpPost(url);
		
	     // 执行请求
	     HttpResponse response = client.execute(post);

	     // 打印执行结果
	     return EntityUtils.toString(response.getEntity(), "utf-8");
				
	}
	
	
	public static Object postByEntity(String add,String json_param) throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();
		URI url = null;
		try {
			
			url=new URI(add);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		 HttpPost post = new HttpPost(url);
		
	     // 设置请求的参数
	     StringEntity params = new StringEntity(json_param,Consts.UTF_8);
	     post.setEntity(params);

	     // 执行请求
	     HttpResponse response = client.execute(post);

	     // 打印执行结果
	     return EntityUtils.toString(response.getEntity(), "utf-8");
				
	}

}
