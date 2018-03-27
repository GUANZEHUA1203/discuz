package com.util;

import java.util.Base64;

public class Base64Util {

    public static String safeUrlBase64Encode(byte[] data){
        String encodeBase64 = Base64.getEncoder().encodeToString(data);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", "");
        return safeBase64Str;
    }

    public static byte[] safeUrlBase64Decode(final String safeBase64Str){
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        int mod4 = base64Str.length()%4;
        if(mod4 > 0){
            base64Str = base64Str + "====".substring(mod4);
        }
        return Base64.getDecoder().decode(base64Str);
    }

    public static void main(String[] args) {
        String base = "http://www.fddyun.com/sys/order/wxinfo?id=415306046303637504";
        String end = safeUrlBase64Encode(base.getBytes());
        String res = new String(safeUrlBase64Decode(end));
        System.out.println(base);
        System.out.println(end);
        System.out.println(res);
    }
}
