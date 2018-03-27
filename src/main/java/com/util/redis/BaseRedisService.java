package com.util.redis;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author HUANGP
 * @date 2017年12月27日
 * @des redis基础类
 */
@Service
public  class BaseRedisService extends RedisGeneratorService<String, String>  {
	public static final long TIME_EXPRIRE=1000*60*3;
	
	 /**
	 * @author HUANGP
	 * @des 保存
	 * @date 2017年12月27日
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key,final String value) { 
        boolean result = txRedisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] keyId  = serializer.serialize(key);  
                byte[] valyeId = serializer.serialize(value);
                connection.set(keyId, valyeId);
                return true;
			}  
        });
        return result;  
	 }
	 
	 /**
	 * @author HUANGP
	 * @des 不重复保存
	 * @date 2017年12月27日
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setNX(final String key,final String value) { 
	        boolean result = txRedisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	                RedisSerializer<String> serializer = getRedisSerializer();  
	                byte[] keyId  = serializer.serialize(key);  
	                byte[] valyeId = serializer.serialize(value);
	                connection.setNX(keyId, valyeId);
	                return true;
				}  
	        });
	        return result;  
	}  
	      
	    
	    /**
	     * @author HUANGP
	     * @des 批量保存（）
	     * @date 2017年12月27日
	     * @param list
	     * @return
	     */
	    public boolean set(final Map<String,String> params) {  
	        boolean result = txRedisTemplate.execute(new RedisCallback<Boolean>() {  
	            public Boolean doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                RedisSerializer<String> serializer = getRedisSerializer();  
	                for (String user : params.keySet()) {  
	                    byte[] key  = serializer.serialize(user);  
	                    byte[] name = serializer.serialize(params.get(user));  
	                    connection.set(key,name);  
	                }  
	                return true;  
	            }  
	        }, false, true);  
	        return result;  
	    } 
	    
	    /**
	     * @author HUANGP
	     * @des 批量保存（重复不会覆盖）
	     * @date 2017年12月27日
	     * @param list
	     * @return
	     */
	    public boolean setNX(final Map<String,String> params) {  
	    	  boolean result = txRedisTemplate.execute(new RedisCallback<Boolean>() {  
		            public Boolean doInRedis(RedisConnection connection)  
		                    throws DataAccessException {  
		                RedisSerializer<String> serializer = getRedisSerializer();  
		                for (String user : params.keySet()) {  
		                    byte[] key  = serializer.serialize(user);  
		                    byte[] name = serializer.serialize(params.get(user));  
		                    connection.setNX(key,name);  
		                }  
		                return true;  
		            }  
		        }, false, true);  
		        return result; 
	    } 
	      
	   
	    /**
	     * @author HUANGP
	     * @des 单个删除
	     * @date 2017年12月27日
	     * @param key
	     */
	    public void delete(String key) {  
	        List<String> list = new ArrayList<String>();  
	        list.add(key);  
	        delete(list);  
	    }  
	  
	  
	    /**
	     * @author HUANGP
	     * @des 批量删除
	     * @date 2017年12月27日
	     * @param keys
	     */
	    public void delete(List<String> keys) {  
	    	txRedisTemplate.delete(keys);  
	    }  
	  
	    /**
	     * @author HUANGP
	     * @des redis参数获取
	     * @date 2017年12月27日
	     * @param keyId
	     * @return
	     */
	    public String get(final String keyId) {  
	        String result = txRedisTemplate.execute(new RedisCallback<String>() {  
	            public String doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                RedisSerializer<String> serializer = getRedisSerializer();  
	                byte[] key = serializer.serialize(keyId);  
	                byte[] value = connection.get(key);  
	                if (value == null) {  
	                    return null;  
	                }  
	                String name = serializer.deserialize(value);  
	                return name;
	            }  
	        });  
	        return result;  
	    } 
	    
	    
	    
	    /**
	     * @author HUANGP
	     * @des  设置有效时间
	     * @date 2017年12月27日
	     * @param keyId 键
	     * @param expire 时间（秒）
	     * @return
	     */
	    public boolean expire(final String keyId,final long expire) {  
	    	boolean result = txRedisTemplate.execute(new RedisCallback<Boolean>() {  
	            public Boolean doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                RedisSerializer<String> serializer = getRedisSerializer();  
	                byte[] key = serializer.serialize(keyId);  
	                Boolean isSucc=connection.expire(key,expire);
	                return isSucc;
	            }  
	        });  
	        return result;  
	    }
}