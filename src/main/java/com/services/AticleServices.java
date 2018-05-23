package com.services;


import java.util.List;
import java.util.Map;

import com.bean.Aticle;

public interface AticleServices {
	public void fabiao(Aticle aticle);
	public List<Aticle> showaticle(int pn,int px);
	public List<Aticle> findAll();
	public List<Aticle> selectAticle(Map<String,Object> map);
	public void deleteAticle(Integer id);
	public void updateAticle(Map<String,Object> map);
	public void autoAddData();
	 /**
	  * 按条件查看总数
	  */
	public int findCountAticle(Map<String, Object> map);
	
	public void insertBatch(List<Aticle> aticles);
}
