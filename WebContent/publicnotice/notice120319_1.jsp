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
					易食飯上線
				</font>
			</center>
			<!-- end -->

			<!-- 公告内容 -->
			<div class="noticecontent">
			 <font size=+1>易食飯上線啦~~~再也不用為午餐煩惱了！</font><br><br>
			 白領們，讓我們一起告別午餐的煩惱。易食飯為白領們提供健康，舒心的午餐。
 
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