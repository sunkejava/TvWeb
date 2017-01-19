package com.tv.impl;

import com.tv.enums.TvSiteEnum;
import com.tv.interfaces.ITvSpider;
import com.tv.util.AbstractSpider;
import com.tv.util.StringUtil;

public class GetTvUrlImpl extends AbstractSpider {
	
	/**
	 * 默认的抓取方法，最多会尝试 {@link ITvSpider#MAX_TRY_TIMES} 次下载
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String getTvUrlImgUrl(String url) throws Exception {
		return getTvUrlImgUrl(url, ITvSpider.MAX_TRY_TIMES);
	}
	
	/**
	 * 抓取网站视频地址
	 * @param urls
	 * @param maxTryTimes
	 * @return
	 */
	public String getTvUrlImgUrl(String urls, Integer maxTryTimes) {
		maxTryTimes = maxTryTimes == null ? ITvSpider.MAX_TRY_TIMES : maxTryTimes;
		String tvImgUrl = "";
		for (int i = 0; i < maxTryTimes; i++) {
			System.err.println("开始第" + (i + 1) + "次尝试获取电影列表！！");
			try {
				if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.JiuJiuRe.getUrl()) {
					System.out.println("获取久久热视频地址：" + urls);
					try {
						String resultcodes = super.crawl(urls);
						String tvUrl = StringUtil.betweenSting(resultcodes, "video_url", ",");
						String imgUrl = StringUtil.betweenSting(resultcodes, "preview_url", ",");
						tvImgUrl = tvUrl + "|" + imgUrl;
						tvImgUrl = tvImgUrl.replace(": '", "").replace("/'", "").replace("'", "");
					} catch (Exception e) {
						throw new RuntimeException(urls + "网页获取失败！！" + e);
					}
				} else if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.YYShenQu.getUrl()) {
					System.out.println("获取YY神曲视频地址：" + urls);
					try {
						String resultcodes = super.crawl(urls);
						String tvUrl = StringUtil.betweenSting(resultcodes, "worksUrl:\"", "\",");
						String imgUrl = StringUtil.betweenSting(resultcodes, "snapshot:\"", "\",");
						tvImgUrl = tvUrl + "|" + imgUrl;
					} catch (Exception e) {
						throw new RuntimeException(urls + "网页获取失败！！" + e);
					}
				}
				return tvImgUrl;
			} catch (Exception e) {
				System.err.println(urls + ",尝试了【" + (i + 1) + "/" + maxTryTimes + "】次依然获取失败了！");
			}
		}

		throw new RuntimeException(urls + ",尝试了" + maxTryTimes + "次依然获取失败了！");
	}
}
