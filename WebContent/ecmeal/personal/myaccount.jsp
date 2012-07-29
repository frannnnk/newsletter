<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ page import="hk.franks.newsletter.pojo.FoodPojo"%>
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
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>
	
	
<script type="text/javascript">


</script>

</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>

<div id="bg_canvas">

	<div id="main">
	
		
			<jsp:include page="/personal/menu.html"></jsp:include>
			<script>
					 document.getElementById('m_myaccount').style.background="#e9e9e9";
			</script>
			
			<div id="account_icon"></div>
			<div id="account_word"></div>
			<div id="passwordEdit">
				<div id="passwordIcon"></div>
				<div class="sep sep_addr"></div>
				
				<form name="passwordform" action="updateaccount.action" method="post" >
				<table cellspacing="0" cellpadding="0" class="addressTbl" >
					<tr >
						<td class="addressTdNnew" width="100">原有密碼</td>
						<td class="addressTdNnew" width="600"><input type="password" name="oldpassword" /></td>
					</tr>
					<tr >
						<td class="addressTdNnew" width="100">新密碼</td>
						<td class="addressTdNnew" width="600"><input type="password" name="newpassword"/></td>
					</tr>
					<tr >
						<td class="addressTdNnew" width="100">再次輸入新密碼</td>
						<td class="addressTdNnew" width="600"><input type="password" name="confrimnewpassword"/>
						<font color="red">${message }	</font>
						</td>
					</tr>
					
				</table>
				</form>
				
			
			
			
			</div>
			
		<a href="javascript:document.passwordform.submit()"><div id="savePasswordButton"></div></a>
			
			
			<!-- 
			<div id="emailEdit">
				<div id="emailIcon"></div>
				<div class="sep sep_addr"></div>
				
				<table cellspacing="0" cellpadding="0" class="addressTbl" >
					<tr >
						<td class="addressTdNnew" width="100">原有Email地址</td>
						<td class="addressTdNnew" width="600"><input type="text" /></td>
					</tr>
					<tr >
						<td class="addressTdNnew" width="100">登陸密碼</td>
						<td class="addressTdNnew" width="600"><input type="text" /></td>
					</tr>
					<tr >
						<td class="addressTdNnew" width="100">新Email地址</td>
						<td class="addressTdNnew" width="600"><input type="text" /></td>
					</tr>
					
					
					
					
				</table>
				
				<div class="sep sep_addr"></div>
			
			
			
			</div>
<div id="saveEmailButton"></div>


 -->

	</div>

	<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
</div>
</body>
</html>