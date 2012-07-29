<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易食飯-活動</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_publicnotice.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_include.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/main/bc_common.css" />
</head>
<body>

	<jsp:include page="/include/header_common.jsp"></jsp:include>
	<jsp:include page="/include/banner_common.jsp"></jsp:include>
	<div id="bg_canvas">

		<div class="content">
			活動時間：4.23-5.23 <br> 活動對象：易食飯註冊用戶。<br>
			活動內容：易食飯會在每週一上午11點抽獎派現金，所有註冊用戶都有機會獲得易食飯送出的HK$10至HK$100的現金。<br>
			每週都有20名獲得現金的用戶<br> 活動信息都會在新浪微博、騰訊微博和facebook上同步直播 <br>
			在新浪微博、騰訊微博或facebook上有分享易食飯網站的中獎用戶將獲得雙倍現金。<br>
			獲得獎金的用戶將會收到易食飯的EMAIL，隨後易食飯會將獎金郵寄到用戶指定的地址。<br> <br>
			<br>
			<%
				Object attrObj = request.getSession().getAttribute("authorization");
				if (attrObj != null && (Boolean) attrObj == true) {
			%>
			您已是易食飯註冊用戶，已經參加此次活動 <br> 分享易食飯仲可以嬴雙倍現金。
			<!-- JiaThis Button BEGIN -->
			<div id="ckepop">
				<span class="jiathis_txt">分享到：</span>  <a
					class="jiathis_button_tsina">新浪微薄</a> <a class="jiathis_button_tqq">腾讯微薄</a>
				<a class="jiathis_button_fb">facebook</a>  <a class="jiathis_counter_style"></a>
			</div>
			<script type="text/javascript">
				var jiathis_config = {
						summary : "辦公室白領的午餐網站。上班族們，不用再為午餐頭痛啦~~~ ",
						title : "易食飯",
					ralateuid : {
						"tsina" : "2458052944"
					},
					hideMore : false
				}
			</script>
			<script type="text/javascript"
				src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
			<!-- JiaThis Button END -->

			<%
				} else {
			%>
			您還不是易食飯註冊用戶，<a href="<%=request.getContextPath()%>/account/login.jsp?type=2">立即註冊</a>，就可參加此次活動<br>
			或者您已是易食飯註冊用戶，<a href="<%=request.getContextPath()%>/account/login.jsp?type=1">請登錄</a>，直接參加此次活動。

			<%
				}
			%>
		</div>


		<!-- footer -->
		<table class="tinynewfooter" width="960" border="0" cellspacing="0"
			cellpadding="0" align="center">
			<tr>
				<td align="center" colspan="5" style="padding-top: 0px;">
					Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
			</tr>
		</table>


	</div>


</body>
</html>