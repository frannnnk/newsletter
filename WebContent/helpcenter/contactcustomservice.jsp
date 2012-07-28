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
				<div class="title">綫上客服</div>
				<div class="text">
					MSN:&nbsp;<span style="color: #C31A1F; font-size: 18px">ecmeal.hk@hotmail.com
					</span><br /> 
					QQ:&nbsp;<span style="color: #C31A1F; font-size: 18px">1982209013
					</span><br /> 
					服務時間：<span style="color: #C31A1F">10：30～15：00</span>
				</div>
				<div class="title">在線咨詢&投訴</div>
				<div class="text">
					咨詢&投訴（客服會在1個工作日處理您的咨詢&投訴）
				</div>


			</div>



		</div>
	</div>
</body>
</html>