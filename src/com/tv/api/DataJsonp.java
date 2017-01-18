package com.tv.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.tv.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * YY神曲、音悦台、51AVI解析
 * 需要Json Jar包
 * @author Administrator
 *
 */
public class DataJsonp {
	public static void main(String[] args) throws Exception {
		JSONArray restsss = GetDate("YY","99931031");//JSON及解析
		System.out.println(restsss);
		JSONArray rest = GetDate("YINYUETAI","833120");
		System.out.println("hdVideoUrl:"+rest.getJSONObject(0).get("hdVideoUrl"));
		System.out.println("hcVideoUrl:"+rest.getJSONObject(0).get("hcVideoUrl"));
		System.out.println("heVideoUrl:"+rest.getJSONObject(0).get("heVideoUrl"));
		System.out.println(rest);
		JSONArray rests = GetDate("51AVI","204960");
		System.out.println(rests);
		JSONArray result22= GetDate("LIAN","http://www.2ta.tv/sy41");
		String a =result22.getJSONObject(0).get("ks") + "&b="
		+ result22.getJSONObject(0).get("type") + "&c=" + result22.getJSONObject(0).get("k1")
		+ "&d=" + result22.getJSONObject(0).get("k3") + "&e="
		+ result22.getJSONObject(0).get("k4")+ "&f=" + result22.getJSONObject(0).get("k6")+ "&g=" + result22.getJSONObject(0).get("k7");
		System.out.println(a);
		
	}
	
	
	
