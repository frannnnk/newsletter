
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div id="main_footer" align="center" class="newfooter"
	style="display: none;">

	<table width="960" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td align="center" colspan="5" style="padding-top: 18px;">

				Copyright © 2012 LokFu Corporation, All Rights Reserved</td>
			</td>
		</tr>
	</table>

</div>

<script>
	var topVal = getDocHeight();

	document.getElementById("main_footer").style.display = "block";

	document.getElementById("main_footer").style.top = topVal + "px";
</script>
