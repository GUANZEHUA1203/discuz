package com.services;

import java.util.List;

import com.bean.Login;

public interface LoginServices {
	/**
	 * 用户登录
	 * @param login
	 * @return
	 */
	public Login userlogin(Login login);
	/**
	 * 注册
	 */
	public int zhuce(Login login);
	/**
	 * 修改密码
	 */
	public Login updateLogin(Login login);
	/*
	 * 	查找所有用户
	 * */
	public List<Login> findLogins();

}
