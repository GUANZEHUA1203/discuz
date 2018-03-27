package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;

import com.bean.Message;
import com.bean.content;
import com.google.gson.Gson;
@ServerEndpoint("/chatSocket")
public class ChatSocket {
	private String username;
	private static Map<String,Session> map=new HashMap<String, Session>();
	private static List<Session> sessions= new ArrayList<Session>();
	private static List<String> names=new ArrayList<String>();
	@OnOpen
	public void open(Session session){
		try{
		String queryString = session.getQueryString();
		System.out.println(queryString);
		username=queryString.split("=")[1];
		
		this.names.add(username);
		this.sessions.add(session);
		this.map.put(this.username, session);
		String msg="欢迎"+this.username+"进入房间";
		Message message=new Message();
		message.setContent(msg);
		message.setListname(this.names);
		this.broadcast(this.sessions , message.tojson());
		} catch(Exception e){
			System.err.println("open"+e);
		}
	}
	private static Gson gson=new Gson();
	@OnMessage
	public void messsage(Session session,String json){
		try{
		content c = gson.fromJson(json, content.class);
		if(c.getType()==1){
			//群（广播）
			Message message=new Message();
			message.setTalkAbout(this.username, c.getMsg());
			this.broadcast(this.sessions, message.tojson());
		}else{
			//私聊
			Message message=new Message();
			message.setTalkAbout(this.username,"<font color='red'>"+"【私聊你】"+"</font>"+"<font style='font-weight:bold'>"+c.getMsg()+"</font>");
			String touser = c.getTouser();
			Session to_session = this.map.get(touser);
			try {
				to_session.getBasicRemote().sendText(message.tojson());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		} catch(Exception e){
			System.err.println("messsage"+e);
		}
	}
	@OnClose
	public void close(Session session){
		try{
		this.sessions.remove(session);
		this.names.remove(username);
		String msg="欢送"+this.username+"退出房间";
		Message message=new Message();
		message.setContent(msg);
		message.setListname(this.names);
		this.broadcast(this.sessions , message.tojson());
	} catch(Exception e){
		System.err.println("messsage"+e);
	}
	}
	public void broadcast(List<Session>  sessions2 ,String msg ){
		for (Iterator iterator = sessions2.iterator(); iterator.hasNext();) {
			Session session = (Session) iterator.next();
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
;