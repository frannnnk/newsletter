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
				<div class="title">取消訂單流程</div>
				<div class="text">
					步驟1、如有特殊情況需要取消或者恢複訂單，請立即與客服取得聯系！ （<a
						href="contactcustomservice.jsp">聯系客服</a>）。<br />
					步驟2、告知訂單的相關信息後，等待客服的處理。<br />
				</div>
				<div class="title">取消訂單規則</div>
				<div class="text">
					1、用戶如因突發事件需要取消訂單，必須盡早告知客服人員，並等待處理結果；<br /> <span
						style="color: #ff0000">2、如店鋪已經開始制作餐品，用戶就不能取消訂單；</span><br />
					3、在訂單無法取消的情況下，如果用戶惡意拒絕接收餐品，易食飯有權追究其法律責任，並要求用戶賠償所有損失；<br />
					4、以上規則的最終解釋權歸易食飯網所有。
				</div>


			</div>
		</div>
	</div>
</body>
</html>