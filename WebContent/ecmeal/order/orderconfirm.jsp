<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.AddressInfoPojo"%>
<%@ page import="hk.franks.newsletter.pojo.OrederFoodPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_order.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/tipTipv13/tipTip.css" />

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_order_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
		<![endif]-->

<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script src="<%=request.getContextPath()%>/js/order.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.minified.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#newAddress").hide();

		$('#addrNew').click(function() {
			$("#newAddress").slideDown();
			document.getElementById("tel").focus();
			
		});

		$('.dont_remove_this').click(function() {
			$("#newAddress").slideUp();
			
		});

	});
</script>

<script type="text/javascript">
//提交前输入效验
function checkbeforesubmit() {
	if($("#isclosed").length>0){
		alert("餐廳已打烊，不能創建訂單！");		
		return false;
		}else{			
			if(document.getElementById("addrNew").checked){				
				var tmpaspecifialaddress = document.getElementById("aspecifialaddress").value;			
				var tmpphone = document.getElementById("tel").value;			
				
				if(tmpaspecifialaddress==""){
					document.getElementById("aspecifialaddress").focus();
					showMyAlert("詳細地址需要填寫!",true);
					return false;
				}else if(tmpphone==""){
					document.getElementById("tel").focus();
					showMyAlert("聯繫電話需要填寫!",true);
					return false;
				}else{
					return true;
				}
			
			}else{
				return true;
			}
	}
}

	//时间切换
	function timechange(spanid,sleObj){
		document.getElementById(spanid).innerHTML = "大約在<font color=red><b>"+sleObj.options[sleObj.selectedIndex].value+"</b></font>送達";
		
	}
	

</script>


</head>
<body>

	<%
		int ismuti = 0;
		if (request.getAttribute("ismulti") != null)
			ismuti = (Integer) request.getAttribute("ismulti");
	%>


	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>


	<div class="left_commbtn" id="feedBackBtn"
		style="position: fixed; right: 0; top: 35%;">
		<a
			href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');"><span><b>同易食飯講幾句</b>
		</span> </a>
	</div>

	<div id="bg_canvas_new">
		<div id="main_new">


			<form name="orderform" action="ordersubmit.action" method="post"
				onsubmit="return checkbeforesubmit();">


				<!-- address building -->


				<div id="confirm_address" class="content_title_new">
					<div style="float: left;" id="icon_address"></div>
					確認送餐地址

				</div>
				<div id="address">

					<%
						int useraddressamount = 0;
						int userdefaultaddress = 0;
					%>
					<!-- 加载用户地址 -->
					<s:iterator value="addressinfoList">
						<%
							useraddressamount++;
						%>
						<label> <input type="radio" name="diliveryaddressText"
							id="diliveryaddress" class="dont_remove_this"
							value="${addressinfoid }@#@${addressText }"
							<s:if test="isdefault==1" >
						checked
						<%userdefaultaddress++;%>
						</s:if> />
							<s:property value="addressText" /> </label>
						<br />
					</s:iterator>


					<%
						if (userdefaultaddress == 0 && useraddressamount > 0) {
					%>

					<script type="text/javascript">
					document.getElementById("diliveryaddress").checked=true;
					</script>
					<%
						}
					%>

					<!-- *ATTENTION* This "class" is different from others   -->

					<%
						int amountindex = 0;
					%>

					<input type="radio" value="addrNew" name="diliveryaddressText"
						id="addrNew" /> <label for="addrNew"> 使用其他地址 </label> <br />
					<!-- *ATTENTION* This "class" is different from others   -->

					<div class="newAddress" id="newAddress">
						<table>
							<tr>
								<td><label> 聯系電話: </label></td>
								<td><input type="text" id="tel" name="aphone"
									class="inputTel" />
								</td>
							</tr>
							<tr>
								<td><label> 詳細地址: </label>
								</td>
								<td><textarea id="aspecifialaddress"
										name="aspecifialaddress" class="newaddr_input" rows="2"
										cols="40"><%=session.getAttribute("areaname")%>，</textarea>
								</td>
							</tr>
						</table>

					</div>
				</div>
				<!-- address building end -->

				<%
					if (useraddressamount == 0) {
				%>

				<script type="text/javascript">
				$("#newAddress").hide();
				$("#newAddress").slideDown();
				document.getElementById("addrNew").checked=true;
				document.getElementById("tel").focus();
				
				</script>

				<%
					}
				%>

				<div id="confirm_order" class="content_title_new">
					<div style="float: left;" id="icon_order"></div>
					確認訂單信息

				</div>

