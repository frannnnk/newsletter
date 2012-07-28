<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.OrderPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單管理</title>


<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.minified.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>



<script type="text/javascript">

//check new order amount
function checkNeworderAmount(){
	var url = 'orderamountcheck.action';
	var params = {
		};	
		$.post(url, params, ordercheckcallbackFun, 'text');
}

//回调方法.
function ordercheckcallbackFun(data) {
	if (data == '0') {
		document.getElementById("regrinformation").innerHTML="...";
	} else if(data=='-1') {
		document.getElementById("regrinformation").innerHTML="系統異常！";
	}else {
		
	//alert(document.getElementById("tipsound"));
		document.getElementById("tipsound").play();   
		 
		 document.getElementById("regrinformation").innerHTML="有待處理的訂單！";
	}
}


//确认订单后设置显示状态.
function setConfirmShowState(orderid){
	var trobj = document.getElementById("tr"+orderid);
	var acobj = document.getElementById("a_c"+orderid);
	var adobj = document.getElementById("a_d"+orderid);	
	var spanobj = document.getElementById("span"+orderid);
	trobj.bgColor = "#FFFF93";
	spanobj.innerHTML="已經確認";
	acobj.href="javascript:alert('已经确定过了，不要乱玩啊！呵呵')";
	adobj.href="javascript:alert('已经确定过了，不要乱玩啊！呵呵')";
}



//取消订单后设置显示状态.
function setCancelShowState(orderid){
	var trobj = document.getElementById("tr"+orderid);
	var adobj = document.getElementById("a_d"+orderid);	
	var acobj = document.getElementById("a_c"+orderid);	
	var spanobj = document.getElementById("span"+orderid);
	trobj.bgColor = "#FFFF93";
	spanobj.innerHTML="已經取消";
	adobj.href="javascript:alert('已经取消过了，不要乱玩啊！呵呵')";
	acobj.href="javascript:alert('已经取消过了，不要乱玩啊！呵呵')";
}

//完成订单后设置显示状态.
function setFinishShowState(orderid){
	var trobj = document.getElementById("tr"+orderid);
	var aaobj = document.getElementById("a_a"+orderid);
	var afobj = document.getElementById("a_f"+orderid);
	var spanobj = document.getElementById("span"+orderid);
	trobj.bgColor = "#FFFF93";
	spanobj.innerHTML="完成訂單";
	aaobj.href="javascript:alert('订单已完成了，不要乱玩啊！呵呵')";
	afobj.href="javascript:alert('订单已完成了，不要乱玩啊！呵呵')";
}

//派送失败订单后设置显示状态.
function setFailureShowState(orderid){
	var trobj = document.getElementById("tr"+orderid);
	var afobj = document.getElementById("a_f"+orderid);
	var aaobj = document.getElementById("a_a"+orderid);
	var spanobj = document.getElementById("span"+orderid);
	trobj.bgColor = "#FFFF93";
	spanobj.innerHTML="派送失敗";
	afobj.href="javascript:alert('已经操作过了，不要乱玩啊！呵呵')";
	aaobj.href="javascript:alert('已经操作过了，不要乱玩啊！呵呵')";
}


	//确认订单
	function confirmOrder(orderid) {	
		var url = 'ordermanagement.action';
		var params = {
				type : 2,
				orderstate:2,
				orderid:orderid
		};
		$.post(url, params, callbackFun, 'text');
		setConfirmShowState(orderid);
	}
	
	
	//取消订单
	function confirmCancle(orderid) {
		if (confirm("確定要取消訂單，小心操作哦！")) {
			
			var url = 'ordermanagement.action';
			var params = {
					type : 2,
					orderstate:5,
					orderid:orderid
			};	
			$.post(url, params, callbackFun, 'text');
			setCancelShowState(orderid);
		}
	}
	
	//完成订单
	function finishOrder(orderid) {

		if (confirm("確定執行此操作？")) {
		var url = 'ordermanagement.action';
		var params = {
				type : 2,
				orderstate:3,
				orderid:orderid
		};	
		$.post(url, params, callbackFun, 'text');
		setFinishShowState(orderid);
		}
	}
	
	//派送失败
	function confirmFailure(orderid) {
		if (confirm("確定要派送失敗，小心操作哦！")) {
			var url = 'ordermanagement.action';
			var params = {
					type : 2,
					orderstate:4,
					orderid:orderid
			};	
			$.post(url, params, callbackFun, 'text');
			setFailureShowState(orderid);
		}
	}
	
	//回调方法.
	function callbackFun(data) {
		if(data=='1'){
			
		}
		else{
			alert("操作失败,请立马联系管理员,可能系统故障!");
		}
	}
	
	//分页
	function pagingsearch(requestPagenum) {
		var tmporderstate = document.getElementById("orderstate").value;		
		window.location.href = "ordermanagement.action?type=1&requestpagenum="
				+ requestPagenum + "&orderstate=" + tmporderstate;

	}
	
	

