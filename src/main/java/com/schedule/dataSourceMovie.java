package com.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.druid.support.logging.Log;
import com.util.ElementsUtil;

public class dataSourceMovie {
	public static List<String> rusltAnser = new ArrayList();
	public static List<String> ipadress = new ArrayList();
	static {
		try {
			ipadress = ElementsUtil.getHtml();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 爬取http://www.friok.com  首页数据 （2页） 
	 */
	public static List<String> addDateMain() {
		List<String> sqlList = new ArrayList();
		String url;
		for (int j = 1; j < 5; j++) {
			url = "";
			url = "http://www.friok.com/page/" + j;
			try {
				System.out.println(url + j);
				getAnser(url + j, 2);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		for (String s : rusltAnser) {
			String[] split = s.split("password");
			String str = split[0].toString();
			System.out.println("str:" + str + "***");
			int indexOf = str.indexOf("<p>");
			int indexOf2 = str.indexOf("</p>");
			String title = "";
			if ((indexOf > 0) && (indexOf > 0)) {
				title = str.substring(indexOf, indexOf2);
			}
			String sql2 = "INSERT INTO `tbl_aticle` ( `atman`, `attitle`, `atcontext`, `atdate`, `atlabel`, `atstate`) VALUES ( 'admin','"
					+ split[1] + "', '" + split[0] + split[2] + "', now(), '1', '0');";
			sqlList.add(sql2);
			System.out.println(sql2);
		}
		tid += 1;
		for (int m = 0; m < rusltAnser.size(); m++) {
			rusltAnser.remove(m);
		}
		return sqlList;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> addDateMain = addDateMain();
		for (String string : addDateMain) {
			System.out.println(string);
		}
	}

	private static int tid = 1;

	public static void getAnser(String url, int type) throws IOException, InterruptedException {
		List<String> into = getInfo(url, "div.entry h2 a[href]");
		List<String> link1 = new ArrayList();
		if (into != null) {
			for (String string : into) {
				List<String> info2 = getInfo(string, "strong > a[href]");
				if ((info2 != null) && (info2.size() > 0)) {
					List<String> downinfo = downinfo((String) info2.get(0));
					if (downinfo != null) {
						rusltAnser.addAll(downinfo);
					}
				}
			}
		}
	}

	public static List<String> getInfo(String url, String reg) throws IOException, InterruptedException {
		Random r = new Random();
		ElementsUtil.visit((String) ipadress.get(r.nextInt(ipadress.size() - 1) + 0));
		List<String> result = new ArrayList();
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(10000));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(10000));
		Document parse = null;
		try {
			parse = Jsoup.connect(url)
					.userAgent(
							"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)")
					.timeout(5000).get();
		} catch (Exception e) {
			Thread.sleep(5000L);
			getInfo(url, reg);
		}
		if (parse == null) {
			return null;
		}
		Elements select = parse.select(reg);
		for (Element element1 : select) {
			String attr = element1.attr("abs:href");
			result.add(attr);
		}
		return result;
	}

	public static List<String> downinfo(String url) throws IOException, InterruptedException {
		Random r = new Random();
		ElementsUtil.visit((String) ipadress.get(r.nextInt(ipadress.size() - 1) + 0));
		List<String> firstLink = new ArrayList();
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(10000));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(10000));
		Document parse = null;
		try {
			parse = Jsoup.connect(url)
					.userAgent(
							"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)")
					.timeout(5000).get();
		} catch (Exception e) {
			Thread.sleep(8000L);
			downinfo(url);
		}
		String baidupanpath = "网盘地址下载(address):";
		String text = "  !^>>^! ";
		String text2 = "^_^";
		if (parse != null) {
			Elements baiduPanLink = null;
//			baiduPanLink=parse.select("#google-ads a[href]");
			baiduPanLink = parse.select(".list a[href]");
			baidupanpath = baidupanpath + baiduPanLink.attr("abs:href");
			Elements answer = parse.select(".desc");
			Elements title = answer.select("p:eq(1)");
			text = text + title.text();
			Elements select = answer.select("p:eq(2)");
			text2 = text2 + select.text();
			firstLink.add(baidupanpath + "password" + text + "password" + text2);
		}
		return firstLink;
	}

	private static List<String> getHtml() throws IOException {
		Document doc = null;
		try {
			doc =

					Jsoup.connect("http://www.xicidaili.com/nt").userAgent("Mozilla").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> list = new ArrayList();
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
