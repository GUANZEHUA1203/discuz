package com.schedule;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.naming.spi.DirStateFactory.Result;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.SqlMapper;
public class Pashujuxiaohua
{
	
  @Resource
  private SqlSessionFactory sqlsessionfactory;
  private static int pagenum = 2;
  public static int countPage=10;
  public static List<String> rusltAnser = new ArrayList();
  public static List<String> rusltTitle = new ArrayList();
  public static List<String> rusltSql = new ArrayList();
  public static Random rand = new Random();
  
  public  List<String> getData(){
    String[] urlPaht = {
      "http://www.jokeji.cn/list29_", 
      "http://www.jokeji.cn/list13_", 
      "http://www.jokeji.cn/list43_", 
      "http://www.jokeji.cn/list5_", 
      "http://www.jokeji.cn/list1_", 
      "http://www.jokeji.cn/list7_", 
      "http://www.jokeji.cn/list4_", 
      "http://www.jokeji.cn/list27_", 
      "http://www.jokeji.cn/list40_", 
      "http://www.jokeji.cn/list16_", 
      "http://www.jokeji.cn/list12_", 
      "http://www.jokeji.cn/list39_", 
      "http://www.jokeji.cn/list18_", 
      "http://www.jokeji.cn/list36_", 
      "http://www.jokeji.cn/list35_", 
      "http://www.jokeji.cn/list30_", 
      "http://www.jokeji.cn/list2_", 
      "http://www.jokeji.cn/list31_", 
      "http://www.jokeji.cn/list34_", 
      "http://www.jokeji.cn/list8_", 
      "http://www.jokeji.cn/list9_", 
      "http://www.jokeji.cn/list6_", 
      "http://www.jokeji.cn/list22_", 
      "http://www.jokeji.cn/list17_", 
      "http://www.jokeji.cn/list15_", 
      "http://www.jokeji.cn/list11_", 
      "http://www.jokeji.cn/list20_", 
      "http://www.jokeji.cn/list38_", 
      "http://www.jokeji.cn/list24_", 
      "http://www.jokeji.cn/list42_", 
      "http://www.jokeji.cn/list23_", 
      "http://www.jokeji.cn/list21_", 
      "http://www.jokeji.cn/list10_", 
      "http://www.jokeji.cn/list3_", 
      "http://www.jokeji.cn/list33_", 
      "http://www.jokeji.cn/list37_", 
      "http://www.jokeji.cn/list41_", 
      "http://www.jokeji.cn/list44_", 
      "http://www.jokeji.cn/list19_", 
      "http://www.jokeji.cn/list14_", 
      "http://www.jokeji.cn/list32_", 
      "http://www.jokeji.cn/list45_", 
      "http://www.jokeji.cn/list46_", 
      "http://www.jokeji.cn/list28_", 
      "http://www.jokeji.cn/list25_", 
      "http://www.jokeji.cn/list26_", 
      "http://www.jokeji.cn/hot.asp?me_page=" };
    
    String[] arrayOfString1 = urlPaht;
    int j = urlPaht.length;
    for (int i = 0; i < j; i++){
      String string = arrayOfString1[i];
      while (pagenum<=countPage){
        String url = string + pagenum + ".htm";
        try {
//        	获取总页数
        	if(pagenum==2){
        		countPage = getCount(url);
        	}
        	System.out.println("url************************" + url);
	        getAnser(url);//获取内容
            pagenum ++ ;
            if(pagenum==countPage-1){
            	pagenum=2;
            	break;
            }
        } catch (Exception e) {
			e.printStackTrace();
		}
      }
    }
    return rusltSql;
  }
  
  public  void getAnser(String url)
    throws IOException, InterruptedException
  {
    List<String> link1 = getInfo(url, ".list_title ul li b a");
    if (link1 != null)
    {
      for (String string : link1) {
        if (string != null) {
          downinfo(string);
        }
      }
      if (rusltAnser != null) {
        for (int i = 0; i < rusltAnser.size(); i++)
        {
          String sql1 = "INSERT INTO `tbl_aticle` (`atman`, `attitle`, `atcontext`, `atdate`, `atlabel`, `atstate`) VALUES ('admin', '" + (String)rusltTitle.get(i) + "', '" + (String)rusltAnser.get(i) + "', now(), '11', '0');";
          rusltSql.add(sql1);
        }
        insertSQL(rusltSql);
        rusltSql.clear();//清空SQL
      }
    }
  }
  
  //获取总页数
  public int getCount(String url){
		try {
			Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
		    Element select = document.select(".next_page a").last();
		    String attr = select.attr("abs:href");
		    if(attr.indexOf("_")>0&&attr.lastIndexOf(".")>0){
			    int indexOf = attr.indexOf("_");
			    int indexOf2 = attr.lastIndexOf(".");
			    String substring = attr.substring(indexOf+1,indexOf2);
			    System.out.println("substring"+substring);
			    return Integer.parseInt(substring);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 30;
  }
  public static List<String> getInfo(String url, String reg)
    throws IOException, InterruptedException
  {
    List<String> result = new ArrayList();
    Document parse = null;
    try
    {
      if (testUrlWithTimeOut(url, 5000))
      {
        parse = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
        Thread.sleep(2000L);
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
      Thread.sleep(rand.nextInt(200) + 5000);
      getInfo(url, reg);
    }
    Elements select = parse.select(reg);
    if (select != null) {
      for (Element element1 : select)
      {
        String attr = null;
        attr = element1.attr("abs:href");
        rusltTitle.add(element1.text());
        result.add(attr);
      }
    }
    return result;
  }
  
  public static List<String> downinfo(String url)
    throws IOException, InterruptedException
  {
    List<String> firstLink = new ArrayList();
    Document parse = null;
    try
    {
      try
      {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(url);
        if (testUrlWithTimeOut(url, 5000))
        {
          if (m.find()) {
            parse = Jsoup.parse(url);
          } else {
            parse = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
          }
          Thread.sleep(3000L);
        }
        if (parse != null)
        {
          Elements baiduPanLink = parse.select("#text110");
          for (Element element : baiduPanLink) {
            if (element.text() != null) {
              firstLink.add(element.text());
            }
          }
        }
        else
        {
          firstLink.add("sorry ! data has been delete");
        }
      }
      catch (Exception e)
      {
        System.out.println(e);
        Thread.sleep(rand.nextInt(200) + 5000);
        downinfo(url);
      }
      rusltAnser.addAll(firstLink);
    }
    catch (Exception e)
    {
      System.out.println(e);
      Thread.sleep(rand.nextInt(200) + 5000);
    }
    return rusltAnser;
  }
  
  public static boolean testUrlWithTimeOut(String urlString, int timeOutMillSeconds)
    throws InterruptedException
  {
    try
    {
      URL url = new URL(urlString);
      URLConnection co = url.openConnection();
      co.setConnectTimeout(timeOutMillSeconds);
      co.connect();
      return true;
    }
    catch (Exception e1)
    {
    	return false;
    }
  }
  
  
  public void insertSQL(List<String> rusltSql){
	  if(rusltSql!=null&&rusltSql.size()>0){
		  SqlSession openSession = this.sqlsessionfactory.openSession();
			for (String string : rusltSql) {
				new SqlMapper(openSession).insert(string);
			}
		  openSession.close();
	  }
  }
}
