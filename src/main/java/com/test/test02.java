/**
 * @autho zehua
 *  下午3:47:22
 */
package com.test;

import java.io.File;

import com.util.DESUtils;

/**下午3:47:22
 * @author 2017*****下午3:47:22
 *
 */
public class test02 {
	public static void main(String[] args) {
		File dir=new File("D:\\img\\jarpackage\\C_");
		if(dir.isDirectory()){
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				String name=file.getName();
				System.out.println(name);
				String encryptString = DESUtils.getEncryptString(name);
				file.renameTo(new File("D:\\img\\jarpackage\\C_"+encryptString+".jpg"));
			}
		}
		
	
	}

}
