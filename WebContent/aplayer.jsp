<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.util.*,net.sf.json.JSONObject,java.sql.*,net.sf.json.JSONArray,com.tv.util.*,com.tv.api.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
			String IDS =request.getParameter("id");
			IDS=IDS.matches(".*[a-zA-Z]+.*")?"79294":IDS;
			String result =DbUtil.getContextFromID(IDS);
			String[] ps = result.split("----");
			String plat="";
			int platFormID;
			String a="";
			String b="";
			String c="";
			String d="";
			String e="";
			String f="";
			String g="";
			String hdVideoUrl="";
			String hcVideoUrl="";
			String heVideoUrl="";
			String tvName = "";
			String tvUrl = "";
			String tvImgUrl = "";
			String tvTagsName = "";
			String tvContext = "";
			String platID = "";
			String tvTypeName = "";
			if(result=="" || result == null){
				response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				String newLocn = "/TvWeb/index.jsp";
				response.setHeader("Location",newLocn);
			}else{
			tvName = ps[1];
			tvUrl = ps[2];
			tvImgUrl = ps[3];
			tvImgUrl = tvImgUrl.indexOf("+")>-1?StringUtil.leftString(tvImgUrl, "+"):tvImgUrl;
			tvTagsName = ps[4];
			tvContext = ps[5];
			platID = ps[6];
			tvTypeName = ps[7];
			tvTypeName=tvTypeName.indexOf("(")>-1?StringUtil.leftString(tvTypeName, "("):tvTypeName;	
			if(platID.equals("恋恋影视")){
				plat="LIAN";
				platFormID=1;
				JSONArray result22 = DataJsonp.GetDate(plat, tvUrl);
				 a = result22.getJSONObject(0).get("ks").toString();
				 b = result22.getJSONObject(0).get("type").toString();
				 c = result22.getJSONObject(0).get("k1").toString();
				 d = result22.getJSONObject(0).get("k3").toString();
				 e = result22.getJSONObject(0).get("k4").toString();
				 f = result22.getJSONObject(0).get("k6").toString();
				 g = result22.getJSONObject(0).get("k7").toString();
			}else if(platID.equals("音悦台MV")){
				plat="YINYUETAI";
				platFormID=2;
				JSONArray result22 = DataJsonp.GetDate(plat, tvUrl);
				hdVideoUrl = result22.getJSONObject(0).get("hdVideoUrl").toString();
				hcVideoUrl = result22.getJSONObject(0).get("hcVideoUrl").toString();
				heVideoUrl = result22.getJSONObject(0).get("heVideoUrl").toString();
				hdVideoUrl=hdVideoUrl=="" || hdVideoUrl == null ? "http://ww3.sinaimg.cn/large/a24d4f55jw1fbxjaxcfk8j20qo0fujrp.jpg" : hdVideoUrl;
				hcVideoUrl=hcVideoUrl=="" || hcVideoUrl == null ? "http://ww3.sinaimg.cn/large/a24d4f55jw1fbxjaxcfk8j20qo0fujrp.jpg" : hcVideoUrl;
				heVideoUrl=heVideoUrl=="" || heVideoUrl == null ? "http://ww3.sinaimg.cn/large/a24d4f55jw1fbxjaxcfk8j20qo0fujrp.jpg" : heVideoUrl;
				}
			}
			
		%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/db_tv?useUnicode=true&characterEncoding=utf8"
     user="root"  password="perp123"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <title><%=tvName %> - 高清视频在线观看 - 恋恋影视</title>
    <meta name="keywords" content="<%=tvContext %>">
    <meta name="description" content="<%=tvTagsName %>">
    <!--[if IE]><link rel="stylesheet" href="/1.css"><![endif]-->
    <link rel="stylesheet" href="css/tv.css" type="text/css" media="screen">
    <script type="text/javascript" src="ckplayer/ckplayer.js" charset="utf-8"></script>
    <script src="js/ac.js"></script>
    <link rel="shortcut icon" href="http://m.syasn.com/favicon.ico" type="image/x-icon">
