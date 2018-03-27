package com.util;
import java.util.Base64;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;  
   
/** 
 * 继承PropertyPlaceholderConfigurer定义支持密文版属性的属性配置器 
 *  
 *  
 */  
public class EncryptPropertyPlaceholderConfigurer extends  
        PropertyPlaceholderConfigurer {  
    private String[] encryptPropNames = {"user","password","url"};//"userName",   
   
    @Override  
    protected String convertProperty(String propertyName, String propertyValue) {  
        if (isEncryptProp(propertyName)) {  
            String decryptValue = DESUtils.getDecryptString(propertyValue);  
            System.out.println(decryptValue);
            return decryptValue;  
        } else {  
            return propertyValue;  
        }  
    }  
   
    /** 
     * 判断是否是加密的属性 
     *  
     * @param propertyName 
     * @return 
     */  
    private boolean isEncryptProp(String propertyName) {  
        for (String encryptpropertyName : encryptPropNames) {  
            if (encryptpropertyName.equals(propertyName))  
                return true;  
        }  
        return false;  
    }  

 
}  