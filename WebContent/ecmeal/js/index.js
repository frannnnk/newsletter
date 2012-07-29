/*Generate the 'recent location' menu*/
/* shop_name@||@icon_url@||@title@||@click_URL*||*shop_name@||@icon_url@||@title@||@click_URL ... */
/**
 *  力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk
 * 
 */

var enhanced = false;

function genShopMenu(){
	
	var sStr = document.getElementById("hidShopStrs").value;
	
	if (sStr == '') {	
		if (document.getElementById("hisContent")!=null) {
			document.getElementById("hisContent").innerHTML = "暫無內容";
		} 
		if (document.getElementById("hotContent")!=null) {//構造熱門地點,适用于 首次登录 没有位置COOKIE记录的用户
			
	
			document.getElementById("hotContent").innerHTML = hotSpotHtml;
		}
		return;
	}
	
	//構造熱門餐廳
	var shops = sStr.split("*||*");
	var html = "";
	var flt = "";
	for (var i = 0 ; i < shops.length ; i++) {
		var shop = shops[i].split("@||@");
		if (i!= 0 && i%6 == 0) {
			//html += "<br/><hr/>";
			//flt= "";
		} else {
			flt = "style='float:left;'";
		}
		html += "<div class='shopShow' align='center' "+flt+">";
		html += "<a href='"+shop[3]+"'>"+"<img height=70px width=96px src='"+shop[1]+"' title='"+shop[2]+"' alt='"+shop[2]+"'/>"+"</a><br/>";
		html += "<a href='"+shop[3]+"'>"+shop[0]+"</a>";
		html += "</div>";
	}
	
	
	if (document.getElementById("hisContent")) {  //熱門餐廳
		document.getElementById("hisContent").innerHTML = html;
	} 
}
//進入地圖熱門地點區域
function gotoMapWithHotspot(hotspotStr){
	var url="/map/map.jsp?hotspot="+hotspotStr;
	window.location.href=url;
}


function handleKeyPress(e,form){
	var key=e.keyCode || e.which;
	if (key==13){
		gotoMap();
	}
	
}





//换一批推荐食物.
function getNewsFromBackend(type) {
		
	
	//type: notice|comment|order
	
	/*
	 * 1.邮件订阅URL
http://localhost:8080/ecmeal/mailsubscription.action?emailAddress=xxxx

2.获取公告URL
http://localhost:8080/ecmeal/publicnotice.action

3.获取评论播报URL
http://localhost:8080/ecmeal/broadcast.action?type=1

4.获取订单播报URL
http://localhost:8080/ecmeal/broadcast.action?type=2

5.添加收藏
http://localhost:8080/ecmeal/myfavaction.action?type=3&restaurantid=1


	 */

	var noticeURL = "publicnotice.action";
	var commentURL = "broadcast.action?type=1";
	var orderURL = "broadcast.action?type=2";
	
	var url = "foodrestaurantbypagingaction.action";
	
	var params = {
			
	};
	
	if (type == "notice") {
		$.post(noticeURL, params, getNoticeJSONText, "json");
	}
	if (type == "comment") {
		$.post(commentURL, params, getCommentJSONText, "json");
	}
	if (type == "order") {
		$.post(orderURL, params, getOrderJSONText, "json");
	}
	
	
	// 成功之后
	//document.getElementById("foodCurrentPagenum").value = requestPagenum;
}


//回调方法.
function getNoticeJSONText(data) {
	
	
	var noticeStr = "<ul>";
	
	$.each(data.result, function(i, item) {
		noticeStr += "<li><div><a href='"+this.url+"' target='_blank' class='ftNotice'>"+this.subject+"</a></div></li>";
	});	
	
	noticeStr += "</ul>";
	
	document.getElementById("bdNotice").innerHTML = noticeStr;
	
	$(function(){
		$('#bdNotice').vTicker({ 
			speed: 500,
			pause: 4000,
			animation: 'fade',
			mousePause: true,
			showItems: 7
		});
	});
	
	
	 
}

