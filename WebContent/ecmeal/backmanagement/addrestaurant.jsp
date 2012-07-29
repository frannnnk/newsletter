<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="hk.franks.newsletter.pojo.DeliverScopePojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />

<link
	href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type="text/javascript"
	src="http://code.google.com/apis/gears/gears_init.js"></script>


<SCRIPT type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
	var geocoder;
	var map;
	var marker;
	var circle;
	var maplistener;
	var infowindow;
	var current_x_coordinate;
	var current_y_coordinate;

	//初始化方法
	function initialize() {


		geocoder = new google.maps.Geocoder();
		var latlng = new google.maps.LatLng(22.303830, 114.182970);
		var myOptions = {
			zoom : 17,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);

		//给map加单击事件.
		maplistener = google.maps.event.addListener(map, 'click', function(
				event) {
			listenerExecute(event);
		});
	}

	//监听器执行方法.
	function listenerExecute(event) {
		placeMarker(event.latLng);
		var location = event.latLng;
		document.getElementById("coordinate").value=location.lat()+","+location.lng();
	}

	//放置标志.
	function placeMarker(location) {
		if (marker == null) {
			marker = new google.maps.Marker({
				position : location,
				map : map,
				animation : google.maps.Animation.DROP,
				flat : false,
				draggable : true,
				title : '试试拖拽'
			});

	
			//给marker加拖拽事件.
			google.maps.event.addListener(marker, 'dragend', function(event) {
				listenerExecute(event);

			});
			//给marker加拖拽事件.
			google.maps.event.addListener(marker, 'dragstart', function(event) {
				infowindow.close();
			});

		} else {
			marker.setPosition(location);
		}

	}


	

	//地址搜索定位.
	function searchAddress() {
		var address = document.getElementById("address").value;
		if (address != "") {
			address = "香港," + address;
			geocoder
					.geocode(
							{
								'address' : address
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									map.setCenter(results[0].geometry.location);
									placeMarker(results[0].geometry.location);
									//设置坐标到文本框
									var location = results[0].geometry.location;
									document.getElementById("coordinate").value=location.lat()+","+location.lng();
								} else {
									alert("Geocode was not successful for the following reason: "
											+ status);
								}
							});
		} else {
			//do nothing right now.
		}
	}

	//地址搜索定位.
	function searchAddressForKeyword(address) {
		document.getElementById("address").value = address;
		if (address != "") {
			address = "香港," + address;
			geocoder
					.geocode(
							{
								'address' : address
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									map.setCenter(results[0].geometry.location);
									placeMarker(results[0].geometry.location);
									//设置坐标到文本框
									var location = results[0].geometry.location;
									document.getElementById("coordinate").value=location.lat()+","+location.lng();
									
								} else {
									alert("Geocode was not successful for the following reason: "
											+ status);
								}
							});
		} else {
			//do nothing right now.
		}
	}

	//地址搜索不打定位标志. scopename是用于显示当前所在区.
	function searchAddressForArea(address, scopename) {
		address = "香港," + address;
		document.getElementById("scopename").innerHTML = scopename;
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
			} else {
				alert("Geocode was not successful for the following reason: "
						+ status);
			}
		});
	}

	
</script>
<style type="text/css">
.maptopnav {
	width: 73%;
	margin-top: 10px;
	margin-left: 13.5%;
	margin-bottom: 10px;
	font-size: 14px;
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}

.maptopnav a {
	text-decoration: none;
	color: #000000;
}

.button {
	width: 80px;
	height: 38px;
}

body {
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}

ul {
	list-style: none;
}

li {
	float: left;
	margin-right:12px;
	
}
</style>

<script type="text/javascript">
function checkbeforeSubmit(){
	var flag=true;
	var name= document.getElementById("name").value;
	var description= document.getElementById("description").value;
	
	var sendprice= document.getElementById("sendprice").value;
	var arrivetime= document.getElementById("arrivetime").value;
	var shopaddress= document.getElementById("shopaddress").value;
	var leaguetime= document.getElementById("leaguetime").value;
	var coordinate= document.getElementById("coordinate").value;
	if(name==""||description==""||sendprice==""||arrivetime==""||shopaddress==""||leaguetime==""||coordinate==""){
		flag=false;	
		alert("信息输入不完整");
	}
	return flag;
}
</script>