<%
	int tmpindex = 0;
%>
				<s:iterator value="restaurantFoodList" id="foodlist"
					status="resindex">
			
					
					<table class="order_table" align="center"
						cellspacing="0" cellpadding="0">
						<tr class="order_detail_header">
							<td class="order_detail_header">餐點名稱</td>
							<td class="order_detail_header">單價</td>
							<td class="order_detail_header">數量</td>
							<td class="order_detail_header">小計金額</td>
						</tr>


						<s:iterator value="foodlist">
							<tr>
								<td class="order_detail_item"><s:property value="foodname" />
								</td>
								<td class="order_detail_item"><s:property value="price" />
								</td>
								<td class="order_detail_item"><s:property value="amount" />
								</td>
								<td class="order_detail_item"><s:property value="moneysum" />
								</td>
							</tr>
						</s:iterator>

					</table>

				
					<!-- the price of each order, if orders are more than one -->
					
					<div class="alertordersum">
						<div style="margin-left: 730px;">



							<font color=black>訂單金額：</font>
							<s:iterator value="orderSummoeyList" id="sum" status="sumindex">
								<s:if test="#resindex.index==#sumindex.index">
									<span style="color: red; font-size: 17px; font-weight: bolder;">$${sum}</span>
								</s:if>

							</s:iterator>
						</div>
					</div>
					
<%tmpindex++; %>
					<!-- end -->
				</s:iterator>

<div
						style='font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande", sans-serif;; margin-left: 30px;margin-top: 0px; margin-bottom: 5px;'>
						期望送達時間: <select name="dilivertime">
						<s:iterator value="thefastDeliveryTimeList" id="tmpdilivertime">
							<option><s:property value="tmpdilivertime"/></option>
							</s:iterator>
						</select> 
						
						<%if(tmpindex>1){ %>
						<font color=#ff821a>Tips:由於所訂外賣系<%=tmpindex %>個不同的地點製作，需創建<%=tmpindex %>張訂單。帶來不便，請您原諒！</font>
						<%} %>
						
					</div>


				<div id="confirm_payment" class="content_title_new">
					<div style="float: left;" id="icon_payment"></div>
					支付方式

				</div>
				<div id="payment_selection">

					<input type="radio" value="1" checked name="paymentmethod"
						id="paymentmethod" /><label for="cash_on_delivery"> 餐到付款
					</label>


					<!-- 此处放hidden存放需要的参数 -->
					<input type="hidden" value="${foodids}" name="foodids" /> <input
						type="hidden" value="${amount}" name="amount" /> <input
						type="hidden" value="${totalmoney}" name="totalmoney" /> <input
						type="hidden" value="${restaurantids}" name="restaurantids" /> <input
						type="hidden" value="${pricesum}" name="pricesum" /> <input
						type="hidden" value="${prices}" name="prices" /> <input
						type="hidden" value="<%=amountindex%>" name="restaurantAmount" />
				</div>

				<div id="confirm_remark" class="content_title_new">
					<div style="float: left;" id="icon_remark"></div>
					備注

				</div>
				<div id="remark">

					<table>
						<tr>
							<td><label for="spe_req"> 特別要求: </label>
							</td>
							<td><textarea id="comments" name="comments"
									class="order_notes" rows="3" cols="38"></textarea>
							</td>
						</tr>
					</table>

				</div>

				<a> <input type="image"
					src="<%=request.getContextPath()%>/picture/hk.franks.newsletter.common/spacer.gif"
					class="submitBtn" /> </a>


				<s:token />

			</form>
			<!-- footer -->
			<table class="tinynewfooter" width="960" border="0" cellspacing="0"
				cellpadding="0" align="center">
				<tr>
					<td align="center" colspan="5" style="padding-top: 18px;">
						Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
				</tr>
			</table>
		</div>

	</div>


	<script>
				document.getElementById("bg_canvas_new").style.height = (getDocHeight())+30+"px";
				
	</script>


	<!-- 流量统计 -->
	<div style="display: none">
		<script
			src="http://s24.cnzz.com/stat.php?id=3627513&web_id=3627513&show=pic"
			language="JavaScript"></script>
	</div>
	<!-- end -->


</body>
</html>