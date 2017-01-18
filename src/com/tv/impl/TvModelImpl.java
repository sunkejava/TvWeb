package com.tv.impl;

import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tv.interfaces.ITvSpider;
import com.tv.model.TvModel;
import com.tv.util.AbstractSpider;
import com.tv.util.DbUtil;
import com.tv.util.NovelSpiderUtil;
import com.tv.util.StringUtil;
import com.tv.enums.TvSiteEnum;

public  class TvModelImpl extends AbstractSpider{
	
	
	/**
	 * 默认的抓取方法，最多会尝试 {@link ITaotuSpider#MAX_TRY_TIMES} 次下载
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected TvModel[] getsTvList(String url) throws Exception {
		return getsTvList(url, ITvSpider.MAX_TRY_TIMES);
	}
	/**
	 * 通过url获取电影列表
	 * @param url
	 * @param maxTryTimes 最大尝试次数
	 * @return
	 */
	public TvModel[] getsTvList(String url,Integer maxTryTimes) {
		maxTryTimes = maxTryTimes == null ? ITvSpider.MAX_TRY_TIMES : maxTryTimes;
		for (int i = 0; i < maxTryTimes ; i++) {
			System.err.println("开始第"+(i+1)+"次尝试获取电影列表！！");
		try {
			String result = super.crawl(url);
			Document doc = Jsoup.parse(result);
			doc.setBaseUri(url);
			Map<String,String> contexts=NovelSpiderUtil.getContext(TvSiteEnum.getEnumByUrl(url));
			String imgnameSelector = contexts.get("tv-list-imgname-selector");
			String idSelector = contexts.get("tv-list-id-selector");
			String timeLongSelector = contexts.get("tv-list-timeLong-selector");
			String addTimeSelector = contexts.get("tv-list-addTime-selector");
			String contentSelector = contexts.get("tv-list-content-selector");
			String starsSelector = contexts.get("tv-list-stars-selector");
			String typeNameSelector = contexts.get("tv-list-typeName-selector");
			String nextPageSelector = contexts.get("tv-list-next-selector");
			TvModel[] tvModel = {};
			if (imgnameSelector == null || idSelector == null || timeLongSelector == null  ) throw new RuntimeException(TvSiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "目前不支持获取图片列表");
			if (imgnameSelector != null || idSelector != null || timeLongSelector != null ) {
			Elements imgNameElement = doc.select(imgnameSelector);
			Elements idElement = doc.select(idSelector);
			Elements timeLongElement = doc.select(timeLongSelector);
			Elements addTimeElement = doc.select(addTimeSelector);
			Elements contextElement = doc.select(contentSelector);
			Elements starsElement = doc.select(starsSelector);
			Elements typeNameElement = doc.select(typeNameSelector);
			Elements nextPageElement = (nextPageSelector.contains(".empty") ? idElement : doc.select(nextPageSelector));
			String nextUrl="";
			tvModel = new TvModel[imgNameElement.size()];
			int a = 0; 
			if(nextPageElement==idElement  || nextPageElement.isEmpty()){
				nextUrl="";
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.TengXun.getUrl()){
				String datapage = nextPageElement.get(0).attr("data-page");
				String datatotal = nextPageElement.get(0).attr("data-total");
				String ps = url.substring(url.lastIndexOf("offset="));
				if(datapage==datatotal){
					nextUrl="";
				}else{
					nextUrl=url.replace(ps,"offset="+Integer.toString(Integer.parseInt(datapage)*30));
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.Porn91.getUrl()){
				int nowP =Integer.parseInt(url.substring(url.lastIndexOf("&page=")+6));
				nextUrl=url.substring(0, url.lastIndexOf("&page="))+"&page="+Integer.toString((nowP+1));
			}else{
				nextUrl=nextPageElement.get(0).absUrl("href");
			}
			System.out.println("正在获取网站：【"+url+"】的图片信息，共计：【"+imgNameElement.size()+"】组视频！！");
			if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YinYueTai.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).attr("data-video-id"))){
						tvModel[a].setTvName(elss.attr("title"));
						tvModel[a].setTvImgUrl(elss.absUrl("src"));
						tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
						tvModel[a].setTypeName(contexts.get("title"));
						tvModel[a].setPlatformName(contexts.get("title"));
						tvModel[a].setAddTime(addTimeElement.get(a).text());
						tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
						tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
						tvModel[a].setTvUrl(idElement.get(a).attr("data-video-id"));
						tvModel[a].setContext(contextElement.get(a).text());
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("title")+"】url：【"+idElement.get(a).attr("data-video-id")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
					
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.AiQiYi.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).attr("href"))){
						tvModel[a].setTvName(elss.attr("title"));
						tvModel[a].setTvImgUrl(elss.absUrl("src"));
						tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
						tvModel[a].setTypeName(typeNameElement.text());
						tvModel[a].setPlatformName(contexts.get("title"));
						tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
						tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
						tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
						tvModel[a].setTvUrl(idElement.get(a).attr("href"));
						tvModel[a].setContext(elss.attr("title") +"--"+(starsElement.size()<=a ? "未知":starsElement.get(a).text()));
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("title")+"】url：【"+idElement.get(a).attr("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
					
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YouKu.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.attr("title"));
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(elss.attr("title") +"--"+(starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("title")+"】url：【"+elss.absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.TengXun.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.text());
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("r-lazyload"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(elss.text() +"--"+ (starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.text()+"】url：【"+elss.absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.MangGuoTV.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.text());
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(elss.text() +"--"+ (starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.text()+"】url：【"+elss.absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YYShenQu.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(elss.attr("alt"));
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"--"+(starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("alt")+"】url：【"+idElement.get(a).absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YYShenQuType.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(elss.attr("alt"));
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"--"+(starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("alt")+"】url：【"+idElement.get(a).absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.SouHu.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(idElement.get(a).text());
					tvModel[a].setTvImgUrl(doc.select(".movie-img img").get(a).absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(idElement.get(a).text()+"--"+(starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+idElement.get(a).text()+"】url：【"+elss.absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.JiuJiuRe.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(elss.attr("alt"));
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(addTimeElement.get(a).text());
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"已播放："+(starsElement.size()<=a ? "未知":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("alt")+"】url：【"+idElement.get(a).absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.Avi51.getUrl()){
				for(Element elss: imgNameElement){
					tvModel[a] = new TvModel();
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.text());
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("src"));
					tvModel[a].setTagsName(elss.text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(addTimeElement.get(a).text());
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "未知": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(starsElement.size()<=a ? "未知":starsElement.get(a).text());
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.text()+"】url：【"+elss.absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.Porn91.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					String resu = addTimeElement.get(a).text();
					tvModel[a].setTvName(elss.attr("title"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(resu.substring(resu.indexOf("作者:"), resu.indexOf("查看:")));
					tvModel[a].setTypeName(contexts.get("title"));
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(resu.substring(resu.indexOf("添加时间:"), resu.indexOf("作者:")));
					tvModel[a].setDateLong(resu.substring(resu.indexOf("时长:"), resu.indexOf("添加时间:")));
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("title")+"--"+resu.substring(resu.indexOf("作者:"), resu.indexOf("查看:")));
					tvModel[a].setNetxUrl(nextUrl);
					
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("title")+"】url：【"+idElement.get(a).absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.LianLianYingShi.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("正在获取网站：【"+url+"】的第【"+(a+1)+"/"+imgNameElement.size()+"】组的视频信息。");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("name"));
					tvModel[a].setTagsName(elss.attr("alt")+"--"+typeNameElement.text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.get(a+(a-1)+1).text()+"--"+timeLongElement.get(a+(a+1)).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"--"+typeNameElement.text());
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("已有重复数据：name:【"+elss.attr("alt")+"】url：【"+idElement.get(a).absUrl("href")+"】");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}
			
			System.out.println("下一页："+nextUrl);
			}
				
			return tvModel;
		} catch (Exception e) {
			System.err.println(url + ",尝试了【" +(i+1)+"/" +maxTryTimes + "】次依然获取失败了！");
			}
		}
		throw new RuntimeException(url + ",尝试了" + maxTryTimes + "次依然获取失败了！");
	}
	
}
