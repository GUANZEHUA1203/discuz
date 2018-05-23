package com.common;




import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @author HUANGP
 * @date 2018年1月2日
 * @des session 封装
 */

public class BaseRedisCache {

	private static Logger logger = LoggerFactory.getLogger(BaseRedisCache.class);
	
	private static final int ATICLE_TIME_OUT=1000*60*60*24*7;//文章缓存时间
	
	@Autowired
	private RedisManager redisManager;
	
	
	
	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "redis_session:";
	
	public void update(String keyId,Object objValue) throws Exception{
		this.save(keyId,objValue);
	}
	
	/**
	 * save session
	 * @param session
	 */
	public void save(String keyId,Object objValue) throws Exception{
		save(keyId, objValue, ATICLE_TIME_OUT);
	}
	public void save(String keyId,Object objValue,int timeout){
		if(keyId == null || objValue == null){
			logger.error("keyId or objValue id is null");
			return;
		}
		byte[] key = getByteKey(keyId);
		byte[] value = SerializeUtils.serialize(objValue);
		this.redisManager.set(key, value,timeout);
	}
	
	public Object getObject(Serializable keyId) throws Exception{
		if(keyId == null){
			logger.error("keyId  is null");
			return null;
		}
		
		byte[] key = getByteKey(keyId);
		byte[] value =redisManager.get(key);
		return (Object) SerializeUtils.deserialize(value);
	}
	

	public Object getSession(Serializable keyId) throws Exception{
		if(keyId == null){
			logger.error("keyId  is null");
			return null;
		}
		
		byte[] key = getByteKey(keyId);
		byte[] value =redisManager.get(key);
		return (Object) SerializeUtils.deserialize(value);
	}

	public HttpSession getSession() throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String sessionId = null;
		if(request != null){
			sessionId = request.getParameter("token");
			if(StringUtils.isEmpty(sessionId)){
				return null;
			}
		}
		return (HttpSession) getSession(sessionId);
	}
	
	
	public HttpServletRequest getHttpServletReauest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public String getToken(){
		return getHttpServletReauest().getParameter("token");
	}

	/**
	 * @author HUANGP
	 * @des delete Session
	 * @date 2018年1月2日
	 */
	public void delete(String keyId) {
		if(keyId == null){
			logger.error("sessionId id is null");
			return;
		}
		redisManager.del(this.getByteKey(keyId));
	}

	/**
	 * @author HUANGP
	 * @des get All active session
	 * @date 2018年1月2日
	 * @return
	 */
	public Collection<Object> getActiveSessions() {
		Set<Object> sessions = new HashSet<Object>();
		Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
		if(keys != null && keys.size()>0){
			for(byte[] key:keys){
				Object s = (Object)SerializeUtils.deserialize(redisManager.get(key));
				sessions.add(s);
			}
		}
		
		return sessions;
	}
	
	/**
	 * 获得byte[]型的key
	 * @return
	 */
	private byte[] getByteKey(Serializable keyId){
		String preKey = this.keyPrefix + keyId;
		return preKey.getBytes();
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	protected void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
	
	
	/**
	 * @author HUANGP
	 * @des HTTP参数获取
	 * @date 2018年1月2日
	 * @param request
	 * @return
	 */
	protected Map<String,Object> initAllHttpParams(HttpServletRequest request) {
		Enumeration KeyVal=request.getParameterNames();
		Map<String,Object> params=new TreeMap<String,Object>();
		while(KeyVal.hasMoreElements()){
			String key=String.valueOf(KeyVal.nextElement());
			String value=request.getParameter(key);
			if(!key.equals("sign"))
			params.put(key,value);
		}
		return params;
	}
	
}
