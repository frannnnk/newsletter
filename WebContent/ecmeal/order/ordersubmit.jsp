<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<%@ page import="hk.franks.newsletter.pojo.OrderPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>


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

		<form name="orderform" action="ordersubmit.action" onsubmit="">


			<div id="main">


				<div id="info_content">
					<div id="icon_done"></div>
					<div id="message_display">
						<div id="main_message">訂單已經成功提交</div>

						<s:iterator value="orderpojoList" id="orderpojo">
							<div id="order_summary_message">
								<font color=black>訂單号：</font><font color="blue">${orderpojo.orderid}</font> <br /> 
								<font color=black>送餐地址：</font><font color="blue"> ${orderpojo.address} </font> <br> 
								<font color=black>支付方式：</font><font color="blue"> 餐到付款 </font> <br> 
								<font color=black>您共需支付金額：</font><font color="red"><b>$${orderpojo.summoney}</b>
							</div>
						</s:iterator>
						
					
						

						<div id="alert_message">
							<span style="font-weight: bolder;">小提示：</span>請查收訂單確認郵件，客服會在5分鐘後發送訂單確認郵件到您的註冊郵箱！

						</div>
						<a href="${mailhostAddress }" target="blank"
							style="TEXT-DECORATION: none" class="ordersubmitcheckbutton">進入我嘅郵箱</a>&nbsp;<a
							href="<%=request.getContextPath()%>/myordersearch.action?type=1&requestpagenum=1"
							style="TEXT-DECORATION: none" class="ordersubmitcheckbutton">查看訂單狀態</a>
					</div>


				</div>

			</div>
			<div id=ordersubmitfooter style="margin-top: 540px;">
				<!-- footer -->
				<table class="tinynewfooter" width="960" border="0" cellspacing="0"
					cellpadding="0" align="center">
					<tr>
						<td align="center" colspan="5" style="padding-top: 18px;">
							Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		document.getElementById("bg_canvas_new").style.height = "623px";
	</script>
	<!-- 流量统计 -->
	<div style="display: none">
		<script
			src="http://s24.cnzz.com/stat.php?id=3627513&web_id=3627513&show=pic"
			language="JavaScript"></script>
	</div>
	<!-- end -->
</body>
</html>