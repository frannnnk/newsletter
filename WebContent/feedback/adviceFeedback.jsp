<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.AdvicePojo"%>
<%@ page import="pojo.AdviceResponsePojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.banner .location {
	display: none;
}

.consultation-bg {
	background: url() no-repeat 0 0;
}

.consultation-tip {
	margin: 16px 0 7px;
	padding-left: 20px;
	font: 14px/18px 宋体;
	color: #333;
	background-position: -74px 0;
}

.consultation {
	margin-top: 17px;
	padding: 5px 16px;
	border: 2px solid #E6E6E6;
}

.consultation .user-time {
	padding: 5px 0;
	height: 16px;
	font: 12px/16px Tahoma;
	color: #878787;
	background:
		url(data:image/gif;base64,R0lGODlhJAABAIAAAL+/v////yH5BAAHAP8ALAAAAAAkAAEAAAIHRIynyetdAAA7)
		repeat-x bottom left;
	_background-image: url(/Content/Images/dot_line.gif);
}

.consultation .user-time .user {
	float: left;
}

.consultation .user-time .time {
	float: right;
}

.consultation .content {
	margin: 17px 0 5px;
	font: 12px/180% 宋体;
	color: #333;
}

.no-reply {
	padding: 12px 35px 10px;
	background-color: #F7F7F7;
	background-position: -58px -49px;
	font: 12px/16px 宋体;
	color: #868686;
}

.reply {
	padding: 12px 0 10px 35px;
	background-color: #FFF9E8;
	background-position: -58px -119px;
	font: 12px/16px 宋体;
	color: #F60;
}

.reply span {
	display: inline-block;
	vertical-align: top;
}

#btnSub {
	width: 87px;
	height: 32px;
	background-position: 0 -218px;
}

.add-overflow {
	position: absolute;
	top: 0;
	left: 114px;
	z-index: 10;
	width: 486px;
	height: 95px;
	text-align: center;
	background-color: #fff;
	filter: alpha(opacity =     50);
	-moz-opacity: 0.5;
	opacity: 0.5;
}

.add-overflow span {
	margin: 29px 0 28px;
}

.add-overflow a {
	color: #06C;
}
</style>

<body>
	

	<s:iterator value="adviceList" id="advicePojo">

		<div class="consultation">
			<div class="user-time">
				<span class="user">${username }说：</span><span class="time">${time}</span>
			</div>
			<div class="content">${content }</div>
		</div>


		<s:iterator value="#advicePojo.adviceResponseList" id="adviceResponsePojo">
			<div class="consultation-bg reply">
				<span>客服回复：</span> <span style="width: 860px;">${content }</span>
			</div>
		</s:iterator>

	</s:iterator>



</body>
</html>