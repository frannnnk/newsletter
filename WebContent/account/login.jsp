<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入註冊-易食飯</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_account.css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/account/validate.js"></script>
<SCRIPT type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>

<script type="text/javascript">
  function inputvalidate(){
	  var userid = document.getElementById("userid").value;
	  var password = document.getElementById("password").value;
	  if (userid==""){
		  document.getElementById("userid").focus();
		  document.getElementById("information").innerHTML = "用户名不能为空";
		  return false;
	  }
	  if (password==""){
		  document.getElementById("password").focus();
		  document.getElementById("information").innerHTML = "密码不能为空";
		  return false;
	  }
	  return true;
  }
  
  //无刷新检测用户是否存在.
	function checkAccount() {
		var userId = document.getElementById("reguserid").value;
		if ((userId != "") && (validateEmail(userId))) {
			var url = 'checkaccount.action';
			var params = {
				userId : userId
			};	
			$.post(url, params, callbackFun, 'text');
		}else{
			if (userId != "")
				document.getElementById("regrinformation").innerHTML="Email格式不正确";
			else
				document.getElementById("regrinformation").innerHTML="";
		}
	}
	//回调方法.
	function callbackFun(data) {
		if (data == '0') {
			
			document.getElementById("regrinformation").innerHTML="";
		} else if(data=='1') {
			
			document.getElementById("regrinformation").innerHTML="此账户已存在";
		}
	}
	
	//输入效验
	function reginputvalidate(){
		
			var userid = document.getElementById("reguserid").value;
	 		var nickname = document.getElementById("regnickname").value; 		
			var password = document.getElementById("regpassword").value;
			
			if (userid==""){
				  document.getElementById("reguserid").focus();
				  document.getElementById("regrinformation").innerHTML = "Email不能为空";
				  return false;
			  }
			
		     if (nickname==""){
				  document.getElementById("regnickname").focus();
				  document.getElementById("regrinformation").innerHTML = "昵称不能为空";
				  return false;
			  }
		    if (password==""){
				  document.getElementById("regpassword").focus();
				  document.getElementById("regrinformation").innerHTML = "密码不能为空";
				  return false;
			  }
		  
		    if(!validateEmail(userid)){
		    	document.getElementById("reguserid").focus();
				document.getElementById("regrinformation").innerHTML = "Email格式不正确";
				return false;
		    } 
		    return true;
		}
	
	function show(obj){

		obj.src="verifycode.action?a="+Math.random();
	}
	
	function init(){
		var type = <%=request.getParameter("type")%>;
		if(type==1)
			document.getElementById("userid").focus();
		else if(type==2)
			document.getElementById("reguserid").focus();
	}

</script>

</head>
<body onload="init()">



	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>

	<div id="bg_canvas_new">



		<!--登录界面-->



		<div id="loginPanel">

			<div id="loginTitle"></div>
			<div id="regTitle"></div>
			<div id="VtSep"></div>
			<div id="loginInputField">

				<form action="<%=request.getContextPath()%>/login.action"
					method="post" theme="simple" onsubmit="return inputvalidate();">

					<table class="Login" cellspacing="10">

						<tr>
							<td>Email</td>
							<td><input type="text" id="userid" name="userid"
								class="inputCmt" class="text wid180" /></td>
						</tr>

						<tr>
							<td>密碼</td>
							<td><input type="password" id="password" name="password"
								class="inputCmt" class="text wid180" /></td>
						</tr>
						<%
							Object attrObj = request.getSession().getAttribute("wrongloginNum");
							if (attrObj != null) {
								Integer wrongloginNum = (Integer) attrObj;
								if (wrongloginNum >= 2) {
						%>
						<tr>
							<td width="60">驗證碼
							<td><input type="text" id="verifycode" name="verifycode"
								class="inputverifycode" value="" class="text wid50" /> <img
								alt="验证码" id="safecode" src="verifycode.action"></td>
							</td>
						</tr>

						<%
							}
							}
						%>

						<tr>
							<td></td>
							<td><input type="checkbox" id="autologin" name="autologin"
								checked /> 下次自動登入</td>
						</tr>
						<tr>
							<td></td>
							<td><font color="red"><span id="information"><s:property
											value="logininformation" /> </span> </font></td>
						</tr>



					</table>

					<input type="image" value="登录" class="submit" id="btnLogin"
						src="<%=request.getContextPath()%>/picture/common/spacer.gif" />
					<font color="#930000">
				</form>

			</div>






			<!-- regPanel -->
			<div id="regInputField">
				<form action="<%=request.getContextPath()%>/register.action"
					method="post" theme="simple" onsubmit="return reginputvalidate();">

					<table cellspacing="10">

						<tr>
							<td>Email</td>
							<td><input type="text" onBlur="checkAccount()"
								name="reguserid" id="reguserid" value="${reguserid }"
								class="inputCmt" /></td>
						</tr>

						<tr>
							<td width="60">會員名稱</td>
							<td><input type="text" name="regnickname" id="regnickname"
								value="${regnickname }" class="inputCmt" /></td>
						</tr>


						<tr>
							<td>新密碼</td>
							<td><input type="password" name="regpassword" value=""
								class="inputCmt" id="regpassword" /></td>
						</tr>

						<tr>
							<td></td>
							<td><font color="red"><span id="regrinformation"><s:property
											value="infomation" /> </span> </font></td>
						</tr>

					</table>
					<input type="image" value="登录" class="submit" id="btnReg"
						src="<%=request.getContextPath()%>/picture/common/spacer.gif" />




				</form>

			</div>
		</div>
		<div style="margin-top:400px;">
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

</body>
</html>