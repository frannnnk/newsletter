/*Generate the 'recent location' menu*/
/* shop_name@||@icon_url@||@title@||@click_URL*||*shop_name@||@icon_url@||@title@||@click_URL ... */
/**
 * 力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk*||*力倬的餐厅@||@http://img1.yiwaimai.com/UploadFiles/Brand/2011-7-22/101218_1218_1523.jpg@||@来订餐吧@||@http://www.frank.hk
 * 
 */

var tf = false;

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
			// html += "<br/><hr/>";
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

function showAddButton(bid){
	
	document.getElementById(bid).style.display = 'block';

}

function hideAddButton(bid){
	
	document.getElementById(bid).style.display = 'none';

}

function addFoodToCart(id,price,name,restId,restName){
	// store Item info
	// format:
	// id@|@price@|@name@|@amount@|@restaurantId@*|*@id@|@price@|@name@|@amount@|@restaurantId
	
	// read existing cookie
	var v = $.cookie('dstCart');
	
	
	
	if (v == '' || v ==null) {
		// no cookie exist, create a new cooiks
		
		
		v = id+'@|@'+price+'@|@'+name+'@|@'+'1'+'@|@'+restId; // default
																// amount is 1
		$.cookie('dstCart', v, { expires: 1, path: '/' });		
		addRestNameAndId(restId,restName);		
		
	} else {
		// already have cookies stored
		addRestNameAndId(restId,restName);	
		
		// search if item already exist in the cookie string.
		var exist = false;
		var items = v.split('@*|*@');
		for (var i = 0 ; i < items.length ; i++) {
			
			// id@|@price@|@name@|@amount
			var item = items[i].split('@|@');
			var itemId =  item[0];
			var itemPrice =  item[1];
			var itemName =  item[2];
			var itemAmount =  item[3];
			var itemRestId = item[4];
			
			if ( itemId == id ) {
				// item exist;
				exist = true;
				editAmount(id,'+');
				break;				
			} 		
		}
		
		if (!exist) {
			// append to existing cookies
			v += '@*|*@'+ id+'@|@'+price+'@|@'+name+'@|@'+'1'+'@|@'+restId; // default
																			// amount
																			// is 1
			$.cookie('dstCart', v, { expires: 1, path: '/' });
			// document.getElementById('cartContent').innerHTML = v;
			// displayCart();
			
		} else {
			// do nothing
		}
	}
	
	//目前更改为选择推荐外卖，外卖放入购物车，并且进入该餐厅。
	window.location.href="restaurantmenuinitaction.action?restaurantid="+restId;
	
/*	var settings =
	{
	'onewinonly'    :  true, 
	'speed'			:	'fast',	 // animations: fast, slow, or integer
	'duplicates'	:	false,  // true or false
	'autoclose'		:	false  // integer or false
	};
	
	
	//$.sticky('<table width="230"><tr><td width="40"><div class="iconOKSmall"></div></td><td><span class="stickyFont"><br/>成功添加到购物车</span></td></tr></table><div id="cartContent">'+displayCartForFoodPage()+'</div><div class="iconCasher"><a href="orderfillout.action"></a></div>',settings);
*/
}


function addToCart(id,price,name, restId){	
	// store Item info
	// format:
	// id@|@price@|@name@|@amount@|@restaurantId@*|*@id@|@price@|@name@|@amount@|@restaurantId
	
	// read existing cookie
	var v = $.cookie('dstCart');

	
	if (v == '' || v ==null) {
		// no cookie exist, create a new cooiks
		
		
		v = id+'@|@'+price+'@|@'+name+'@|@'+'1'+'@|@'+restId; // default
																// amount is 1
		$.cookie('dstCart', v, { expires: 1, path: '/' });			
		displayCart();
		return;
	} else {
		// already have cookies stored
		// search if item already exist in the cookie string.
		var exist = false;
		var items = v.split('@*|*@');
		for (var i = 0 ; i < items.length ; i++) {
			
			// id@|@price@|@name@|@amount
			var item = items[i].split('@|@');
			var itemId =  item[0];
			var itemPrice =  item[1];
			var itemName =  item[2];
			var itemAmount =  item[3];
			
			if ( itemId == id ) {
				// item exist;
				exist = true;
				editAmount(id,'+');
				break;				
			} 		
		}
		
		if (!exist) {
			// append to existing cookies
			v += '@*|*@'+ id+'@|@'+price+'@|@'+name+'@|@'+'1'+'@|@'+restId; // default
																			// amount
																			// is 1
			$.cookie('dstCart', v, { expires: 1, path: '/' });
			document.getElementById('cartContent').innerHTML = v;
			displayCart();
			
		} else {
			// do nothing
		}
	}
	
	// change icon
	document.getElementById("addCart_"+id).style.backgroundPosition='-70px -704px';
}

