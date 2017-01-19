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
	 * 判断字符串是负为空
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
	 * 判断字符串是否不是空
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
	 * 取文本左边
	 * @param str
	 * @param leftstr
	 * @return
	 */
	public static String leftString(String str,String leftstr ){
		String lstr = null;
		//返回左边文本的第一个字符位置
		int endInt = str.indexOf(leftstr);
		lstr = str.substring(0, endInt);
		return lstr;
	}
	/**
	 * 取文本右边
	 * @param str
	 * @param rightstr
	 * @return
	 */
	public static String rightString(String str,String rightstr ){
		String rstr = null;
		//返回右边文本的最后一个字符位置
		int beginInt = str.lastIndexOf(rightstr)+rightstr.length();
		rstr = str.substring(beginInt);
		return rstr;
	}
	/**
	 * 取文本中间
	 * @param str
	 * @param leftstr
	 * @param rightstr
	 * @return
	 */
	public static String betweenSting(String str,String leftstr,String rightstr ){
		String bstr = null;
		//返回左边文本的最后一个字符位置
		int beginInt = str.lastIndexOf(leftstr)+leftstr.length();
		//返回右边文本地第一个位置
		int endInt = str.indexOf(rightstr,beginInt);
		//System.out.println("beginInt:"+beginInt+"---EndInt:"+endInt);
		bstr = str.substring(beginInt,endInt);
		return bstr;
	}
	
	/**
	 * 传入日期格式获取String类型的日期
	 * @param dateFormat 例如：“yyyy-MM-dd”
	 * @return 返回值为String  例如："2016-12-10"
	 */
	public static String getNowTime(String dateFormat){
		Date date = new Date();
		long time = date.getTime();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String result = format.format(time);
		return result;
	}
	
	/**
	 * 将time字符串数据转换为时间字符串
	 * @param time 例如:"1465228800000"
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
	        //生成实现指定摘要算法的 MessageDigest 对象。
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        //使用指定的字节数组更新摘要。
	        md.update(plainText.getBytes());
	        //通过执行诸如填充之类的最终操作完成哈希计算。
	        byte b[] = md.digest();
	        //生成具体的md5密码到buf数组
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
	        //System.out.println("32位: " + buf.toString());// 32位的加密
	        //System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取 
	        return result.toUpperCase();
	   }
	 
	 public static final String KEY_SHA = "SHA"; 
	  public static String getResult(String inputStr)
	  {
	    BigInteger sha =null;
	    System.out.println("=======加密前的数据:"+inputStr);
	    byte[] inputData = inputStr.getBytes(); 
	    try {
	       MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA); 
	       messageDigest.update(inputData);
	       sha = new BigInteger(messageDigest.digest()); 
	       System.out.println("SHA加密后:" + sha.toString()); 
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
	    	TASKS.put("最多播放", "totalViews");
			TASKS.put("最多推荐", "totalRecommends");
			TASKS.put("最多收藏", "totalFavorites");
			TASKS.put("最新发布", "pubdate");
			TASKS.put("Shenqu", "shenqu");
	    	TASKS.put("神曲风云榜-日榜", "daily");
			TASKS.put("神曲风云榜-月榜", "monthly");
	    	TASKS.put("Beautyleg","http://www.2ta.tv/b");
			TASKS.put("3AGirL","http://www.2ta.tv/3a");
			TASKS.put("4K-STAR","http://www.2ta.tv/4k");
			TASKS.put("RQ-STAR","http://www.2ta.tv/rq");
			TASKS.put("经典写真","http://www.2ta.tv/m");
			TASKS.put("Rosimm","http://www.2ta.tv/rs");
			TASKS.put("Siyamm","http://www.2ta.tv/sy");
			TASKS.put("Ru1mm","http://www.2ta.tv/ru");
			TASKS.put("Showgirl","http://www.2ta.tv/s");
			TASKS.put("nylonjp","http://www.2ta.tv/ny");
			TASKS.put("丽柜Ligui","http://www.2ta.tv/lg");
			TASKS.put("细高跟","http://www.2ta.tv/xi");
			TASKS.put("美女自拍","http://www.2ta.tv/p");
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
			TASKS.put("AISS爱丝","http://www.2ta.tv/as");
			TASKS.put("推女郎","http://www.2ta.tv/tg");
			TASKS.put("Beautyleg时尚写真","http://www.2ta.tv/bn");
			TASKS.put("瑜伽美女","http://www.2ta.tv/yg");
			TASKS.put("XiuRen","http://www.2ta.tv/xr");
			TASKS.put("Ru1mm-vip","http://www.2ta.tv/rv");
			TASKS.put("Allure Girls","http://www.2ta.tv/au");
			TASKS.put("中高艺","http://www.2ta.tv/z");
			TASKS.put("芬妮玉足","http://www.2ta.tv/fn");
			TASKS.put("Ugirls尤果","http://www.2ta.tv/ug");
			TASKS.put("赤足者","http://www.2ta.tv/cz");
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
