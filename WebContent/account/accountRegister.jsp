<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户注册</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/screen.v1.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/register.v1.css" />


<script type="text/javascript" src="<%=request.getContextPath() %>/js/account/validate.js"></script>
<SCRIPT type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
	//无刷新检测用户是否存在.
	function checkAccount() {
		var userId = document.getElementById("userid").value;
		if ((userId != "") && (validateEmail(userId))) {
			var url = 'checkaccount.action';
			var params = {
				userId : userId
			};
			$.post(url, params, callbackFun, 'text');
		}else{
			alert("Email格式不正確！");
		}
	}
	//回调方法.
	function callbackFun(data) {
		if (data == '0') {
			alert("此帳戶可以註冊");
		} else if(data=='1') {
			alert("此帳戶已存在");
		}
	}
	
	//输入效验
	function inputvalidate(){
		
			var userid = document.getElementById("userid").value;
	 		var nickname = document.getElementById("nickname").value; 		
			var password = document.getElementById("password").value;
			var cpassword = document.getElementById("cpassword").value;	
			var verifycode = document.getElementById("verifycode").value; 
			if (userid==""){
				  document.getElementById("userid").focus();
				  document.getElementById("information").innerHTML = "Email不能為空";
				  return false;
			  }
		     if (nickname==""){
				  document.getElementById("nickname").focus();
				  document.getElementById("information").innerHTML = "昵称不能為空";
				  return false;
			  }
		    if (password==""){
				  document.getElementById("password").focus();
				  document.getElementById("information").innerHTML = "密碼不能為空";
				  return false;
			  }
		    if (cpassword==""){
				  document.getElementById("cpassword").focus();
				  document.getElementById("information").innerHTML = "密碼不能為空";
				  return false;
			  }
		    if (verifycode==""){
				  document.getElementById("verifycode").focus();
				  document.getElementById("information").innerHTML = "驗證碼不能為空";
				  return false;
			  }
		    if(password!=cpassword){
		    	document.getElementById("password").focus();
				document.getElementById("information").innerHTML = "2次密碼輸入不一致";
				document.getElementById("password").value = "";
				document.getElementById("cpassword").value = "";
				return false;
		    }
		    if(!validateEmail(userid)){
		    	document.getElementById("userid").focus();
				document.getElementById("information").innerHTML = "Email格式不正確";
				return false;
		    } 
		    return true;
		}
	
	function show(obj){

		obj.src="verifycode.action?a="+Math.random();
	}
	
</script>

</head>
<body>


<center>




	<s:form action="register" method="post"
		onsubmit="return inputvalidate();">
		<table class="Register">
			<tr>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td class="rig"><span class="Num"><strong>*</strong> </span>&nbsp;Email：</td>
				<td><input type="text" name="userid" id="userid" value="${userid }"
					class="text wid180" /><input type="button"
					onclick="checkAccount()" value="检测账户" />
				</td>
			</tr>

			<tr>
				<td class="rig"><span class="Num"><strong>*</strong> </span>&nbsp;昵称：</td>
				<td><input type="text" name="nickname" id="nickname" value="${nickname }"
					class="text wid180" /></td>
			</tr>


			<tr>
				<td class="rig"><span class="Num"><strong>*</strong> </span>&nbsp;登录密码：</td>
				<td><input type="password" name="password" value=""
					id="password" class="text wid180" /></td>
			</tr>


			<tr>
				<td class="rig"><span class="Num"><strong>*</strong> </span>&nbsp;确认密码：</td>
				<td><input type="password" name="cpassword" value=""
					id="cpassword" class="text wid180" /></td>
			</tr>

			<tr>
				<td class="rig"><span class="Num"><strong>*</strong> </span>&nbsp;验证码：</td>
				<td><input type="text" name="verifycode" value=""
					id="verifycode" class="text wid180" /><img alt="验证码" id="safecode"
					src="verifycode.action"> <a
					href="javascript:show(document.getElementById('safecode'))">看不清?</a>
				</td>
				</td>

			</tr>

			<tr>
				<td><s:submit name="submit" value="同意一下条款,提交"></s:submit>
					&nbsp;<font color="red"><span id="information"><s:property
								value="infomation" /> </span> </font>
				</td>
				<td></td>
			</tr>
		</table>
	</s:form>

<iframe src="<%=request.getContextPath() %>/notification/serviceterms.html" 
width="500" height="145" frameborder=0 scrolling=auto></iframe>


</center>

</body>
</html>