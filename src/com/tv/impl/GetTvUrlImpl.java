package com.tv.impl;

import com.tv.enums.TvSiteEnum;
import com.tv.interfaces.ITvSpider;
import com.tv.util.AbstractSpider;
import com.tv.util.StringUtil;

public class GetTvUrlImpl extends AbstractSpider {
	
	/**
	 * Ĭ�ϵ�ץȡ���������᳢�� {@link ITvSpider#MAX_TRY_TIMES} ������
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String getTvUrlImgUrl(String url) throws Exception {
		return getTvUrlImgUrl(url, ITvSpider.MAX_TRY_TIMES);
	}
	
	/**
	 * ץȡ��վ��Ƶ��ַ
	 * @param urls
	 * @param maxTryTimes
	 * @return
	 */
	public String getTvUrlImgUrl(String urls, Integer maxTryTimes) {
		maxTryTimes = maxTryTimes == null ? ITvSpider.MAX_TRY_TIMES : maxTryTimes;
		String tvImgUrl = "";
		for (int i = 0; i < maxTryTimes; i++) {
			System.err.println("��ʼ��" + (i + 1) + "�γ��Ի�ȡ��Ӱ�б���");
			try {
				if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.JiuJiuRe.getUrl()) {
					System.out.println("��ȡ�þ�����Ƶ��ַ��" + urls);
					try {
						String resultcodes = super.crawl(urls);
						String tvUrl = StringUtil.betweenSting(resultcodes, "video_url", ",");
						String imgUrl = StringUtil.betweenSting(resultcodes, "preview_url", ",");
						tvImgUrl = tvUrl + "|" + imgUrl;
						tvImgUrl = tvImgUrl.replace(": '", "").replace("/'", "").replace("'", "");
					} catch (Exception e) {
						throw new RuntimeException(urls + "��ҳ��ȡʧ�ܣ���" + e);
					}
				} else if (TvSiteEnum.getEnumUrl(urls) == TvSiteEnum.YYShenQu.getUrl()) {
					System.out.println("��ȡYY������Ƶ��ַ��" + urls);
					try {
						String resultcodes = super.crawl(urls);
						String tvUrl = StringUtil.betweenSting(resultcodes, "worksUrl:\"", "\",");
						String imgUrl = StringUtil.betweenSting(resultcodes, "snapshot:\"", "\",");
						tvImgUrl = tvUrl + "|" + imgUrl;
					} catch (Exception e) {
						throw new RuntimeException(urls + "��ҳ��ȡʧ�ܣ���" + e);
					}
				}
				return tvImgUrl;
			} catch (Exception e) {
				System.err.println(urls + ",�����ˡ�" + (i + 1) + "/" + maxTryTimes + "������Ȼ��ȡʧ���ˣ�");
			}
		}

		throw new RuntimeException(urls + ",������" + maxTryTimes + "����Ȼ��ȡʧ���ˣ�");
	}
}