<style type="text/css">
</style>
</head>
<body onLoad="initialize()">
<a href="<%=request.getContextPath() %>/backmanagement/index.html"><font size=+2>返回管理首页</font></a>&nbsp;&nbsp;

	<center>
		<div>
			<font size="+3">添加餐廳</font>
			<form action="restaurantmanagementaction.action"
				onsubmit="return checkbeforeSubmit()" method="post">
				<table>

					<tr>
						<td>餐館名称</td>
						<td align="left"><input type="text" id="name" name="name"
							value="">
						</td>
					</tr>
					<tr>
						<td>餐館说明</td>
						<td align="left"><textarea id="description"
								name="description"></textarea>
						</td>
					</tr>
					<tr>
						<td>电话</td>
						<td align="left"><input type="text" id="phone" name="phone"
							value="">
						</td>
					</tr>
					<tr>
						<td>传真</td>
						<td align="left"><input type="text" id="fax" name="fax"
							value="">
						</td>
					</tr>
					<tr>
						<td>EMAIL</td>
						<td align="left"><input type="text" id="email" name="email"
							value="">
						</td>
					</tr>
					<tr>
						<td>起送價格</td>
						<td align="left"><input type="text" id="sendprice"
							name="sendprice" value="">
						</td>
					</tr>
					<tr>
						<td>送餐所需時間</td>
						<td align="left"><input type="text" id="arrivetime"
							value="60" name="arrivetime">(單位：分鐘)</td>
					</tr>

					<tr>
						<td>營業時間（即收單時間）</td>
						<td align="left"><input type="text" id="shopstarttime"
							value="11:00" name="shopstarttime">至<input type="text"
							id="shopendtime" value="21:00" name="shopendtime">(格式：11:05
							都是半角)</td>
					</tr>
					<tr>
						<td>店鋪地址</td>
						<td align="left"><input type="text" id="shopaddress"
							name="shopaddress" value="">
						</td>
					</tr>
					<tr>
						<td>加盟時間</td>
						<td align="left"><input type="text"
							value="<%=java.text.DateFormat.getDateInstance().format(
					new java.util.Date())%>"
							id="leaguetime" name="leaguetime">(格式：YYYY-MM-DD)</td>
					</tr>
					<tr>
						<td>餐厅坐标</td>
						<td><input type="text" size="39" value="" id="coordinate"
							name="coordinate" readonly>(注：请在地图上指定)</td>
					</tr>

					<tr>
						<td>服務區域</td>
						<td><u> <%
 	List dslist = (List) request.getAttribute("deliverscopelist");
 	for (int i = 0; i < dslist.size(); i++) {
 		DeliverScopePojo pojo = (DeliverScopePojo) dslist.get(i);
 %>

								<li><input type='checkbox' name='deliverscope'
									value=<%=pojo.getId()%>><%=pojo.getDescription()%></li> <%
 	}
 %> </u>
						</td>
					</tr>



					<tr>
						<td><input type="hidden" value="add" id="type" name="type">
						</td>
						<td><input type="submit" value="保存" class="button">&nbsp;
							<font color="red">
								<%
									Object obj;
									if ((obj = request.getAttribute("responsemessage")) != null)
										out.print(obj);
								%> </font></td>
					</tr>

				</table>
				<s:token></s:token>
			</form>
		</div>
	</center>
	<br>
	<br>
	<!-- 导航菜单栏 -->
	<div class="maptopnav">

		当前所在区:<span id="scopename">红磡</span>
		<hr>
		<a href="Javascript:searchAddressForArea('红磡站','红磡')">红磡</a> <a
			href="Javascript:searchAddressForArea('旺角站','旺角')">旺角</a> <a
			href="Javascript:searchAddressForArea('中环站','中环')">中环</a>
		&nbsp;&nbsp; <input type="text" id="address" /><input type="button"
			onclick="searchAddress()" value="搜索" class="button" /> <font
			color=red>地图上指定餐厅位置</font>
	</div>

	<div id="map_canvas"
		style="height: 83%; width: 73%; top: 10px; margin-left: 13.5%; margin-bottom: 10px; border: #FF0000;"></div>
	<br>
	<br>
</body>
</html>