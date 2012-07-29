<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板</title>
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
					<b>公告标题</b>
				</h1>
			</center>
			<!-- end -->

			<!-- 公告内容 -->
			<div class="noticecontent">公告内容...</div>
			<!-- end -->
		</div>

	<jsp:include page="/include/footer_common.jsp"></jsp:include>

	</div>


</body>
</html>