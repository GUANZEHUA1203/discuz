package com.dao;

import java.util.List;

import com.bean.Login;

public interface LoginDao {
	/**
	 * 用户登录
	 * @param login
	 * @return
	 */
	public Login userlogin(Login login);
	/**
	 * 添加
	 */
	public int addlogin(Login login);
	/**
	 * 修改密码
	 */
	public Login updateLogin(Login login);
	/*
	 * 	查找所有用户
	 * */
	public List<Login> findLogins();
}
