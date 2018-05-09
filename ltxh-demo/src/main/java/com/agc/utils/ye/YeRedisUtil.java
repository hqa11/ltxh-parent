package com.agc.utils.ye;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.agc.common.JedisPoolFactory;

import redis.clients.jedis.Jedis;

/**
 * redis相关工具类
 * @author YE
 * 2016-7-11 17:09:35
 */
public class YeRedisUtil {

	private  JedisPoolFactory  factory;
	public void setFactory(JedisPoolFactory factory) {
		this.factory = factory;
	}

	public  String getFieldValue(String key, String fieldName) {
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			return jedis.hget(key, fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
	}

	/**
	 * 缓存简单key value
	 * 
	 * @param key
	 * @param valueg
	 */
	public  void setValue(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
	}

	/**
	 * 设置key的过期时间
	 * 
	 * @param key
	 * @param valueg
	 */
	public  void setExpire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
	}



	/**
	 * 根据key取得简单value
	 * 
	 * @param key
	 */
	public  String getValue(String key) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			result = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
		return result;
	}

	/**
	 * 缓存一个hashmap
	 * 
	 * @param key
	 * @param map
	 */
	public  void setHashMap(String key, HashMap<String, String> map) {
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			jedis.hmset(key, map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
	}

	/**
	 * 根据key,取得一个hashmap
	 * 
	 * @param key
	 * @param args
	 * @return
	 */
	public  Map<String, String> getHashMap(String key) {
		Map<String, String> result = new HashMap<String, String>();
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			result = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
		return result;
	}

	/**
	 * 缓存1个 sort set
	 */

	/**
	 * 根据key，取得一个 sort set
	 */

	/**
	 * 移除一个缓存
	 * 
	 * @param key
	 */
	public  void removeItem(String key) {
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
	}

	/**
	 * 批量删除 
	 * @param pre_str 必须是*号结尾
	 */
	public  void batchRemove(String pre_str) {
		Jedis jedis = null;
		try {
			jedis = factory.getPool().getResource();
			jedis.connect();
			Set<String> set = jedis.keys(pre_str);  
			Iterator<String> it = set.iterator();  
			while(it.hasNext()){  
				String keyStr = it.next();  
				jedis.del(keyStr);  
			}  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				factory.getPool().returnResource(jedis);
			}
		}
	}


}
