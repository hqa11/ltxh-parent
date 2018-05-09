package com.agc.user.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.agc.core.common.Page;
import com.agc.core.common.Pageable;
import com.agc.core.utils.BeanUtil;
import com.agc.core.utils.DataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;


public abstract class BaseDao<E,PK extends Serializable>{

	private Class<E> clazz;

	@Resource
	protected JdbcTemplate jtp;


	/**
	 * 根据id查询对象
	 * @param id
	 * @param tbName
	 * @param pk 表的主键(小写)
	 * @return
	 */
	public Map<String, Object> getById(Integer id,String tbName,String pk){
		try {
			String sql = "SELECT * FROM "+tbName+" WHERE "+pk+"= ?";
			Map<String, Object> map = jtp.queryForMap(sql,id);
			return map;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据id查询对象
	 * @param id
	 * @param tbName
	 * @param pk 表的主键(小写)
	 * @return
	 */
	public <H> H getById(Integer id,Class<H> clazz,String pk){
		try {
		String sql = "SELECT * FROM "+clazz.getSimpleName().toLowerCase()+" WHERE "+pk+"= ?";
		Map<String, Object> map = jtp.queryForMap(sql,id);
		H h = BeanUtil.mapToBean(map, clazz);
		return h;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 *  更新某表
	 * @param params
	 * @param tbName
	 * @param pk
	 * @return
	 */
	public int updateById(Map<String,Object> params,String tbName,String pk){
		try {
			if(!params.containsKey(pk))throw new Exception("no PK in map");
			StringBuffer sql = new StringBuffer("UPDATE "+tbName+" SET ");
			String sql_str = "";
			Object[] os = null;
			if(params !=null && !params.isEmpty()){
				os = new Object[params.size()- 1];		
				int index = 0;
				for (String key : params.keySet()) {
					if(key.equals(pk))continue;
					sql.append(key + " = ?,");
					os[index] = (params.get(key) instanceof Date) ? new java.sql.Date(((Date)params.get(key)).getTime()): params.get(key);
					index ++;
				}
				sql_str = sql.substring(0, sql.length() - 1) + " WHERE "+pk+"="+params.get(pk);
			}
			int ret = jtp.update(sql_str,os);
			return ret;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据id更新(此方法会更新空值)
	 * @param o
	 * @param pk 对象的主键(字段名称)
	 * @return
	 */
	public int updateByIdWithNull(Object o,String pk){
		
		Map<String, Object> beanMap = BeanUtil.beanToMap(o);
		return updateById(beanMap,o.getClass(),pk.toLowerCase());
	}
	
	/**
	 * 根据id更新(此方法不会更新空值)
	 * @param o
	 * @param pk 对象的主键(字段名称)
	 * @return
	 */
	public int updateById(Object o,String pk){
		Map<String, Object> beanMap = BeanUtil.beanToMap(o);
		DataUtil.removeNullKey(beanMap);
		return updateById(beanMap,o.getClass(),pk.toLowerCase());
	}
	
	/**
	 *  更新某表
	 * @param params
	 * @param tbName
	 * @param pk 表的主键(小写)
	 * @return
	 */
	public int updateById(Map<String,Object> params,Class<?> clazz,String pk){
		
		return updateById(params,clazz.getSimpleName().toLowerCase(),pk);
	}


	
	/**
	 * 新增某表返回主键
	 * @param params
	 * @param tbName
	 * @param pk
	 * @return
	 */
	public int insertBean(final Map<String,Object> params,Class<?> clazz){
		return insertBean(params,clazz.getSimpleName().toLowerCase());    
	}

	
	/**
	 * 新增数据  
	 * @param params
	 * @param tbName
	 * @return
	 */
	public int insertBean(Object o){
		Map<String, Object> beanMap = BeanUtil.beanToMap(o);
		return insertBean(beanMap,o.getClass());
	}
	
	
	/**
	 * 新增某表返回主键
	 * @param params
	 * @param tbName
	 * @param pk 表的主键(小写)
	 * @return
	 */
	public int insertBean(final Map<String,Object> params,String tbName){
		StringBuffer sql = new StringBuffer("INSERT INTO "+tbName+" ( ");
		String sql_str = "";
		if(params !=null && !params.isEmpty()){
			for (String key : params.keySet()) {
				sql.append(key + ",");
			}
			sql_str = sql.substring(0, sql.length() - 1) + " ) " +" VALUES (";
		}
		sql = new StringBuffer(sql_str);
		for (String key : params.keySet()) {
			sql.append("?,");
		}
		sql_str = sql.substring(0, sql.length() - 1) + " ) ";
		final String final_sql = sql_str;
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		jtp.update( new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement ps = conn.prepareStatement(final_sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				for (String key : params.keySet()) {
					ps.setObject(index, (params.get(key) instanceof Date) ? new java.sql.Date(((Date)params.get(key)).getTime()): params.get(key));
					index ++;
				}
				return ps;
			}
		},
		keyHolder);
		return keyHolder.getKey().intValue();   
	}
	
	/**
	 * 分页列表
	 * @param sql
	 * @param pb
	 * @param clazz
	 * @param orderby
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <H> List<H> getPageList(String sql, Pageable pb, Class<H> clazz){
		@SuppressWarnings("unchecked")
		List<Object> params = pb.getQueryVal();
		StringBuffer newSql = new StringBuffer(sql);
		newSql.insert(0,"SELECT * FROM ( ").append(" ) def_tb_name");
		newSql.append(" LIMIT " + pb.getStart() + " , " + pb.getEnd());
		List<Map<String,Object>> result = jtp.queryForList(newSql.toString(),params.toArray());
		List<H> ret = new ArrayList();
		for (Map m : result) {
			ret.add((H) BeanUtil.mapToBean(m, clazz));
		}
		return ret;
	}
	
	/**
	 * 分页总数目
	 * @param sql
	 * @param pb
	 * @param clazz
	 * @param orderby
	 * @return
	 */
	public Long getPageCount(String sql,Pageable pb){
		@SuppressWarnings("unchecked")
		List<Object> params = pb.getQueryVal();
		StringBuffer newSql = new StringBuffer(sql);
		newSql.insert(0,"SELECT COUNT(1) FROM ( ").append(" ) def_tb_name");
		Long count = jtp.queryForObject(newSql.toString(),Long.class,params.toArray());
		return count;
	}
	
	/**
	 * 获取分页
	 * @param sql
	 * @param pb
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <H> Page<H> getPage(String sql,Pageable pb,Class<H> clazz){
		List<H> pageList = getPageList(sql, pb, clazz);
		Long pageCount = getPageCount(sql, pb);
		return new Page(pageList, pageCount, pb);
	}
	
	/**
	 * 获取实体
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public <H> H getMap(String sql,Class<H> clazz,Object... args){
		try {
			Map<String, Object> map = jtp.queryForMap(sql,args);
			H h = BeanUtil.mapToBean(map, clazz);
			return h;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 获取list
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public <H> List<H> getList(String sql,Class<H> clazz,Object... args){
		try {
			List<Map<String, Object>> list = jtp.queryForList(sql,args);
			List<H> ret = new ArrayList();
			for (Map m : list) {
				ret.add((H) BeanUtil.mapToBean(m, clazz));
			}
			return ret;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