function editAmount(id,action) {
	// action: '+','-','x'
	// read existing cookie
	var v = $.cookie('dstCart') ;
	if (v == '' || v == null) {
		return;
	}
	var items = v.split('@*|*@');
	var newCookiesString = "";
	for (var i = 0 ; i < items.length ; i++) {
		
		// id@|@price@|@name@|@amount
		var item = items[i].split('@|@');
		var itemId =  item[0];
		var itemPrice =  item[1];
		var itemName =  item[2];
		var itemAmount =  parseInt(item[3],10);
		var itemRestId = item[4];
		
		
		if ( itemId == id ) {
			// item exist;
			exist = true;
			
			// edit the amount
			if ('+' == action) {
				itemAmount = itemAmount +1;
			} else if ('-' == action) {
				itemAmount = itemAmount -1;
			} else if ('x' == action) {
				continue;
			}			
		} 	
		
		newCookiesString += itemId+'@|@'+itemPrice+'@|@'+itemName+'@|@'+itemAmount + '@|@' + itemRestId + '@*|*@'; 
		
	}
	newCookiesString += '@ENDMARK@';
	newCookiesString = newCookiesString.replace('@*|*@@ENDMARK@', '');
	newCookiesString = newCookiesString.replace('@ENDMARK@', '');
	
	
	
	if (newCookiesString.length == 0 || newCookiesString == '' || newCookiesString == null) {
		$.cookie('dstCart', null, { expires: 1, path: '/' });
	} else {
		$.cookie('dstCart', newCookiesString, { expires: 1, path: '/' });
	}
		
	
	displayCart();

}

function displayCart(){
	
	if (document.getElementById('do_not_edit_this')) {
		if (document.getElementById("cartContent")) {
			document.getElementById("cartContent").innerHTML = displayCartForFoodPage();
			return;
		}		
	}
	
	
	var v = $.cookie('dstCart') ;
	if (v == '' || v == null) {
		document.getElementById("cartContent").innerHTML = "餐車內暫時沒有餐點";
		return;
	}
	var totalAmt = 0.00;
	var items = v.split('@*|*@');
	var cartHtml = "<table cellspacing='0' cellpadding='2' style='width:220px;font-size:13px;'>";
	cartHtml += "<tr><td width='50%' class='cartTitle'>外賣名稱</td><td width='20%' class='cartTitle'>單價</td><td width='20%' class='cartTitle'>數量</td><td width='10%' class='cartTitle'>&nbsp;</td></tr>";
	
	var rowHtml = "";
	
	for (var i = 0 ; i < items.length ; i++) {
		
		// id@|@price@|@name@|@amount
		var item = items[i].split('@|@');
		var itemId =  item[0];
		var itemPrice =  item[1];
		var itemName =  item[2];
		var itemAmount =  parseInt(item[3],10);
		var itemRestId = item[4];
		
		var restReplaceMark = "<!--@REST@"+itemRestId+"@REST@-->"; 
		
		
		
		rowHtml += "<tr class='cartList'>";
		rowHtml += "<td>";
		rowHtml += itemName;
		rowHtml += "</td>";
		rowHtml += "<td>";
		rowHtml += itemPrice;
		rowHtml += "</td>";	
		rowHtml += "<td>";
		rowHtml += itemAmount;
		rowHtml += "</td>";
		rowHtml += "<td>";
		rowHtml += "<input type='checkbox' checked onclick='editAmount(\""+itemId+"\",\"x\");'/>";
		rowHtml += "</td>";		
		rowHtml += "</tr>";
		
		
		
		totalAmt = totalAmt + ( parseFloat(itemPrice.replace('$','').replace(' ','')) * parseInt(itemAmount,10) );
		
		
		if (cartHtml .indexOf(restReplaceMark) == -1) {
			// restaurant not exist;
			//rowHtml = "<td colspan='4' class='shopCartRow'><div class='shopCartIcon'></div><span class='shopCartTitle'>"+getRestNameById(itemRestId)+"</span></td>" + rowHtml + restReplaceMark;
			cartHtml += rowHtml;
		} else {
			// restaurant exist;
			cartHtml = cartHtml.replace(restReplaceMark,rowHtml+""+restReplaceMark);
			
		}
		
		
		rowHtml = "";
	}
	
	cartHtml += "</table>" +"<div id='totalAmt'><span style='font-size:14px;color:red;'>總金額: </span>$"+ totalAmt + "</div>";
	if (document.getElementById("cartContent")) {
		document.getElementById("cartContent").innerHTML = cartHtml;
	} else {
		return cartHtml;
	}
}


