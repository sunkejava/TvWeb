package com.tv.impl;

import com.tv.model.TvModel;
import com.tv.util.AbstractSpider;
import com.tv.util.DbUtil;
import com.tv.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LetvModelImpl extends AbstractSpider{
	
	/**
	 * ͨ��url��ȡ��Ӱ�б�
	 * @param url
	 * @param maxTryTimes ����Դ���
	 * @return
	 */
	public TvModel[] getsTvList(String url) {
		TvModel[] tvModel = {};	
		String nextUrl="";
			try {
				String result = super.crawl(url);
				JSONObject jsn = JSONObject.fromObject(result.toString());
				JSONArray result2 = jsn.getJSONArray("album_list");
				result2 = result2.isEmpty() ? jsn.getJSONArray("video_list") :result2;
				tvModel=new TvModel[result2.size()];
				String nowPage = url.substring(url.indexOf("p=")+2, url.indexOf("&", url.indexOf("p=")));
				nextUrl=url.replace("p="+nowPage, "p="+Integer.toString(Integer.parseInt(nowPage)+1));
				if(result2.size()>0){
				for(int i = 0;i<result2.size();i++ ){
					System.out.println("���ڻ�ȡ��վ����"+url+"���ĵڡ�"+(i+1)+"/"+result2.size()+"�������Ƶ��Ϣ��");
					JSONObject concent = (JSONObject) result2.get(i);
					String name = concent.get("name").toString();
					String tvurl="http://www.le.com/ptv/vplay/"+concent.get("aid").toString()+".html";	
					tvModel[i] = new TvModel();
					if(DbUtil.getresultOftvUrl(tvurl)){
						String typeName=concent.get("categoryName").toString()+":"+concent.get("subCategoryName").toString()+concent.get("areaName").toString();					
						String description=concent.get("description").toString();
						String tags=concent.get("tag").toString();
						String duration=concent.get("duration").toString();
						JSONObject imgs=(JSONObject)concent.get("images");
						String img = imgs.get("600*800").toString();
						img=img.isEmpty() ? imgs.get("300*400").toString() : img;
						String releaseDate=concent.get("releaseDate").toString();
						String subname=concent.get("subname").toString();
						tvModel[i].setTvName(name);
						tvModel[i].setTvUrl(tvurl);
						tvModel[i].setTvImgUrl(img);
						tvModel[i].setTagsName(tags);
						tvModel[i].setPlatformName("����TV");
						tvModel[i].setTypeName(typeName);
						tvModel[i].setAddTime(StringUtil.timetoDate(releaseDate, "yyyy-MM-dd"));
						tvModel[i].setDateLong(duration);
						tvModel[i].setContext(description+subname);
						tvModel[i].setCrawlTime(StringUtil.getNowTime("yyyy-MM-dd"));
						tvModel[i].setNetxUrl(nextUrl);
						DbUtil.insertTv(tvModel[i]);
						}else{
							System.out.println("�����ظ����ݣ�name:��"+name+"��url����"+tvurl+"��");
							tvModel[i].setNetxUrl(nextUrl);
						}
					
					}
				}else{
					nextUrl="";
				}
				System.out.println("��һҳ:"+nextUrl);
				
			} catch (Exception e) {
				System.err.println("���󣡣�");
				throw new RuntimeException(e);
			}
			return tvModel;
	}
}
