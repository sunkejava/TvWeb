package com.tv.api;

import net.sf.json.JSONArray;

public class DataJsonImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static String getTvUrl(String platID,String ID) throws Exception{
		String tvUrl = "";
		if(platID.equals("LIAN")){
			//http://k.syasn.com/rv/
			//rv12.mp4?k1=182.18.113.103
			//&k2=cs48xwpy1u
			//&k3=3334ee03a67f8b20090d98237faa1cb2
			//&k4=6b6e2b805fa527ecb0cebb0ca2bd14db
			//&k5=rv12
			//&k6=1ce30c497dc50f9cb265474fe082aa75
			//&k7=1c7835092b94bf5a82ae6e4112964851
			String k2="";
			String aurl="";
			JSONArray result22= DataJsonp.GetDate("LIAN","http://www.2ta.tv/sy41");
			String a =aurl+"/"+result22.getJSONObject(0).get("type") +"/"+
			result22.getJSONObject(0).get("ks") + ".mp4?k1="+ result22.getJSONObject(0).get("k1")
			+ "&k2=" + k2+"&k3="+result22.getJSONObject(0).get("k3") + "&k4="
			+ result22.getJSONObject(0).get("k4")+ "&k5=" +result22.getJSONObject(0).get("ks")
			+"&k6="+ result22.getJSONObject(0).get("k6")+ "&k7=" + result22.getJSONObject(0).get("k7");
			
		}
		return tvUrl;
	}
	

}
