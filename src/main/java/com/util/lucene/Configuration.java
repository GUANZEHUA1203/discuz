package com.util.lucene;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	// 采用单例模式
	private static final Configuration configuration = new Configuration();

	private Configuration() {
	}

	public synchronized static Configuration getInstance() {
		return configuration;
	}

	public String read(String properties, String key) {
		// 读取配置文件
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(properties);
		Properties p = new Properties();
		try {
			p.load(in);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		// 取得配置文件中的值
		return p.getProperty(key);
	}
}
