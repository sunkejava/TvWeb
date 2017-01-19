package com.tv.enums;

/**
 * 已经被支持的网站枚举
 * @author Administrator
 *
 */
public enum TvSiteEnum {
	YYShenQu(1,"shenqu.yy.com"),
	YinYueTai(2,"yinyuetai.com"),
	YouKu(3,"youku.com"),
	TengXun(4,"v.qq.com"),
	AiQiYi(5,"iqiyi.com"),
	TuDou(6,"tudou.com"),
	SouHu(7,"sohu.com"),
	LeShi(8,"le.com"),
	MangGuoTV(9,"mgtv.com"),
	JiuJiuRe(10,"99ff1.com"),
	Avi51(11,"av51.biz"),
	LianLianYingShi(12,"2ta.tv"),
	YYShenQuType(13,"shenqu.yy.com/clist"),
	Porn91(14,"91dizhi.at.gmail.com");
	
	private int id;
	private String url;
	
	private TvSiteEnum(int id,String url){
		this.id = id;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static TvSiteEnum getEnumById(int id){
		switch(id){
		case 1: return YYShenQu;
		case 2: return YinYueTai;
		case 3: return YouKu;
		case 4: return TengXun;
		case 5: return AiQiYi;
		case 6: return TuDou;
		case 7: return SouHu;
		case 8: return LeShi;
		case 9: return MangGuoTV;
		case 10: return JiuJiuRe;
		case 11: return Avi51;
		case 12: return LianLianYingShi;
		case 13: return YYShenQuType;
		case 14: return Porn91;
		default: throw new RuntimeException("没有ID为"+id+"的网站！！");
		}
	}
	public static TvSiteEnum getEnumByUrl(String url){
		for(TvSiteEnum taotuSiteEnum: values()){
			if(url.contains(taotuSiteEnum.url)){
				return taotuSiteEnum;
			}
		}
		throw new RuntimeException("url为"+url+"的网站尚未支持！！！");
	}
	
	public static String getEnumUrl(String url){
		String result = "";
		if(url.contains(YYShenQu.getUrl())){
			result = YYShenQu.getUrl();
		}else if(url.contains(YYShenQuType.getUrl())){
			result = YYShenQuType.getUrl();
		}else if(url.contains(Porn91.getUrl())){
			result = Porn91.getUrl();
		}else if(url.contains(YinYueTai.getUrl())){
			result = YinYueTai.getUrl();
		}else if(url.contains(YouKu.getUrl())){
			result = YouKu.getUrl();
		}else if(url.contains(TengXun.getUrl())){
			result = TengXun.getUrl();
		}else if(url.contains(AiQiYi.getUrl())){
			result = AiQiYi.getUrl();
		}else if(url.contains(TuDou.getUrl())){
			result = TuDou.getUrl();
		}else if(url.contains(SouHu.getUrl())){
			result = SouHu.getUrl();
		}else if(url.contains(LeShi.getUrl())){
			result = LeShi.getUrl();
		}else if(url.contains(MangGuoTV.getUrl())){
			result = MangGuoTV.getUrl();
		}else if(url.contains(JiuJiuRe.getUrl())){
			result = JiuJiuRe.getUrl();
		}else if(url.contains(Avi51.getUrl())){
			result = Avi51.getUrl();
		}else if(url.contains(LianLianYingShi.getUrl())){
			result = LianLianYingShi.getUrl();
		}else{
			result = "网站：【"+url+"】尚未支持解析！！";
		}
		return result;
	}
}
