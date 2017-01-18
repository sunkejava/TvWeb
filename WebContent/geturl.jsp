<%@ page language="java" import="net.sf.json.JSONArray,com.tv.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String searchContext = request.getParameter("q");
if(searchContext == "" || searchContext == null){
response.sendRedirect("404.jsp");
}else{

}
%>