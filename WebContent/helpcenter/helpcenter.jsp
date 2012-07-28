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
				<a href="/canteen/index.jsp">首頁</a> > 幫助中心
			</div>





			<div class="menu">


				<jsp:include page="/helpcenter/menu.jsp"></jsp:include>



			</div>
			<div class="content">


				<div class="title">如何訂餐？</div>
				<div class="text">
					只需四步操作，外賣就送到你手。<br> 第一步：指定位置；（可以在地圖上指定，也可以在熱搜地點上指定） <br>
					第二步：選擇外賣；<br> 第三步：填寫訂單；（如果是首次落單，需填寫送餐地址；之後落單，送餐地址會自動加載）<br>
					最後一步就是等待外賣送達啦。
				</div>

				<div class="title">派送時間？</div>
				<div class="text">
					11：20前落單，最早12：10前后可以送達。<br>
					11：20-11：50落單，最早12：40前后可以送達。<br>
					11：50-12：20落單，最早13：10前后可以送達。<br>
					12：20-12：50落單，最早13：40前后可以送達。<br>
					12：50-13：20落單，最早14：10前后可以送達。<br>
					13：20-13：50落單，最早14：40前后可以送達。<br>
				</div>

				<div class="title">如何註冊用戶？</div>
				<div class="text">
					您可以點擊易食飯頁面左上角的<a href="/canteen/account/login.jsp?type=2">免費註冊</a>，然後輸入一些簡單的信息，就可以完成註冊了。
				</div>

				<div class="title">註冊用戶嘅特權？</div>
				<div class="text">
					1、可以記錄您常用的用於訂餐的地址，這樣，您就不用每次輸入地址了。
					<p>2、可以進行網上下單，為您提供更便捷的服務。
					<p>3、可以對您訂購的外賣進行評分，讓我們做的更好。
					<p>4、可以參與易食飯每週一的微博抽獎，每週都有5名註冊用戶可以獲得易食飯的現金獎勵。
					<p>5、可以邀請好友使用我地嘅服務。
					<p>註冊賬號更多好處，敬請期待……
					<p>
				</div>

				<div class="title">如何添加送餐地址？</div>
				<div class="text">
					有2種方式可以添加送餐地址，一種是當您進行網上下單，訂單被創建後，系統會自動將您的填寫的送餐地址保存起來，方便下次使用；另一種是，用戶可以進入頁面右上角的地址管理，進行地址添加和修改。
				</div>


				<div class="title">如何進行意見反饋？</div>
				<div class="text">您可以到頁面右上角“反饋建議”已經頁面的“同易食飯說幾句”提交意見反饋。</div>


				<div class="title">如果你有推薦嘅餐廳？</div>
				<div class="text">
					可以通過發送電子郵件方式告知我們，易食飯郵箱地址 business@ecmeal.hk<br>
					如果你推薦的餐廳被易食飯採納，易食飯會奉送$HK500現金代表所有嘅易食飯用戶作為答謝。
				</div>



			</div>

		</div>
		<!-- 流量统计 -->
		<div style="display: none">
			<script
				src="http://s24.cnzz.com/stat.php?id=3627513&web_id=3627513&show=pic"
				language="JavaScript"></script>
		</div>
		<!-- end -->
</body>
</html>