package com.tv.util;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


public class StringUtil {
	/**
	 * �ж��ַ����Ǹ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * �ж��ַ����Ƿ��ǿ�
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str != null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ȡ�ı����
	 * @param str
	 * @param leftstr
	 * @return
	 */
	public static String leftString(String str,String leftstr ){
		String lstr = null;
		//��������ı��ĵ�һ���ַ�λ��
		int endInt = str.indexOf(leftstr);
		lstr = str.substring(0, endInt);
		return lstr;
	}
	/**
	 * ȡ�ı��ұ�
	 * @param str
	 * @param rightstr
	 * @return
	 */
	public static String rightString(String str,String rightstr ){
		String rstr = null;
		//�����ұ��ı������һ���ַ�λ��
		int beginInt = str.lastIndexOf(rightstr)+rightstr.length();
		rstr = str.substring(beginInt);
		return rstr;
	}
	/**
	 * ȡ�ı��м�
	 * @param str
	 * @param leftstr
	 * @param rightstr
	 * @return
	 */
	public static String betweenSting(String str,String leftstr,String rightstr ){
		String bstr = null;
		//��������ı������һ���ַ�λ��
		int beginInt = str.lastIndexOf(leftstr)+leftstr.length();
		//�����ұ��ı��ص�һ��λ��
		int endInt = str.indexOf(rightstr,beginInt);
		//System.out.println("beginInt:"+beginInt+"---EndInt:"+endInt);
		bstr = str.substring(beginInt,endInt);
		return bstr;
	}
	
	/**
	 * �������ڸ�ʽ��ȡString���͵�����
	 * @param dateFormat ���磺��yyyy-MM-dd��
	 * @return ����ֵΪString  ���磺"2016-12-10"
	 */
	public static String getNowTime(String dateFormat){
		Date date = new Date();
		long time = date.getTime();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String result = format.format(time);
		return result;
	}
	
	/**
	 * ��time�ַ�������ת��Ϊʱ���ַ���
	 * @param time ����:"1465228800000"
	 * @param format   "yyyy-MM-dd"
	 * @return result  "2016-06-07"
	 */
	public static String timetoDate(String time,String format){
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		
		String result = sformat.format(Long.parseLong(time));
		return result;
	}
	
