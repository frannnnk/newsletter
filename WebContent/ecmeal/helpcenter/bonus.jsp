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
				<div class="text">
					<span style="color: #ff0000; font-size: 14px"><b>積分換禮、抵扣餐費等功能籌備中，敬請期待！</b>
					</span>
				</div>
				<div class="title">積分獲取</div>
				<div class="text">
					在易食飯外賣成功訂餐，即可獲得訂單金額相應的積分；<br /> 訂餐成功後，發表餐廳評價，每一個評價可以獲得1個積分；<br />
					將來易食飯外賣會舉辦各種站內站外活動，積分回饋用戶！
				</div>
				<div class="title">積分使用</div>
				<div class="text">
					使用積分兌換相應的實物禮品；<span style="color: #666;">（籌備中）</span><br />
					使用積分抵扣相應的餐費；<span style="color: #666;">（籌備中）</span>
				</div>



			</div>



		</div>
	</div>

</body>
</html>