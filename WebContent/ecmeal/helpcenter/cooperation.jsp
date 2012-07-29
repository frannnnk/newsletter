<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯-幫助中心</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_helpcenter.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />
</head>
<body>
	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>

	<div id="bg_canvas">
		<div class="main">

			<div class="nav-position">
				<a href="/canteen">首頁</a> > 幫助中心
			</div>





			<div class="menu">


				<jsp:include page="/helpcenter/menu.jsp"></jsp:include>


			</div>
			<div class="content">
			
			<div class="title">商家合作</div>
			<div class="text">
				如果您的餐館有外賣需求，或者您有您熟悉的某家餐館的信息，您可以通過以下方式提交餐館信息<br>
				Email:&nbsp;<a>business@ecmeal.hk</a><br>
				Phone:&nbsp;3568 3510
			
			</div>
			
			
			
			</div>
		</div>
</body>
</html>