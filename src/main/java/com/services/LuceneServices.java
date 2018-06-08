package com.services;


import com.bean.Article;
import com.bean.Aticle;
import com.common.PageParam;
import com.util.PageUtils;

public interface LuceneServices {
	
	public PageUtils<Article> pageinfo(String info,PageParam pageParam);
	
	public void addInfo(Aticle aticle);
	
	public void deleteInfo(String id);
	
	public void updateInfo(Aticle aticle);
}
