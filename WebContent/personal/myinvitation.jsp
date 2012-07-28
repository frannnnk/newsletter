<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.FavoritePojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_my.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />

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
<script src="<%=request.getContextPath()%>/js/common.js"></script>

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
	<div id="main">


		<jsp:include page="/personal/menu.html"></jsp:include>
		<script>
			document.getElementById('m_myinv').style.background = "#e9e9e9";
		</script>
		<div id="inv_icon"></div>
		<div id="inv_word"></div>
		
		
		
		
		<div id="commentContainer">
		
					<div class="sep sep_inv"></div>
		

			<div class="invInfo">您尚有 <b> ${invitationQuota } </b>個邀請名額，輸入朋友的Email，我們將電郵通知你的朋友。 </div>
			<form action="myinvitation.action">
				<input type="text" value="" name="friendemail" id="friendemail" class="inputCmt"/><input type="image" class="submit" id="btnInv" src="<%=request.getContextPath()%>/picture/common/spacer.gif"/>
				<font color="red" >${message }</font> <input type="hidden" value="1"
					name="type" id="type" />
			</form>
			<br>
		<div class="invInfo">已邀请的好友</div>
			
			
			
			
			
			
			<table cellspacing="0" cellpadding="0" class="commentTbl" border="0">

				<tr>
					<td class="orderTh" width="200" align="center">好友電郵</td>

					<td class="orderTh" width="155" align="center">邀請狀態</td>
				</tr>
				
				<s:iterator value="myinvitationList">
					<tr>
						<td class="commentTd" width=180 align="center">${friendemail }</td>
						<td class="commentTd" align="center">${stateText }</td>
					</tr>
				</s:iterator>

				


			</table>
			
			
			
			
			

		</div>
	</div>

<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
</div>
</body>
</html>