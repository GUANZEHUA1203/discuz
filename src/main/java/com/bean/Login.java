package com.bean;
// default package

/**
 * Login entity. @author MyEclipse Persistence Tools
 */

public class Login implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String password;
	private Integer power;
	private Integer isdelete;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** minimal constructor */
	public Login(Integer id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Login(Integer id, String name, String password,
			Integer power, Integer isdelete) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.power = power;
		this.isdelete = isdelete;
	}

	// Property accessors

	public Integer getid() {
		return this.id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getname() {
		return this.name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getpassword() {
		return this.password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public Integer getpower() {
		return this.power;
	}

	public void setpower(Integer power) {
		this.power = power;
	}

	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", name=" + name + ", password=" + password
				+ ", power=" + power + ", isdelete=" + isdelete + "]";
	}
	

}