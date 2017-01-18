package com.tv.util;

public class Abstractp extends AbstractSpider {
	
	public  void getsource(String url){
		try {
			String urlsou = super.crawl(url);
			System.out.println(urlsou);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
