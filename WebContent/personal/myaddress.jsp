<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="pojo.AddressInfoPojo"%>
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
				document.getElementById('m_myaddress').style.background = "#e9e9e9";
			</script>

			<div id="address_icon"></div>
			<div id="address_word"></div>
			<div id="currentAddress">
				<div id="currentAddressIcon"></div>
				<div class="sep sep_addr"></div>

				<table cellspacing="0" cellpadding="0" class="addressTbl">
					<!-- 聖朗 "設為默認" (香港叫做“預設”)按鈕的樣式為 “setDefaultIcon” “當前默認”按鈕的樣式為 "defaultIcon" 你的程序控制一下不同的樣式-->
					<!-- 對於修改地址，我的想法是，用戶點擊修改按鈕，隨即將該行地址變為可編輯狀態 修改後保存即可 你可以參照現在這個頁面的效果 -->
					<!-- 如果按照上述邏輯，那麼每一個 input 的id格式規定為 "addr_n" n 為 1,2,3,4,5... -->
					<!-- 相應的 點擊edit圖標的事件  onclick="editAddr('addr_3');" 的參數也要對應 -->

					<!-- 加载用户地址 -->
					<s:iterator value="addressinfoList">

						<tr>

							<td class="addressTd" width="465"><input
								id='<s:property value="addressinfoid" />' type="text"
								class="inputDefault" value='<s:property value="addressText" />'
								readOnly /></td>
							<td class="addressTd" width="215"><div class="editIcon"
									onclick="editAddr('<s:property value="addressinfoid" />');"></div>
								<a
								href="javascript:deleteAddress(<s:property value="addressinfoid" />)"
								title="點擊刪除地址">
									<div class="deleteIcon"></div> </a> <s:if test="isdefault==0">
									<a
										href="javascript:defaultAddress(<s:property value="addressinfoid" />)"
										title="點擊預設地址"><div class="setDefaultIcon"></div> </a>
								</s:if> <s:else>
									<div class="defaultIcon"></div>
								</s:else>
							</td>
						</tr>


					</s:iterator>


				</table>
				<script>
					lockInput();
				</script>
				



			</div>





			<div id="newAddress">
				<div id="newAddressIcon"></div>
				<div class="sep sep_addr"></div>
				<form name="addressform" action="myaddresssave.action">
					<table cellspacing="0" cellpadding="0" class="addressTbl">
						<tr>
							<td class="addressTdNnew" width="100">您的稱呼</td>
							<td class="addressTdNnew" width="600"><input type="text"
								name="areceiver" value="<%=session.getAttribute("nickname")%>"/>
							</td>
						</tr>
						<tr>
							<td class="addressTdNnew" width="100">聯系電話</td>
							<td class="addressTdNnew" width="600"><input type="text"
								name="aphone" />
							</td>
						</tr>
						<tr>
							<td class="addressTdNnew" width="100">所在區域</td>
							<td class="addressTdNnew" width="600"><input type="text"
								name="alocation" />&nbsp;&nbsp;(如：中环，红磡，旺角，尖沙咀等)
							</td>
						</tr>


						<tr>
							<td class="addressTdNnew" width="100">詳細地址</td>
							<td class="addressTdNnew" width="600"><textarea id="astreet"
									name="astreet" class="order_notes" rows="3" cols="24"></textarea>
							</td>
						</tr>
						<tr>
							<td class="addressTdNnew" width="100">是否默认</td>
							<td class="addressTdNnew" width="600"><input type="checkbox"
								name="aisdefault" checked />
							</td>
						</tr>

					</table>
				</form>

				



			</div>

			<a href="javascript:document.addressform.submit()"><div
					id="saveAddressButton"></div> </a>




		</div>

		<jsp:include page="/include/tiny_footer_common.jsp"></jsp:include>
	</div>
</body>
</html>