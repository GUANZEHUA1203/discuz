package com.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;

import com.bean.Article;
import com.bean.Aticle;
import com.common.PageParam;
import com.services.LuceneServices;
import com.util.PageUtils;
import com.util.lucene.DocumentUtils;
import com.util.lucene.LuceneUtils;

public class LuceneServicesImpl implements LuceneServices {

	public PageUtils<Article> pageinfo(String info, PageParam pageParam) {
		return null;
		/*IndexSearcher indexSearcher = new IndexSearcher(LuceneUtils.directory);
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_30, new String[] { "title", "content" }, LuceneUtils.analyzer);
		Query query = queryParser.parse("2013");
		TopDocs topDocs = indexSearcher.search(query, 50);// 查询50条结果
		int count = topDocs.totalHits;// 总的记录数
		int scoreCount = Math.min(count, );// 截止条数
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		// 将搜索出的doc转换为model保存在List中
		List<Article> articleList = new ArrayList<Article>();
		for (int i = firstResult; i < scoreCount; i++) {
			int index = scoreDocs[i].doc;
			Document document = indexSearcher.doc(index);
			Article article = DocumentUtils.document2Article(document);
			articleList.add(article);
		}

		// 输出查询结果
		for (Article article : articleList) {
			System.out.println(article.getId());
			System.out.println(article.getTitle());
			System.out.println(article.getContent());
		}
		return null;*/
	}

	public void addInfo(Aticle aticle) {

	}

	public void deleteInfo(String id) {

	}

	public void updateInfo(Aticle aticle) {

	}

}
