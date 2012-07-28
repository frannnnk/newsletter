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

				<div class="title">關於我們</div>
				<div class="text">
					易食飯（www.ecmeal.hk）是香港樂乎電子商務科技有限公司精心打造的網絡訂餐平台。致力于爲香港用戶提供專業、便捷的餐飲外賣在線訂購服務、外賣優惠信息服務、准確即時的餐品配送服務。<br />
				</div>

				<div class="title">我們承諾</div>
				<div class="text">
					1、嚴格監控每一個工作環節，保證用戶按時用餐；<br /> 2、不斷推出各類優惠政策，滿足用戶需求；<br />
					3、不滿足現狀，不斷的向自己提出更高的要求，向用戶提供更優質的服務。<br />
				</div>

				<div class="title">我們的目標</div>
				<div class="text">專注於香港，給香港人民提供最優質的餐飲綫上服務!</div>


			</div>
		</div>

	</div>


</body>
</html>