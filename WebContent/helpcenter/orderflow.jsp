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
	<div id="bg_canvas2">
		<div class="main">

			<div class="nav-position">
				<a href="/canteen">首頁</a> > 幫助中心
			</div>





			<div class="menu">


				<jsp:include page="/helpcenter/menu.jsp"></jsp:include>



			</div>
			<div class="content">
				<div class="title">在綫訂餐流程</div>
				<div class="text">
					<b>步驟1、</b>輸入您所在的大致地理位置，（如：海濱南岸、黃埔花園、香港理工大學等）點擊“進入店鋪列表頁”。<br /> <img
						src="http://file.129t.com/Custom_Uploads/GiftRecommend/ywmdcbz1.jpg"><br />
					<br /> <b>步驟2、</b>進入店鋪列表頁，選擇合適的店鋪進行訂餐<br /> <img
						src="http://file.129t.com/Custom_Uploads/GiftRecommend/ywmdcbz2.jpg"><br />
					<br /> <b>步驟3、</b>將您喜歡的美食，依次放入餐車，並選擇訂購單個餐品的數量。點擊“點好了，就這些”繼續完成訂購。<br />
					<img
						src="http://file.129t.com/Custom_Uploads/GiftRecommend/ywmdcbz3.jpg"><br />
					<br /> <b>步驟4、完整填寫您的送餐信息，並做好訂餐備注。如：送餐時間、送餐要求、是否需要發票等，確認無誤點擊去結算。<br />
						<img
						src="http://file.129t.com/Custom_Uploads/GiftRecommend/ywmdcbz4.jpg"><br />
						<br /> <b>步驟5、</b>成功下單，查看（追蹤）訂單情況。<a
						href="http://help.yiwaimai.com/Default.aspx/20004">訂單狀態解釋>></a><br />
						<img
						src="http://file.129t.com/Custom_Uploads/GiftRecommend/ywmdcbz5.jpg"><br />
						<br /> <b>步驟6、</b>等餐上門<br /> 如訂單成功，請耐心等待送餐上門。<br />
						請注意：餐點送達時，請務必當面核對您所訂購的餐點數量和品種，如有差錯，請立即向外送員或客服反映；<br />
				</div>


			</div>
		</div>
</body>
</html>