/**
 * @autho zehua
 *  下午3:47:22
 */
package com.test;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.DESUtils;

/**下午3:47:22
 * @author 2017*****下午3:47:22
 *
 */
public class test02 {
	public static void main(String[] args) {
		String url="http://www.jokeji.cn/list29_2.htm";
		Document document;
		try {
			document = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
		    Element select = document.select(".next_page a").last();
		    java.lang.String attr = select.attr("abs:href");
		    System.out.println(attr);
		    int indexOf = attr.indexOf("_");
		    int indexOf2 = attr.lastIndexOf(".");
		    System.out.println(attr.substring(indexOf+1,indexOf2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void String(){
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
