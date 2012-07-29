<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ page import="hk.franks.newsletter.pojo.FoodCategoryPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	margin-bottom: 10px;
}

body {
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}
</style>

<SCRIPT type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery/jquery-1.3.2.min.js"></script>

<script type="text/javascript">

//编辑
function edit(categoryid){
	if(document.getElementById("categroyname"+categoryid).disabled==true){
		document.getElementById("categroyname"+categoryid).disabled=false;
		document.getElementById("foodcategroy"+categoryid).disabled=false;
		document.getElementById("rank"+categoryid).disabled=false;	
		document.getElementById("submit"+categoryid).disabled=false;
		document.getElementById("delete"+categoryid).disabled=false;
		document.getElementById("flag"+categoryid).disabled=false;
		document.getElementById("edit"+categoryid).value="关闭";
	}else{
		document.getElementById("categroyname"+categoryid).disabled=true;
		document.getElementById("foodcategroy"+categoryid).disabled=true;
		document.getElementById("rank"+categoryid).disabled=true;	
		document.getElementById("submit"+categoryid).disabled=true;
		document.getElementById("delete"+categoryid).disabled=true;
		document.getElementById("flag"+categoryid).disabled=true;
		document.getElementById("edit"+categoryid).value="修改";
	}
}


//提交修改 ajax提交
function submitUpdate(categoryid){
	
	var name=document.getElementById("categroyname"+categoryid).value;
	var description = document.getElementById("foodcategroy"+categoryid).value;
	var rank = document.getElementById("rank"+categoryid).value;
	var flagobj = document.getElementById("flag"+categoryid);
	var flag;
	 if(flagobj.checked){
		  flag=1;
	  }else{
		  flag=0;
	  }
	
	if(name==""||rank==""){
		alert("信息填寫不完整");
	}else{
		 
		var url = 'foodmanagementaction.action';
		var params = {
				categoryid : categoryid,
				categoryname:name,
				categorydescription:description,
				categoryrank:rank,
				categoryflag:flag,
				type:"foodcategoryupdate"
		};	
		$.post(url, params, callbackFunForUpdate, 'text');
		edit(categoryid);
	}
}

//回调方法.
function callbackFunForUpdate(data) {
	if (data == '0') {
		alert("提交成功");
		
	} else {
		
		alert("提交失败,请刷新页面重新操作，若还有问题请联系管理员");
	}
}

//删除此类别，连带食物一起删除
function deleteCategory(categoryid){
	if(window.confirm("are you sure to do this operation?")){
		window.location.href="foodmanagementaction.action?type=foodcategorydelete&restaurantid=${restaurantPojo.id }&categoryid="+categoryid;
	}
}


</script>

</head>
<body>

	<a href="<%=request.getContextPath()%>/restaurantupdate.action?type=0">返回餐厅管理页面</a>&nbsp;&nbsp;
	


	<form action="foodmanagementaction.action">

		<!-- 餐馆 -->
		<div>
			<h1>
				<b><font color="blue">${restaurantPojo.name }</font>食物类别添加</b>
			</h1>
		</div>

		<!-- 添加食物类别 -->
		<div>

			<table>
				<tr>
					<td>食物类别</td>
					<td><input type="text" id="foodcategory" name="foodcategory">
					</td>
				</tr>
				<tr>
					<td>类别说明</td>
					<td><input type="text" id="fcdescription" name="fcdescription">
					</td>
				</tr>
			</table>


		</div>

		<input type="submit" value="提交" style="width: 120px; height: 50px">

		<a
			href="<%=request.getContextPath()%>/foodmanagementaction.action?restaurantid=${restaurantPojo.id }">进入此餐厅食物管理页面</a>
		<br> <font color=red>${message }</font> <input type="hidden"
			name="type" value="addfoodcategory" /> <input type="hidden"
			name="restaurantid" value="${restaurantPojo.id }" />

	</form>

	<hr>
	<br>
	<font size="+2"><b>修改食物類別信息</b> </font>
	<br>
	<br>
	<table>
		<tr align=center>
			<td><b>ID</b>
			</td>
			<td width=230><b>食物類別名称</b>
			</td>
			<td><b>食物類別描述</b>
			</td>
			<td><b>是否啟用</b>
			</td>
			<td><b>類別排序</b>
			</td>
			<td></td>
		</tr>

		<s:iterator value="foodcategoryList" id="foodcategorypojo">
			<tr align=center>
				<td>${foodcategorypojo.id }</td>
				<td><input type="text" value="${foodcategorypojo.name }"
					id="categroyname${foodcategorypojo.id }" disabled="true" /></td>
				<td><input type="text" value="${foodcategorypojo.description }"
					id="foodcategroy${foodcategorypojo.id }" size=80 disabled="true" />
				</td>
				<td align=center><s:if test="#foodcategorypojo.flag==1">
						<input disabled="disabled" type="checkbox"
							name=flag${foodcategorypojo.id} id=flag${foodcategorypojo.id} checked />
					</s:if> <s:else>
						<input disabled="disabled" type="checkbox"
							name=flag${foodcategorypojo.id} id=flag${foodcategorypojo.id} />
					</s:else>
				</td>
				
				<td><input type="text" value="${foodcategorypojo.rank }"
					id="rank${foodcategorypojo.id }" size=4 disabled="true" />
				</td>
				
				<td><input type="button" value="修改" id="edit${foodcategorypojo.id }"
					onclick="edit(${foodcategorypojo.id })"> 
					<input type="button" value="提交" id="submit${foodcategorypojo.id }"
					onclick="submitUpdate(${foodcategorypojo.id })" disabled="true">
					<input type="button" value="删除" id="delete${foodcategorypojo.id }"
					onclick="deleteCategory(${foodcategorypojo.id })" disabled="true">
				</td>

			</tr>
		</s:iterator>
	</table>



</body>
</html>