	public static JSONArray GetDate(String DataType,String ID) throws Exception{
		String urls = null;
		//创建组建后的Json对象存放的Array数组
		JSONArray thisj = new JSONArray();
		JSONArray pthisj = new JSONArray();
		if(DataType.equals("YY")){
			 urls = "http://www.yy.com/u/works/s?yyno="+ID+"&p=1";
			//需要抛出异常
				URL url2 = new URL(urls);
				BufferedReader bufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(url2.openStream()),"utf-8"));
				String line2;
				StringBuffer sb2 = new StringBuffer();
				while((line2 = bufr2.readLine())!= null){
					sb2.append(line2);
				}
				bufr2.close();
				//将sb字符串转换为json对象
				JSONObject jsn1 = JSONObject.fromObject(sb2.toString());
				//JSONObject jsonobject2 = JSONObject.fromObject("{\""+"data"+StringUtil.rightString(sb2.toString(),"data"));
				int Sumpage = (int) jsn1.get("pages");
				for(int j=0;j<Sumpage;j++){
					String purls = "http://www.yy.com/u/works/s?yyno="+ID+"&p="+(j+1);
					//需要抛出异常
						URL purl2 = new URL(purls);
						BufferedReader pbufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(purl2.openStream()),"utf-8"));
						String pline2;
						StringBuffer psb2 = new StringBuffer();
						while((pline2 = pbufr2.readLine())!= null){
							psb2.append(pline2);
						}
						pbufr2.close();
						//将sb字符串转换为json对象
						JSONObject pjsn1 = JSONObject.fromObject(psb2.toString());
						JSONArray presult2 = pjsn1.getJSONArray("data");
						JSONObject newjsonp = new JSONObject();
						newjsonp.put("song_album_id",Integer.toString(j+1));
						newjsonp.put("song_album",presult2.getJSONObject(0).get("ownername")+"---第"+(j+1)+"页");
						newjsonp.put("song_album1","来自YY神曲");		
				for(int i = 0;i<presult2.size();i++ ){
					if(j==Sumpage){
						System.out.println(presult2.size());
					}
					JSONObject concent = (JSONObject) presult2.get(i);
					//获取需要的的内容并组成新的json对象2
					JSONObject newjson = new JSONObject();
					newjson.put("song_id", Integer.toString(i+1));
					//获取神曲名称并加入新的Json对象中
					newjson.put("song_title",concent.get("songname"));
					//获取神曲歌手并加入新的Json对象中
					newjson.put("singer", concent.get("ownername"));
					//获取歌曲播放地址并加入新的Json对象中
					newjson.put("pic", concent.get("snapshoturl"));
					newjson.put("likeCount", concent.get("likeCount"));
					newjson.put("watchCount", concent.get("watchCount"));
					newjson.put("duration", concent.get("duration"));
					newjson.put("resurl",concent.get("resurl"));
					//将组建后的json对象数据加入到newarr数组中
						pthisj.add(newjson);
					}
				newjsonp.put("song_list",pthisj);
				pthisj.clear();
				thisj.add(newjsonp);
				}
		}else if(DataType.equals("YINYUETAI")){
			 urls = "http://www.yinyuetai.com/api/info/get-video-urls?videoId="+ID;
			String purls="http://v.yinyuetai.com/video/"+ID;
			 //需要抛出异常
				URL url2 = new URL(urls);
				BufferedReader bufr2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(url2.openStream()),"utf-8"));
				String line2;
				StringBuffer sb2 = new StringBuffer();
				while((line2 = bufr2.readLine())!= null){
					sb2.append(line2);
				}
				bufr2.close();
				
				URL urlq = new URL(purls);
				BufferedReader bufr3 = new BufferedReader(new InputStreamReader(new BufferedInputStream(urlq.openStream()),"utf-8"));
				String line3;
				StringBuffer sb3 = new StringBuffer();
				while((line3 = bufr3.readLine())!= null){
					sb3.append(line3);
				}
				bufr3.close();
				String context = StringUtil.betweenSting(sb3.toString(),"<title>", "</title>");
				//System.out.println(context);
				//将sb字符串转换为json对象
				JSONObject jsonobject2 = JSONObject.fromObject("{data:["+sb2.toString()+"]}");
				//取的需要的数据data{}中的数据
				JSONArray result2 = jsonobject2.getJSONArray("data");
				for(int i = 0;i<result2.size();i++ ){
					JSONObject concent = (JSONObject) result2.get(i);
					//获取需要的的内容并组成新的json对象2
					JSONObject newjson = new JSONObject();
					//获取神曲名称并加入新的Json对象中
					newjson.put("hdVideoUrl",StringUtil.leftString(concent.get("hdVideoUrl").toString(), "?"));
					//获取神曲歌手并加入新的Json对象中
					newjson.put("hcVideoUrl", StringUtil.leftString(concent.get("hcVideoUrl").toString(), "?"));
					//获取歌曲播放地址并加入新的Json对象中
					newjson.put("heVideoUrl",StringUtil.leftString(concent.get("heVideoUrl").toString(), "?"));
					newjson.put("title",context);
					//将组建后的json对象数据加入到newarr数组中
					thisj.add(newjson);
				}
		}else if(DataType.equals("51AVI")){
			urls = "http://www.51av.org/modules/premium/player/ckplayer/config.php?id="+ID;
			//获取String类型的XML
			InputStream is=downloadXML(urls);
			String outfile=convertStreamToString(is);
			
			//取出需要的文本
			String VideUrl = StringUtil.betweenSting(outfile, "<file>", "</file>");
			String VideoSize = StringUtil.betweenSting(outfile, "<size>", "</size>");
			String VideoSeconds = StringUtil.betweenSting(outfile, "<seconds>", "</seconds>");
			String VideoText = "{'file':'"+VideUrl+"','size':'"+VideoSize+"','seconds':'"+VideoSeconds+"'}";
/*			System.out.println("电影地址："+VideUrl);
			System.out.println("电影大小："+VideoSize);
			System.out.println("电影时长："+VideoSeconds);*/
			thisj.add(VideoText);
	}else if(DataType.equals("LIAN")){
		/**
		 * 获取关键k值
		 * @param name  tvName
		 * @return
		 * @throws IOException
		 */
		String IDS = "";
		Pattern pattern = Pattern.compile("\\d+$");
		Matcher matcher = pattern.matcher(ID);
		  if(matcher.find()){
			  IDS =  matcher.group();
		  }
		  else{
		   System.out.println("字符串"+ID+"不是以数字结尾的");
		  }
		
//			String n1Type = StringUtil.leftString(ID, "&");
//			String IDS = StringUtil.rightString(ID, "&");
			String n1Type = StringUtil.leftString(ID.substring(ID.lastIndexOf("/")+1),IDS);
			IDS = ID.substring(ID.lastIndexOf("/")+1);
			String results = null;
			String url = "http://h.syasn.com/?n="+IDS;
			
			  HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
			  client.getHttpConnectionManager().getParams().setConnectionTimeout(15000); //通过网络与服务器建立连接的超时时间
			  client.getHttpConnectionManager().getParams().setSoTimeout(60000); //Socket读数据的超时时间，即从服务器获取响应数据需要等待的时间
			  client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
			  GetMethod method = new GetMethod(url);
			  method.setRequestHeader("Content-Type", "text/html;charset=gbk"); // 防止乱码
			  method.setRequestHeader("Connection","close");
			  method.setRequestHeader("Referer", "http://www.2ni.tv/"+IDS); // 设置来路
			  method.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0"); 
			  try {
				client.executeMethod(method);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  	
			  InputStream inputStream = method.getResponseBodyAsStream();  
			  BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
			  StringBuffer stringBuffer = new StringBuffer();  
			  String str= "";  
			  while((str = br.readLine()) != null){  
			  stringBuffer .append(str );  
			  }
			  String rightString = StringUtil.rightString(stringBuffer.toString(), "};");
			  String stringk14 = StringUtil.leftString(rightString, "mip");
			  //System.out.println(stringk14);
			  String str14left = stringk14.substring(0,68);
			  //System.out.println(str14left);
			  String str14right = stringk14.substring(68);
			  //System.out.println(str14right);
			  String k4 = StringUtil.betweenSting(str14left, "='", "',");
			  String k1 = StringUtil.betweenSting(str14right, "='", "',");
			  //System.out.println(k4+"-----"+k1);
			  String k3string = StringUtil.rightString(rightString, "mik=");
			  String k3 = StringUtil.betweenSting(k3string, "?'", "':");
			  String result = StringUtil.rightString(stringBuffer.toString(), "mid=");
			  String mid = StringUtil.betweenSting(result, "?(", " '");
			  String k7 = mid.substring(3);
			  String result2 = StringUtil.rightString(stringBuffer.toString(), "mis=");
			  String mis = StringUtil.rightString(result2, "?'");
			  String k6 = mis.substring(0,32);
//			  results="{'k6':'"+k6+"','k7':'5"+k7+"','ks':'"+IDS+"','type':'"+n1Type+"','mip':'"+mip+"','mik':'"+mik+"','min':'"+min+"'}";
			  results = "{'ks':'"+IDS+"','type':'"+n1Type+"','k1':'"+k1+"','k3':'"+k3+"','k4':'"+k4+"','k6':'"+k6+"','k7':'5"+k7+"'}";
			  thisj.add(results);
		
	} 
		return thisj;

}
	public static InputStream downloadXML(final String urlStr){
		InputStream inStream = null;
					   URL url = null;
					try {
						url = new URL(urlStr);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					   HttpURLConnection conn = null;
					try {
						conn = (HttpURLConnection) url.openConnection();
						conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					   conn.setConnectTimeout(5 * 1000);  
					  try {
						conn.setRequestMethod("GET");
						conn.connect();
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}  
					  
					   try {
						   inStream= conn.getInputStream();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	return inStream;  
	}
	public static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {   
		 
		   BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));   

				StringBuilder sb = new StringBuilder();   



				String line = null;   

				try {   

					while ((line = reader.readLine()) != null) {   

						sb.append(line + "/n");   

					}   

				} catch (IOException e) {   

					e.printStackTrace();   

				} finally {   

					try {   

						is.close();   

					} catch (IOException e) {   

						e.printStackTrace();   

					}   
 
				}   
 
 
 
				return sb.toString();   
 
			}
}
