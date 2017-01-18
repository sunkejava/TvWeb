package com.tv.start;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tv.util.StringUtil;
import com.tv.impl.getAllWebSiteUrl;

public class Start {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginTime = dfs.parse(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
		getAllWebSiteUrl getAllWebSiteUrl = new getAllWebSiteUrl();
		getAllWebSiteUrl.process();
		Date endTime = dfs.parse(StringUtil.getNowTime("yyyy-MM-dd HH:mm:ss"));
		long between=(endTime.getTime()-beginTime.getTime())/1000;//除以1000是为了转换成秒   
		long day1=between/(24*3600);   
		long hour1=between%(24*3600)/3600;   
		long minute1=between%3600/60;   
		long second1=between%60/60;   
		System.err.println("本次采集共计耗时："+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");
	}
}
