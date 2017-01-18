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
	 * Ĭ�ϵ�ץȡ���������᳢�� {@link ITaotuSpider#MAX_TRY_TIMES} ������
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected TvModel[] getsTvList(String url) throws Exception {
		return getsTvList(url, ITvSpider.MAX_TRY_TIMES);
	}
	/**
	 * ͨ��url��ȡ��Ӱ�б�
	 * @param url
	 * @param maxTryTimes ����Դ���
	 * @return
	 */
	public TvModel[] getsTvList(String url,Integer maxTryTimes) {
		maxTryTimes = maxTryTimes == null ? ITvSpider.MAX_TRY_TIMES : maxTryTimes;
		for (int i = 0; i < maxTryTimes ; i++) {
			System.err.println("��ʼ��"+(i+1)+"�γ��Ի�ȡ��Ӱ�б���");
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
			if (imgnameSelector == null || idSelector == null || timeLongSelector == null  ) throw new RuntimeException(TvSiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "Ŀǰ��֧�ֻ�ȡͼƬ�б�");
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
			System.out.println("���ڻ�ȡ��վ����"+url+"����ͼƬ��Ϣ�����ƣ���"+imgNameElement.size()+"������Ƶ����");
			if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YinYueTai.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).attr("data-video-id"))){
						tvModel[a].setTvName(elss.attr("title"));
						tvModel[a].setTvImgUrl(elss.absUrl("src"));
						tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
						tvModel[a].setTypeName(contexts.get("title"));
						tvModel[a].setPlatformName(contexts.get("title"));
						tvModel[a].setAddTime(addTimeElement.get(a).text());
						tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
						tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
						tvModel[a].setTvUrl(idElement.get(a).attr("data-video-id"));
						tvModel[a].setContext(contextElement.get(a).text());
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("title")+"��url����"+idElement.get(a).attr("data-video-id")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
					
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.AiQiYi.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).attr("href"))){
						tvModel[a].setTvName(elss.attr("title"));
						tvModel[a].setTvImgUrl(elss.absUrl("src"));
						tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
						tvModel[a].setTypeName(typeNameElement.text());
						tvModel[a].setPlatformName(contexts.get("title"));
						tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
						tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
						tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
						tvModel[a].setTvUrl(idElement.get(a).attr("href"));
						tvModel[a].setContext(elss.attr("title") +"--"+(starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("title")+"��url����"+idElement.get(a).attr("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
					
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YouKu.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.attr("title"));
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(elss.attr("title") +"--"+(starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("title")+"��url����"+elss.absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.TengXun.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.text());
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("r-lazyload"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(elss.text() +"--"+ (starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.text()+"��url����"+elss.absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.MangGuoTV.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.text());
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(elss.text() +"--"+ (starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.text()+"��url����"+elss.absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YYShenQu.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(elss.attr("alt"));
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"--"+(starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("alt")+"��url����"+idElement.get(a).absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.YYShenQuType.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(elss.attr("alt"));
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"--"+(starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("alt")+"��url����"+idElement.get(a).absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.SouHu.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(idElement.get(a).text());
					tvModel[a].setTvImgUrl(doc.select(".movie-img img").get(a).absUrl("src"));
					tvModel[a].setTagsName(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(idElement.get(a).text()+"--"+(starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+idElement.get(a).text()+"��url����"+elss.absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.JiuJiuRe.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					tvModel[a].setTvName(elss.attr("alt"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(elss.attr("alt"));
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(addTimeElement.get(a).text());
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("alt")+"�Ѳ��ţ�"+(starsElement.size()<=a ? "δ֪":starsElement.get(a).text()));
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("alt")+"��url����"+idElement.get(a).absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.Avi51.getUrl()){
				for(Element elss: imgNameElement){
					tvModel[a] = new TvModel();
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					if(DbUtil.getresultOftvUrl(elss.absUrl("href"))){
					tvModel[a].setTvName(elss.text());
					tvModel[a].setTvImgUrl(idElement.get(a).absUrl("src"));
					tvModel[a].setTagsName(elss.text());
					tvModel[a].setTypeName(typeNameElement.text());
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(addTimeElement.get(a).text());
					tvModel[a].setDateLong(timeLongElement.size() <= a ? "δ֪": timeLongElement.get(a).text());
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(elss.absUrl("href"));
					tvModel[a].setContext(starsElement.size()<=a ? "δ֪":starsElement.get(a).text());
					tvModel[a].setNetxUrl(nextUrl);
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.text()+"��url����"+elss.absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.Porn91.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
					tvModel[a] = new TvModel();
					if(DbUtil.getresultOftvUrl(idElement.get(a).absUrl("href"))){
					String resu = addTimeElement.get(a).text();
					tvModel[a].setTvName(elss.attr("title"));
					tvModel[a].setTvImgUrl(elss.absUrl("src"));
					tvModel[a].setTagsName(resu.substring(resu.indexOf("����:"), resu.indexOf("�鿴:")));
					tvModel[a].setTypeName(contexts.get("title"));
					tvModel[a].setPlatformName(contexts.get("title"));
					tvModel[a].setAddTime(resu.substring(resu.indexOf("���ʱ��:"), resu.indexOf("����:")));
					tvModel[a].setDateLong(resu.substring(resu.indexOf("ʱ��:"), resu.indexOf("���ʱ��:")));
					tvModel[a].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
					tvModel[a].setTvUrl(idElement.get(a).absUrl("href"));
					tvModel[a].setContext(elss.attr("title")+"--"+resu.substring(resu.indexOf("����:"), resu.indexOf("�鿴:")));
					tvModel[a].setNetxUrl(nextUrl);
					
					a++;
					}else{
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("title")+"��url����"+idElement.get(a).absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}else if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.LianLianYingShi.getUrl()){
				for(Element elss: imgNameElement){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(a+1)+"/"+imgNameElement.size()+"�������Ƶ��Ϣ��");
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
						System.out.println("�����ظ����ݣ�name:��"+elss.attr("alt")+"��url����"+idElement.get(a).absUrl("href")+"��");
						tvModel[a].setNetxUrl(nextUrl);
						a++;
					}
				}
			}
			
			System.out.println("��һҳ��"+nextUrl);
			}
				
			return tvModel;
		} catch (Exception e) {
			System.err.println(url + ",�����ˡ�" +(i+1)+"/" +maxTryTimes + "������Ȼ��ȡʧ���ˣ�");
			}
		}
		throw new RuntimeException(url + ",������" + maxTryTimes + "����Ȼ��ȡʧ���ˣ�");
	}
	
}
