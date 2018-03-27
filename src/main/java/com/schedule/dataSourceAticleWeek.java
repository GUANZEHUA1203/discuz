package com.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.ElementsUtil;

public class dataSourceAticleWeek
{
  public static List<String> rusltAnser = new ArrayList();
  public static List<String> ipadress = new ArrayList();
  public static List<String> rusltsql = new ArrayList();
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
  
  public static List<String> getData()
  {
    String[] urlName = { "renwu", "rensheng", "xinling", "qinggan", "chengzhang", "chushi", "shiye", "meiwen", "qingchun", "shenghuo", "zhihui", "lehuo", "zuowensucai" };
    int[] pageNum = { 10, 20, 30, 10, 20, 20, 20, 20, 20, 20, 20, 20, 30 };
    int i;
    for (int n = 0; n < urlName.length - 1; n++) {
      for (i = 1; i < pageNum[n] - 2; i++)
      {
        String path = null;
        if (i == 1)
        {
          path = httpUrl + urlName[n];
        }
        else
        {
          System.out.println("next Data ready");
          path = httpUrl + urlName[n] + "/page/" + i;
        }
        System.out.println(path);
        try
        {
          getAnser(path, 2);
        }
        catch (Exception e)
        {
          System.err.println("getData Fail");
          e.printStackTrace();
        }
      }
    }
    for (String string : rusltAnser)
    {
      String[] split = string.split("password");
      String sql = "INSERT INTO `aticle` ( `atman`, `attitle`, `atcontext`, `atdate`, `atlabel`, `atstate`) VALUES ( 'admin','" + ElementsUtil.delDom(split[0]) + "', '" + ElementsUtil.delDom(split[1]) + "', now(), '8', '0')";
      System.out.println(sql);
      rusltsql.add(sql);
    }
    return rusltsql;
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
      System.out.println("sleep..........");
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
      System.out.println("sleep..........");
      Thread.sleep(rand.nextInt(5000) + 500);
      downinfo(url);
    }
    String baidupanpath = "  ";String text = "  ";
    if (parse != null)
    {
      baidupanpath = baidupanpath + parse.select(".entry-header").toString();
      Elements select = parse.select(".entry-content");
      if (select.is("img")) {
        select.select("img").remove();
      }
      text = text + select.toString();
      text = text + select.toString();
      firstLink.add(baidupanpath + "password" + text);
    }
    return firstLink;
  }
}
