package com.core;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	
	@Override
	protected RequestCondition<VersionCondition> getCustomTypeCondition(Class<?> handlerType) {
		Version findAnnotation = AnnotationUtils.findAnnotation(handlerType, Version.class);
		return createVersionCondition(findAnnotation);
	}
	@Override
	protected RequestCondition<VersionCondition> getCustomMethodCondition(Method method) {
		Version findAnnotation = AnnotationUtils.findAnnotation(method, Version.class);
		return createVersionCondition(findAnnotation);
	}
	
	public VersionCondition createVersionCondition(Version v){
		return v==null?null:new VersionCondition(v.value());
	}
}
