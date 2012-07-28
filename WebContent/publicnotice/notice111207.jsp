<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯-公告</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_publicnotice.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />
</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>
	<div id="bg_canvas">
		<div class="nav-position">
			<a href="/canteen">首页</a> > 公告
		</div>
		<div class="content">

			<!-- 标题 -->
			<center>
				<h1>
					<b>易食飯公測進行中...</b>
				</h1>
			</center>
			<!-- end -->

			<!-- 公告内容 -->
			<div class="noticecontent">易食飯網站正在如火如荼的進行着公測。<p>如果你對我們網站有任何的建議、想法和需求，請給我們留言，讓我們大家一起參與、做的更好！
			<p>
			<a  style="color: gray" href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');">我要留言</a>
			</div>
			<!-- end -->
		</div>

	<jsp:include page="/include/footer_common.jsp"></jsp:include>

	</div>


</body>
</html>