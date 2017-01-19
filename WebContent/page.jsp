<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.tv.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
			String us =request.getParameter("u");
			String context = StringUtil.getContextFromUrl(us);
			int p;
			if(request.getParameter("s")==null || request.getParameter("s") == ""){
				p=1;
			}else{
				p=Integer.parseInt(request.getParameter("s"));
			}
			if(p<1){
				p=1;
			}
			int p2=p+1;
			int p3=p2+1;
		%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=context %> - Decline影视</title>
<link rel="stylesheet" href="css/tv.css" type="text/css" media="screen">
<link rel="shortcut icon" href="http://m.syasn.com/favicon.ico" type="image/x-icon">
</head>
<body id="x" class="v c0 cn  yc bg108" type="" style="">
    <div id="dg"></div>
    <div id="eye">
        <div id="h1">
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
                    <input id="sn" name="so" size="20" required="" type="text">
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
    <div id="wr" class="cf cw wz jlx pg1 lb1 p5">
    <a rev="ps699" rel="b710" title="13875" name="S" class="//a.aq-cn.com:88/b710" 
    type="b" href="http://www.2ta.tv/b710" id="n1" lang="ps569">p313</a>
        <!--[if IE 6]><script src="/1.js"></script><![endif]-->
        <script type="text/javascript" src="js/tv0.js"></script>
        <div id="cate" data-type="cat" data-name="b"></div>
        <div class="hm1">
            <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/db_tv?useUnicode=true&characterEncoding=utf8"
     user="root"  password="perp123"/>
     		<sql:query dataSource="${snapshot}" var="result1">
				SELECT id,tvName,tvImgUrl FROM db_tvurls where typeName like '%<%=context%>%' ORDER BY id DESC limit <%=(p-1)*10 %>,10;
			</sql:query>
			<c:forEach var="row1" items="${result1.rows}">
            <div id="hm" class="hm"> <span class="add" id="13875" title="添加到点播单，稍后观看，在观看记录保存">+</span> 
                <a id="ha" href="player.jsp?id=<c:out value='${row1.id}'/>" title="<c:out value='${row1.tvName}'/>" target="_blank" class="r50">
                    <div id="si" class="su"><span>&nbsp;173&nbsp;<span class="sm">&nbsp;次播放&nbsp;</span></span>
                    </div>
                    <div id="si"><span>&nbsp;0&nbsp;<span class="sm">人喜欢&nbsp;</span></span>
                    </div>
                    <img id="ka" class="i r21" alt="<c:out value='${row1.tvName}'/>" src="<c:out value='${row1.tvImgUrl}'/>" name="<c:out value='${row1.tvImgUrl}'/>" style="display: inline;">
                    <p><c:out value='${row1.tvName}'/></p>
                </a>
            </div>
            </c:forEach>
        </div>
        <sql:query dataSource="${snapshot}" var="result2">
				SELECT CEIL(COUNT(*)/10) AS sl FROM db_tvurls where typeName like '%<%=context%>%';
			</sql:query>
        <div id="ys">
        <div id='ys'><a id='ye' href='page.jsp?u=<%=us %>&s=<%=p-1 %>' title='上一页'><</a>
        <span id="ye" class="on r17" title="当前页-第<%=p %>页"><%=p %></span>  
        <a id="ye" href="page.jsp?u=<%=us %>&s=<%=p2 %>" title="第<%=p2 %>页" class="r73"><%=p2 %></a>
        <a id="ye" href="page.jsp?u=<%=us %>&s=<%=p3 %>" title="第<%=p3 %>页" class="r40"><%=p3 %></a>
        <span id="ye" class="r82">...</span>
        <c:forEach var="row2" items="${result2.rows}">
        	<a id="ye" href="page.jsp?u=<%=us %>&s=<c:out value='${row2.sl}'/>" title="最后一页" class="r35"><c:out value='${row2.sl}'/></a>
        </c:forEach>
        <a id="ye" href="page.jsp?u=<%=us %>&s=<%=p2 %>" title="下一页" class="r4">&gt;</a>
        </div>
        <div id="xt" class="ne">
            <div id="xtx">×</div>
        </div>
        <script type="text/javascript" src="js/tv1.js"></script>
        <div id="fb"></div>
    </div>
    </div>
    <div title="网站设置" id="gs"></div>
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

.hm {
    float: none;
    margin: 10px!important;
    width: 15%!important;
}
    </style>
</body>

</html>