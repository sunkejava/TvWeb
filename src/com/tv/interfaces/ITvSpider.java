package com.tv.interfaces;


/**
 * ��ȡĳ��վ�����Ƶ����
 * @author Administrator
 *
 */
public interface ITvSpider {
	/**ץȡҳ��ʧ�����Դ���*/
	public static final int MAX_TRY_TIMES = 3; 
	/**
	 * ͨ��һ��url��ȡ������ͼ��ʵ��
	 * @param url
	 * @param maxTryTimes ����ʧ�����ԵĴ���
	 * @return
	 */
	public String getAllUrls(String url,Integer maxTryTimes);
	
}
