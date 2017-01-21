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
	 * ץȡ��վ��Ƶ��ַ
	 * @param urls
	 * @param maxTryTimes
	 * @return
	 */
	public String getTvUrlImgUrl(String urls){
		
		String tvImgUrl = "";
			try {
				if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.JiuJiuRe.getUrl()) {
					String purl = "http://www."+TvSiteEnum.JiuJiuRe.getUrl()+"/embed/"+StringUtil.betweenSting(urls, "videos/", "/");
					//System.out.println("��ȡ�þ�����Ƶ��ַ��" + purl);
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
						throw new RuntimeException(purl + "��ҳ��ȡʧ�ܣ���" + e);
					}
				} else if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.YYShenQu.getUrl()) {
					System.out.println("��ȡYY������Ƶ��ַ��" + urls);
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
						throw new RuntimeException(urls + "��ҳ��ȡʧ�ܣ���" + e);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return tvImgUrl;
	}
}
