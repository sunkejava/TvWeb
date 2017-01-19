package com.tv.test;


import org.junit.Test;

import com.tv.impl.GetTvUrlImpl;
import com.tv.impl.LetvModelImpl;
import com.tv.impl.TvModelImpl;
import com.tv.impl.getAllWebSiteUrl;
import com.tv.model.TvModel;

public class TestCase{
	@Test
	public void getYinYueTaiList(){
		TvModelImpl tvmodelImpl = new TvModelImpl();
		TvModel[] tvModel = tvmodelImpl.getsTvList("http://mv.yinyuetai.com/all?pageType=page&sort=totalViews&tab=allmv&parenttab=mv", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getIqiYiTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://list.iqiyi.com/www/1/2-------------11-1-1-iqiyi--.html", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getYouKuTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://list.youku.com/category/show/c_96_s_1_d_1_p_1.html?spm=a2h1n.8251845.0.0", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getTengXunTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://v.qq.com/x/teleplaylist/?sort=4&ipay=868&offset=0", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getMangGuoTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://list.mgtv.com/3/--------0-2-1-0--.html", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getShenquBangDanTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://shenqu.yy.com/hot/daily.html", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getShenquTypeTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://shenqu.yy.com/clist/t10029.html", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getSoHuTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://film.sohu.com/list_0_0_0_0_0_1_60.html", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getJiuJiuReTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://www.99ff1.com/categories/cb186ba8ba160b86eb97025b94353cac/", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getAv51Tv(){
		TvModelImpl tvModelImpl = new TvModelImpl();		
		TvModel[] tvModel =tvModelImpl.getsTvList("http://av51.biz/kr/", 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void get91PornTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();
		String url = "http://email.91dizhi.at.gmail.com.7h1.space/v.php?next=watch&page=7";
		TvModel[] tvModel =tvModelImpl.getsTvList(url, 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getLianlianTv(){
		TvModelImpl tvModelImpl = new TvModelImpl();
		String url = "http://www.2ta.tv/3a";
		TvModel[] tvModel =tvModelImpl.getsTvList(url, 10);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	@Test
	public void getLetvModelTv(){
		LetvModelImpl LetvModelImpl = new LetvModelImpl();
		String url = "http://list.le.com/apin/chandata.json?c=1&d=1&md=&o=9&p=1&s=1";
		TvModel[] tvModel =LetvModelImpl.getsTvList(url);
		for(TvModel tvs : tvModel){
			System.out.println(tvs);
		}
	}
	
	//http://mv.yinyuetai.com/all?sort=totalViews
	//获取所有信息
	@Test
	public void getAllUrl(){
		getAllWebSiteUrl getAllWebSiteUrl = new getAllWebSiteUrl();
		getAllWebSiteUrl.process();
		              //http://v.qq.com/x/cartoonlist/?sort=4&offset=0
		
	}
	
	@Test
	public void getTvUrlTest(){
		GetTvUrlImpl gettvurlImpl = new GetTvUrlImpl(); 
		String dizhi = gettvurlImpl.getTvUrlImgUrl("http://shenqu.yy.com/play/id_1103653387979597894.html");
		System.out.println(dizhi);
	}
}
