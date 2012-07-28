/*Generate the 'recent location' menu*/
/* shop_name@||@icon_url@||@title@||@click_URL*||*shop_name@||@icon_url@||@title@||@click_URL ... */
/**
 *  力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk
 * 
 */
function genShopMenu(){
	var sStr = document.getElementById("hidShopStrs").value;
	
	if (sStr == '') {
		if (document.getElementById("hisContent")) {
			document.getElementById("hisContent").innerHTML = "暫無內容";
		} 
		if (document.getElementById("hotContent")) {
			document.getElementById("hotContent").innerHTML = "暫無內容";
		}
		return;
	}
	
	var shops = sStr.split("*||*");
	var html = "";
	var flt = "";
	
	for (var i = 0 ; i < shops.length ; i++) {
		var shop = shops[i].split("@||@");
		if (i!= 0 && i%6 == 0) {
			//html += "<br/><hr/>";
			flt= "";
		} else {
			flt = "style='float:left;'";
		}
		html += "<div class='shopShow' align='center' "+flt+">";
		html += "<img src='"+shop[1]+"' title='"+shop[2]+"' alt='"+shop[2]+"'/><br/>";
		html += "<a href='"+shop[3]+"'>"+shop[0]+"</a>";
		html += "</div>";
	}
	
	if (document.getElementById("hisContent")) {
		document.getElementById("hisContent").innerHTML = html;
	} 
	if (document.getElementById("hotContent")) {
		document.getElementById("hotContent").innerHTML = html;
	} 
}