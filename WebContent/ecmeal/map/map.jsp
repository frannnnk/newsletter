<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>位置指定-易食飯</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_map.css" />
	

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_map_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
		<![endif]-->
		
		
<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>

	
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type="text/javascript"
	src="http://code.google.com/apis/gears/gears_init.js"></script>
<SCRIPT type="text/javascript"
	src="/canteen/js/jquery/jquery-1.3.2.min.js"></script>
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
			zoom : 18,
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
		//初始化位置信息框.
		infoBoxinit();	
		initfromindex();
		
		locateHotArea();
	}

	//监听器执行方法.
	function listenerExecute(event) {
		placeMarker(event.latLng);

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
				title : '試試拖拽'
			});

			circle = new google.maps.Circle({
				map : map,
				radius : 24,
				clickable : true,
				fillColor : "#B15BFF",
				fillOpacity : 0.3,
				strokeColor : "#B15BFF",
				strokeOpacity : 0.7,
				strokeWeight : 1
			});

			circle.bindTo('center', marker, 'position');
			//给circle加单击事件.
			google.maps.event.addListener(circle, 'click', function(event) {
				listenerExecute(event);
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
			infowindow.close();
			marker.setPosition(location);
		}
		//效验区域是否开通外卖业务.
		checkScopeAvaliability(location);

	}

	//初始化位置信息展示框.
	function infoBoxinit() {
		infowindow = new google.maps.InfoWindow({

		});
	}

	//效验所选位置是否开通外卖业务.
	function checkScopeAvaliability(location) {
		
		current_x_coordinate = location.lat();
		current_y_coordinate = location.lng();
		var params = {
			x_coordinate : current_x_coordinate,
			y_coordinate : current_y_coordinate
		};
		var url = "checkscopeavaliable.action"
		$.post(url, params, callbackFun, 'text');

		infowindow
		.setContent("loading...");
		
	}
	//回调方法.
	function callbackFun(data) {
		if (data != "-1") {
			
			var tmparray = data.split("@|@");
			var tmpareaid = tmparray[0];
			var tmpaddress = tmparray[1];
			infowindow
					.setContent("<p><b>請確定您的位置：</b><p><br><b><font color='blue'>我在 '"+tmpaddress+"' 附近</font></b><p><br><a class='myButton' href=javascript:confirmLocation('"
							+ tmpareaid + "','"+tmpaddress+"')>確認</a>");
			
		} else {
			infowindow.setContent("<br><p><font color='red'><b>抱歉！該地點暫時未開通外賣配送業務，<p>如果需要開通，請<a href=javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');>告訴我們</a>。</b></font></p>");
		}
		//打开信息框.
		infowindow.open(map, marker);
	}

	//确认位置并提交
	function confirmLocation(tmpareaid, tmpaddress) {
		var url = "locationconfirmaction.action?areaid=" + tmpareaid+"&areaname="+tmpaddress+"&x_coordinate="+current_x_coordinate+"&y_coordinate="+current_y_coordinate;
		window.location.href = url;
	}

	//地址搜索定位.
	function searchAddress() {
		var address = document.getElementById("address").value;
		if (address != "") {
			searchAddressForArea(address, address);
			geocoder
					.geocode(
							{
								'address' : "香港," + address
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									map.setCenter(results[0].geometry.location);
									//placeMarker(results[0].geometry.location);
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
			geocoder
					.geocode(
							{
								'address' : "香港," + address
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									map.setCenter(results[0].geometry.location);
									//placeMarker(results[0].geometry.location);
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
		document.getElementById("scopename").innerHTML = scopename;
		geocoder.geocode({
			'address' : "香港," +address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
			} else {
				alert("Geocode was not successful for the following reason: "
						+ status);
			}
		});
	}

	function initfromindex() {
<%String localename = (String) request.getParameter("localeName");%> //街道名称
		var localename ="<%=localename%>";
		if ((localename != "null") && (localename != "")) {
			searchAddressForKeyword(localename);
		} else if ((area != "null") && (area != "")) {
			searchAddressForArea(area, areadisplayname);
		}
	}
	
	
	//首页的热门地点初始化定位大致范围
	function locateHotArea() {
		var hotspotstr = document.getElementById("hotspot").value;
		if (hotspotstr != "") {
			searchAddressForArea(hotspotstr, hotspotstr);
		}
	}
	
	function handleKeyPress(e,form){
		
		var key=e.keyCode || e.which;
		if (key==13){
			searchAddress();
		}
		
	}
	
	function inputValueChange(actType){
	var defaultVal = "請輸入街道名稱";
	var curVal = document.getElementById("address").value;
	
	
	if (actType == "focus" && curVal == defaultVal) {
		document.getElementById("address").value = "";
	}
	
	if (actType == "blur" && curVal == "") {
		document.getElementById("address").value = defaultVal;
	}
	
}
</script>

<style type="text/css">

</style>



</head>
<body onLoad="initialize()">




	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>

	<div id="bg_canvas">
	

	<!-- 导航菜单栏 -->
	<div class="maptopnav">

		<font color=red><span id="scopename">Tips:&nbsp;請在地圖上指定你當前的位置
		</span>
		</font> 
		<hr>
		<input id="address"  class="searchInput" name="searchInput" type="text" value="請輸入街道名稱"
					onkeypress="handleKeyPress(event,this.form)" onfocus="inputValueChange('focus');" onblur="inputValueChange('blur');"  />
		<input
					type="button" id="searchInputBtn"					
					onclick="searchAddress();" value="搜索"/>
					
		<div class="hotPlaces">
			<div class="hotPlace"><font color="gray">外賣派送開通區域:</font></div>
			<div class="hotPlace"><a href="Javascript:searchAddressForArea('红磡站','红磡')">紅磡</a></div>
			<!--  <div class="hotPlace"><a href="Javascript:searchAddressForArea('旺角站','旺角')">旺角</a></div> 
			<div class="hotPlace"><a href="Javascript:searchAddressForArea('中环站','中环')">中環</a></div>
		-->
		</div>
		




		<%
			String hotspot = request.getParameter("hotspot");
			if (hotspot == null)
				hotspot = "";
		%>


	</div>
	<input type="hidden" value="<%=hotspot%>" id="hotspot" />

<div id="map_canvas_border" class="map_canvas_border">
<div id="map_canvas" class="mapCss"></div>	
</div>	
	
		
	
</div>
	
	
		<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
		
		 
	

</body>
</html>
