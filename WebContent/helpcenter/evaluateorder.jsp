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
			
			<div class="title">訂單評價</div>
			<div class="text">
				訂單在處理完成之後，也就是外賣送達用戶手中之後，用戶可以對已完成的訂單進行服務質量評價。<br>
				易食飯一定會虛心接受用戶的評價，有則改之無則加勉。
			</div>
			


		</div>


	</div>

</div>
</body>
</html>