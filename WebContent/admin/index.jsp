<%@page import="model.UsersModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<jsp:useBean id="logic" scope="request" class="hk.franks.newsletter.logic.AccountRelateLogic" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NewsLetter</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Title will be insert by this file -->
<%@ include file="../include/head.jsp" %>
<% List<UsersModel> users = logic.getWaitingApprovalUserList();




%>



<script>
$(document).ready(function() {
  
});

function approveUser(userId){
	
	
	$.post("<%=request.getContextPath()%>/account.action?action=approveuser&userid="+userId, $('#loginForm').serialize(),function(data){
			
			var obj = jQuery.parseJSON(data);
			
			
			if ("S" == obj.result) {
				Succeed();
				$('#tr_user_'+userId).fadeOut('slow',function(){
					$('#tr_user_'+userId).remove();
				});
				
			} else {
				Error();
			}
			
	});
	
	
}

function Succeed(){
	
	var n = noty({text: 'User has been approved.',type: 'success'});	
	
}


function Error(){

	var n = noty({text: 'Unable to approve user.',type: 'error'});	

	

}


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


</script>

<style>

.tableTd{
	border-bottom:1px solid #d9d9d9;width:400px;
	padding:8px;
}
.smallbt{
	font-size:15px;
	padding:3px;
	padding-left:10px;
	padding-right:10px;
	margin:0px;
}

</style>

</head>
<body>
<div style="width:550px; border-top:1px solid #d9d9d9;border-bottom:1px solid #d9d9d9; margin-top:30px;margin-left:30px;padding-top:10px;padding-bottom:10px;" class="position_top_left font_NeoSansRegular">	
	<span style="font-size:20px;font-weight:bolder;">System Administration -- User Approval</span>
</div>



<div style="width:800px;  margin-top:130px;margin-left:30px;padding-top:10px;padding-bottom:10px;" class=" font_NeoSansRegular">	
	<table  cellspacing="0" style="width:750px;" border="0">
	
	<%  for (UsersModel user: users) {%>
		<tr id="tr_user_<%=user.getUserId()%>">
		
			<td  class="tableTd" style="width:50px;">
					<%=user.getScreenName() %>
			</td>
			
			<td  class="tableTd" style="width:250px;">
					<%=user.getEmail() %>
			</td>
			
			<td  class="tableTd" style="width:10px;">
					<%=user.getGender() %>
			</td>
			
			<td  class="tableTd" style="width:140px;">
					<%=user.getLanguage() %>
			</td>
			
			<td  class="tableTd" style="width:150px;">
					<%=user.getCreateDate() %>
			</td>
			
			<td  class="tableTd" style="width:100px;">
					<input type="button" class="formee-button smallbt" value="Approve" onclick="approveUser('<%=user.getUserId()%>');"/>
			</td>
		</tr>
		
	<%} %>
	</table>
</div>



<input  id="enter" class="formee-button position_centered_fixed font_NeoSansRegular" style="wdith:100px;margin-top: 80px; margin-left: -50px;display:none;" type="button" value="Enter" />
</body>
</html>