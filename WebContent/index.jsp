<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NewsLetter</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Title will be insert by this file -->
<%@ include file="include/head.jsp" %>



<script>
$(document).ready(function() {
  $('#enter').delay(1800).fadeIn(1000);
});


</script>

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_index_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
<![endif]-->
</head>
<body>
<div style="width:550px; border-top:1px solid #d9d9d9;border-bottom:1px solid #d9d9d9;margin-top: -50px; margin-left: -275px; padding-top:30px;padding-bottom:30px;" class="position_centered_fixed font_NeoSansRegular">	
	<span style="font-size:30px;">Welcome to our <span style="font-size:30px;font-weight:bolder;">Friendly Tattler</span> project.</span>
</div>
<input  id="enter" class="formee-button position_centered_fixed font_NeoSansRegular" style="wdith:100px;margin-top: 80px; margin-left: -50px;display:none;" type="button" value="Enter" />
</body>
</html>