package com.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

public class VersionCondition implements RequestCondition<VersionCondition> {

	private double version;

	// 路径中版本的前缀， 这里用 /v([0-9]+(.[0-9]{1}))/的形式
	private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v([0-9]+(.[0-9]{1}))");

	public VersionCondition(double version) {
		this.version = version;
	}
	
	public VersionCondition combine(VersionCondition vc) {
		return new VersionCondition(vc.getVersion());
	}

	public int compareTo(VersionCondition vc, HttpServletRequest request) {
		// 优先匹配最新的版本号
        return (vc.getVersion() > this.version)?1:-1;
	}

	public VersionCondition getMatchingCondition(HttpServletRequest request) {
		 Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
	        if(m.find()){
	        	Double version = Double.valueOf(m.group(1));
	            if(version >= this.version) //如果请求的版本号大于配置版本号， 则满足 
	            	return this;
	        }
	        return null;
	}

	public double getVersion() {
		return version;
	}


}