function getCommentJSONText(data) {
	
var noticeStr = "<ul>";
	
	$.each(data.result, function(i, item) {
		noticeStr += "<li><b>"+this.restaurantname+"</b><p><font color=#585858>"+this.reviewcontent+"</font></li>";
	});	
	
	noticeStr += "</ul>";

	
	document.getElementById("bdComment").innerHTML = noticeStr;
	
	
	$(function(){
		$('#bdComment').vTicker({ 
			speed: 500,
			pause: 3000,
			animation: 'fade',
			mousePause: true,
			showItems: 10
		});
	});
	
	
	 
}

function getOrderJSONText(data) {
	
var noticeStr = "<ul>";
	
	$.each(data.result, function(i, item) {
		noticeStr += "<li><span class='bdusername'>"+this.nickname+"</span> 訂購了<span class='bdrestname'>【"+this.restaurantname+"】</span>的<span class='bdfoodname'>"+this.foodname+"</span></li>";
	});	
	
	noticeStr += "</ul>";	
	document.getElementById("bdOrder").innerHTML = noticeStr;	
	
	
}



function tabEnhance(){
	/*
	 * The reason why i use this function is the News autoscroll function conflict with the tab switch function. 
	 * I have no choic but using this function to pause the execution for a while after user click the *second* tab. 
	 * 
	 * Frank 2011 Oct 28 Hong Kong
	 */
	window.setTimeout(doEnhance,50);
}



function doEnhance(){
	
	if (enhanced) {
		return;
	}
	
	$(function(){
		$('#bdOrder').vTicker({ 
			speed: 500,
			pause: 3000,
			animation: 'fade',
			mousePause: true,
			showItems: 10
		});
	});		
	
	enhanced = true;

}


function inputValueChange(actType){
	
	var defaultVal = "請輸入您的大致位置或進入地圖定位";
	var curVal = document.getElementById("searchInput").value;
	
	if (actType == "focus" && curVal == defaultVal) {
		document.getElementById("searchInput").value = "";
	}
	
	if (actType == "blur" && curVal == "") {
		document.getElementById("searchInput").value = defaultVal;
	}
}

function alert(str) {
	Dialog.alert(str);
}

function searchLocation(inputkeyword,x_cor,y_cor) {
	var locationKeyword;
	if (inputkeyword == "")
		locationKeyword = document.getElementById("searchInput").value;
	else
		locationKeyword = inputkeyword;
	if (locationKeyword != "" && locationKeyword != null) {
		window.location.href = "locationconfirmaction.action?areaname="
				+ locationKeyword+"&x_coordinate="+x_cor+"&y_coordinate="+y_cor;
	}
}
//ecmeal version2.0- go to map page
function gotoMap(){
	
	var defaultVal = "請輸入您的大致位置或进入地圖定位";
	var	locationKeyword = document.getElementById("searchInput").value;
	if (locationKeyword == defaultVal) {
		locationKeyword = "紅磡";
	}
	window.location.href = "map/map.jsp?localeName="+locationKeyword;
}

//邮件订阅
function mailsubscrib() {
	var mailaddress = document.getElementById("mailsubscription").value;
	if (validateEmail(mailaddress)) {
		var url = 'mailsubscription.action';
		var params = {
			emailAddress : mailaddress
		};
		$.post(url, params, mailsubscribcallbackFun, 'text');
	} else {
		//Dialog.alert("提示：你点击了一个按钮");
		alert(" Email格式不正確!");
	}
}
//邮件订阅callback方法
function mailsubscribcallbackFun(data) {
	if (data == "1") { //订阅成功
		alert("訂閱成功!");

	} else if (data == "0") {
		alert("該郵箱已經訂閱!");

	} else {//订阅失败
		alert("系统繁忙!");
	}
}