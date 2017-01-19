package com.tv.impl;

import com.tv.enums.TvSiteEnum;
import com.tv.util.AbstractSpider;
import com.tv.util.StringUtil;

public class GetTvUrlImpl extends AbstractSpider{
	public String getTvUrlImgUrl(String urls){
		String tvImgUrl="";
		if(TvSiteEnum.getEnumUrl(urls)==TvSiteEnum.JiuJiuRe.getUrl()){
			System.out.println("获取久久热视频地址："+urls);
			try {
				String resultcodes=super.crawl(urls);
				String tvUrl=StringUtil.betweenSting(resultcodes, "video_url", ",");
				String imgUrl=StringUtil.betweenSting(resultcodes, "preview_url", ",");
				tvImgUrl=tvUrl+"|"+imgUrl;
				tvImgUrl=tvImgUrl.replace(": '", "").replace("/'", "").replace("'", "");
			} catch (Exception e) {
				throw new RuntimeException(urls+"网页获取失败！！"+e);
			}
		} else if(TvSiteEnum.getEnumUrl(urls)==TvSiteEnum.YYShenQu.getUrl()){
			System.out.println("获取YY神曲视频地址："+urls);
			try {
				String resultcodes=super.crawl(urls);
				String tvUrl=StringUtil.betweenSting(resultcodes, "worksUrl:\"", "\",");
				String imgUrl=StringUtil.betweenSting(resultcodes, "snapshot:\"", "\",");
				tvImgUrl=tvUrl+"|"+imgUrl;
			} catch (Exception e) {
				throw new RuntimeException(urls+"网页获取失败！！"+e);
			}
		}
		return tvImgUrl;
	}
}
