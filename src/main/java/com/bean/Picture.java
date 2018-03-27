/**
 * @autho zehua
 *  下午2:29:52
 */
package com.bean;

import java.util.Date;

/**下午2:29:52
 * @author 2017*****下午2:29:52
 *
 */
public class Picture {
	private Integer id;
	private String name;
	private Date date;
	private String preview;
	private String referer;
	private String title;
	private String url;
	private String width;
	private String image;
	private String height;
	
	
	
	
	
	
	
	
	public Picture() {
		super();
	}
	public Picture(String name, Date date) {
		super();
		this.name = name;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	

}
