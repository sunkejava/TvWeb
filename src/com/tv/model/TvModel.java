package com.tv.model;

public class TvModel {
	
	private String tvName;//视频名称
	private String tvUrl;//视频地址
	private String tvImgUrl;//视频图片地址
	private String tagsName;//标签
	private String typeName;//视频分类
	private String platformName;//视频平台
	private String dateLong;//视频长度
	private String addTime;//视频添加时间
	private String crawlTime;//抓取时间
	private String context;//视频简介
	private String netxUrl;//下一页
	/**
	 * @return the tvName
	 */
	public String getTvName() {
		return tvName;
	}
	/**
	 * @param tvName the tvName to set
	 */
	public void setTvName(String tvName) {
		this.tvName = tvName;
	}
	/**
	 * @return the tvUrl
	 */
	public String getTvUrl() {
		return tvUrl;
	}
	/**
	 * @param tvUrl the tvUrl to set
	 */
	public void setTvUrl(String tvUrl) {
		this.tvUrl = tvUrl;
	}
	/**
	 * @return the tvImgUrl
	 */
	public String getTvImgUrl() {
		return tvImgUrl;
	}
	/**
	 * @param tvImgUrl the tvImgUrl to set
	 */
	public void setTvImgUrl(String tvImgUrl) {
		this.tvImgUrl = tvImgUrl;
	}
	/**
	 * @return the tagsName
	 */
	public String getTagsName() {
		return tagsName;
	}
	/**
	 * @param tagsName the tagsName to set
	 */
	public void setTagsName(String tagsName) {
		this.tagsName = tagsName;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the dateLong
	 */
	public String getDateLong() {
		return dateLong;
	}
	/**
	 * @param dateLong the dateLong to set
	 */
	public void setDateLong(String dateLong) {
		this.dateLong = dateLong;
	}
	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	/**
	 * @return the crawlTime
	 */
	public String getCrawlTime() {
		return crawlTime;
	}
	/**
	 * @param crawlTime the crawlTime to set
	 */
	public void setCrawlTime(String crawlTime) {
		this.crawlTime = crawlTime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TvModel [tvName=" + tvName + ", tvUrl=" + tvUrl + ", tvImgUrl=" + tvImgUrl + ", tagsName=" + tagsName
				+ ", typeName=" + typeName + ", dateLong=" + dateLong + ", addTime=" + addTime + ", crawlTime="
				+ crawlTime + ", context="
						+ context + ", platformName="
								+ platformName + ", netxUrl="
										+ netxUrl + "]";
	}
	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * @return the platformName
	 */
	public String getPlatformName() {
		return platformName;
	}
	/**
	 * @param platformName the platformName to set
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	/**
	 * @return the netxUrl
	 */
	public String getNetxUrl() {
		return netxUrl;
	}
	/**
	 * @param netxUrl the netxUrl to set
	 */
	public void setNetxUrl(String netxUrl) {
		this.netxUrl = netxUrl;
	}
	
	
	
	
	
}
