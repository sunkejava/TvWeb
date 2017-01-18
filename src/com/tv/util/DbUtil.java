package com.tv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tv.model.TvModel;

/**
 * Mysql数据库操作类
 * @author Administrator
 *
 */
public class DbUtil {
	private String dbUrl = "jdbc:mysql://localhost:3306/db_tv?useUnicode=true&characterEncoding=utf8";//数据库连接地址
	private String dbUserName = "root";//用户名
	private String dbPassword = "perp123";//密码
	private String jdbcName = "com.mysql.jdbc.Driver";//驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con != null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getresultOftvUrl("http://www.2ta.tv/3a9123423"));
		
	}
	/**
	 * 根据url判断数据库中是否存在，存在返回fasle,不存在返回true
	 * @param tvurl
	 * @return
	 */
	public static boolean getresultOftvUrl(String tvurl){
		boolean flag=true;
		DbUtil dbutil = new DbUtil();
		Connection con = null;
		try {
			con = dbutil.getCon();
			String sql = "SELECT tvUrl FROM db_tvurls where tvurl='"+tvurl+"'";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.last();
			int s= rs.getRow();
			System.out.println("查询结果共计:"+s+"个！！！");
			if(s>0){
				flag=false;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static String getresult() {
		String a = "";
		DbUtil dbutil = new DbUtil();
		Connection con = null;
		try {
			con = dbutil.getCon();
			String sql = "SELECT tvUrl FROM db_tvurls";
			PreparedStatement pstmt;
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int as = 1;
			while (rs.next()) {
				System.out.println("正在获取db_tv中的第" + as + "条数据！！");
				a = a + rs.getString(1) + "----";
				as++;
			}
			pstmt.close();
		} catch (Exception e1) {
			System.err.println("数据库连接失败！！");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	public static boolean insertTv(TvModel tvModel){
		boolean resu = false;
		DbUtil dbutil = new DbUtil();
		Connection con = null;
		try {
			con = dbutil.getCon();
			String sql = "INSERT INTO db_tvurls (tvName,tvUrl,tvImgUrl,tagsName,typeName,addTime,crawlTime,dateLong,context,platformName) VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,tvModel.getTvName());
			ps.setString(2,tvModel.getTvUrl());
			ps.setString(3,tvModel.getTvImgUrl());
			ps.setString(4,tvModel.getTagsName());
			ps.setString(5,tvModel.getTypeName());
			ps.setString(6,tvModel.getAddTime());
			ps.setString(7,tvModel.getCrawlTime());
			ps.setString(8,tvModel.getDateLong());
			ps.setString(9,tvModel.getContext());
			ps.setString(10,tvModel.getPlatformName());	
			int result = ps.executeUpdate();
			if(result > 0){
				resu= true;
			}else{
				resu= false;
			}
			ps.close();
		} catch (Exception e) {
			System.err.println("数据库连接失败！！");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return resu;

	}
	public static String getContextFromID(String id){
		DbUtil dbutil = new DbUtil();
		Connection con = null;
		String result="";
		try {
			con = dbutil.getCon();
			String sql = "SELECT id,tvName,tvUrl,tvImgUrl,tagsName,context,platformName,typeName FROM db_tvurls WHERE id="+id;
		    PreparedStatement pstmt;
		    pstmt = (PreparedStatement)con.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        System.out.println("============================");
	        int a=1;
	        while (rs.next()) {
	            System.out.println("正在获取db_taotu中的第"+a+"条数据！！");
	        	result = result+rs.getInt(1)+"----"+rs.getString(2)+"----"+rs.getString(3)+"----"+rs.getString(4)+"----"+rs.getString(5)+"----"+rs.getString(6)+"----"+rs.getString(7)+"----"+rs.getString(8);
	        	a++;
	        }
	            System.out.println("============================");
	            System.out.println(result);
	            pstmt.close();
		} catch (Exception e1) {
			System.err.println("数据库连接失败！！");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return result;
	}
}
