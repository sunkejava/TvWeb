package com.tv.util;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

public class NovelSpiderHttpGet extends HttpGet {

	public NovelSpiderHttpGet() {
	}

	public NovelSpiderHttpGet(URI uri) {
		super(uri);
	}

	public NovelSpiderHttpGet(String uri) {
		super(uri);
		setDefaultConfig();
	}
	
	
	private void setDefaultConfig(){
		this.setConfig(RequestConfig.custom()
				.setSocketTimeout(1000*60*5)
				.setMaxRedirects(1000*60*5)
				.setConnectTimeout(1000*60*10)
				.setConnectionRequestTimeout(1000*60*5).build());
		this.setHeader("Accept-Language","zh-CN,zh;q=0.8");
		this.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0");
	
	}

}
