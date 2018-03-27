package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.util.StringUtil;
import com.util.redis.BaseRedisService;
import com.util.webUtil.paramUtil;

public class BasicContrller {
	@Autowired
	private BaseRedisService baseRedisService;
	
	/*public Object getRedisValue(HttpServletRequest request,HttpServletResponse  response) {
		 String requestParameter = paramUtil.getRequestParameter(request);
		int hashCode = requestParameter.hashCode();
		if(baseRedisService.get(StringUtil.getString(hashCode))!=null) {
			return baseRedisService.get(StringUtil.getString(hashCode));
		}
		return hashCode;
	}*/

}
