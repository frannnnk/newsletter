<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.FavoritePojo"%>
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
	href="<%=request.getContextPath()%>/css/main/bc_my.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/tipTipv13/tipTip.css" />

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

</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>


<div id="bg_canvas">
	<div id="main">


		<jsp:include page="/personal/menu.html"></jsp:include>
		<script>
			document.getElementById('m_myfav').style.background = "#e9e9e9";
		</script>

		<div id="fav_icon"></div>
		<div id="fav_word"></div>
		<div id="commentContainer">


		


			<table cellspacing="0" cellpadding="0" class="commentTbl" border="0">

				<tr>
					<td class="orderTh" width="175" align="center">餐廳名稱</td>

					<td class="orderTh" width="155" align="center">收藏時間</td>
					<td class="orderTh" width="135" align="center">操作</td>
				</tr>

				<s:iterator value="myFavoriteList">
					<tr>
						<td class="commentTd commentShopName" align="center">
						<a href="restaurantmenuinitaction.action?restaurantid=${restaurantid }&locationstr=${locationstr }"><font color="gray">${restaurantName}</font></a>
						</td>

						<td class="commentTd" align="center"><font color="gray">${time}</font></td>
						<td class="commentTd" align="center"><a
							href="javascript:deleteFav(${id})" title="點擊刪除收藏">刪除
						</a>
						</td>
					</tr>
				</s:iterator>



			


			</table>



		</div>





	</div>

<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
</div></body>
</html>