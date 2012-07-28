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
				<div class="title">訂單狀態</div>
				<div class="text">
					<a href="#tip10">訂單處理中</a><br /> <a href="#tip11">製作派送中</a><br /> <a
						href="#tip12">已取消</a><br /> <a href="#tip13">成功送達</a><br />
				</div>
				<div class="title">各处理状态明细</div>
				<div class="text">
					<div id=tip10>
						<b>訂單處理中</b>
					</div>
					狀態解釋：<span style="color: #666;">此狀態下，說明訂單已經被客服收到，正在處理中。</span><br />
					用戶處理：<span style="color: #666;">用戶可進入自己的郵箱，查收訂單郵件</span><br />
					<br />
					<div id=tip11>
						<b>製作派送中</b>
					</div>
					狀態解釋：<span style="color: #666;">此狀態下，說明外賣正在製作派送中。</span><br />
					用戶處理：<span style="color: #666;">餐點正在製作派送中，請您耐心等待外送員將餐點送到您的府上。</span><br />
					<br />
					<div id=tip12>
						<b>已取消</b>
					</div>
					狀態解釋：<span style="color: #666;">此狀態下，說明訂單未能處理成功。</span><br />
					用戶處理：<span style="color: #666;">如還要訂購需重新發起訂單或者聯繫客服恢復訂單</span><br />
					<br>
					<div id=tip13>
						<b>成功送達</b>
					</div>
					狀態解釋：<span style="color: #666;">此狀態下，說明外賣已成功送達。</span><br /> 用戶處理：<span
						style="color: #666;">可以對此訂單進行評價</span><br />
				</div>
				<div class="title">如何查看訂餐狀態</div>
				<div class="text">
					方法1：完成訂單創建後，點擊“查看訂單狀態”按鈕，查看訂單狀態<br /> 方法2：點擊頁面右上方“我的訂單”，查看訂單狀態<br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>