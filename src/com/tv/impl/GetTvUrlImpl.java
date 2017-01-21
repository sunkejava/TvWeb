package com.tv.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.tv.enums.TvSiteEnum;
import com.tv.interfaces.ITvSpider;
import com.tv.util.AbstractSpider;
import com.tv.util.StringUtil;

public class GetTvUrlImpl extends AbstractSpider {
	

	
	/**
	 * 抓取网站视频地址
	 * @param urls
	 * @param maxTryTimes
	 * @return
	 */
	public String getTvUrlImgUrl(String urls){
		
		String tvImgUrl = "";
			try {
				if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.JiuJiuRe.getUrl()) {
					String purl = "http://www."+TvSiteEnum.JiuJiuRe.getUrl()+"/embed/"+StringUtil.betweenSting(urls, "videos/", "/");
					//System.out.println("获取久久热视频地址：" + purl);
					try {
						//String resultcodes = super.crawl(purl);
						URL url2 = new URL(purl);
						BufferedReader bufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(url2.openStream()),"utf-8"));
						String line2;
						StringBuffer sb2 = new StringBuffer();
						while((line2 = bufr2.readLine())!= null){
							sb2.append(line2);
						}
						String resultcodes =sb2.toString();
						String tvUrl = StringUtil.betweenSting(resultcodes, "video_url", ",");
						String imgUrl = StringUtil.betweenSting(resultcodes, "preview_url", ",");
						tvImgUrl = tvUrl + "----" + imgUrl;
						tvImgUrl = tvImgUrl.replace(": '", "").replace("/'", "").replace("'", "");
					} catch (Exception e) {
						throw new RuntimeException(purl + "网页获取失败！！" + e);
					}
				} else if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.YYShenQu.getUrl()) {
					System.out.println("获取YY神曲视频地址：" + urls);
					try {
						//String resultcodes = super.crawl(urls);
						URL url2 = new URL(urls);
						BufferedReader bufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(url2.openStream()),"utf-8"));
						String line2;
						StringBuffer sb2 = new StringBuffer();
						while((line2 = bufr2.readLine())!= null){
							sb2.append(line2);
						}
						String resultcodes =sb2.toString();
						String tvUrl = StringUtil.betweenSting(resultcodes, "worksUrl:\"", "\",");
						String imgUrl = StringUtil.betweenSting(resultcodes, "snapshot:\"", "\",");
						tvImgUrl = tvUrl + "----" + imgUrl;
					} catch (Exception e) {
						throw new RuntimeException(urls + "网页获取失败！！" + e);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return tvImgUrl;
	}
}
