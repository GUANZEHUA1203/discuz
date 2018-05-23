package com.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Component
@Aspect
@Order(99)
public class WebResultRedis {
	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("public * *(..)")
	public void aspect() {}
	@Around("aspect()")
	public Object getRedisValue(HttpServletRequest request,HttpServletResponse  response) {
		System.out.println("before");
//		 String requestParameter = paramUtil.getRequestParameter(request, response);
//		int hashCode = requestParameter.hashCode();
//		if(baseRedisService.get(StringUtil.getString(hashCode))!=null) {
//			return baseRedisService.get(StringUtil.getString(hashCode));
//		}
//		return hashCode;
		return response;
	}
	 @AfterReturning(pointcut="execution(* com.controller..*(..)) && @ResponseBody(* com.controller..*(..))", returning="resultObj")  
	public Object setRedisValue(HttpServletRequest request,HttpServletResponse  response,Object resultObj) {
		System.out.println("after");
//		String requestParameter = paramUtil.getRequestParameter(request, response);
//		int hashCode = requestParameter.hashCode();
//		baseRedisService.set(StringUtil.getString(hashCode), StringUtil.getString(resultObj));
//		return hashCode;
		return resultObj;
	}
	
	

}
