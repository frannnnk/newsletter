<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ page import="hk.franks.newsletter.pojo.DeliverScopePojo"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantDeliverScopePojo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派送范围管理</title>
<style type="text/css">
.button {
	width: 80px;
	height: 38px;
}
</style>
<SCRIPT type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
 //编辑
function edit(scopeid){
	if(document.getElementById("availableflag"+scopeid).disabled==true){
		document.getElementById("availableflag"+scopeid).disabled=false;
		document.getElementById("submit"+scopeid).disabled=false;
		document.getElementById("del"+scopeid).disabled=false;
		document.getElementById("editbutton"+scopeid).value="关闭";
	}else{
		document.getElementById("availableflag"+scopeid).disabled=true;
		document.getElementById("submit"+scopeid).disabled=true;
		document.getElementById("del"+scopeid).disabled=true;
		document.getElementById("editbutton"+scopeid).value="修改";
	}
}
 
 
//提交保存新增派送范围
function submitSave(){
	var resid = document.getElementById("restaurantid").value;
	var deliveryscopebox = document.getElementsByName("deliveryscopebox");
    var deliveryscopeids="";
    for(var i=0;i<deliveryscopebox.length;i++){
    	if(deliveryscopebox[i].checked){
    		deliveryscopeids = deliveryscopeids +","+deliveryscopebox[i].value;
    	}
    }
	
	if(deliveryscopeids==""){
		alert("没有新增，保存个锤子");
	}else{
		
		var url = 'restaurantupdate.action';
		var params = {
				resid : resid,
				deliveryscopeids:deliveryscopeids,
				type:"2"
		};	
		
		$.post(url, params, callbackFunForSave, 'text');
		document.getElementById("submitbtn").disabled=true;
	}
}

//回调方法.
function callbackFunForSave(data) {
	if (data == '0') {
		alert("添加成功");
		window.location.reload();
	} else {
		
		alert("提交失败,请刷新页面重新操作，若还有问题请联系管理员");
	}
}


//提交保存修改派送范围
function submitUpdate(scopeid){
	var resid = document.getElementById("restaurantid").value;
	var availflagobj = document.getElementById("availableflag"+scopeid);
	var availfalg=null;
	if(availflagobj.checked){
		 availfalg=1;
	 }else{
		  availfalg=0;
	  }
	if(availfalg==null){
		alert("找管理员");
	}else{
		var url = 'restaurantupdate.action';
		var params = {
				resid : resid,
				restaurantscopeid:scopeid,
				scopeflag:availfalg,
				type:"3"
		};	
		$.post(url, params, callbackFunForUpdate, 'text');
		edit(scopeid);
	}
}

//回调方法.
function callbackFunForUpdate(data) {
	if (data == '0') {
		alert("修改成功");
		
	} else {
		
		alert("提交失败,请刷新页面重新操作，若还有问题请联系管理员");
	}
}

//刪除
function delscope(parscopeid){
	var resid = document.getElementById("restaurantid").value;	
	var url = 'restaurantupdate.action';
	var params = {
			resid : resid,
			restaurantscopeid:parscopeid,
			type:"5"
	};	
	$.post(url, params, callbackFunFordel, 'text');	
}


//回调方法.
function callbackFunFordel(data) {
	if (data == '1') {
		alert("刪除成功");
		window.location.reload();
	} else {
		
		alert("提交失败,请刷新页面重新操作，若还有问题请联系管理员");
	}
}
 
</script>
<style type="text/css">
body{
font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}
</style>
</head>
<body>
<a href="<%=request.getContextPath() %>/restaurantupdate.action?type=0">返回餐厅管理页面</a>
	<form action="foodmanagementaction.action">

	<!-- 餐馆 -->
	<div>
		<h1>
			<b><font color="blue">${restaurantPojo.name }</font>派送范围管理</b>
		</h1>
	</div>

	<div>
		<font size="+2"><b>添加派送范围</b> </font><br> <br>
		<s:iterator value="resAvailChooosedeliveryList" id="deliverScopePojo">
			<lable> <input type="checkbox" name="deliveryscopebox"
				id="deliveryscopebox" value="${deliverScopePojo.id }">${deliverScopePojo.description
			}</input> </lable>
		</s:iterator>
		<br> <br> <input type="button" onclick="submitSave()" id="submitbtn"
			value="保存" class="button" />
	</div>
	<hr>
	<br>
	<div>
		<font size="+2"><b>修改派送范围</b> </font><br> <br>
		<table>
			<tr align=center>
				<td><b>ID</b>
				</td>
				<td width=100><b>描述</b>
				</td>
				<td width=150><b>详细描述</b>
				</td>
				<td><b>是否启用</b>
				</td>
				<td></td>
				<td></td>
			</tr>
			<s:iterator value="restaurantDeliveryList" id="resdeliverScopePojo">
				<tr align=center>
					<td>${resdeliverScopePojo.id }</td>
					<td>${resdeliverScopePojo.description }</td>
					<td>${resdeliverScopePojo.note }</td>
					<td><s:if test="#resdeliverScopePojo.availableflag==1">
							<input disabled="disabled" type="checkbox"
								name=availableflag${resdeliverScopePojo.id}
								id=availableflag${resdeliverScopePojo.id} checked />
						</s:if> <s:else>
							<input disabled="disabled" type="checkbox"
								name=availableflag${resdeliverScopePojo.id}
								id=availableflag${resdeliverScopePojo.id} />
						</s:else></td>
					<td><input type="button"
						id="editbutton${resdeliverScopePojo.id}" value="修改"
						onclick="edit(${resdeliverScopePojo.id})" /> <input
						disabled="disabled" id="submit${resdeliverScopePojo.id}"
						type="button" value="提交"
						onclick="submitUpdate(${resdeliverScopePojo.id})"/>
						
						<input
						disabled="disabled" id="del${resdeliverScopePojo.id}"
						type="button" value="刪除"
						onclick="delscope(${resdeliverScopePojo.id})"/>
						
					</td>
				</tr>
			</s:iterator>


		</table>

	</div>

	<input type="hidden" id="restaurantid" name="restaurantid"
		value="${restaurantPojo.id }" />
</body>
</html>