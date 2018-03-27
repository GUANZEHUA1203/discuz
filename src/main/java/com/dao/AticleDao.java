package com.dao;

import java.util.List;
import java.util.Map;

import com.bean.Aticle;

public interface AticleDao {
	/*
	 * 发文章
	 * */
	
	public void addAticle(Aticle aticle);
	/*
	 * 查看文章
	 * */
	
	public List<Aticle> findAticle(Map<String, Object> map);
	/**
	 * 修改文章
	 */
	public void updateAticle(Map<String, Object> map);
	/**
	 * 删除帖子
	 */
	public void deleteAticle(int id);
	/*
	 * 查找所有文章
	 * */
	public List<Aticle> findAticles();
	/*
	 * 分页
	 */
	public List<Aticle> paginationAticle(int pn,int px);
	/**
	 * 查找最大id
	 * */
	
	public int findMaxId();
	 /**
	  * 按条件查看总数
	  */
	public int findCountAticle(Map<String, Object> map);
}
