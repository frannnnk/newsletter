<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.RestaurantPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<SCRIPT type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
//食物类别管理
function foodcategorymanage(resid){
	url="foodmanagementaction.action?type=initaddcategory&restaurantid="+resid;
	window.location.href=url;
}
//食物管理
function foodmanage(resid){
	
	var url="foodmanagementaction.action?restaurantid="+resid;
	window.location.href=url;
}
//餐厅派送范围管理
function deliveryscopemanage(resid){
	var url ="restaurantupdate.action?type=4&&resid="+resid;
	window.location.href=url;
}


//编辑
function edit(resid){
	if(document.getElementById("sendprice"+resid).disabled==true){
		document.getElementById("sendprice"+resid).disabled=false;
		document.getElementById("arrivetime"+resid).disabled=false;
		document.getElementById("diliveryfee"+resid).disabled=false;
		document.getElementById("shophour"+resid).disabled=false;
	
		document.getElementById("flag"+resid).disabled=false;
		document.getElementById("submit"+resid).disabled=false;
		document.getElementById("description"+resid).disabled=false;
	
		document.getElementById("rank"+resid).disabled=false;
		
		document.getElementById("editbutton"+resid).value="关闭";
	}else{
		document.getElementById("sendprice"+resid).disabled=true;
		document.getElementById("arrivetime"+resid).disabled=true;
		document.getElementById("diliveryfee"+resid).disabled=true;
		document.getElementById("shophour"+resid).disabled=true;
		document.getElementById("flag"+resid).disabled=true;
		document.getElementById("description"+resid).disabled=true;
		document.getElementById("submit"+resid).disabled=true;
		document.getElementById("rank"+resid).disabled=true;

		document.getElementById("editbutton"+resid).value="修改";
	}
}

//提交修改 ajax提交
function submitupdate(resid){
	var sendprice = document.getElementById("sendprice"+resid).value;
	var arrivetime = document.getElementById("arrivetime"+resid).value;
	var diliveryfee = document.getElementById("diliveryfee"+resid).value;
	var shophour = document.getElementById("shophour"+resid).value;
	var description = document.getElementById("description"+resid).value;
	var rank = document.getElementById("rank"+resid).value;
	var flagobj = document.getElementById("flag"+resid);
	var ishot;
	var iscoorperation;
	var flag;
	  
	  if(flagobj.checked){
		  flag=1;
	  }else{
		  flag=0;
	  }
	
	if(sendprice==""||arrivetime==""||diliveryfee==""||shophour==""){
		alert("信息填寫不完整");
	}else{
		var url = 'restaurantupdate.action';
		var params = {
				resid : resid,
				description:description,
				sendprice:sendprice,
				arrivetime:arrivetime,
				diliveryfee:diliveryfee,
				shophour:shophour,
				flag:flag,
				rank:rank,
				type:1
		};	
		$.post(url, params, callbackFun, 'text');
		
		edit(resid);
	}
}

//回调方法.
function callbackFun(data) {
	if (data == '0') {
		alert("提交成功");
		
	} else {
		
		alert("提交失败,请刷新页面重新操作，若还有问题请联系管理员");
	}
	
}

</script>

<style type="text/css">
a {
	font-size: 8px;
	TEXT-DECORATION: none;
}

body {
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}
</style>
</head>

<body>

	<a href="<%=request.getContextPath()%>/backmanagement/index.html"><font
		size=+2>返回管理首页</font> </a>&nbsp;&nbsp;

	<h1>餐厅信息管理</h1>

	<table>
		<tr align=center>
			<td><b>餐厅ID</b>
			</td>
			<td width=100><b>餐厅名称</b>
			</td>
			<td width=110><b>餐厅说明</b>
			</td>
			<td><b>起送价格</b>
			</td>
			<td><b>派送时间(分钟)</b>
			</td>
			<td><b>派送费用</b>
			</td>
			<td><b>营业时间</b>
			</td>

			<td><b>是否上架</b>
			</td>
			<td><b>排序</b>
			</td>
		</tr>
		<s:iterator value="restaurantList" id="restaurantpojo">

			<tr align=center>
				<td>${restaurantpojo.id }</td>
				<td>${restaurantpojo.name }</td>
				<td><textarea disabled="disabled"
						name=description${restaurantpojo.id}
						id=description${restaurantpojo.id}>${restaurantpojo.description }</textarea>
				</td>
				<td><input disabled="disabled" type="text"
					name=sendprice${restaurantpojo.id} id=sendprice${restaurantpojo.id}
					value=${restaurantpojo.send_price } size=5 />
				</td>
				<td><input disabled="disabled" type="text"
					name=arrivetime${restaurantpojo.id}
					id=arrivetime${restaurantpojo.id}
					value=${restaurantpojo.arrivetime } size=5 /></td>
				<td><input disabled="disabled" type="text"
					name=diliveryfee${restaurantpojo.id}
					id=diliveryfee${restaurantpojo.id}
					value=${restaurantpojo.diliveryfee } size=5 />
				</td>
				<td><input disabled="disabled" type="text"
					name=shophour${restaurantpojo.id} id=shophour${restaurantpojo.id}
					value=${restaurantpojo.shophour } size=8 />
				</td>


				<!-- 是否上架-->
				<td align=center><s:if test="#restaurantpojo.flag==1">
						<input disabled="disabled" type="checkbox"
							name=flag${restaurantpojo.id} id=flag${restaurantpojo.id} checked />
					</s:if> <s:else>
						<input disabled="disabled" type="checkbox"
							name=flag${restaurantpojo.id} id=flag${restaurantpojo.id} />
					</s:else>
				</td>

				<!-- 排序 -->

				<td><input type="text" value="${restaurantpojo.rank}" disabled="disabled"
					name="rank${restaurantpojo.id}" id="rank${restaurantpojo.id}" size=2/></td>

				<td><input type="button" id="editbutton${restaurantpojo.id}"
					value="修改" onclick="edit(${restaurantpojo.id})"> <input
					disabled="disabled" id="submit${restaurantpojo.id}" type="button"
					value="提交" onclick=submitupdate(${restaurantpojo.id})></td>

				<td><a target="blank"
					href="javascript:foodcategorymanage(${restaurantpojo.id})"><font
						size=2>食物类别管理</font> </a> <a target="blank"
					href="javascript:foodmanage(${restaurantpojo.id})"><font size=2>食物管理</font>
				</a> <a target="blank"
					href="javascript:deliveryscopemanage(${restaurantpojo.id})"><font
						size=2>派送范围管理</font> </a>
				</td>

			</tr>
		</s:iterator>
	</table>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</body>
</html>