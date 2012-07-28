<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.RestaurantPojo"%>
<%@ page import="pojo.FoodPojo"%>
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
<script src="<%=request.getContextPath()%>/js/common.js"></script>

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
			document.getElementById('m_mycomment').style.background = "#e9e9e9";
		</script>

			<div id="comment_icon"></div>
			<div id="comment_word"></div>
			<div id="commentContainer">





				<table cellspacing="0" cellpadding="0" class="commentTbl" border="0">

					<tr>
						<td class="orderTh" width="175" align="center">訂單號</td>
						<td class="orderTh" width="235" align="center">評論內容</td>
						<td class="orderTh" width="155" align="center">評論時間</td>
						<td class="orderTh" width="135" align="center">操作</td>
					</tr>

					<s:iterator value="myReviewList">
						<tr>
							<td class="commentTd commentShopName" align="center"><font
								color="gray">${orderid }</font>
							</td>
							<td class="commentTd" align="center"><font color="gray">${content
									}</font>
							</td>
							<td class="commentTd" align="center"><font color="gray">${time
									}</font>
							</td>
							<td class="commentTd" align="center"><a
								href="javascript:deleteComment(${id})" title="點擊刪除评论">删除</a></td>
						</tr>
					</s:iterator>



				</table>
				<!-- 生成頁碼 -->
				<script type="text/javascript">
							if(${totalPageNum}>1){
								var pagecodeStr="";
								
								 for(var i=1;i<=${totalPageNum};i++){
									
									 if(i==${requestPageNum}){
									 	pagecodeStr=pagecodeStr+"<a href='mycommentaction.action?type=1&requestPageNum="+i+"'> "+i+" </a>"
									 }else{
										 pagecodeStr=pagecodeStr+"<a href='mycommentaction.action?type=1&requestPageNum="+i+"'> ["+i+"]</a>"
									 }
								 }
							 document.write(pagecodeStr);
							}
							</script>
				<!-- end -->


			</div>





		</div>
		<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
	</div>
</body>
</html>