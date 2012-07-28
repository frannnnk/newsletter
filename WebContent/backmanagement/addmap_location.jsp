<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择地点 - 易食飯外卖 - 香港外卖|网上订餐</title>

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
	var marker1;
	var marker2;
	var marker3;
	var marker4;
	var maplistener;
	var polygon=null;

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
	}

	//mark拖拽监听方法.
	function markdraglistenerExecute(event, tmpmark) {
		var marktitle = tmpmark.getTitle();
		if (marktitle == 'mark1') {
			fillPositionText("1", tmpmark.getPosition());
		} else if (marktitle == 'mark2') {
			fillPositionText("2", tmpmark.getPosition());
		}else if (marktitle == 'mark3') {
			fillPositionText("3", tmpmark.getPosition());
		}else if (marktitle == 'mark4') {
			fillPositionText("4", tmpmark.getPosition());
		}
		drawOverlay();
	}

	function fillPositionText(type, location) {
		var current_x_coordinate = location.lat();
		var current_y_coordinate = location.lng();
		var result = current_x_coordinate + "," + current_y_coordinate;
		if (type == "1") {
			document.getElementById("point1").value = result;
		} else if (type == "2") {
			document.getElementById("point2").value = result;
		}else if (type == "3") {
			document.getElementById("point3").value = result;
		}else if (type == "4") {
			document.getElementById("point4").value = result;
		}
	}

	//放置标志.
	function placeMarker(location) {
		if (marker1 == null) {
			marker1 = new google.maps.Marker({
				position : location,
				map : map,
				animation : google.maps.Animation.DROP,
				flat : false,
				draggable : true,
				title : 'mark1'
			});
			fillPositionText("1", location);
		} 
		else if (marker2 == null) {
			marker2 = new google.maps.Marker({
				position : location,
				map : map,
				animation : google.maps.Animation.DROP,
				flat : false,
				draggable : true,
				title : 'mark2'
			});
			fillPositionText("2", location);
		}
		else if (marker3 == null) {
			marker3 = new google.maps.Marker({
				position : location,
				map : map,
				animation : google.maps.Animation.DROP,
				flat : false,
				draggable : true,
				title : 'mark3'
			});
			fillPositionText("3", location);
		}
		else if (marker4 == null) {
			marker4 = new google.maps.Marker({
				position : location,
				map : map,
				animation : google.maps.Animation.DROP,
				flat : false,
				draggable : true,
				title : 'mark4'
			});
			fillPositionText("4", location);
			drawOverlay();
		}

		//给marker1加拖拽事件.
		google.maps.event.addListener(marker1, 'dragend', function(event) {
			markdraglistenerExecute(event, marker1);
		});
		//给marker2加拖拽事件.
		google.maps.event.addListener(marker2, 'dragend', function(event) {
			markdraglistenerExecute(event, marker2);
		});
		//给marker2加拖拽事件.
		google.maps.event.addListener(marker3, 'dragend', function(event) {
			markdraglistenerExecute(event, marker3);
		});
		//给marker2加拖拽事件.
		google.maps.event.addListener(marker4, 'dragend', function(event) {
			markdraglistenerExecute(event, marker4);
		});
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

	//画多边形,计算多边形面积  
	function drawOverlay() {  
	    var flightPlanCoordinates = [];  
	    flightPlanCoordinates.push(marker1.getPosition()); 
	    flightPlanCoordinates.push(marker2.getPosition());  
	    flightPlanCoordinates.push(marker3.getPosition());  
	    flightPlanCoordinates.push(marker4.getPosition());  
	    if(polygon!=null)
	    	polygon.setMap(null);  
	    
	    polygon = new google.maps.Polygon({  
	        path: flightPlanCoordinates,  
	        strokeColor: "#FF0000",  
	        strokeOpacity: 0.8,  
	        strokeWeight: 2,  
	        fillColor: "#FF0000",  
	        fillOpacity: 0.35  
	    });  
	  
	    polygon.setMap(map);  

	}  
	
	//清除标记.
	function clearmark() {
		marker1.setVisible(false);
		document.getElementById("point1").value = "";
		marker1 = null;
		
		marker2.setVisible(false);
		document.getElementById("point2").value = "";
		marker2 = null;

		marker3.setVisible(false);
		document.getElementById("point3").value = "";
		marker3 = null;
		
		marker4.setVisible(false);
		document.getElementById("point4").value = "";
		marker4 = null;
		
		if(polygon!=null)
	    	polygon.setMap(null);  
	}
	
	//提交前的效验
	function checkbeforsubmit(){
		var flag = false;
		var p1= document.getElementById("specificdescription").value;
		var p2 =document.getElementById("description").value;
		if(p1==""||p2==""){
			alert("写入信息不完整");
			flag = false;
		}else{
			flag = true;
		}
		return flag;
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
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}

</style>



</head>
<body onLoad="initialize()">
<a href="<%=request.getContextPath() %>/backmanagement/index.html"><font size=+2>返回管理首页</font></a>&nbsp;&nbsp;

	<form action="maplocationmanagementaction.action"
		onsubmit="return checkbeforsubmit()">



		<!-- 导航菜单栏 -->
		<div class="maptopnav">

			<font size="+1">当前在<font color="red"><b><span id="scopename">红磡</span></b></font></font>
			<hr>
			<a href="Javascript:searchAddressForArea('红磡站','红磡')">红磡</a> <a
				href="Javascript:searchAddressForArea('旺角站','旺角')">旺角</a> <a
				href="Javascript:searchAddressForArea('中环站','中环')">中环</a>
			&nbsp;&nbsp; <input type="text" id="address" /><input type="button"
				onclick="searchAddress()" value="搜索" />&nbsp;&nbsp; <input
				type="button" onclick="clearmark()" value="清空坐标" /> <br>
			<hr>

			mark1坐标：<input type="text" id="point1" name="point1" value=""
				style="width: 300px;" readonly/> mark2坐标：<input type="text" id="point2"
				name="point2" value="" style="width: 300px;" readonly/> <br> mark3坐标：<input
				type="text" id="point3" name="point3" value="" style="width: 300px;" readonly/>
			mark4坐标：<input type="text" id="point4" name="point4" value=""
				style="width: 300px;" readonly/> <br> <br> 区域名称：<input
				type="text" id="description" name="description" value=""
				style="width: 300px;" /> <br> <br> 区域详细描述：<input
				type="text" id="specificdescription" name="specificdescription"
				value="" style="width: 300px;" /> <input type="submit"
				value="保存区域信息" />
		</div>
		<s:token />
	</form>

	<div id="map_canvas"
		style="height: 83%; width: 73%; top: 10px; margin-left: 13.5%; margin-bottom: 10px; border: #FF0000;"></div>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

</body>
</html>
