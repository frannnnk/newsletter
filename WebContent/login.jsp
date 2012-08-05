<%@page import="hk.franks.newsletter.controller.utils.CommonUtil"%>
<%@page import="hk.franks.newsletter.controller.utils.ConstantUtil"%>
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


$.noty.defaults = {
		  layout: 'topCenter',
		  theme: 'default',
		  type: 'alert',
		  text: '',
		  dismissQueue: false, // If you want to use queue feature set this true
		  template: '<div class="noty_message font_NeoSansRegular"><span class="noty_text"></span><div class="noty_close"></div></div>',
		  animation: {
		    open: {height: 'toggle'},
		    close: {height: 'toggle'},
		    easing: 'swing',
		    speed: 500 // opening & closing animation speed
		  },
		  timeout: 3000, // delay for closing event. Set false for sticky notifications
		  force: false, // adds notification to the beginning of queue when set to true
		  modal: false,
		  closeWith: ['click'], // ['click', 'button', 'hover']
		  callback: {
		    onShow: function() {},
		    afterShow: function() {},
		    onClose: function() {},
		    afterClose: function() {}
		  },
		  buttons: false // an array of buttons
};



$(document).ready(function() {
  	
  	
  	<%
if ("100".equalsIgnoreCase(request.getParameter("m"))) {
%>


var nbn = noty({text: 'You need to login first to access this page.',type: 'information',modal: false,dismissQueue: true,force: true,
					 animation: {
				    open: {height: 'toggle'},
				    close: {height: 'toggle'},
				    easing: 'swing',
				    speed: 500 // opening & closing animation speed
				  },
				 timeout: 4000});
				 
				 

<%


} else if ("101".equalsIgnoreCase(request.getParameter("m"))) {

%>
var nbn = noty({text: 'You need to have the administrator role to access this page.',type: 'information',modal: false,dismissQueue: true,force: true,
					 animation: {
				    open: {height: 'toggle'},
				    close: {height: 'toggle'},
				    easing: 'swing',
				    speed: 500 // opening & closing animation speed
				  },
				 timeout: 4000});

<%

}  else if ("102".equalsIgnoreCase(request.getParameter("m"))) {

%>
var nbn = noty({text: 'You are already logged in. However you dont have the administartor role to access this page.',type: 'error',modal: false,dismissQueue: true,force: true,
					 animation: {
				    open: {height: 'toggle'},
				    close: {height: 'toggle'},
				    easing: 'swing',
				    speed: 500 // opening & closing animation speed
				  },
				 timeout: false});

<%

} else if ("103".equalsIgnoreCase(request.getParameter("m"))) {

%>
var nbn = noty({text: 'Your account is currently pending approval.<br/>We will process your registration in 2 days, once your account is approved, we will notice you by email.',type: 'error',modal: false,dismissQueue: true,force: true,
					 animation: {
				    open: {height: 'toggle'},
				    close: {height: 'toggle'},
				    easing: 'swing',
				    speed: 500 // opening & closing animation speed
				  },
				 timeout: false});

<%

} 

%>
  	
});


function login(){

	// encrypt password
	$('#encryptedPassword').val(hex_md5($('#encryptedPassword').val()));

	$.post("<%=request.getContextPath()%>/account.action?action=login", $('#loginForm').serialize(),function(data){
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
	<% 
		String path = "";
		if(!CommonUtil.isExNull((String)session.getAttribute(ConstantUtil.FILTER_REDIRECT_PATH))) {
			path = (String)session.getAttribute(ConstantUtil.FILTER_REDIRECT_PATH);
			session.setAttribute(ConstantUtil.FILTER_REDIRECT_PATH,"");
		} else {
			path = "member/main.jsp";
		}
	 %>
	window.open("<%=path%>","_self");
}


function loginError(msg){

	$('#encryptedPassword').val("");

	$("#msg").addClass("formee-msg-error");	
	$("#msg").html("Error La");
	//$("#msg").fadeIn("slow");
	$("#loginBox").effect("shake", { times:5 }, 50);
	

}







</script>

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