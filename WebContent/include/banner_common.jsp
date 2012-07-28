<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- Banner, including the logo area -->
<div id="banner_container">
	<div id="baner_centralizer">
		<div id="banner_logo_common">
			<a href="<%=request.getContextPath()%>/index.jsp"> <img
				src="<%=request.getContextPath()%>/picture/common/logo_102_w.png"
				alt="易食飯外賣" height="102" border="0" />
			</a>
			<!-- This on branch HEAD -->
		</div>
	</div>
</div>