	 public static String toMD5(String plainText) throws NoSuchAlgorithmException {
	     String result; 
	        //����ʵ��ָ��ժҪ�㷨�� MessageDigest ����
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        //ʹ��ָ�����ֽ��������ժҪ��
	        md.update(plainText.getBytes());
	        //ͨ��ִ���������֮������ղ�����ɹ�ϣ���㡣
	        byte b[] = md.digest();
	        //���ɾ����md5���뵽buf����
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	          i = b[offset];
	          if (i < 0)
	            i += 256;
	          if (i < 16)
	            buf.append("0");
	          buf.append(Integer.toHexString(i));
	        }
	        result = buf.toString();
	        //System.out.println("32λ: " + buf.toString());// 32λ�ļ���
	        //System.out.println("16λ: " + buf.toString().substring(8, 24));// 16λ�ļ��ܣ���ʵ����32λ���ܺ�Ľ�ȡ 
	        return result.toUpperCase();
	   }
	 
	 public static final String KEY_SHA = "SHA"; 
	  public static String getResult(String inputStr)
	  {
	    BigInteger sha =null;
	    System.out.println("=======����ǰ������:"+inputStr);
	    byte[] inputData = inputStr.getBytes(); 
	    try {
	       MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA); 
	       messageDigest.update(inputData);
	       sha = new BigInteger(messageDigest.digest()); 
	       System.out.println("SHA���ܺ�:" + sha.toString()); 
	    } catch (Exception e) {e.printStackTrace();}
	    return sha.toString();
	  }
	  public static void main(String args[])
	  {
	    
	    
	   System.out.println("-------"+getContextFromUrl("1v"));
	  }
	    
	    public static String[] concat(String[] a, String[] b) {
	    	   String[] c= new String[a.length+b.length];
	    	   System.arraycopy(a, 0, c, 0, a.length);
	    	   System.arraycopy(b, 0, c, a.length, b.length);
	    	   return c;
	    	}
	    
	    public static String getContextFromUrl(String url){
	    	String p = "";
	    	final Map<String,String> TASKS = new TreeMap<>();
	    	TASKS.put("��ಥ��", "totalViews");
			TASKS.put("����Ƽ�", "totalRecommends");
			TASKS.put("����ղ�", "totalFavorites");
			TASKS.put("���·���", "pubdate");
			TASKS.put("Shenqu", "shenqu");
	    	TASKS.put("�������ư�-�հ�", "daily");
			TASKS.put("�������ư�-�°�", "monthly");
	    	TASKS.put("Beautyleg","http://www.2ta.tv/b");
			TASKS.put("3AGirL","http://www.2ta.tv/3a");
			TASKS.put("4K-STAR","http://www.2ta.tv/4k");
			TASKS.put("RQ-STAR","http://www.2ta.tv/rq");
			TASKS.put("����д��","http://www.2ta.tv/m");
			TASKS.put("Rosimm","http://www.2ta.tv/rs");
			TASKS.put("Siyamm","http://www.2ta.tv/sy");
			TASKS.put("Ru1mm","http://www.2ta.tv/ru");
			TASKS.put("Showgirl","http://www.2ta.tv/s");
			TASKS.put("nylonjp","http://www.2ta.tv/ny");
			TASKS.put("����Ligui","http://www.2ta.tv/lg");
			TASKS.put("ϸ�߸�","http://www.2ta.tv/xi");
			TASKS.put("��Ů����","http://www.2ta.tv/p");
			TASKS.put("ѧԺ��˽��","http://www.2ta.tv/xy");
			TASKS.put("�Ըг�ģ","http://www.2ta.tv/c");
			TASKS.put("PANSд��","http://www.2ta.tv/ps");
			TASKS.put("����Сվ","http://www.2ta.tv/q");
			TASKS.put("��������","http://www.2ta.tv/qw");
			TASKS.put("����˽��","http://www.2ta.tv/a");
			TASKS.put("����˽��II","http://www.2ta.tv/2a");
			TASKS.put("��������","http://www.2ta.tv/f");
			TASKS.put("��������II","http://www.2ta.tv/2f");
			TASKS.put("��������III","http://www.2ta.tv/3f");
			TASKS.put("����MV","http://www.2ta.tv/k");
			TASKS.put("����Ů����","http://www.2ta.tv/zb");
			TASKS.put("������Ů","http://www.2ta.tv/j");
			TASKS.put("������ŮII","http://www.2ta.tv/2j");
			TASKS.put("������ŮIII","http://www.2ta.tv/3j");
			TASKS.put("������ŮIV","http://www.2ta.tv/4j");
			TASKS.put("������ŮV","http://www.2ta.tv/5j");
			TASKS.put("AISS��˿","http://www.2ta.tv/as");
			TASKS.put("��Ů��","http://www.2ta.tv/tg");
			TASKS.put("Beautylegʱ��д��","http://www.2ta.tv/bn");
			TASKS.put("�٤��Ů","http://www.2ta.tv/yg");
			TASKS.put("XiuRen","http://www.2ta.tv/xr");
			TASKS.put("Ru1mm-vip","http://www.2ta.tv/rv");
			TASKS.put("Allure Girls","http://www.2ta.tv/au");
			TASKS.put("�и���","http://www.2ta.tv/z");
			TASKS.put("��������","http://www.2ta.tv/fn");
			TASKS.put("Ugirls�ȹ�","http://www.2ta.tv/ug");
			TASKS.put("������","http://www.2ta.tv/cz");
			TASKS.put("vipc1","http://www.2ta.tv/vc/1v");
			TASKS.put("vipc2","http://www.2ta.tv/vc/2v");
			TASKS.put("vipc3","http://www.2ta.tv/vc/3v");
			TASKS.put("vipc4","http://www.2ta.tv/vc/4v");
			TASKS.put("vipc5","http://www.2ta.tv/vc/5v");
			TASKS.put("vipc6","http://www.2ta.tv/vc/6v");
			TASKS.put("vipc7","http://www.2ta.tv/vc/7v");
			TASKS.put("vipc8","http://www.2ta.tv/vc/8v");
			TASKS.put("vipc9","http://www.2ta.tv/vc/9v");
			String as ="http://www.2ta.tv/".concat(url);
			String asa = "http://www.2ta.tv/vc/".concat(url);
			for(Entry<String,String> entry :TASKS.entrySet()){
				System.out.println(entry.getValue());
				if(entry.getValue().equals(as) || entry.getValue().equals(asa) || entry.getValue().equals(url)){
					p=entry.getKey();
					System.out.println(p);
				}else{
					
				}
				
			}
	    	return p;
	    }
}
