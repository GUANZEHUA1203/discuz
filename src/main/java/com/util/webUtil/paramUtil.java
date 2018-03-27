package com.util.webUtil;



import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
//获取请求参数
@Controller
public class paramUtil {
    public static Map<String, Object> getRequestParameter(HttpServletRequest request, HttpServletResponse response) {
    	java.util.Map<String, Object> paramMap=new HashMap<String, Object>();
    	Enumeration em = request.getParameterNames();
    	 while (em.hasMoreElements()) {
    	    String name = (String) em.nextElement();
    	    String value = request.getParameter(name);
    	    paramMap.put(name, value);
    	}
		return  paramMap;
    }
    
}