<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.RestaurantPojo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯管理页面</title>

<SCRIPT type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
//编辑
function edit(foodid){
	if(document.getElementById("isrecommend"+foodid).disabled==true){
		document.getElementById("price"+foodid).disabled=false;
		document.getElementById("isrecommend"+foodid).disabled=false;
		document.getElementById("flag"+foodid).disabled=false;	
		document.getElementById("submit"+foodid).disabled=false;
		document.getElementById("categorychoose"+foodid).disabled=false;
		document.getElementById("foodname"+foodid).disabled=false;
		document.getElementById("editbutton"+foodid).value="关闭";
	}else{
		document.getElementById("price"+foodid).disabled=true;
		document.getElementById("isrecommend"+foodid).disabled=true;
		document.getElementById("flag"+foodid).disabled=true;	
		document.getElementById("submit"+foodid).disabled=true;
		document.getElementById("categorychoose"+foodid).disabled=true;
		document.getElementById("foodname"+foodid).disabled=true;
		document.getElementById("editbutton"+foodid).value="修改";
	}
}



//提交修改 ajax提交
function submitUpdate(foodid){
	var foodname = document.getElementById("foodname"+foodid).value;
	var foodprice = document.getElementById("price"+foodid).value;
	var foodcategoryid = document.getElementById("categorychoose"+foodid).value;
	var isrecommentobj = document.getElementById("isrecommend"+foodid);
	var flagobj = document.getElementById("flag"+foodid);
	 
	
	var isrecommend;
	var flag;
	 
	  if(isrecommentobj.checked){
		  isrecommend=1;
	  }else{
		  isrecommend=0;
	  }
	  
	  if(flagobj.checked){
		  flag=1;
	  }else{
		  flag=0;
	  }
	 
	if(foodcategoryid==""||foodprice==""){
		alert("信息填寫不完整");
	}else{
		
		var url = 'foodmanagementaction.action';
		var params = {
				foodid : foodid,
				foodname:foodname,
				price:foodprice,
				isrecommend:isrecommend,
				foodflag:flag,
				foodcategoryid:foodcategoryid,
				type:"foodupdate"
		};	
		$.post(url, params, callbackFunForUpdate, 'text');
		edit(foodid);
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

//提交保存新增食物 ajax提交
function submitSave(){
	
	var resid = document.getElementById("restaurantid").value;
	var foodname = document.getElementById("foodname").value;
	var description = document.getElementById("description").value;
	var foodprice = document.getElementById("price").value;
	
	var isrecommend=   $('input:radio[name="isrecommend"]:checked').val();
	var foodcategoryid = $('input:radio[name="category"]:checked').val();
		
	if(resid==""||foodname=="" ||foodprice=="" ||isrecommend==""||foodcategoryid==""||foodcategoryid==undefined||foodcategoryid==null){
		alert("信息填寫不完整");
	}else{
		
		var url = 'foodmanagementaction.action';
		var params = {
				restaurantid : resid,
				foodname:foodname,
				description:description,
				price:foodprice,
				isrecommend:isrecommend,
				foodcategoryid:foodcategoryid,
				type:"add"
		};	
		
		$.post(url, params, callbackFunForSave, 'text');
		document.getElementById("submit").disabled=true;
		
	}
}

//回调方法.
function callbackFunForSave(data) {
	if (data == '0') {
		window.location.reload();
	} else {
		alert("提交失败,请刷新页面重新操作，若还有问题请联系管理员");
	}
}



</script>

<style type="text/css">
.food ul {
	list-style: none;
}

.food li {
	float: left;
	margin-right: 5px;
}

div {
	margin-bottom: 20px;
}
body{
font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}
</style>


</head>
<body>



<a href="<%=request.getContextPath() %>/restaurantupdate.action?type=0">返回餐厅管理页面</a>




	<!-- 餐馆 -->
	<div>
		<h1>
			<b><font color="blue">${restaurantPojo.name }</font>食物餐单管理</b>
		</h1>
	</div>


	<!-- 添加菜单BOX -->
	<div class="food">
		<font size="+2"><b>添加食物信息</b> </font><br> <br>

		<s:iterator value="foodcategoryList" id="foodcatogorypojo">
			<label> <input type="radio" name="category" id="category"
				value="${foodcatogorypojo.id }" checked> ${foodcatogorypojo.name }</input> </label>

		</s:iterator>
		</ul>
		<br>
		<table>
			<tr>
				<td>食物名称</td>
				<td><input type="text" id="foodname" name="foodname"></td>
			</tr>
			<tr>
				<td>食物描述</td>
				<td><input type="text" id="description" name="description">
				</td>
			</tr>
			<tr>
				<td>價格</td>
				<td><input type="text" id="price" name="price">
				</td>
			</tr>
			<tr>
				<td>是否为推荐外卖</td>
				<td><label> <input type="radio" id="isrecommend"
						name="isrecommend" value="0" checked>不推荐（默认）</label><label>
						<input type="radio" id="isrecommend" name="isrecommend" value="1">推荐</label>
				</td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" id="submit" value="提交"
					style="width: 120px; height: 50px" onclick="submitSave()">
					
					<a href="<%=request.getContextPath() %>/foodmanagementaction.action?type=initaddcategory&restaurantid=${restaurantPojo.id }">返回食物类别管理页面</a>
					
				</td>
			</tr>

		</table>

	</div>
	<input type="hidden" id="type" name="type" value="add">
	<input type="hidden" name="restaurantid" id="restaurantid"
		value="${restaurantPojo.id }" />


	<hr>
	<font size="+2"><b>修改食物信息</b> </font>
	<br>
	<br>
	<table>
		<tr align=center>
			<td><b>食物ID</b>
			</td>
			<td width=230><b>食物名称</b>
			</td>
			<td><b>食物价格</b>
			</td>
			<td><b>是否推荐</b>
			</td>
			<td><b>是否启用</b>
			</td>
			<td><b>食物类别</b>
			</td>
		</tr>

		<s:iterator value="foodList" id="foodpojo">
			<tr>
				<td align=center>${foodpojo.foodid }</td>
				<td align=center>
				
				<input disabled="disabled" type="text"
					name=foodname${foodpojo.foodid} id=foodname${foodpojo.foodid}
					value=${foodpojo.foodname } size=18 />
				</td>

				<td align=center><input disabled="disabled" type="text"
					name=price${foodpojo.foodid} id=price${foodpojo.foodid}
					value=${foodpojo.price } size=6 /></td>
				<td align=center><s:if test="#foodpojo.is_recommend==1">
						<input disabled="disabled" type="checkbox"
							name=isrecommend${foodpojo.foodid}
							id=isrecommend${foodpojo.foodid} checked />
					</s:if> <s:else>
						<input disabled="disabled" type="checkbox"
							name=isrecommend${foodpojo.foodid}
							id=isrecommend${foodpojo.foodid} />
					</s:else>
				</td>
				<td align=center><s:if test="#foodpojo.flag==1">
						<input disabled="disabled" type="checkbox"
							name=flag${foodpojo.foodid} id=flag${foodpojo.foodid} checked />
					</s:if> <s:else>
						<input disabled="disabled" type="checkbox"
							name=flag${foodpojo.foodid} id=flag${foodpojo.foodid} />
					</s:else>
				</td>
				<td align=center><select disabled="disabled"
					name="categorychoose${foodpojo.foodid}"
					id="categorychoose${foodpojo.foodid}">
						<s:iterator value="foodcategoryList" id="foodcatogorypojo">
							<s:if test="#foodcatogorypojo.id==#foodpojo.foodcategory_id">
								<option value="${foodcatogorypojo.id }" selected>${foodcatogorypojo.name
									}</option>
							</s:if>
							<s:else>
								<option value="${foodcatogorypojo.id }">${foodcatogorypojo.name
									}</option>
							</s:else>
						</s:iterator>
				</select></td>

				<td align=center><input type="button" id="editbutton${foodpojo.foodid}"
					value="修改" onclick="edit(${foodpojo.foodid})" /> <input
					disabled="disabled" id="submit${foodpojo.foodid}" type="button"
					value="提交" onclick=submitUpdate(${foodpojo.foodid}) /></td>

			</tr>
		</s:iterator>
	</table>
	<br>
	<br>

</body>
</html>