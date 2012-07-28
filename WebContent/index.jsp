<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.RestaurantPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META NAME="Keywords" CONTENT="香港外賣,香港外卖,香港订餐,香港訂餐,香港美食">
<META NAME="Title" CONTENT="香港外賣">
<META NAME="Subject" CONTENT="香港外賣">


<title>易食飯</title>
<jsp:include page="/include/meta.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_index.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />

<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_index_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
		<![endif]-->

<script
	src="<%=request.getContextPath()%>/js/jquery/jquery-1.3.2.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.tools.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/jquery/jquery.vticker.1.4.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/account/validate.js"></script>






<!-- 图片切换CSS -->
<style type="text/css">
.container,.container * {
	margin: 0;
	padding: 0;
}

.container {
	width: 960px;
	height: 100px;
	overflow: hidden;
	position: relative;
	
}

.slider {
	position: absolute;
	
}

.slider li {
	list-style: none;
	display: inline;
}

.slider img {
	width: 960px;
	height: 100px;
	display: block;
	border: 0px;
}

.num {
	position: absolute;
	right: 5px;
	bottom: 5px;
}

.num li {
	float: left;
	color: #FF7300;
	text-align: center;
	line-height: 16px;
	width: 16px;
	height: 16px;
	font-family: Arial;
	font-size: 12px;
	cursor: pointer;
	overflow: hidden;
	margin: 3px 1px;
	border: 1px solid #FF7300;
	background-color: #fff;
}

.num li.on {
	color: #fff;
	line-height: 21px;
	width: 21px;
	height: 21px;
	font-size: 16px;
	margin: 0 1px;
	border: 0;
	background-color: #FF7300;
	font-weight: bold;
}
</style>



