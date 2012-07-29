<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.OrderPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 90秒自动刷新一次页面 -->
<meta http-equiv="refresh" content='60'>
<title>易食飯</title>
<jsp:include page="/include/meta.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_my.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/tipTipv13/tipTip.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />
<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_my_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
<![endif]-->

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script src="<%=request.getContextPath()%>/js/my.js"></script>
<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>

<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.minified.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>



<script type="text/javascript">
	//用戶評價
	function evaluate(orderid) {
		var myObject = new Object();
		myObject.orderid = orderid;
		//window.showModalDialog("personal/orderEvaluate.html",myObject);
		var url = "personal/orderEvaluate.html?type=1&orderid=" + orderid;
		var diag = new Dialog();
		diag.Title = "訂單評價";
		diag.CancelEvent = function() {
			window.location.reload();
			diag.close();
		};
		diag.URL = url;
		diag.show();

	}

	//取消订单
	function confirmCancle(orderid) {
		if (confirm("確定要取消訂單，小心操作哦！")) {
			var url = "myorderstateupdateaction.action?type=1&changeState=5&&orderid="
					+ orderid;
			window.location.href = url;
		}
	}
	
	//分页
	function pagingsearch(requestPagenum) {	
		window.location.href = "<%=request.getContextPath()%>/myordersearch.action?type=1&requestpagenum="+requestPagenum;

	}
</script>

</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>


	 <div id="bg_canvas" style=""> 
	

		<div id="main">


			<jsp:include page="/personal/menu.html"></jsp:include>
			<script>
				document.getElementById('m_myorder').style.background = "#e9e9e9";
			</script>

			<div id="order_icon"></div>
			<div id="order_word"></div>



			<div id="orderTabs">

				<!-- Jquery Tabs -->
				<!-- tabs -->
				<ul class="css-tabs">
					<li><a href="" class="current"><div id="todayOrderIcon">
							</div> </a></li>
					<li><a
						href="<%=request.getContextPath()%>/myordersearch.action?type=2&requestpagenum=1"><div
								id="pastOrderIcon"></div> </a></li>

				</ul>

				<!-- panes -->
				<div class="css-panes">

					<div style="display: block;">

						

						
						
						
						<table cellspacing="0" cellpadding="0" class="orderTbl" border="0">

							<tr>
								<td class="orderTh" width="80" align="center">訂單號</td>

								<td class="orderTh" width="80" align="center">總金額</td>
								<td class="orderTh" width="240" align="center">外賣清單</td>
								<td class="orderTh" width="120" align="center">落單時間</td>
								<td class="orderTh" width="90" align="center">支付方式</td>
								<td class="orderTh" width="80" align="center">訂單狀態</td>
								<td class="orderTh" width="100" align="center">操作</td>
							</tr>
						
							


							<s:iterator value="todayOrderList">
								<tr>
									<td class="orderTd" align="center">${orderid }</td>

									<td class="orderTd" align="center"><font color="red">$${summoney
											}</font>
									</td>
									<td class="orderTd" align="center">
										<table style="border: solid 1px #ff0000;">
											<s:iterator value="orderDetailList">
												<tr>
													<td width=30px><font color="blue">${foodamount }份</font></td>
													<td>${foodname}</td>
													<td><font color="red">$${foodorderprice}</font>
													</td>
												</tr>
											</s:iterator>
										</table>
									</td>
									<td class="orderTd" align="center">${createtime }</td>
									<td class="orderTd" align="center">${paymethodText }</td>
									<td class="orderTd" align="center"><b><font color="red">${orderstateText
											}</font></b></td>
									<td class="orderTd" align="center">
										<!--訂單狀態是待確定時，用戶可執行的狀態更改操作 只是取消訂單；訂單狀態是已確定時，用戶可執行的狀態更改操作 只是完成訂單（即派送完成）  -->
										<s:if test="orderstate==1">
											<a href="javascript:confirmCancle(${orderid })"
												title="您真的要取消訂單嗎？">取消訂單</a>
										</s:if> <s:if test="orderstate==2">
											<a
												href="myorderstateupdateaction.action?type=1&changeState=3&orderid=${orderid }"
												title="若外賣已成功派送到您手中，請點擊此操作">确认送達</a>
										</s:if> <s:if test="orderstate==5">
											--
										</s:if> <s:if test="orderstate==3">
											<a href="javascript:evaluate(${orderid })" title="評價此訂單">
												評價 </a>
										</s:if> <s:if test="orderstate==6">
										 -- 
									</s:if>
									</td>
								</tr>

							</s:iterator>

						</table>
						
						
						
						


							<!-- 生成頁碼 -->
							<script type="text/javascript">
							if(${totalPagenum }>1){
								var pagecodeStr="";
								
								 for(var i=1;i<=${totalPagenum };i++){
									
									 if(i==${currentpagenum}){
									 	pagecodeStr=pagecodeStr+"<a href='javascript:pagingsearch("+i+")'> "+i+" </a>"
									 }else{
										 pagecodeStr=pagecodeStr+"<a href='javascript:pagingsearch("+i+")'>["+i+"]</a>"
									 }
								 }
							 document.write(pagecodeStr);
							}
							</script>
							<!-- end -->


					</div>


				</div>

			</div>




		</div>
		<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
	</div>

<script>

var topVal = getDocHeight();

	
	document.getElementById("main_footer").style.top = topVal + "px";
	document.getElementById("bg_canvas").style.height = topVal + "px";

</script>

</body>
</html>