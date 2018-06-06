package com.util.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUtils {
	public static Directory directory;

	public static Analyzer analyzer;
	static {
		try {
			directory = FSDirectory.open(new File("./indexDir"));
			analyzer = new IKAnalyzer();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
