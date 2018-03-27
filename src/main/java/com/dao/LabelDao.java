package com.dao;

import java.util.List;

import com.bean.Label;
public interface LabelDao {
	/*
	 * 查找所有标签
	 */
	public List<Label> findAll();
}