</script>


<style>
ul   li {
	float: left;
	margin: 18px;
	list-style: none;
}

a {
	text-decoration: none;
	color: blue;
}

.neworderTbl {
	width: 1200px;
	margin-top: 10px;
	margin-bottom: 5px;
	font-size: 13px;
	margin-bottom: 5px;
}

.orderTh {
	
}

#neworderTabs { /*border: 1px solid black;*/
	margin-top: 10px;
	margin-left: 30px;
	margin-bottom: 40px;
	width: 1200px;
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
}

.textarea {
	border-left: 0px;
	border-top: 0px;
	border-right: 0px;
	border-bottom: 0px;
	width: 100px;
	height: 60px;
	font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande",
		sans-serif;
	resize: none;
}
</style>


</head>
<body>


	<div id="main">


		<div id="neworderTabs">


			<font size="+2">订单管理>>></font>&nbsp; &nbsp;<a
				href="ordermanagement.action?type=1&orderstate=1&requestpagenum=1"
				title="点击进入">待確認订单界面 </a> &nbsp; &nbsp;<a
				href="ordermanagement.action?type=1&orderstate=2&requestpagenum=1"
				title="点击进入">已確認订单界面 </a>

			<hr>
			<br> <font size="4">當前在<font color="red"><b>${message
						}</b> </font>管理頁面</font>
						
						<!-- for test -->
						
						 <script language="javascript">
						 window.setInterval(checkNeworderAmount,90000);
						 </script>
						
	
						<embed id="tipsound" src="/canteen/sounds/vimdone.wma" hidden="true" loop="0"  autostart="false"></embed>
					
						
						<div style="margin-left:1000px">
							<font color="red">
								<span id="regrinformation">...</span>
							</font>
						</div>
						<br>
						<!-- test end -->
						
			

			<!-- panes -->


			<div style="display: block;">


				<table cellspacing="0" cellpadding="0" class="neworderTbl"
					border="0">

					<tr>
						<td class="orderTh" width="80" align="center"><b>訂單號</b>
						</td>
						<td class="orderTh" width="80" align="center"><b>總金額</b>
						</td>
						<td class="orderTh" width="340" align="center"><b>外賣清單</b>
						</td>
						<td class="orderTh" width="50" align="center"></td>
						<td class="orderTh" width="280" align="center"><b>派送地址</b>
						</td>
						<td class="orderTh" width="20" align="center"></td>
						<td class="orderTh" width="80" align="center"><b>送達時間</b>
						</td>
						<td class="orderTh" width="80" align="center"><b>備註</b>
						</td>
						<td class="orderTh" width="200" align="center"><b>操作</b>
						</td>
						<td class="orderTh" width="50" align="center"></td>
						
						

						<td class="orderTh" width="120" align="center"><b>落單時間</b>
						</td>
						<!-- <td class="orderTh" width="90" align="center"><b>支付方式</b>
						</td>

						<td class="orderTh" width="120" align="center"><b>確認方式</b>
						</td> -->


					</tr>

					<s:iterator value="orderList">
						<tr id="tr${orderid }" height=60px>
							<td class="orderTd" align="center"><font color="#000079">${orderid
									}</font>
							</td>
							<td class="orderTd" align="center"><font color="red">$${summoney
									}</font>
							</td>
							<td class="orderTd" align="center"><font color="#000079">
									<table width=450px style="border: solid 1px #ff0000;">
										<s:iterator value="orderDetailList">
											<tr>
											    <td width=30px><font color="blue">${foodamount}份</font></td>
												<td>${foodname}</td>
												<td width=30px><font color="red">$${foodorderprice}</font>
												</td>
												<td align=center width=150px title='${resname}:${resphone}'>${resname}</td>
											</tr>
										</s:iterator>
									</table> </font>
							</td>