</head>
<body onload="show()" id="x" class="v bg101 c9 cs ssy slg yc" type="1" style="">
    <div id="dg"></div>
    <div id="eye">
        <div id="h2">
            <div class="cf cw f2">
                <div id="myl">
                    <div id="i"></div>
                    <h1 id="ou"><a href="index.jsp" rel="home" title="返回首页">Decline影视</a></h1><a href="index.jsp" rel="home" title="首页HomePage" id="ho" target="_blank">首页</a><span id="dh"><span id="dhs">≣导航</span>
                    <div id="dh1" class="bkss">
						<a href="page.jsp?u=b" target="_blank">Beautyleg</a>
						<a href="page.jsp?u=3a" target="_blank">3AGirL</a>
						<a href="page.jsp?u=4k" target="_blank">4K-STAR</a>
						<a href="page.jsp?u=rq" target="_blank">RQ-STAR</a>
                        <a href="page.jsp?u=m" target="_blank">经典写真</a>
						<a href="page.jsp?u=rs" target="_blank">Rosimm</a>
						<a href="page.jsp?u=sy" target="_blank">Siyamm</a>
						<a href="page.jsp?u=ru" target="_blank">Ru1mm</a>
                        <a href="page.jsp?u=s" target="_blank">Showgirl</a> 
						<a href="page.jsp?u=ny" target="_blank">Pantyhose</a>
						<a href="page.jsp?u=lg" target="_blank">丽柜Ligui</a>
						<a href="page.jsp?u=xi" target="_blank">细高跟</a>
						<a href="page.jsp?u=p" target="_blank">微拍福利</a>
                        <a href="page.jsp?u=xy" target="_blank">学院派私拍</a> 
						<a href="page.jsp?u=c" target="_blank">性感车模</a>
						<a href="page.jsp?u=ps" target="_blank">PANS写真</a>
						<a href="page.jsp?u=q" target="_blank">动感小站</a>
						<a href="page.jsp?u=qw" target="_blank">锦尚天舞</a>
                        <a href="page.jsp?u=a" target="_blank">国产私拍</a> 
						<a href="page.jsp?u=2a" target="_blank">国产私拍II</a>
						<a href="page.jsp?u=f" target="_blank">韩国饭拍</a>
						<a href="page.jsp?u=2f" target="_blank">韩国饭拍II</a>
						<a href="page.jsp?u=3f" target="_blank">韩国饭拍III</a>
                        <a href="page.jsp?u=k" target="_blank">韩国MV</a> 
						<a href="page.jsp?u=zb" target="_blank">韩国女主播</a>
						<a href="page.jsp?u=j" target="_blank">街拍美女</a>
						<a href="page.jsp?u=2j" target="_blank">街拍美女II</a>
						<a href="page.jsp?u=3j" target="_blank">街拍美女III</a>
                        <a href="page.jsp?u=4j" target="_blank">街拍美女IV</a>
						<a href="page.jsp?u=5j" target="_blank">街拍美女V</a>
						<a href="page.jsp?u=as" target="_blank">爱丝AISS</a>
						<a href="page.jsp?u=tg" target="_blank">推女郎</a>
						<a style="color:#DCF524" href="page.jsp?u=totalFavorites" target="_blank">最多收藏</a>
						<a style="color:#DCF524" href="page.jsp?u=pubdate" target="_blank">最新发布</a>
						<a style="color:#DCF524" href="page.jsp?u=shenqu" target="_blank">YY神曲</a>
						<a style="color:red;" href="page.jsp?u=vc"target="_blank">会员专区</a>
                        <a style="color:red;" href="page.jsp?u=1v" target="_blank">会员I区</a>
						<a style="color:red;" href="page.jsp?u=2v" target="_blank">会员II区</a>
						<a style="color:red;" href="page.jsp?u=3v" target="_blank">会员III区</a>
						<a style="color:red;" href="page.jsp?u=4v" target="_blank">会员IV区</a>
						<a style="color:red;" href="page.jsp?u=5v" target="_blank">会员V区</a>
						<a style="color:red;" href="page.jsp?u=6v" target="_blank">会员VI区</a>
						<a style="color:red;" href="page.jsp?u=7v" target="_blank">会员VII区</a>
						<a style="color:red;" href="page.jsp?u=8v" target="_blank">会员VIII区</a>
						<a style="color:red;" href="page.jsp?u=9v" target="_blank">会员IX区</a>
                        <a href="page.jsp?u=daily" style="color:#D688E0;" target="_blank">YY日榜</a>
						<a href="page.jsp?u=monthly" style="color:#D688E0;" target="_blank">YY月榜</a>
						<a href="page.jsp?u=totalViews" style="color:#D688E0;" target="_blank">最多播放</a>
						<a class="bgn ne">官方论坛</a>
						<a style="color:red;" href="page.jsp?u=vip" target="_blank" id="jya" class="hg">开通VIP会员</a>
						<a href="page.jsp?u=totalRecommends" style="color:#E703FC;" target="_blank">最多推荐</a>
						<a id="ja" style="color:#CCD67E;" title="点击展开更多专辑">更多专辑+</a>
							<div class="hg">
								<a href="page.jsp?u=bn" target="_blank">BL时尚写真</a>
								<a href="page.jsp?u=yg" target="_blank">瑜伽美女</a>
								<a href="page.jsp?u=xr" target="_blank">秀人写真</a>
								<a href="page.jsp?u=rv" target="_blank">Ru1mm-vip</a>
								<a href="page.jsp?u=au" target="_blank">Allure Girls</a>
								<a href="page.jsp?u=z" target="_blank">中高艺</a>
								<a href="page.jsp?u=fn" target="_blank">芬妮玉足</a>
								<a href="page.jsp?u=ug" target="_blank">Ugirls尤果</a>
								<a href="page.jsp?u=cz" target="_blank">赤足者</a>
								<a class="bgn">即将上线···</a>
							</div>
                    </div>
                    </span>
                </div>
                <div id="so">
                    <form id="sf" method="get" action="so.jsp?s=" target="_blank">
                        <input id="sn" type="text" value="" name="so" size="20" required="">
                        <button id="sr" type="submit"></button>
                    </form>
                </div>
                <div id="myr">
	                <div id="log">
	                    <div title="关于" id="loc"><i></i>关于</div>
	                </div><a id="see" title="图库" href="http://img.sunkejava.com/TaotuWeb/" target="_blank">图片专区</a><a id="ssc" title="SunkeJava's Blog" href="www.sunkejava.com" styles="color:red" target="_blank">SunkeJava's Blog</a>
            	</div>
            </div>
        </div>
        <div id="hh" class="ne"></div>
        <div id="wr" class="cf cw wz jlx pg1 lb1 p5">
       <a rev="<%= a %>" rel="<%= a %>" title="706" lang="4j216" 
       	name="Ss1|Ss2|Ss3|Ss4" class="//a.aq-cn.com:88/b389" 
       	type="<%= b %>" href="<%= a %>" id="n1"></a>
            <!--[if IE 6]><script src="js/tv1.js"></script><![endif]-->
            <script type="text/javascript" src="js/tv0.js"></script>
            <div class="cy">
                <div class="kv">
                    <div id="ph" class="f2">
                        <img id="pi" class="pd" src="<%=tvImgUrl %>" date="<%=tvImgUrl%>">
                        <div id="pt"> <span id="pt1"><%=tvName %></span>
                            <br> <span id="pt2">专辑&nbsp;&gt; <a href="page.jsp?u=sy" id="LE" target="_blank"><%=tvTagsName %></a>&nbsp;</span> 
                        </div>
                    </div>
                    <div id="a1"></div>
                    <div id="zj" class="nb in">
                        <div id="iy" class="nb" style="display: none;">
                            
                            <div id="ir">
                                
                            </div>
                        </div>
                        <div id="bf" style="display: block;"></div>
                        <div id="da"></div>
                        <div id="nb" class="nb bn">
                            
                        </div>
                    </div>
                </div>
                <div>
                    <div id="pb" class="f2">&nbsp;<a href="page.jsp?u=sy" id="LE" target="_blank"><%=tvTypeName %></a><%=tvName %></div>
                    <div id="br" class="cf">
                        <div id="xh">
                            <div class="fl" id="lmn"><span title="喜欢" id="lm"><i class="li"></i><span id="ln">喜欢</span></span><span id="ln" title="被502人喜欢过" class="f2">502</span>
                            </div>
                        </div>
                        <div id="bs"> <span id="qr" data="sss222"> <i></i>用手机看 <div id="qrb"> <div id="qrt"> <h4>扫描二维码随身看视频</h4> <a href="http://baike.baidu.com/view/132241.htm" target="_blank">什么是二维码？</a> </div> <div id="qrh"><p>用手机或平板摄像头拍下右侧的二维码，您可以：</p><p>1 在手机或平板上继续观看该视频</p><p>2 分享给你的微信好友或者朋友圈</p><span>正在观看：</span>
                            <a id="qra" title="<%=tvName%>"><%=tvName %></a>
                        </div>
                        <div id="qrp">
                            <img id="qri" name="//s.jiathis.com/qrcode.php?url=http%3A%2F%2F2ta.tv%2Fsy47" alt="<%=tvName%>">
                        </div>
                    </div>
                    </span><span id="zx" title="下载本部视频Download this video"><i></i>视频下载</span> 
                    <div class="bdsharebuttonbox"> <a id="fe" title="点击分享到更多社区" class="bds_more" data-cmd="more"><i></i>好友分享</a> 
                        <a class="bds_qzone" data-cmd="qzone"></a>
                        <a class="bds_tsina" data-cmd="tsina"></a>
                        <a class="bds_tqq" data-cmd="tqq"></a>
                        <a class="bds_renren" data-cmd="renren"></a>
                        <a class="bds_weixin jiathis_button_weixin" title="分享到微信" target="_blank" href="http://s.jiathis.com/?webid=weixin&amp;url=http%3A%2F%2F2ta.tv%2Fsy47&amp;title=siyamm%20%E4%B8%9D%E9%9B%85%E5%86%99%E7%9C%9F%20%E7%AC%AC47%E9%9B%86%20-%20%E9%AB%98%E6%B8%85%E8%A7%86%E9%A2%91%E5%9C%A8%E7%BA%BF%E8%A7%82%E7%9C%8B%20-%20%E6%81%8B%E6%81%8B%E5%BD%B1%E8%A7%86%20&amp;uid=1&amp;isexit=false"
                        rel="nofollow"></a>
                        <a class="bds_tieba" data-cmd="tieba"></a>
                        <a class="bds_sqq" data-cmd="sqq"></a>
                        <a class="bds_douban" data-cmd="douban"></a>
                        <a class="bds_mshare" data-cmd="mshare"></a>
                    </div>
                </div><span id="bm"><i id="bi"></i><span>862318</span>&nbsp;次播放</span>
            </div>
            <sql:query dataSource="${snapshot}" var="result1">
				SELECT id,tvName FROM db_tvurls where id between <%=IDS %>+1 and <%=IDS %>+101;
			</sql:query>
			<sql:query dataSource="${snapshot}" var="result2">
				SELECT id,tvName FROM db_tvurls where id between <%=IDS %>+101 and <%=IDS %>+201;
			</sql:query>
            <div id="jj">
                <div id="jt"> 
                	<span id="j1" class="on">
                		<i id="r1">
                			<i id="r2"></i>
                			<i id="r3"></i>
                		</i>剧集列表：前100集
                	</span> 
                	<span id="j2">
                		<i id="r1">
                			<i id="r2"></i>
                			<i id="r3"></i>
                		</i>后100集
                	</span>  
                	<tt id="bac"> 
                		<tt class="add" id="1672" title="添加到点播单，稍后观看，在观看记录保存">+</tt> 
                    	<tt id="sc">
                    		<i id="lsc" title="收藏本部视频"></i>
                        </tt>
                    </tt>
                </div>
                <div id="jb">
	                    <div id="j1">
	                    	<c:forEach var="row1" items="${result1.rows}">
		                    	<a href="player.jsp?id=<c:out value='${row1.id}'/>" target="_blank"><c:out value='${row1.tvName}'/></a>
	                    	</c:forEach>
	                    </div>
                    <div id="j2">
                    	<c:forEach var="row2" items="${result2.rows}">
                    		<a href="player.jsp?id=<c:out value='${row2.id}'/>" target="_blank"><c:out value='${row2.tvName}'/></a>
                    	</c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div id="cyReward" role="cylabs" data-use="reward" sid="<%=IDS %>"></div>
