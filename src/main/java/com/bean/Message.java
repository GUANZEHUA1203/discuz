package com.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class Message {
	private String content;
	private List<String> listname;
	private String talkAbout;
 public String getTalkAbout() {
		return talkAbout;
	}
	public void setTalkAbout(String name,String msg) {
		this.talkAbout = "用户【"+name+"】于"+ new SimpleDateFormat("HH:mm:ss").format(new Date())+"<br/>"+"<span>"+msg+"</span>"+"<br/>";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getListname() {
		return listname;
	}
	public void setListname(List<String> listname) {
		this.listname = listname;
	}
	public Message() {
		super();
	}
	
	public String tojson(){
		return gsion.toJson(this);
	}
	private static Gson gsion=new Gson();

}
