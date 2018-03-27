package com.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void setCookie(HttpServletResponse response,String name,String value){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String name){
        if(request.getCookies() != null)for(Cookie c : request.getCookies()){
            if(name.equals(c.getName())){
                return c.getValue();
            }
        }
        return null;
    }
}