<script type="text/javascript" charset="utf-8" src="http://changyan.itc.cn/js/lib/jquery.js"></script>
<script type="text/javascript" charset="utf-8" src="https://changyan.sohu.com/js/changyan.labs.https.js?appid=cysKOEP16"></script>
<!--PC和WAP自适应版-->
<div id="SOHUCS" sid="<%=IDS %>" ></div> 
<script type="text/javascript"> 
(function(){ 
var appid = 'cysKOEP16'; 
var conf = 'prod_aa32cf21afc84404843c21a3734cd2c4'; 
var width = window.innerWidth || document.documentElement.clientWidth; 
if (width < 960) { 
window.document.write('<script id="changyan_mobile_js" charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/mobile/wap-js/changyan_mobile.js?client_id=' + appid + '&conf=' + conf + '"><\/script>'); } else { var loadJs=function(d,a){var c=document.getElementsByTagName("head")[0]||document.head||document.documentElement;var b=document.createElement("script");b.setAttribute("type","text/javascript");b.setAttribute("charset","UTF-8");b.setAttribute("src",d);if(typeof a==="function"){if(window.attachEvent){b.onreadystatechange=function(){var e=b.readyState;if(e==="loaded"||e==="complete"){b.onreadystatechange=null;a()}}}else{b.onload=a}}c.appendChild(b)};loadJs("http://changyan.sohu.com/upload/changyan.js",function(){window.changyan.api.config({appid:appid,conf:conf})}); } })(); </script>
        
        <p>&nbsp;</p>
        <!-- rst -->
        <div id="xc" class="cf"><span id="xct">猜你喜欢</span>
            <br>
            <sql:query dataSource="${snapshot}" var="result3">
				SELECT id,tvName,tvUrl,SUBSTRING_INDEX(tvImgUrl,'+',1) as tvImgUrl FROM db_tvurls where id between <%=IDS %>+1 and <%=IDS %>+9;
			</sql:query>
			<c:forEach var="row3" items="${result3.rows}">
            <div class="hm">
                <div class="xh">
                    <div class="xs f2"><span>&nbsp;<c:out value='${row3.tvName}'/>&nbsp;</span>
                    </div>
                    <div id="si"><span>&nbsp;9584&nbsp;<span class="sm">人喜欢&nbsp;</span></span>
                    </div>
                    <a id="ha" href="player.jsp?id=<c:out value='${row3.id}'/>" title="<c:out value='${row3.tvName}'/>" class="r71">
                        <p><c:out value='${row3.tvName}'/></p>
                        <img id="ca" class="i" alt="<c:out value='${row3.tvName}'/>" src="<c:out value='${row3.tvImgUrl}'/>.165/210" name="<c:out value='${row3.tvImgUrl}'/>.165/210">
                    </a>
                </div>
            </div>
            </c:forEach>
        </div>
        <div id="mr"></div>
        <div id="CYan">　</div>
        <div id="SOHUCS"></div>
        <div id="nr"></div>
    </div>
    <div id="se" class="ks">
        <div class="alike cf ne"></div>
        <div id="s1" class="s1 f2">
            <sql:query dataSource="${snapshot}" var="result4">
				SELECT id,tvName,typeName,tvUrl,SUBSTRING_INDEX(tvImgUrl,'+',1) as tvImgUrl FROM db_tvurls where id between <%=IDS %>+1 and <%=IDS %>+17 and typeName='<%=tvTypeName %>';
			</sql:query>
			<c:forEach var="row4" items="${result4.rows}">
            <li> 
            	<span class="add" id="1746" title="添加到点播单，稍后观看，在观看记录保存">+</span> 
                <div id="si"> <span>&nbsp;402&nbsp;<span class="sm">人喜欢&nbsp;</span></span>
                </div>
                <div id="sp">
                    <div id="sp1"><a href="player.jsp?id=<c:out value='${row4.id}'/>" title="<c:out value='${row4.tvName}'/>"><c:out value='${row4.tvName}'/></a>
                    </div>
                    <div id="sp2">频道：<a href="page.jsp?u=sy" id="LE" target="_blank"><%=tvTypeName %></a>
                    </div>
                    <div id="sp3"><i id="bi"></i>&nbsp;663313&nbsp;次播放</div>
                </div>
                <a id="sa" href="player.jsp?id=<c:out value='${row4.id}'/>" title="<c:out value='${row4.tvName}'/>" target="_blank" class="r43">
                    <img id="ka" alt="<c:out value='${row4.tvName}'/>" src="<c:out value='${row4.tvImgUrl}'/>!145,85" name="<c:out value='${row4.tvImgUrl}'/>!145,85" class="r24" style="display: inline;">
                </a>
            </li>
            </c:forEach>
        </div>
    </div>
    <div id="xt" class="ne">
        <div id="xtx">×</div>
    </div>
    <script type="text/javascript" src="js/tv1.js"></script>
    <div id="fb"></div>
	<script type="text/javascript">
	var ps;
	var flashvars;
	var tvimg;
	function show() {
		var i1 = W("ke"),
		rd1 = yp() ? i1 ? "m2" : "ms" : i1 ? "c2" : "cs";
		  p = get();
		  s = (yp() ? "" : rm(8));
		  var q= "?k1=<%= c %>&k2="+rd1+s+"&k3=<%= d %>&k4=<%= e %>&k5=<%= a %>&k6=<%= f %>&k7=<%= g %>";
		 
		  var typea='<%=plat%>';
		  if(typea.indexOf("YINYUETAI")>=0){
			  ps='<%=hdVideoUrl%>';
			  tvimg='<%=tvImgUrl%>';
		  }else if(typea.indexOf("LIAN")>=0){
			  ps = p+q;
			  tvimg='<%=tvImgUrl%>!960,571';
		  }
		  
		  flashvars={
	    			f:ps,
	    			a:'',
	    			s:'0',
	    			c:'0',
	    			x:'',
	    			i:tvimg,
	    			e:'2',
	    			v:'50',
	    			p:'0',
	    			h:'3',
	    			wh:'4:3',
	    			lv:'0',
	    			Title:'<%= tvName %>',
	    			deft:'流畅432P,高清540P,超清720P',
	    			deff:'<%=hdVideoUrl%>|<%=hcVideoUrl%>|<%=heVideoUrl%>',
	    			loaded:'loadedHandler'
	    			};
	    		var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always'};
	    		var video=[ps+'>video/mp4'];
	    		CKobject.embed('ckplayer/ckplayer.swf','a1','ckplayer_a1','100%','100%',false,flashvars,video,params);
	      
	}
	function loadedHandler(){
		if(CKobject.getObjectById('ckplayer_a1').getType()){//说明使用html5播放器
			document.title="正在播放："+flashvars.Title;
			
		}
		else{
			document.title="正在播放："+flashvars.Title;
			
		}
	}
	
	</script>
<style>
	.hm2 {
	    text-align: center!important;
	    float: none!important;
	    padding-top: 10px!important;
	}
	
	.cn .hm {
	    width: 16%!important;
	}
	
	.co .hm {
	    width: 15%!important;
	}
	
	.hm,.hm2,.hm1,#i2,.my .hm {
	    text-align: center!important;
	}
	
	.co {
	    background-color: #8A484F!important;
	}
</style>
</body>
</html>