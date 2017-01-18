package com.tv.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.tv.enums.TvSiteEnum;
import com.tv.interfaces.Processor;
import com.tv.model.TvModel;
import com.tv.util.DbUtil;

public class getAllWebSiteUrl implements Processor {
		private static final Map<String,String> TASKS = new TreeMap<>();
		static{
			TASKS.put("最多播放", "http://mv.yinyuetai.com/all?pageType=page&sort=totalViews&tab=allmv&parenttab=mv");
			TASKS.put("最多推荐", "http://mv.yinyuetai.com/all?pageType=page&sort=totalRecommends&tab=allmv&parenttab=mv");
			TASKS.put("最多收藏", "http://mv.yinyuetai.com/all?pageType=page&sort=totalFavorites&tab=allmv&parenttab=mv");
			TASKS.put("最新发布", "http://mv.yinyuetai.com/all?pageType=page&sort=pubdate&tab=allmv&parenttab=mv");
			TASKS.put("爱奇艺电影", "http://list.iqiyi.com/www/1/----------2---11-1-1-iqiyi--.html");
			TASKS.put("爱奇艺电视剧", "http://list.iqiyi.com/www/2/----------------iqiyi--.html");
			TASKS.put("爱奇艺综艺", "http://list.iqiyi.com/www/6/----------------iqiyi--.html");
			TASKS.put("爱奇艺动漫", "http://list.iqiyi.com/www/4/----------------iqiyi--.html");
			TASKS.put("爱奇艺记录片", "http://list.iqiyi.com/www/3/----------------iqiyi--.html");
			TASKS.put("爱奇艺网络电影", "http://list.iqiyi.com/www/16/----------------iqiyi--.html");
			TASKS.put("爱奇艺日韩音乐", "http://list.iqiyi.com/www/5/218-------------11-1-2-iqiyi--.html");
			TASKS.put("爱奇艺脱口秀", "http://list.iqiyi.com/www/31/----------------iqiyi--.html");
			TASKS.put("优酷电影", "http://list.youku.com/category/show/c_96_pt_2_s_6_d_1_p_1.html?spm=a2h1n.8251845.0.0");
			TASKS.put("优酷电视剧", "http://list.youku.com/category/show/c_97.html?spm=a2h1n.8251846.filterPanel.5~1~3!2~A");
			TASKS.put("优酷综艺", "http://list.youku.com/category/show/c_85.html?spm=a2h1n.8251845.filterPanel.5~1~3!4~A");
			TASKS.put("优酷动漫", "http://list.youku.com/category/show/c_100.html?spm=a2h1n.8251845.filterPanel.5~1~3!5~A");
			TASKS.put("优酷音乐", "http://list.youku.com/category/show/c_95.html?spm=a2h1n.8251845.filterPanel.5~1~3!6~A");
			TASKS.put("腾讯电影", "http://v.qq.com/x/movielist/?pay=100002&offset=0");
			TASKS.put("腾讯电视剧", "http://v.qq.com/x/teleplaylist/?sort=4&ipay=868&offset=0");
			TASKS.put("腾讯动漫", "http://v.qq.com/x/cartoonlist/?sort=4&offset=0");
			TASKS.put("芒果电影", "http://list.mgtv.com/3/--------0-2-1-0--.html");
			TASKS.put("芒果综艺", "http://list.mgtv.com/1/-------------.html");
			TASKS.put("芒果电视剧", "http://list.mgtv.com/2/-------------.html");
			TASKS.put("芒果动漫", "http://list.mgtv.com/50/-------------.html");
			TASKS.put("神曲风云榜-日榜", "http://shenqu.yy.com/hot/daily.html");
			TASKS.put("神曲风云榜-月榜", "http://shenqu.yy.com/hot/monthly.html");
			TASKS.put("YY另类", "http://shenqu.yy.com/clist/t10029.html");
			TASKS.put("YY动听", "http://shenqu.yy.com/clist/t10025.html");
			TASKS.put("YY说唱", "http://shenqu.yy.com/clist/t10026.html");
			TASKS.put("YY乐器演奏", "http://shenqu.yy.com/clist/t10028.html");
			TASKS.put("YY热舞", "http://shenqu.yy.com/clist/t10027.html");
			TASKS.put("搜狐片库", "http://film.sohu.com/list_0_0_0_0_0_1_60.html");
			TASKS.put("SM性虐", "http://www.99ff1.com/categories/sm/");
			TASKS.put("一本道", "http://www.99ff1.com/categories/d0df014883946b42d905f96390f84892/");
			TASKS.put("三级片", "http://www.99ff1.com/categories/f29f9403bdd60bc69cb48db835fea297/");
			TASKS.put("东京热", "http://www.99ff1.com/categories/5a4e793520c46cbaee2b67760e5aea28/");
			TASKS.put("中文字幕", "http://www.99ff1.com/categories/093dcb72562c17853642cc077829020a/");
			TASKS.put("制服丝袜", "http://www.99ff1.com/categories/0df1ef331cf742a67f694a8a4ca81ed8/");
			TASKS.put("加勒比", "http://www.99ff1.com/categories/2e42ff66f321b5bda55e8005ed5a002c/");
			TASKS.put("口爆颜射", "http://www.99ff1.com/categories/a785acbbfbd0023ac38026cff52b2098/");
			TASKS.put("国产自拍", "http://www.99ff1.com/categories/451bf6aed63bf5d8f04a13fb2448d52d/");
			TASKS.put("小格式综合", "http://www.99ff1.com/categories/5d915fafb4889f0b225f2b80cc975332/");
			TASKS.put("成人动漫", "http://www.99ff1.com/categories/f8789a0bf37c07c754c8e6fe27daf5f6/");
			TASKS.put("日本无码", "http://www.99ff1.com/categories/300353f4488f57f8718f8f7e246c749b/");
			TASKS.put("日本有码", "http://www.99ff1.com/categories/2f3d48fda20378cbdaaa099069a4af1e/");
			TASKS.put("李宗瑞全集", "http://www.99ff1.com/categories/b8e26987b0bae1d56a00de631e4d1a68/");
			TASKS.put("欧美", "http://www.99ff1.com/categories/ffc7a71b8aae8ddd42031cea42f5bb7c/");
			TASKS.put("潮吹", "http://www.99ff1.com/categories/4bbb84c5513b8b7ff3bf3233f6b6bae9/");
			TASKS.put("肛交", "http://www.99ff1.com/categories/0c3d3255db00e2d3b27db08661096b23/");
			TASKS.put("韩国女主播", "http://www.99ff1.com/categories/281b22d3c2f5255ed57e7a09bee253f1/");
			TASKS.put("韩国综合", "http://www.99ff1.com/categories/34f2b06d023bb081554c7378c9b2de44/");
			TASKS.put("高清", "http://www.99ff1.com/categories/cb186ba8ba160b86eb97025b94353cac/");
			//TASKS.put("91Porn", "http://email.91dizhi.at.gmail.com.7h1.space/v.php?next=watch&page=1");
			TASKS.put("Beautyleg","http://www.2ta.tv/b");
			TASKS.put("3AGirL","http://www.2ta.tv/3a");
			TASKS.put("4K-STAR","http://www.2ta.tv/4k");
			TASKS.put("RQ-STAR","http://www.2ta.tv/rq");
			TASKS.put("经典写真","http://www.2ta.tv/m");
			TASKS.put("Rosimm","http://www.2ta.tv/rs");
			TASKS.put("Siyamm","http://www.2ta.tv/sy");
			TASKS.put("Ru1mm","http://www.2ta.tv/ru");
			TASKS.put("Showgirl","http://www.2ta.tv/s");
			TASKS.put("Pantyhose","http://www.2ta.tv/ny");
			TASKS.put("丽柜Ligui","http://www.2ta.tv/lg");
			TASKS.put("细高跟","http://www.2ta.tv/xi");
			TASKS.put("微拍福利","http://www.2ta.tv/p");
			TASKS.put("学院派私拍","http://www.2ta.tv/xy");
			TASKS.put("性感车模","http://www.2ta.tv/c");
			TASKS.put("PANS写真","http://www.2ta.tv/ps");
			TASKS.put("动感小站","http://www.2ta.tv/q");
			TASKS.put("锦尚天舞","http://www.2ta.tv/qw");
			TASKS.put("国产私拍","http://www.2ta.tv/a");
			TASKS.put("国产私拍II","http://www.2ta.tv/2a");
			TASKS.put("韩国饭拍","http://www.2ta.tv/f");
			TASKS.put("韩国饭拍II","http://www.2ta.tv/2f");
			TASKS.put("韩国饭拍III","http://www.2ta.tv/3f");
			TASKS.put("韩国MV","http://www.2ta.tv/k");
			TASKS.put("韩国女主播","http://www.2ta.tv/zb");
			TASKS.put("街拍美女","http://www.2ta.tv/j");
			TASKS.put("街拍美女II","http://www.2ta.tv/2j");
			TASKS.put("街拍美女III","http://www.2ta.tv/3j");
			TASKS.put("街拍美女IV","http://www.2ta.tv/4j");
			TASKS.put("街拍美女V","http://www.2ta.tv/5j");
			TASKS.put("爱丝AISS","http://www.2ta.tv/as");
			TASKS.put("推女郎","http://www.2ta.tv/tg");
			TASKS.put("BL时尚写真","http://www.2ta.tv/bn");
			TASKS.put("瑜伽美女","http://www.2ta.tv/yg");
			TASKS.put("秀人写真","http://www.2ta.tv/xr");
			TASKS.put("Ru1mm-vip","http://www.2ta.tv/rv");
			TASKS.put("Allure Girls","http://www.2ta.tv/au");
			TASKS.put("中高艺","http://www.2ta.tv/z");
			TASKS.put("芬妮玉足","http://www.2ta.tv/fn");
			TASKS.put("Ugirls尤果","http://www.2ta.tv/ug");
			TASKS.put("赤足者","http://www.2ta.tv/cz");
			TASKS.put("会员I区","http://www.2ta.tv/vc/1v");
			TASKS.put("会员II区","http://www.2ta.tv/vc/2v");
			TASKS.put("会员III区","http://www.2ta.tv/vc/3v");
			TASKS.put("会员IV区","http://www.2ta.tv/vc/4v");
			TASKS.put("会员V区","http://www.2ta.tv/vc/5v");
			TASKS.put("会员VI区","http://www.2ta.tv/vc/6v");
			TASKS.put("会员VII区","http://www.2ta.tv/vc/7v");
			TASKS.put("会员VIII区","http://www.2ta.tv/vc/8v");
			TASKS.put("会员IX区","http://www.2ta.tv/vc/9v");
			//TASKS.put("乐视电视剧", "http://list.le.com/apin/chandata.json?c=2&d=1&md=&o=20&p=1&s=1");
			//TASKS.put("乐视电影", "http://list.le.com/apin/chandata.json?c=1&d=1&md=&o=9&p=1&s=1");
			
		}
		@Override
		public void process() {
			for(Entry<String,String> entry :TASKS.entrySet()){
				TvModelImpl tvModelImpl = new TvModelImpl();
				LetvModelImpl LetvModelImpl = new LetvModelImpl();
				String url = entry.getValue();
				TvModel[] tvmodel= {};
				if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.LeShi.getUrl()){
					tvmodel=LetvModelImpl.getsTvList(url);
					String nextUrl="";
					for(TvModel tv:tvmodel){
						if(tv.getTvName() == null){
							nextUrl = tv.getNetxUrl();
						}else{
							DbUtil.insertTv(tv);
							nextUrl = tv.getNetxUrl();
						}
					}
					if(nextUrl==""){
						
					}else{
						getallurls(nextUrl);
					}
				}else{
					tvmodel=tvModelImpl.getsTvList(url,10);
					String nextUrl="";
					for(TvModel tv:tvmodel){
						if(tv.getTvName() == null){
							nextUrl = tv.getNetxUrl();
						}else{
							DbUtil.insertTv(tv);
							nextUrl = tv.getNetxUrl();
						}
						
					}
					if(nextUrl==""){
						
					}else{
						getallurls(nextUrl);
					}
				}
				System.out.println(url);
			}
		}
		
		public void getallurls(String nextsUrl){
			TvModelImpl tvModelImpl = new TvModelImpl();
			LetvModelImpl LetvModelImpl = new LetvModelImpl();
			String url = nextsUrl;
			TvModel[] tvmodel= {};
			if(TvSiteEnum.getEnumUrl(url)==TvSiteEnum.LeShi.getUrl()){
				tvmodel=LetvModelImpl.getsTvList(url);
				String nextUrl="";
				for(TvModel tv:tvmodel){
					if(tv.getTvName() == null){
						nextUrl = tv.getNetxUrl();
					}else{
						DbUtil.insertTv(tv);
						nextUrl = tv.getNetxUrl();
					}
				}
				if(nextUrl==""){
					
				}else{
					getallurls(nextUrl);
				}
			}else{
				tvmodel=tvModelImpl.getsTvList(url,10);
				String nextUrl="";
				for(TvModel tv:tvmodel){
					if(tv.getTvName() == null){
						nextUrl = tv.getNetxUrl();
					}else{
						DbUtil.insertTv(tv);
						nextUrl = tv.getNetxUrl();
					}
				}
				if(nextUrl==""){
					
				}else{
					getallurls(nextUrl);
				}
			}
		}
}
