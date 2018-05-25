package com.schedule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.ElementsUtil;

/**
 * @autho zehua
 *  上午10:43:11
 */

/**上午10:43:11
 * @author 2017*****上午10:43:11
 *
 */
public class pashuju {
	
	public static List<String> rusltAnser=new ArrayList<String>();
	
	public static List<String> ipadress=new ArrayList<String>();
	
	static{
		try {
			ipadress = ElementsUtil.getHtml();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static List<String> addDateMain() throws IOException, InterruptedException{
		List<String> sqlList=new ArrayList<String>();
/*		String urlName[]={"dyzy","danjiyx","gaoq","tushuzy","dsjzy","jiaoyu-2","yinyue","tupian","zongyi","lxys","bdyz","rjzy"};//{"dyzy","danjiyx","gaoq","tushuzy","dsjzy","jiaoyu-2","yinyue","tupian","zongyi","lxys","bdyz","rjzy"};
		int pageNum[]={10,20,15,10,10,2,2,2,2,2,1,2};//{137,110,81,65,51,42,20,19,17,15,7,2};
		int type[]={2,2,2,2,45,2,42,43,2,2,2,2};//{2,2,2,2,45,2,42,43,2,2,2,2};
	for (int i = 0; i <urlName.length; i++) {
			for (int j =1; j < pageNum[i]; j++) {
				String url="http://www.friok.com/category/"+urlName[i]+"/";
				if(j!=1){
					System.err.println(url+"page/"+j);
					getAnser(url+"page/"+j,type[i]);
				}else{
					System.err.println(url);
					getAnser(url,type[i]);
				}
				
			}
			
			}
			*/	
			for (int j =1; j < 5; j++) {
				String url="";
			    url="http://www.friok.com/page/"+j;
			    getAnser(url+j,2);
			}
		/*2.*/
		for (String s : rusltAnser) {
			String[] split = s.split("password");
			String str = split[0].toString();
			System.out.println("str:"+str+"***");
			int indexOf = str.indexOf("<p>");
			int indexOf2 = str.indexOf("</p>");
			String title="";
			if(indexOf>0&&indexOf>0){
				title=str.substring(indexOf,indexOf2);
			}
			String sql2="INSERT INTO `tbl_aticle` ( `atman`, `attitle`, `atcontext`, `atdate`, `atlabel`, `atstate`) VALUES ( 'admin','"+split[1]+"', '"+split[0]+split[2]+"', now(), '电影', '0');";
			sqlList.add(sql2);
			System.out.println(sql2);
		}
		tid++;
		for (int m=0;m<rusltAnser.size();m++) {
			rusltAnser.remove(m);
		}
		return sqlList;
	}
	private static int tid=1;
	@SuppressWarnings("null")
	public static void getAnser(String url,int type) throws IOException, InterruptedException{
		List<String> into = getInfo(url, "div.entry h2 a[href]");
		List<String> link1=new ArrayList<String>();
		if(into!=null){
			for (String string : into) {
				List<String> info2 = getInfo(string, "strong > a[href]");
				if(info2!=null&&info2.size()>0){
					List<String> downinfo = downinfo(info2.get(0));
					if(downinfo!=null){
						rusltAnser.addAll(downinfo);
					}
				}
				
			}
		}
			
	
	}
	
	public static List<String> getInfo(String url,String reg) throws IOException, InterruptedException{
		Random r=new Random();
		ElementsUtil.visit(ipadress.get(r.nextInt(ipadress.size()-1)+0));
		List<String> result=new ArrayList<String>();
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(10000));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(10000));
		Document parse=null;
		try{
			parse= Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
		}catch(Exception e){
			Thread.sleep(5000);
			getInfo(url,reg);
		}
		if(parse==null){
			return null;
		}
		Elements select = parse.select(reg);
		for (Element element1 : select) {
			String attr = element1.attr("abs:href");
			result.add(attr);
		}
		return result;
	}
	public static List<String> downinfo(String url) throws IOException, InterruptedException{
		Random r=new Random();
		ElementsUtil.visit(ipadress.get(r.nextInt(ipadress.size()-1)+0));
		List<String> firstLink=new ArrayList<String>();
		System.setProperty("sun.net.client.defaultConnectTimeout", String
                .valueOf(10000));
		System.setProperty("sun.net.client.defaultReadTimeout", String
                .valueOf(10000)); 
		Document parse=null;
		try{
			 parse = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
		}catch(Exception e){
			Thread.sleep(8000);
			downinfo(url);
		}
		String baidupanpath="下载地址：",text="* ",text2="^_^";
		if(parse!=null){
			Elements baiduPanLink = parse.select("#google-ads a[href]");//
			baidupanpath += baiduPanLink.attr("abs:href");
			Elements answer = parse.select(".desc");//
			Elements title = answer.select("p:eq(1)");
			text += title.text();
			Elements select = answer.select("p:eq(2)");
			text2 += select.text();
			firstLink.add(baidupanpath+"password"+text+"password"+text2);//answer==null?"":.toString()
		}
		return firstLink;
		
	}
	 private static List<String> getHtml() throws IOException {
	        Document doc = null;
	        try {
	            doc = Jsoup.connect("http://www.xicidaili.com/nt")
	                    .userAgent("Mozilla")
	                    .get();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        List<String> list = new ArrayList<String>();
	        Elements elements = doc.select("tr.odd");
	        int len = elements.size();
	        Element element = null;
	        for (int i = 0; i < len; i++) {
	            element = elements.get(i);
	            StringBuilder sBuilder = new StringBuilder(20);
	            sBuilder.append(element.child(1).text());
	            sBuilder.append(":");
	            sBuilder.append(element.child(2).text());
	            list.add(sBuilder.toString());
	        }
	        doc = null;
	        elements.clear();
	        elements = null;
	        return list;
	    }
	 
	 

	

}
