function MM_swapImgRestore() { // v3.0
	var i, x, a = document.MM_sr;
	for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
		x.src = x.oSrc;
}
function MM_preloadImages() { // v3.0
	var d = document;
	if (d.images) {
		if (!d.MM_p)
			d.MM_p = new Array();
		var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
		for (i = 0; i < a.length; i++)
			if (a[i].indexOf("#") != 0) {
				d.MM_p[j] = new Image;
				d.MM_p[j++].src = a[i];
			}
	}
}

function MM_findObj(n, d) { // v4.01
	var p, i, x;
	if (!d)
		d = document;
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all)
		x = d.all[n];
	for (i = 0; !x && i < d.forms.length; i++)
		x = d.forms[i][n];
	for (i = 0; !x && d.layers && i < d.layers.length; i++)
		x = MM_findObj(n, d.layers[i].document);
	if (!x && d.getElementById)
		x = d.getElementById(n);
	return x;
}

function MM_swapImage() { // v3.0
	var i, j = 0, x, a = MM_swapImage.arguments;
	document.MM_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3)
		if ((x = MM_findObj(a[i])) != null) {
			document.MM_sr[j++] = x;
			if (!x.oSrc)
				x.oSrc = x.src;
			x.src = a[i + 2];
		}
}

function lockInput() {

	var i = 1;
	while (true) {

		var ipt = document.getElementById("addr_" + i);
		if (ipt) {
			ipt.readOnly = true;
			i++;
		} else {
			break;
		}

	}

}

function editAddr(iptId) {

	var flag = document.getElementById(iptId).readOnly;
	if (flag) {// 只读状态，则更改按钮未更改功能.
		document.getElementById(iptId).className = 'inputEdit';
		document.getElementById(iptId).readOnly = false;
	} else { // 如果是可编辑状态 则更改按钮为保存功能
		var addressText = document.getElementById(iptId).value;
		var addressid = iptId;
		var url = "myaddressupdate.action?addressid=" + addressid
				+ "&&addressText=" + addressText;
		window.location.href = url;
	}
}

// 刪除地址
function deleteAddress(addressid) {
	var url = "myaddressdelete.action?addressid=" + addressid;
	window.location.href = url;
}

// 預設地址
function defaultAddress(addressid) {
	var url = "myaddressdefaultset.action?addressid=" + addressid;
	window.location.href = url;
}

//删除我的收藏
function deleteFav(favoriteid){
	var url = "myfavaction.action?type=2&&favid=" + favoriteid;
	window.location.href = url;
}

//删除
function deleteComment(commentid){
	var url = "mycommentaction.action?type=2&&reviewid=" + commentid;
	window.location.href = url;
}