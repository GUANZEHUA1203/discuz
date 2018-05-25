package com.schedule;

import com.util.ElementsUtil;
import com.util.SqlMapper;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

public class dataSourceAllAticle
{
  @Autowired
  private static SqlSessionFactory sqlSessionFactory;
  
  public static List<String> rusltAnser = new ArrayList<String>();
  public static List<String> ipadress = new ArrayList<String>();
  public static List<String> rusltsql = new ArrayList<String>();
  public static Random rand = new Random();
  
  static
  {
    try
    {
      ipadress = ElementsUtil.getHtml();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private static String httpUrl = "http://www.85nian.net/category/";
  
  public static void getData()
  {
    String[] urlName = { "renwu", "rensheng", "xinling", "qinggan", "chengzhang", "chushi", "shiye", "meiwen", "qingchun", "shenghuo", "zhihui", "lehuo", "zuowensucai" };
    int[] pageNum = { 85, 150, 105, 168, 137, 92, 126, 166, 27, 79, 170, 199, 306 };
    for (int n = 0; n < urlName.length - 1; n++) {
      for (int i = 1; i < pageNum[n] - 2; i++)
      {
        String path = null;
        if (i == 1)
        {
          path = httpUrl + urlName[n];
        }
        else
        {
          path = httpUrl + urlName[n] + "/page/" + i;
        }
        try
        {
          List<String> rusltAnser = getAnser(path, 2);
          for (String string : rusltAnser)
          {
        	  SqlSession openSession = sqlSessionFactory.openSession();
            String[] split = string.split("password");
            String sql = "INSERT INTO `tbl_aticle` ( `atman`, `attitle`, `atcontext`, `atdate`, `atlabel`, `atstate`) VALUES ( 'admin','" + ElementsUtil.delDom(split[0]) + "', '" + ElementsUtil.delDom(split[1]) + "', now(), '8', '0')";
            new SqlMapper(openSession).insert(sql);
            openSession.close();
          }
         
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public static List<String> getAnser(String url, int type)
    throws IOException, InterruptedException
  {
    List<String> into = getInfo(url, "h2.entry-title a");
    if (into != null) {
      for (String s : into)
      {
        List<String> downinfo = downinfo(s);
        if (downinfo != null) {
          rusltAnser.addAll(downinfo);
        }
      }
    }
    return rusltAnser;
  }
  
  public static List<String> getInfo(String url, String reg)
    throws IOException, InterruptedException
  {
    Random r = new Random();
    ElementsUtil.visit((String)ipadress.get(r.nextInt(ipadress.size() - 1) + 0));
    List<String> result = new ArrayList();
    System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(10000));
    System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(10000));
    Document parse = null;
    try
    {
      parse = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
    }
    catch (Exception e)
    {
      Thread.sleep(rand.nextInt(500) + 1000);
      getInfo(url, reg);
    }
    if (parse == null) {
      return null;
    }
    Elements select = parse.select(reg);
    for (Element element1 : select)
    {
      String attr = element1.attr("abs:href");
      result.add(attr);
    }
    return result;
  }
  
  public static List<String> downinfo(String url)
    throws IOException, InterruptedException
  {
    Random r = new Random();
    ElementsUtil.visit((String)ipadress.get(r.nextInt(ipadress.size() - 1) + 0));
    List<String> firstLink = new ArrayList();
    System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(10000));
    System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(10000));
    Document parse = null;
    try
    {
      parse = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)").timeout(5000).get();
    }
    catch (Exception e)
    {
      Thread.sleep(rand.nextInt(5000) + 500);
      downinfo(url);
    }
    String baidupanpath = "  ";String text = "  ";
    if (parse != null)
    {
      baidupanpath = baidupanpath + parse.select(".entry-header").text().toString();
      Elements select = parse.select(".entry-content");
      if (select.is("img")) {
        select.select("img").remove();
      }
      text = text + select.toString();
      String sql = "INSERT INTO `aticle` ( `atman`, `attitle`, `atcontext`, `atdate`, `atlabel`, `atstate`) VALUES ( 'admin','" + baidupanpath + "', '" + text + "', now(), '8', '0')";
      firstLink.add(baidupanpath + "password" + text);
    }
    return firstLink;
  }
}