function displayCartForFoodPage(){
	
	var v = $.cookie('dstCart') ;
	if (v == '' || v == null) {
		return "餐車內暫時沒有外賣";
	}
	var totalAmt = 0.00;
	var items = v.split('@*|*@');
	var cartHtml = "<table cellspacing='0' class='stickyFont' cellpadding='2' style='width:220px;font-size:13px;'>";
	cartHtml += "<tr style='font-weight:bolder;height:100px'><td width='50%' class='cartTitle'>外賣名稱</td><td width='20%' class='cartTitle'>單價</td><td width='20%' class='cartTitle'>數量</td><td width='10%' class='cartTitle'>&nbsp;</td></tr>";
	
	var rowHtml = "";
	
	for (var i = 0 ; i < items.length ; i++) {
		
		// id@|@price@|@name@|@amount
		var item = items[i].split('@|@');
		var itemId =  item[0];
		var itemPrice =  item[1];
		var itemName =  item[2];
		var itemAmount =  parseInt(item[3],10);
		var itemRestId = item[4];
		
		var restReplaceMark = "<!--@REST@"+itemRestId+"@REST@-->"; 
		
		
		
		rowHtml += "<tr class='cartList'>";
		rowHtml += "<td>";
		rowHtml += itemName;
		rowHtml += "</td>";
		rowHtml += "<td>";
		rowHtml += itemPrice;
		rowHtml += "</td>";	
		rowHtml += "<td>";
		rowHtml += itemAmount;
		rowHtml += "</td>";
		rowHtml += "<td>";
		rowHtml += "<input type='checkbox' checked onclick='editAmount(\""+itemId+"\",\"x\");'/>";
		rowHtml += "</td>";		
		rowHtml += "</tr>";
		
		
		
		totalAmt = totalAmt + ( parseFloat(itemPrice.replace('$','').replace(' ','')) * parseInt(itemAmount,10) );
		
		
		if (cartHtml .indexOf(restReplaceMark) == -1) {
			// restaurant not exist;
			//rowHtml = "<td colspan='4' class='shopCartRow'><div class='shopCartIcon'></div><span class='shopCartTitle'>"+getRestNameById(itemRestId)+"</span></td>" + rowHtml + restReplaceMark;
			cartHtml += rowHtml;
		} else {
			// restaurant exist;
			cartHtml = cartHtml.replace(restReplaceMark,rowHtml+""+restReplaceMark);
			
		}
		
		
		rowHtml = "";
	}
	
	cartHtml += "</table>" +"<div id='totalAmtFt'><span style='font-size:15px;color:#666;'>總金額: </span>$"+ totalAmt + "<br/><span style='font-size:10px;color:#666;'>*總金額以訂單頁面為准</span></div>";
	
	return cartHtml;
}

// 提交ITEMS.
function submitItems(){
	var url="orderfillout.action";
	window.location.href=url;
}

function goToByScroll(id){
		$('html,body').animate({scrollTop: $("#"+id).offset().top},'slow');
}
	



