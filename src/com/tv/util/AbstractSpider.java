package com.tv.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.tv.enums.TvSiteEnum;


public abstract class AbstractSpider {
	/**
	 * 通过一个url获取该网页的内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	protected String crawl(String url) throws Exception {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))) {
			String result = EntityUtils.toString(httpResponse.getEntity(),NovelSpiderUtil.getContext(TvSiteEnum.getEnumByUrl(url)).get("charset"));
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
