package com.bean;

import java.util.Date;


public class Aticle {
	private Integer id;
	private String man;
	private String title;
	private Date date;
	private String context;
	private String label;
	private Integer state;
	
	public Aticle(Integer id, String man, String title, Date date,
			String context, String label, Integer state) {
		super();
		this.id = id;
		this.man = man;
		this.title = title;
		this.date = date;
		this.context = context;
		this.label = label;
		this.state = state;
	}
	
	
	public Aticle(String man, String title, String context) {
		super();
		this.man = man;
		this.title = title;
		this.context = context;
	}


	public Aticle() {
		super();
	}


	public Aticle(String man, String title, Date date, String context,
			String label, Integer state) {
		super();
		this.man = man;
		this.title = title;
		this.date = date;
		this.context = context;
		this.label = label;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMan() {
		return man;
	}
	public void setMan(String man) {
		this.man = man;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "Aticle [id=" + id + ", man=" + man + ", title=" + title
				+ ", date=" + date + ", context=" + context + ", label="
				+ label + ", state=" + state + "]";
	}
	

}
