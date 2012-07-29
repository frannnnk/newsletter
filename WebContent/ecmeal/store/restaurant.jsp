<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ page import="hk.franks.newsletter.pojo.FoodPojo"%>
<%@ page import="hk.franks.newsletter.pojo.FoodCategoryPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_store.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/tipTipv13/tipTip.css" />

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_store_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
<![endif]-->
<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.7.0.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script src="<%=request.getContextPath()%>/js/store.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/tipTipv13/jquery.tipTip.minified.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/scrolltopcontrol.js"></script>

<script>
var currentMenuTab = "";
var initHeight = "";

function adjustHeight(){
	

	//document.getElementById("main_footer").style.display = "block";
	document.getElementById("bg_canvas").style.height = initHeight + "px";
	document.getElementById("main_footer").style.top = initHeight + "px";
	
	var topVal = getDocHeight();
	
	document.getElementById("bg_canvas").style.height = (topVal + 50)
			+ "px";
	document.getElementById("main_footer").style.top = (topVal) + "px";
}

$(document).ready(function() {

	var $scrollingDiv = $("#main_right_top_store");
 
		$(window).scroll(function(){
		
			if ($(window).scrollTop() > 145) {
				$scrollingDiv
				.stop()
				.animate({"marginTop": ($(window).scrollTop() -145 ) + "px"}, "slow" );	
			} else {
				$scrollingDiv
				.stop()
				.animate({"marginTop": (0 + 0 ) + "px"}, "slow" );	
			}			
					
		});
		
		$(".rest-tab").each(function(intIndex){			
			$(this).bind (
					"click",
					function(){
						
						
						$(".rest-tab").each(function(){ $(this).removeClass('rest-tab-activated');});
						
						if (currentMenuTab != "") {
								$("#"+currentMenuTab).fadeOut('fast',function(){
								$('#content_menu_'+(intIndex+1)).fadeIn('fast',function(){adjustHeight();});
							}); 
						} else {
							$('#content_menu_'+(intIndex+1)).fadeIn('fast',function(){adjustHeight();});
						}
						
						$(this).addClass('rest-tab-activated');
						currentMenuTab = 'content_menu_'+(intIndex+1);
						
					}
			);			
		});
		
		$('.rest-tab').first().click();

});
</script>

