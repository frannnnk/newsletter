<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script src="<%=request.getContextPath()%>/js/zDialog.js"></script>
<script src="<%=request.getContextPath()%>/js/zDrag.js"></script>


<!-- 页面顶部导航栏 -->
<div class="top_nav">
	<div class="layout grid top_nav_container">
		<div class="cell left">
			<%
				Object attrObj = request.getSession().getAttribute("authorization");
				if (attrObj != null && (Boolean) attrObj == true) {
			%>

			<span style="font-weight: bold;"></span><font color="black">hi</font>，
			<a style="color: gray;"
				href="<%=request.getContextPath()%>/myhome/mybigcanteen.html"> <%=request.getSession().getAttribute("nickname")%></a><font
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


		<div class="cell right">
			<span style="margin: 0;">
			<a style="color: gray" href="<%=request.getContextPath()%>/myordersearch.action">用戶中心</a> 
			<span style="color: gray">|</span> 
			<a style="color: gray" href="<%=request.getContextPath()%>/myfavaction.action">我的收藏</a>
			<span style="color: gray">|</span> 
			<a style="color: gray" href="<%=request.getContextPath()%>/myordersearch.action">我的訂單</a> 
			<span style="color: gray">|</span> 
			<a style="color: gray" href="<%=request.getContextPath()%>/helpcenter/helpcenter.jsp" target="_blank">訂餐幫助</a>
			<span style="color: gray">|</span> 
			<a  style="color: gray" href="javascript:giveFeedback('<%=request.getContextPath()%>/feedback/offerfeedback.html');">反饋建議</a>
			</span>
		</div>

	</div>
</div>