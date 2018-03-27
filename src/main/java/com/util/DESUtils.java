package com.util;
import java.security.Key;  
import java.security.SecureRandom;  
   
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
   
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  
   
public class DESUtils {  
  
    private static Key key;  
    private static String KEY_STR = "iYIP4P+NspFrzA0h5ii7Qw==";// 密钥  
    private static String CHARSETNAME = "UTF-8";// 编码  
    private static String ALGORITHM = "DES";// 加密类型  
   
    static {  
        try {  
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);  
            generator.init(new SecureRandom(KEY_STR.getBytes()));  
            key = generator.generateKey();  
            generator = null;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
   
    /** 
     * 对str进行DES加密 
     *  
     * @param str 
     * @return 
     */  
    public static String getEncryptString(String str) {  
        BASE64Encoder base64encoder = new BASE64Encoder();  
        try {  
            byte[] bytes = str.getBytes(CHARSETNAME);  
            Cipher cipher = Cipher.getInstance(ALGORITHM);  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            byte[] doFinal = cipher.doFinal(bytes);  
            return base64encoder.encode(doFinal);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
   
    /** 
     * 对str进行DES解密 
     *  
     * @param str 
     * @return 
     */  
    public static String getDecryptString(String str) {  
        BASE64Decoder base64decoder = new BASE64Decoder();  
        try {  
            byte[] bytes = base64decoder.decodeBuffer(str);  
            Cipher cipher = Cipher.getInstance(ALGORITHM);  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            byte[] doFinal = cipher.doFinal(bytes);  
            return new String(doFinal, CHARSETNAME);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    public static void main(String[] args) {
    	String os = System.getProperty("os.name");  
    	System.err.println(os);
    	if(os.toLowerCase().startsWith("win")){  
    		  System.out.println(os + " can't gunzip");  
    		}  
    	String encryptString = getEncryptString("com.mysql.jdbc.Driver");
    	String getDecryptString = getDecryptString("NNcv5saI8e+EQC6o9RTcVjlrrz9C4gcYpwfuBG4ktya8MYLXqD9E3HNgrTGY2LbeyzWobUok3R2c9iV18hvQp8lIykOUOwCqW68Dn3XuSDM6NUf+DQ3Swh1B3hggtajCsF7onp3K/Od7vyRQWdnrtg==");

    	
    	System.out.println(encryptString);
    	System.out.println(getDecryptString);
    }
}  