</head>
<body>




	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>

	<div class="left_commbtn" id="feedBackBtn"
		style="position: fixed; right: 0; top: 35%;">
		<a
			href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');"><span><b>同易食飯講幾句</b>
		</span> </a>
	</div>







	<!-- 热门餐厅 -->

	<input type="hidden" value="${hotrestaurant}" id="hidShopStrs" />

	<div id="bg_canvas">
		<div id="top_ads">

			<!-- 图片广告展示 -->
			<div class="container" id="idTransformView">
				<ul class="slider" id="idSlider">
					<li><a href="helpcenter/helpcenter.jsp"><img
							src="picture/common/steps-1.png" /> </a>
					</li>

				</ul>
				<!-- <ul class="num" id="idNum">
					<li>1</li>
					<li>2</li>
					<li>3</li>
				</ul> -->
			</div>

			<script type="text/javascript">
				var $G = function(id) {
					return "string" == typeof id ? document.getElementById(id)
							: id;
				};

				var Class = {
					create : function() {
						return function() {
							this.initialize.apply(this, arguments);
						}
					}
				}

				Object.extend = function(destination, source) {
					for ( var property in source) {
						destination[property] = source[property];
					}
					return destination;
				}

				var TransformView = Class.create();
				TransformView.prototype = {
					//容器对象,滑动对象,切换参数,切换数量
					initialize : function(container, slider, parameter, count,
							options) {
						if (parameter <= 0 || count <= 0)
							return;
						var oContainer = $G(container), oSlider = $G(slider), oThis = this;

						this.Index = 0;//当前索引

						this._timer = null;//定时器
						this._slider = oSlider;//滑动对象
						this._parameter = parameter;//切换参数
						this._count = count || 0;//切换数量
						this._target = 0;//目标参数

						this.SetOptions(options);

						this.Up = !!this.options.Up;
						this.Step = Math.abs(this.options.Step);
						this.Time = Math.abs(this.options.Time);
						this.Auto = !!this.options.Auto;
						this.Pause = Math.abs(this.options.Pause);
						this.onStart = this.options.onStart;
						this.onFinish = this.options.onFinish;

						oContainer.style.overflow = "hidden";
						oContainer.style.position = "relative";

						oSlider.style.position = "absolute";
						oSlider.style.top = oSlider.style.left = 0;
					},
					//设置默认属性
					SetOptions : function(options) {
						this.options = {//默认值
							Up : true,//是否向上(否则向左)
							Step : 5,//滑动变化率
							Time : 10,//滑动延时
							Auto : true,//是否自动转换
							Pause : 4000,//停顿时间(Auto为true时有效)
							onStart : function() {
							},//开始转换时执行
							onFinish : function() {
							}//完成转换时执行
						};
						Object.extend(this.options, options || {});
					},
					//开始切换设置
					Start : function() {
						if (this.Index < 0) {
							this.Index = this._count - 1;
						} else if (this.Index >= this._count) {
							this.Index = 0;
						}

						this._target = -1 * this._parameter * this.Index;
						this.onStart();
						this.Move();
					},
					//移动
					Move : function() {
						clearTimeout(this._timer);
						var oThis = this, style = this.Up ? "top" : "left", iNow = parseInt(this._slider.style[style]) || 0, iStep = this
								.GetStep(this._target, iNow);

						if (iStep != 0) {
							this._slider.style[style] = (iNow + iStep) + "px";
							this._timer = setTimeout(function() {
								oThis.Move();
							}, this.Time);
						} else {
							this._slider.style[style] = this._target + "px";
							this.onFinish();
							if (this.Auto) {
								this._timer = setTimeout(function() {
									oThis.Index++;
									oThis.Start();
								}, this.Pause);
							}
						}
					},
					//获取步长
					GetStep : function(iTarget, iNow) {
						var iStep = (iTarget - iNow) / this.Step;
						if (iStep == 0)
							return 0;
						if (Math.abs(iStep) < 1)
							return (iStep > 0 ? 1 : -1);
						return iStep;
					},
					//停止
					Stop : function(iTarget, iNow) {
						clearTimeout(this._timer);
						this._slider.style[this.Up ? "top" : "left"] = this._target
								+ "px";
					}
				};

				window.onload = function() {
					function Each(list, fun) {
						for ( var i = 0, len = list.length; i < len; i++) {
							fun(list[i], i);
						}
					}
					;

					var objs = $G("idNum").getElementsByTagName("li");

					var tv = new TransformView(
							"idTransformView",
							"idSlider",
							100,
							1,
							{
								onStart : function() {
									Each(
											objs,
											function(o, i) {
												o.className = tv.Index == i ? "on"
														: "";
											})
								}//按钮样式
							});

					tv.Start();

					Each(objs, function(o, i) {
						o.onmouseover = function() {
							o.className = "on";
							tv.Auto = false;
							tv.Index = i;
							tv.Start();
						}
						o.onmouseout = function() {
							o.className = "";
							tv.Auto = true;
							tv.Start();
						}
					})

				}
			</script>

			<!-- end -->


		</div>

		<div id="main">



			<div id="main_location_search">

				<!-- 
				<div id="searchLabel">請輸入您大致的送餐地點，如：黃埔花園</div>
 -->
				<!-- Version 1 
				<input id="searchInput" name="searchInput" type="text"
					onkeypress="handleKeyPress(event,this.form)" /> <input
					type="image" id="searchInputBtn"
					src="picture/common/button_search_shop.png"
					onmouseover="this.src
					= 'picture/common/button_search_shop_active.png';"
					onmouseout="this.src = 'picture/common/button_search_shop.png';"
					onclick="searchLocation('');" />
			-->

				<input id="searchInput" class="searchInputText" name="searchInput"
					type="text" value="請輸入您的大致位置或進入地圖定位"
					onkeypress="handleKeyPress(event,this.form)"
					onfocus="inputValueChange('focus');"
					onblur="inputValueChange('blur');" /> <input type="button"
					id="searchInputBtn" onclick="gotoMap();" value="搜附近外賣" />
				<div class="locBtnSB" onmouseover="this.className = 'locBtnSA';"
					onmouseout="this.className = 'locBtnSB';" title="地圖定位"
					onclick="window.open('map/map.jsp','_self')"></div>

			</div>

			<div id="main_left_top">

				<!-- Start of the previous location Container -->
				<div id="location_indication">
					<%
						String flag = (String) request.getAttribute("flag");
						if (flag.equals("true")) {
					%>

					<div id="icon_location">您最近使用過的地點：</div>

					<div class="location_title location_title_grey">
						<a style="text-decoration: none; color: #7E3D76;"
							href="javascript:searchLocation('${historylocation1}','${x1_cor}','${y1_cor}')">${historylocation1}</a>
						<span class="location_all_shop"><a
							href="javascript:searchLocation('${historylocation1}','${x1_cor}','${y1_cor}')">進入在綫餐廳</a>
						</span>
					</div>



					<%
						if (request.getAttribute("historylocation2") != null) {
					%>
					<!-- Check db and display history location (if any) *NO MORE THAN TWO*-->

					<div class="location_title">
						<a style="text-decoration: none; color: #7E3D76;"
							href="javascript:searchLocation('${historylocation2}','${x2_cor}','${y2_cor}')">
							${historylocation2}</a> <span class="location_all_shop"><a
							href="javascript:searchLocation('${historylocation2}','${x2_cor}','${y2_cor}')">進入在綫餐廳</a>
						</span>
					</div>

					<%
						}
						} else {
					%>


					<!-- When no history location info found, display the hot place info. -->
					<div id="icon_location">
						热搜地點 <font color="#635F5E" size=-1>（點擊地址進入在綫餐廳）</font>
					</div>
					<div class="location_content" id="hotContent">

						<table>
							<tr>
								<td width=50px algin=center class="hotspottitle">紅磡</td>
								<td algin=center ><u class="hotspotcolor">

										<li><a href=javascript:searchLocation('恒藝珠寶中心','22.310356291943833','114.19084764787681')>恒藝珠寶中心</a>
									</li>

										<li><a href=javascript:searchLocation('維港中心1座','22.30971112077453','114.19089056322105')>維港中心1座</a>
									</li>

										<li><a href=javascript:searchLocation('維港中心2座','22.31010814953919','114.19155575105674')>維港中心2座</a>
									</li>

										<li><a href=javascript:searchLocation('駿升中心','22.309353793921126','114.18974257776267')>駿升中心</a>
									</li>

										<li><a href=javascript:searchLocation('富高工業中心','22.310137926651038','114.18981767961509')>富高工業中心</a>
									</li>

										<li><a href=javascript:searchLocation('凱旋工商中心一期二期','22.309532457462126','114.18881989786155')>凱旋工商中心一期二期</a>
									</li>

										<li><a href=javascript:searchLocation('凱旋工商中心三期','22.310202443704906','114.18899692365653')>凱旋工商中心三期</a>
									</li>

										<li><a href=javascript:searchLocation('康樂投資大廈','22.31020740655397','114.18858386346824')>康樂投資大廈</a>
									</li>
										<li><a href=javascript:searchLocation('紅磡商業中心','22.31003866958687','114.18801523515708')>紅磡商業中心</a>
									</li>

										<li><a href=javascript:searchLocation('隆基大樓','22.309244610533526','114.18797231981284')>隆基大樓</a>
									</li> </u></td>
							</tr>


						</table>

					</div>
					<%
						}
					%>

				</div>
			</div>
			<!-- End of the previous location Container -->
			<script>
				genShopMenu();
			</script>



			<div id="main_right_top">
				<div id="websiteinfo">
					<div id="icon_notification">易食飯公告</div>
					<div class=" notice_content">
						<div id="bdNotice"></div>

					</div>

				</div>
			</div>
			<script>
				getNewsFromBackend('notice');
			</script>
			<div id="main_right_center">

				<div id="websiteinfo">
					<div id="icon_websiteinfo">分享易食飯</div>
					<div class=" snsinfo_content">

						<!-- JiaThis Button BEGIN -->
						<!-- JiaThis Button BEGIN -->
						<div id="ckepop">
							<a class="jiathis_button_tsina"><font color="#6E6D68">分享到新浪微博</font></a><br> <a
								class="jiathis_button_tqq"><font color="#6E6D68">分享到騰訊微博</font></a><br> <a
								class="jiathis_button_fb"><font color="#6E6D68">分享到Facebook</font></a>

						</div>
						<script type="text/javascript">
							var jiathis_config = {
								summary : "辦公室白領的午餐網站。上班族們，不用再為午餐頭痛啦~~~ ",
								title : "易食飯",
								ralateuid : {
									"tsina" : "2458052944"
								},
								hideMore : true
							}
						</script>

						<!-- JiaThis Button END -->
						
					</div>

				</div>
			</div>

		</div>
		<jsp:include page="/include/footer_common.jsp"></jsp:include>

		<!-- jiathis -->
		<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js"
			charset="utf-8"></script>
		<!-- end -->

		<!-- 流量统计 -->
		<script
			src="http://s24.cnzz.com/stat.php?id=3627513&web_id=3627513&show=pic"
			language="JavaScript"></script>
		<!-- end -->

	</div>
	
</body>
</html>