<td class="orderTh" width="50" align="center"></td>
							<td class="orderTd" align="center"><font color="#000079">${address
									}</font>
							</td>
							<td class="orderTh" align="center"></td>
							<td class="orderTd" align="center"><font color="#000079">${dilivertime
									}</font>
							</td>
							<td class="orderTd" align="center"><textarea
									name="textarea${orderid }" id="textarea${orderid }"
									class="textarea" readonly=true>${orderComments }</textarea>
							</td>
							<td class="orderTd" align="center" id="td${orderid }">
								<!--訂單狀態是待確定時，用戶可執行的狀態更改操作 只是取消訂單；訂單狀態是已確定時，用戶可執行的狀態更改操作 只是完成訂單（即派送完成）  -->
								<s:if test="orderstate==1">
									<a id="a_c${orderid }"
										href="javascript:confirmOrder(${orderid })"><b>確認訂單</b> </a>  |  
									<a id="a_d${orderid }"
										href="javascript:confirmCancle(${orderid })" title="此操作小心謹慎"><font
										color="red"><b>取消訂單</b> </font> </a>
								</s:if> <s:if test="orderstate==2">
									<a id="a_a${orderid }"
										href="javascript:finishOrder(${orderid })"><b>完成訂單</b> </a>  |  
									<a id="a_f${orderid }"
										href="javascript:confirmFailure(${orderid })" title="此操作小心謹慎"><font
										color="red"><b>派送失败</b> </font>
								</s:if></td>
							<td class="orderTh" width="50" align="center"></td>
							


							<td class="orderTd" align="center"><font COLOR=#6C3365>${createtime
									}</font></td>
							<%-- <td class="orderTd" align="center"><font COLOR=#6C3365>${paymethodText
									}</font></td>

							<s:if test="creditValue==0">
								<td class="orderTd" align="center" title="電話號碼在派送地址中查找"><font
									color="red"><B>${confirmMethod }</B> </font>
								</td>
							</s:if>
							<s:if test="creditValue==1">
								<td class="orderTd" align="center" title="系統會在狀態更改後自動發郵件確認"><font
									COLOR=#6C3365>${confirmMethod }</font>
								</td>
							</s:if> --%>

						</tr>

					</s:iterator>

				</table>

				<hr>


				<!-- 生成页码 -->
				<script type="text/javascript">
				 var pagecodeStr="";
				 for(var i=1;i<=${totalpagenum };i++){
					 if(i==${currentpagenum}){
					 	pagecodeStr=pagecodeStr+"<a href='javascript:pagingsearch("+i+")'> "+i+" </a>&nbsp;"
					 }else{
						 pagecodeStr=pagecodeStr+"<a href='javascript:pagingsearch("+i+")'>["+i+"]</a>&nbsp;"
					 }
				 }
				 document.write(pagecodeStr);
 				</script>



			</div>





			<!-- activate tabs with JavaScript -->
			<script>
				$(function() {
					// :first selector is optional if you have only one tabs on the page
					$(".css-tabs:first").tabs(".css-panes:first > div");
				});
			</script>


		</div>




	</div>

	<input type="hidden" id="totalpagenum" value="${totalpagenum }" />
	<input type="hidden" id="currentpagenum" value="${currentpagenum }" />
	<input type="hidden" id="orderstate" value="${orderstate }" />

	<br>
</body>
</html>