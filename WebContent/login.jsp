<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NewsLetter -- Login</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Title will be insert by this file -->
<%@ include file="include/head.jsp" %>



<script>
$(document).ready(function() {
  $('#enter').delay(1800).fadeIn(1000);
});


function login(){

	// encrypt password
	$('#encryptedPassword').val(hex_md5($('#encryptedPassword').val()));

	$.post("login.action?action=login", $('#loginForm').serialize(),function(data){
			//alert(data);
			
			var obj = jQuery.parseJSON(data);
			
			
			if ("S" == obj.result) {
				loginSucceed();
			} else {
				loginError();
			}
			
	});

}

function loginSucceed(){
	$('#encryptedPassword').val("");
	window.open("member/main.jsp","_self");
}


function loginError(msg){

	$('#encryptedPassword').val("");

	$("#msg").addClass("formee-msg-error");	
	$("#msg").html("Error La");
	//$("#msg").fadeIn("slow");
	$("#loginBox").effect("shake", { times:5 }, 50);
	

}


</script>

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_index_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
<![endif]-->
</head>
<body>
<form action="" name="loginForm" id="loginForm">
<div style="width:550px; border-bottom:0px solid #d9d9d9;margin-top: -250px; margin-left: -275px; padding-top:30px;padding-bottom:30px;" class="position_centered_fixed font_NeoSansRegular formee">	
		<span style="font-size:30px;">Welcome to our <span style="font-size:30px;font-weight:bolder;">Friendly Tattler</span> project.</span>
	</div>
	
	<div id="msg" style="width:390px; margin-top: -185px; margin-left: -235px; display:none; font-size:20px;" class="position_centered_fixed font_NeoSansRegular formee "></div>
	
	<div  id="loginBox" style="width:450px; margin-top: -135px; margin-left: -235px; padding-top:30px;padding-bottom:30px;" class="position_centered_fixed font_NeoSansRegular formee">	
	
		<fieldset>
    	<div  class="login" style="margin:30px;">
            <div class="grid-12-12">
                    <label>Email <em class="formee-req">*</em></label>
                   <input type="text" value=""  name="userid"/>
            </div>
            <div class="grid-12-12 clear">
                    <label>Password <em class="formee-req">*</em></label>
                   <input type="password" value=""  name="encryptedPassword" id="encryptedPassword"/>
            </div>
              
            
            <div class="grid-12-12">
                   <input class="right" type="button" title="Login" value="Login" onclick="login();"/>
            </div>
            <div class="grid-12-12">
                  <span class="right"><a href="register.jsp">Need an account?</a></span>
            </div>
            
            
        </div>
    </fieldset>
</div>

</form>

</body>
</html>