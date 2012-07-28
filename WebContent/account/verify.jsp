<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<%@ page import="pojo.OrderPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="3;URL=index.jsp" />
<title>易食飯</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_order.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/tipTipv13/tipTip.css" />

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_order_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
		<![endif]-->

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script src="<%=request.getContextPath()%>/js/order.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.minified.js"></script>

<script src="<%=request.getContextPath()%>/js/common.js"></script>


</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>

	<div id="bg_canvas_new">
		<div class="left_commbtn" id="feedBackBtn"
			style="position: fixed; right: 0; top: 35%;">
			<a
				href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');"><span><b>同易食飯講幾句</b>
			</span> </a>
		</div>




		<div id="main">


			<div id="info_content">

				<div id="message_display">
					<div style="font-size:18px;padding-left: 400px">
						${message }  
					</div><br>
					<div style="font-size:18px;padding-left: 375px">
						3秒後自動跳轉到<a href="index.jsp">首頁</a>
					</div>
					
				</div>


			</div>

		</div>
		<div id=ordersubmitfooter style="margin-top: 560px;">
			<!-- footer -->
			<table class="tinynewfooter" width="960" border="0" cellspacing="0"
				cellpadding="0" align="center">
				<tr>
					<td align="center" colspan="5" style="padding-top: 18px;">
						Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementById("bg_canvas_new").style.height = "620px";
	</script>

</body>
</html>