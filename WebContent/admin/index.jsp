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
<%@ include file="../include/head.jsp" %>



<script>
$(document).ready(function() {
  $('#enter').delay(1800).fadeIn(1000);
});


</script>

</head>
<body>
<div style="width:550px; border-top:1px solid #d9d9d9;border-bottom:1px solid #d9d9d9; margin-top:30px;margin-left:30px;padding-top:10px;padding-bottom:10px;" class="position_top_left font_NeoSansRegular">	
	<span style="font-size:20px;font-weight:bolder;">System Administration</span>
</div>
<input  id="enter" class="formee-button position_centered_fixed font_NeoSansRegular" style="wdith:100px;margin-top: 80px; margin-left: -50px;display:none;" type="button" value="Enter" />
</body>
</html>