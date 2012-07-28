<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯-公告</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_publicnotice.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />
</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>
	<div id="bg_canvas">
		
		<div class="content">

			<!-- 标题 -->
			<center>
				<font size=+3>
					易食飯派現金咯！
				</font>
			</center>
			<!-- end -->

			<!-- 公告内容 -->
			<div class="noticecontent">
			<font size=+1>活動規則</font><br><br>
			<b>參與對象：</b>所有易食飯<a target="blank" href="<%=request.getContextPath() %>/account/login.jsp?type=2">註冊用戶</a>都可以參與。<br>
			<b>派發規則：</b>每周一易食飯會在所有註冊用戶中隨機抽取5名易食飯親，派發現金獎勵給他們。<br>
			<b>現金金額：</b>2名用戶可以獲得$100HK；3名用戶可以獲得$50HK；<br>
			<b>公佈時間：</b>每周一11：00，易食飯會在www.ecmeal.hk的公告中公佈現金派發名單，並且還會同步在<a target="blank" href="http://weibo.com/u/2458052944">易食飯官方微博</a>公佈現金派發名單。易食飯會以郵件和電話的方式通知現金獲得用戶。<br>
			<b>派發方式：</b>易食飯會在3個工作日內將現金郵寄給獲獎用戶。
			
			
			
 
			</div>
			<!-- end -->
		</div>

	
				<!-- footer -->
				<table class="tinynewfooter" width="960" border="0" cellspacing="0"
					cellpadding="0" align="center">
					<tr>
						<td align="center" colspan="5" style="padding-top: 0px;">
							Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
					</tr>
				</table>
			

	</div>


</body>
</html>