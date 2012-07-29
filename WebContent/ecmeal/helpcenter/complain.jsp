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
				<div class="title">服务时间</div>
				<div class="text">
					<b>咨詢服務時間：</b><span style="color: #ff0000">10：30～21：00</span><br />
					<b>投訴處理時間：</b><span style="color: #ff0000">投訴提交後的1個工作日，客服會向您返回處理結果。</span>
				</div>
				<div class="title">遇上何事可以投訴？</div>
				<div class="text">
					1、易食飯外賣網站操作不方便；<br /> 2、餐品價格與實際不符；<br /> 3、配送超時；<br />
					4、餐點與訂單不符；<br /> 5、配送人員態度不佳。<br /> <span style="color: #ff0000">您的投訴也是我們前進的動力，我們將竭力改進，非常感謝。</span></br>
					<span style="font-size: 15px">您可以點擊頁面右上角的“反饋建議”進行諮詢和投訴;
				</div>



			</div>



		</div>
	</div>
</body>
</html>