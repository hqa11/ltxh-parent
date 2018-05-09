package com.agc.common;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

import redis.clients.jedis.JedisPool;

public class JedisPoolFactory{
	private Config poolConfig;
	private String host;
	private int port;   
	private int timeout;
	private String password;
	private Integer needPassword;
	private JedisPool pool;
	public JedisPoolFactory(Config poolConfig, String host, int port,
			int timeout, String password, Integer needPassword) {
		super();
		this.setPoolConfig(poolConfig);
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.password = password;
		this.needPassword = needPassword;
		if(needPassword == 1){
			setPool(new JedisPool(poolConfig, host, port, timeout, password));
		}else{
			setPool(new JedisPool(poolConfig, host, port, timeout));
		}
	}

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getNeedPassword() {
		return needPassword;
	}
	public void setNeedPassword(Integer needPassword) {
		this.needPassword = needPassword;
	}
	public JedisPool getPool() {
		return pool;
	}
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	public Config getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(Config poolConfig) {
		this.poolConfig = poolConfig;
	}
}
