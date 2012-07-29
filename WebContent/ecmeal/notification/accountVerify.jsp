<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.AddressInfoPojo"%>
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
			href="<%=request.getContextPath()%>/css/main/bc_my_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
<![endif]-->

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script src="<%=request.getContextPath()%>/js/my.js"></script>
<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>

<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.minified.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>


</head>


<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>
<div id="bg_canvas">
	<center>
		<br>



<div id="main">
			<div id="stages_submit"></div>

			<div id="info_content">
				<div id="icon_done"></div>
				<div id="message_display">
					<div id="main_message">${message } </div>

					<a href="<%=request.getContextPath()%>/index.jsp">去首頁</a>

				</div>


			</div>

		</div>






	</center>
		<jsp:include page="/include/footer_common.jsp"></jsp:include>
	</div>
	
</body>
</html>