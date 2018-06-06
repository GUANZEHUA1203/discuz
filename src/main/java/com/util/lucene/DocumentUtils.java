package com.util.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;

import com.bean.Article;

public class DocumentUtils {
	public static Document article2Document(Article article) {
		Document document = new Document();
		Field idField = new Field("id", NumericUtils.longToPrefixCoded(article.getId()), Store.YES, Index.NOT_ANALYZED);
		Field titleField = new Field("title", article.getTitle(), Store.YES, Index.ANALYZED);
		Field contentField = new Field("content", article.getContent(), Store.YES, Index.ANALYZED);
		document.add(idField);
		document.add(titleField);
		document.add(contentField);
		return document;
	}

	public static Article document2Article(Document document) {
		Article article = new Article();
		article.setId(NumericUtils.prefixCodedToLong(document.get("id")));
		article.setTitle(document.get("title"));
		article.setContent(document.get("content"));
		return article;
	}
}
