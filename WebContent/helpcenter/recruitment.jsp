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

			<div class="title">加入我们</div>
			<div class="text">
				選擇適合您的職位，將簡歷投至<A>hr@ecmeal.hk</A>(標題請註明職位名稱，如：應聘-客服專員)<br />
				易食飯的大門永遠給自信的你敞開。
				<br /><br />
				<b>客服專員</b><br />
				職位要求：<br />
				1.具有較好的應變能力和執行能力、溝通能力；<br />
				2.具備較好的學習能力；<br />
				3.具備良好的團隊合作精神，責任感和敬業精神;<br /><br />
				
				<b>銷售代表</b><br />
				職位要求：<br />
				1.具有較好的應變能力和執行能力、溝通能力；<br />
				2.具備較好的學習能力；<br />
				3.具備良好的團隊合作精神，責任感和敬業精神;<br /><br />
				
			</div>
		</div>
</div>

	</div>
</body>
</html>