</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>
	<div class="left_commbtn" id="feedBackBtn"
		style="position: fixed; right: 0; top: 35%;">
		<a
			href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');"><span><b>我來推薦好食嘅嘢！</b>
		</span> </a>
	</div>

	<div id="locCon">

		<div class="banner" id="locationBar">
			<div class="location">
				<div class="cell"
					style="color: #FDEBC9; font-family: 微软雅黑; width: 59px;">
					<b>您現在在:</b>
				</div>

				<div class="cell txtLocation">
					<font style="font-family: 微软雅黑;" color=#FF8000><%=session.getAttribute("areaname")%>&nbsp;<font
						style="font-family: 微软雅黑;" color=#FF8000>在綫餐廳 </font> </font>
				</div>
				<a href="<%=request.getContextPath()%>/map/map.jsp"
					class="cell thickbox" title="換地點" id="btnChangeLocale"></a>

			</div>
		</div>
	</div>



	<div id="bg_canvas">
		<div id="main">
			<s:if test="%{numberoftab>=1}">
				<div id="main_left_center_store">

					<!-- Start of the Service Info area -->
					<div id="menu">
						<div id="icon_menu">
							<s:if test="%{numberoftab==1}">
							<div class="rest-tab">餐牌</div>
							</s:if>
							<s:elseif test="%{numberoftab==2}">
							<div class="rest-tab" alt="點擊切換" title="點擊切換">餐牌1</div>
							<div class="rest-tab" alt="點擊切換" title="點擊切換">餐牌2</div>
							</s:elseif>
							<s:elseif test="%{numberoftab==3}">
							<div class="rest-tab" alt="點擊切換" title="點擊切換">餐牌1</div>
							<div class="rest-tab" alt="點擊切換" title="點擊切換">餐牌2</div>
							<div class="rest-tab" alt="點擊切換" title="點擊切換">餐牌3</div>
							</s:elseif>
						</div>


						<s:if test="%{numberoftab>=1}">
							<div class="content_common menu_content content_menu"
								id="content_menu_1">


								<div class="catMsg">
									<s:iterator value="categoryPojoList1" id="categoryPojo"
										status="categoryindex">
										<div
											style="color:white;float:left;margin-left:9px;cursor: url('<%=request.getContextPath()%>/picture/hk.franks.newsletter.common/Hand-Arrow.cur'),auto;"
											onclick="goToByScroll('category${categoryPojo.id}')">
											${categoryPojo.name }</div>
									</s:iterator>
								</div>


								<!-- Category Start -->
								<s:iterator value="categoryPojoList1" id="categoryPojo"
									status="categoryindex">

									<div id="category${categoryPojo.id}" class="menu_category">
										<b>${categoryPojo.name }</b>&nbsp;&nbsp; <font size=-1
											color=red>${categoryPojo.description }</font>
									</div>



									<s:iterator value="SortFoodListCollection1" id="foodlist"
										status="foodindex">
										<s:if test="#categoryindex.index==#foodindex.index">

											<s:iterator value="foodlist">

												<div class="menu_odd toolTipCss"
													onmouseover="showAddButton('addCart_<s:property value="foodid" />');"
													onmouseout="hideAddButton('addCart_<s:property value="foodid" />');">
													<span style="float: left;"> <s:property
															value="foodname" /> </span> <span style="float: right;">
														<table cellspacing="0" cellpadding="0">
															<tr>
																<td><div id="addCart_<s:property value="foodid" />"
																		class="addCartButton"
																		onclick="addToCart('<s:property value="foodid" />','<s:property value="price" />','<s:property value="foodname" />','<s:property value="restaurant_id" /> ');"
																		onmouseover="this.style.backgroundPosition='-0px -728px';"
																		onmouseout="this.style.backgroundPosition='-0px -704px';">&nbsp;</div>
																</td>
																<td>$<s:property value="price" />
																</td>
															</tr>
														</table> </span>

												</div>

											</s:iterator>
										</s:if>
									</s:iterator>
								</s:iterator>
							</div>

						</s:if>



						<s:if test="%{numberoftab>=2}">
							<div class="content_common menu_content content_menu"
								id="content_menu_2">

								<div class="catMsg">
									<s:iterator value="categoryPojoList2" id="categoryPojo"
										status="categoryindex">
										<div
											style="color:white;float:left;margin-left:9px;cursor: url('<%=request.getContextPath()%>/picture/hk.franks.newsletter.common/Hand-Arrow.cur'),auto;"
											onclick="goToByScroll('category${categoryPojo.id}')">
											${categoryPojo.name }</div>
									</s:iterator>
								</div>


								<!-- Category Start -->
								<s:iterator value="categoryPojoList2" id="categoryPojo"
									status="categoryindex">

									<div id="category${categoryPojo.id}" class="menu_category">
										<b>${categoryPojo.name }</b>&nbsp;&nbsp; <font size=-1
											color=red>${categoryPojo.description }</font>
									</div>



									<s:iterator value="SortFoodListCollection2" id="foodlist"
										status="foodindex">
										<s:if test="#categoryindex.index==#foodindex.index">

											<s:iterator value="foodlist">

												<div class="menu_odd toolTipCss"
													onmouseover="showAddButton('addCart_<s:property value="foodid" />');"
													onmouseout="hideAddButton('addCart_<s:property value="foodid" />');">
													<span style="float: left;"> <s:property
															value="foodname" /> </span> <span style="float: right;">
														<table cellspacing="0" cellpadding="0">
															<tr>
																<td><div id="addCart_<s:property value="foodid" />"
																		class="addCartButton"
																		onclick="addToCart('<s:property value="foodid" />','<s:property value="price" />','<s:property value="foodname" />','<s:property value="restaurant_id" /> ');"
																		onmouseover="this.style.backgroundPosition='-0px -728px';"
																		onmouseout="this.style.backgroundPosition='-0px -704px';">&nbsp;</div>
																</td>
																<td>$<s:property value="price" />
																</td>
															</tr>
														</table> </span>

												</div>

											</s:iterator>
										</s:if>
									</s:iterator>
								</s:iterator>
							</div>
						</s:if>


						<s:if test="%{numberoftab>=3}">
							<div class="content_common menu_content content_menu"
								id="content_menu_3">

								<div class="catMsg">
									<s:iterator value="categoryPojoList3" id="categoryPojo"
										status="categoryindex">
										<div
											style="color:white;float:left;margin-left:9px;cursor: url('<%=request.getContextPath()%>/picture/hk.franks.newsletter.common/Hand-Arrow.cur'),auto;"
											onclick="goToByScroll('category${categoryPojo.id}')">
											${categoryPojo.name }</div>
									</s:iterator>
								</div>


								<!-- Category Start -->
								<s:iterator value="categoryPojoList3" id="categoryPojo"
									status="categoryindex">

									<div id="category${categoryPojo.id}" class="menu_category">
										<b>${categoryPojo.name }</b>&nbsp;&nbsp; <font size=-1
											color=red>${categoryPojo.description }</font>
									</div>



									<s:iterator value="SortFoodListCollection3" id="foodlist"
										status="foodindex">
										<s:if test="#categoryindex.index==#foodindex.index">

											<s:iterator value="foodlist">

												<div class="menu_odd toolTipCss"
													onmouseover="showAddButton('addCart_<s:property value="foodid" />');"
													onmouseout="hideAddButton('addCart_<s:property value="foodid" />');">
													<span style="float: left;"> <s:property
															value="foodname" /> </span> <span style="float: right;">
														<table cellspacing="0" cellpadding="0">
															<tr>
																<td><div id="addCart_<s:property value="foodid" />"
																		class="addCartButton"
																		onclick="addToCart('<s:property value="foodid" />','<s:property value="price" />','<s:property value="foodname" />','<s:property value="restaurant_id" /> ');"
																		onmouseover="this.style.backgroundPosition='-0px -728px';"
																		onmouseout="this.style.backgroundPosition='-0px -704px';">&nbsp;</div>
																</td>
																<td>$<s:property value="price" />
																</td>
															</tr>
														</table> </span>

												</div>

											</s:iterator>
										</s:if>
									</s:iterator>
								</s:iterator>
							</div>
						</s:if>



					</div>


					<!-- End of the Service Info area -->
					<script>
						$(function() {
							$(".toolTipCss").tipTip();
						});
					</script>


				</div>
			</s:if>
			<s:else>
				<!-- new sale out takeaway today -->

				<div
					style='font-family: "微软雅黑", "黑体", "Lucida Sans Unicode", "Lucida Grande", sans-serif; margin-left: 100px; margin-top: 50px;'>
					<table>
						<tr style="height: 28px;">
							<td><img src="picture/hk.franks.newsletter.common/saleout.jpg" />
							</td>
							<td style="padding-left: 6px"><font size=8><b>今天已收工啦！</b></font>
							
							</td>
						</tr>

					</table>
				</div>
			</s:else>



			<s:if test="%{numberoftab>=1}">
				<div id="main_right_top_store">
					<div id="cart">
						<div id="icon_cart">我的餐車</div>
						<div class="content_common cart_content" id="cartContent"></div>
						<!-- 是否达到起送价格提升 -->
						<font color="red" size="+1"><b><span id="message"></span>
						</b> </font>
						<!-- end -->
						<div id="icon_checkout">
							<a href="javaScript:submitItems();"> </a>

						</div>

						<script>
						displayCart();
					</script>

					</div>
					<br /> <br />
					<table>
						<tr style="background: #ff821a; height: 28px;">
							<td style="padding-left: 6px; border-radius: 6px;"><font
								color="#FFF" size=3><b>訂餐提示</b> </font></td>
						</tr>
						<tr>
							<td
								style="background-color: #fffaeb; font-size: 12px; color: #7B7B7B; padding-top: 5px; padding-left: 6px; padding-right: 10px">爲了您在中午能准时享受美食，最好在<font
								color=#7B7B7B>11:50</font>前下訂單，爲制作和配送留足時間。<br> <font
								color=#7B7B7B>越早落單越準時享受午餐。</font> <br>價格以網上標註為準，不再另外加收送餐費!
								<br> <font color=#7B7B7B>派送時間 &nbsp;11：30-15：00</font> <br>
								<font color=#7B7B7B>客服時間 &nbsp;10：30-15：00</font> <s:if
									test="%{numberoftab>=2}">
									<br>
									<font color="#ff821a">(不同餐牌的外賣來自不同的製作地點，若帶來不便，請您原諒！)</font>
								</s:if></td>
						</tr>
					</table>
				</div>

			</s:if>

			<div id="main_right_bottom"></div>



		</div>

	</div>
	<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
	<script>
		initHeight =  getDocHeight();
		document.getElementById("bg_canvas").style.height = initHeight
				+ "px";
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