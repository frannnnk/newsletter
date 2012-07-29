<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<script src="<%=request.getContextPath()%>/zDialog/zDialog.js"></script>
<script src="<%=request.getContextPath()%>/zDialog/zDrag.js"></script>


<!-- 页面顶部导航栏 -->
<div class="top_nav">
		<div class="header_left">
			<%
				Object attrObj = request.getSession().getAttribute("authorization");
				if (attrObj != null && (Boolean) attrObj == true) {
			%>

			<span style="font-weight: bold;"></span><font color="black">hi</font>，
			<a style="color: gray;"
				href="<%=request.getContextPath()%>/personal/myaccount.jsp"> <%=request.getSession().getAttribute("nickname")%></a><font
				color="black">！</font> <a style="color: #black;"
				href="logout.action">退出</a>

			<%
				} else {
			%>

			<span style="font-weight: bold;"></span>hi，歡迎來易食飯！<a
				style="color: #6600FF"
				href="<%=request.getContextPath()%>/account/login.jsp?type=1">
				請登錄 </a> &nbsp;<a style="color: #6600FF"
				href="<%=request.getContextPath()%>/account/login.jsp?type=2">免費註冊</a>

			<%
				}
			%>
		</div>


		<div class="header_right">
			<span style="margin: 0;">
			
			<a style="color: gray" href="<%=request.getContextPath()%>/myordersearch.action?type=1&requestpagenum=1">我的訂單</a> 
			<span style="color: gray">|</span> 
			<a style="color: gray" href="<%=request.getContextPath()%>/myaddressinit.action">地址管理</a>
			<span style="color: gray">|</span> 
			<a style="color: gray" href="<%=request.getContextPath()%>/myinvitation.action">我的邀請</a> 
			<span style="color: gray">|</span>  
			<a  style="color: gray" href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');">反饋建議</a>
			</span>
		</div>

</div>

<script src="<%=request.getContextPath()%>/js/jquery/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/js/hk.franks.newsletter.common.js"></script>
<div class="left_cart" id="left_cart">
		<div id="cartCloseBtn" onclick="showMyCart();"></div>
		<div id="allPageCartContent"></div>
</div>
<div class="left_alert" id="left_alert">
		<div id="alertCloseBtn" onclick="showMyAlert();"></div>
		<div id="allPageAlertContent"></div>
</div>