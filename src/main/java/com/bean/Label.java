package com.bean;

public class Label {
	private Integer id;
	private String name;
	
	public Label() {
		super();
	}
	
	public Label(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public Label(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Label [id=" + id + ", name=" + name + "]";
	}
	
}
