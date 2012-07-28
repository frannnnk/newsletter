<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="footer_cover"></div>

<div id="main_footer" align="center" class="newfooter"
	style="display: none;">


	<table width="960" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr class="color666">
			<td width="210" align="center">客服咨询</td>
			<td width="192" align="center">新手上路</td>
			<td width="192" align="center">會員服務</td>
			<td width="192" align="center">關注我們</td>
			<td width="192" align="center">易食飯網</td>
		</tr>

		<tr>
			<td align="center">
				<table>

					<tr>
						<td align="center"><a target="_blank"
							href="http://wpa.qq.com/msgrd?v=3&uin=1982209013&site=qq&menu=yes"><img
								border="0" src="picture/common/pa.gif"
								alt="有事就QQ我！" title="有事就QQ我！"> </a> <BR>
								
								<a target="_blank" href="http://settings.messenger.live.com/Conversation/IMMe.aspx?

invitee=faaaab6f80a5bcf3@apps.messenger.live.com&mkt=zh-

CN&useTheme=true&themeName=blue&foreColor=333333&backColor=E8F1F8&linkColor=333333&borderColor=AFD3EB&buttonForeColor=333333&buttonBa

ckColor=EEF7FE&buttonBorderColor=AFD3EB&buttonDisabledColor=EEF7FE&headerForeColor=0066A7&headerBackColor=8EBBD8&menuForeColor=333333

&menuBackColor=FFFFFF&chatForeColor=333333&chatBackColor=FFFFFF&chatDisabledColor=F6F6F6&chatErrorColor=760502&chatLabelColor=6E6C6C"

><img style="border-style: none;" src="picture/common/msn.jpg" alt="有事就MSN我！" title="有事就MSN我！"/></a>
								
								</td>
					</tr>
					<tr>
						<td  align="center"></td>

					</tr>
				</table></td>
			<td align="center">
				<table >
					<tr >
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/helpcenter.jsp"><font color="#6E6D68">訂餐幫助</font></a>
							</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/orderstate.jsp"><font color="#6E6D68">訂單狀態</font></a>
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/evaluateorder.jsp"><font color="#6E6D68">評價訂單</font></a>
						</td>
					</tr>
				</table>
			</td>
			<td align="center">
				<table>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/bonus.jsp"><font color="#6E6D68">關於積分</font></a>
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/contactcustomservice.jsp"><font color="#6E6D68">聯繫客服</font>



						
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/complain.jsp"><font color="#6E6D68">咨詢投訴</font></a>
						</td>
					</tr>
				</table></td>
			<td align="center">
				<table>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="http://weibo.com/2458052944"><font color="#6E6D68">新浪微博</font></a>
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="http://t.qq.com/bigcanteen"><font color="#6E6D68">騰訊微博</font></a>
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="http://weibo.com/2458052944"><font color="#6E6D68">搜狐微博</font></a>
						</td>
					</tr>
				</table>
			</td>	
				
			<td align="center">
				<table>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/aboutus.jsp"><font color="#6E6D68">關於我們</font></a>
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/recruitment.jsp"><font color="#6E6D68">人才招聘</font></a>
						</td>
					</tr>
					<tr>
						<td class="borderleft" align="center"><a target="blank"
							href="<%=request.getContextPath()%>/helpcenter/cooperation.jsp"><font color="#6E6D68">商家合作</font></a>
						</td>
					</tr>
				</table>
			</td>
			
		</tr>
		<tr>
			<td align="center" colspan="5" style="padding-top: 18px;"><br>
				Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
		</tr>
	</table>

</div>

<script>
	var topVal = getDocHeight();

	document.getElementById("main_footer").style.display = "block";
	document.getElementById("footer_cover").style.height = (topVal + 108)
			+ "px";
	document.getElementById("main_footer").style.top = 520 + "px";
</script>
