package hk.franks.newsletter.logic;

import hk.franks.newsletter.common.CommonFunction;
import hk.franks.newsletter.common.Constant;
import hk.franks.newsletter.dao.AccountRelateDao;
import hk.franks.newsletter.dao.OrderRelatedDao;
import hk.franks.newsletter.logic.mail.MailFuncion;
import hk.franks.newsletter.pojo.OrderDetailPojo;
import hk.franks.newsletter.pojo.OrderFormDetailPojo;
import hk.franks.newsletter.pojo.OrderPojo;
import hk.franks.newsletter.pojo.OrederFoodPojo;
import hk.franks.newsletter.pojo.RestaurantPojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;




/**
 * 订单相关逻辑类
 * 
 * @author 胡圣朗
 * 
 */
public class OrderRelatedLogic {

	private static Logger logger = Logger.getLogger(OrderRelatedLogic.class
			.getName()); // 日志对象;
	private MailFuncion mail = new MailFuncion();
	private OrderRelatedDao dao = new OrderRelatedDao();// 数据库操作对象.
	private AccountRelateDao accountdao = new AccountRelateDao();
	private ReviewFavoriteBonusLogic bonuslogic = new ReviewFavoriteBonusLogic();

	/**
	 * 获取用户地址信息
	 * 
	 * @param userid
	 *            用户ID
	 * @return 用户地址信息对象集合
	 */
	public List getAddress(String userid) {
		logger.debug("获取用户地址");
		return dao.getaddressinfo(userid);
	}

	/**
	 * 包装成已定食物对象列表.
	 * 
	 * @param orderstr
	 *            订单信息字符串. 格式：foodid@|@price@|@foodname@|@amount
	 * @|@食物个属性间分隔； @*|*@各个食物对象间的分隔;
	 * @return 0-餐馆名称LIST;1-餐馆食物LIST.2-residList
	 */
	public List[] wraptoOrderFoodlist(String orderstr) {
		/*
		 * String objsplit = "@\\*\\|\\*@"; String paramsplit = "@\\|@"; List
		 * orderFoodList = new ArrayList(); String foodobjstextArray[] =
		 * orderstr.split(objsplit); for (int j = 0; j <
		 * foodobjstextArray.length; j++) { String foodobjText =
		 * foodobjstextArray[j]; OrederFoodPojo orderfoodpojo = new
		 * OrederFoodPojo(); String foodobjarray[] =
		 * foodobjText.split(paramsplit);
		 * orderfoodpojo.setFoodid(foodobjarray[0]);
		 * orderfoodpojo.setPrice(foodobjarray[1]);
		 * orderfoodpojo.setFoodname(foodobjarray[2]);
		 * orderfoodpojo.setAmount(foodobjarray[3]);
		 * orderfoodpojo.setResId(foodobjarray[4].trim()); float sum = (new
		 * Float(foodobjarray[1].trim()).floatValue()) (new
		 * Integer(foodobjarray[3].trim())); orderfoodpojo.setMoneysum(new
		 * Float(sum).toString()); orderFoodList.add(orderfoodpojo);
		 * 
		 * } return orderFoodList;
		 */

		String objsplit = "@\\*\\|\\*@";
		String paramsplit = "@\\|@";
		List recordrestaurantIdList = new ArrayList();

		List restaurantIdList = new ArrayList();
		List restaurantNameList = new ArrayList();
		List restaurantPojoList = new ArrayList();
		List restaurantFoodList = new ArrayList();

		String foodobjstextArray[] = orderstr.split(objsplit);
		// 取餐厅数量，遍历解析一次foodobjstextArray；
		int restaurantsize = 0;
		List realRestaurantIdList = new ArrayList();
		for (int i = 0; i < foodobjstextArray.length; i++) {
			String foodobjText = foodobjstextArray[i];
			String foodobjarray[] = foodobjText.split(paramsplit);
			String tmprestaurantid = foodobjarray[4];
			if (!realRestaurantIdList.contains(tmprestaurantid)) {
				restaurantsize++;
				realRestaurantIdList.add(tmprestaurantid);
			}
		}

		for (int i = 0; i < restaurantsize; i++) {
			String restaurantid = (String) realRestaurantIdList.get(i);
			List orderFoodList = new ArrayList();
			for (int j = 0; j < foodobjstextArray.length; j++) {

				String foodobjText = foodobjstextArray[j];
				OrederFoodPojo orderfoodpojo = new OrederFoodPojo();
				String foodobjarray[] = foodobjText.split(paramsplit);
				String tmprestaurantid = foodobjarray[4];
				if (restaurantid.equals(tmprestaurantid)) {
					orderfoodpojo.setFoodid(foodobjarray[0]);
					orderfoodpojo.setPrice(foodobjarray[1]);
					orderfoodpojo.setFoodname(foodobjarray[2]);
					orderfoodpojo.setAmount(foodobjarray[3]);
					float sum = (new Float(foodobjarray[1].trim()).floatValue())
							* (new Integer(foodobjarray[3].trim()));
					orderfoodpojo.setMoneysum(new Float(sum).toString());
					orderFoodList.add(orderfoodpojo);
				}
			}
			restaurantIdList.add(restaurantid);
			restaurantFoodList.add(orderFoodList);
		}
		List[] resultListarray = { null, restaurantFoodList, restaurantIdList,
				restaurantPojoList };
		return resultListarray;

	}

	/**
	 * 获取所有餐厅的ID， 多个用逗号隔开。从食物订单中获取所有餐厅ID.
	 * 
	 * @param restaurantsStr
	 */
	public String getRestaurantids(String orderstr) {
		String objsplit = "@\\*\\|\\*@";
		String paramsplit = "@\\|@";
		// 取餐厅数量，遍历解析一次foodobjstextArray；
		String foodobjstextArray[] = orderstr.split(objsplit);
		String resultRestaurantids = "";
		List realRestaurantIdList = new ArrayList();
		for (int i = 0; i < foodobjstextArray.length; i++) {
			String foodobjText = foodobjstextArray[i];
			String foodobjarray[] = foodobjText.split(paramsplit);
			String tmprestaurantid = foodobjarray[4];
			if (!realRestaurantIdList.contains(tmprestaurantid)) {
				resultRestaurantids = resultRestaurantids + ","
						+ tmprestaurantid;
				realRestaurantIdList.add(tmprestaurantid);
			}
		}
		return resultRestaurantids.substring(1);
	}

	/**
	 * 获取每张订单的总金额.
	 * 
	 * @return 总金额.
	 */
	public List getOrderSumMoney(List orderFoodList) {
		List orderSumMoneyList = new ArrayList();
		float sumlMoney = 0;
		for (int i = 0; i < orderFoodList.size(); i++) {
			List foodList = (List) orderFoodList.get(i);
			sumlMoney = 0;
			for (int j = 0; j < foodList.size(); j++) {
				OrederFoodPojo orderfoodpojo = (OrederFoodPojo) foodList.get(j);
				float sum = new Float(orderfoodpojo.getMoneysum());
				sumlMoney += sum;
			}
			orderSumMoneyList.add(sumlMoney);
		}

		return orderSumMoneyList;
	}

	/**
	 * 获取总金额.
	 * 
	 * @return 总金额.
	 */
	public String getTotalMoney(List orderFoodList) {

		float totalMoney = 0;
		for (int i = 0; i < orderFoodList.size(); i++) {
			List foodList = (List) orderFoodList.get(i);
			for (int j = 0; j < foodList.size(); j++) {
				OrederFoodPojo orderfoodpojo = (OrederFoodPojo) foodList.get(j);
				float sum = new Float(orderfoodpojo.getMoneysum());
				totalMoney += sum;
			}
		}

		return new Float(totalMoney).toString();
	}

	/**
	 * 构造食物ID
	 * 
	 * @param orderfoodList
	 * @return[] 0-多个食物ID字符串；1-食物数量的字符串；2-食物单价 一個餐館不同外賣用逗号隔开，不同餐館 用分號隔開;
	 */
	public String[] buildFoodidsAndAmountsText(List restaurantFoodList) {

		String result[] = { "", "", "" };
		for (int i = 0; i < restaurantFoodList.size(); i++) {
			List orderfoodList = (List) restaurantFoodList.get(i);
			for (int j = 0; j < orderfoodList.size(); j++) {
				OrederFoodPojo orderfoodpojo = (OrederFoodPojo) orderfoodList
						.get(j);
				String foodid = orderfoodpojo.getFoodid();
				String amount = orderfoodpojo.getAmount();
				String price = orderfoodpojo.getPrice();
				result[0] = result[0] + "," + foodid;
				result[1] = result[1] + "," + amount;
				result[2] = result[2] + "," + price;
			}
			if (i != restaurantFoodList.size() - 1) {
				result[0] = result[0] + ";";
				result[1] = result[1] + ";";
				result[2] = result[2] + ";";
			}
		}
		result[0] = result[0].substring(1);
		result[1] = result[1].substring(1);
		result[2] = result[2].substring(1);
		return result;
	}

	/**
	 * 获取小计金额的字符串 不同餐馆用分号隔开.
	 * 
	 * @return
	 */
	public String getpriceSumStr(List orderSummoeyList) {
		String pricesumStr = "";
		for (int i = 0; i < orderSummoeyList.size(); i++) {
			Float tmp = (Float) orderSummoeyList.get(i);
			pricesumStr = pricesumStr + ";," + tmp.toString();
		}
		return pricesumStr.substring(2);
	}

	/**
	 * 提交订单可能有拆单 形成多张订单.
	 * 
	 * @param foodids
	 * @param amount
	 * @param moneysum
	 * @param address
	 * @param dilivertime
	 * @param paymentmethod
	 * @param comments
	 * @param accountid
	 * @param restaurantid
	 * @return 订单结果展示对象集合.
	 */
	public List submitOrderincludeSplit(String foodids, String amount,
			String prices, String pricesum, String address,
			String deliveryTime, String paymentmethod, String comment,
			String accountid, String restaurantids, String locationstr) {
		String splitStr = ";,";
		String restaurantidarray[] = restaurantids.split(",");
		String foodidsarray[] = foodids.split(splitStr);
		String amountsarray[] = amount.split(splitStr);
		String pricesarray[] = prices.split(splitStr);
		String pricesumarray[] = pricesum.split(splitStr);
		String createtime = CommonFunction.getcurrentTimetext();

		List allFoodObjList = new ArrayList();

		for (int i = 0; i < restaurantidarray.length; i++) {
			List orderfoodList = new ArrayList();
			String foodidsStr = foodidsarray[i];
			String amountsStr = amountsarray[i];
			String pricesStr = pricesarray[i];

			String foodidArray[] = foodidsStr.split(",");
			String amountsStrArray[] = amountsStr.split(",");
			String priceArray[] = pricesStr.split(",");
			for (int j = 0; j < foodidArray.length; j++) {
				OrderFormDetailPojo orderformdetailpojo = new OrderFormDetailPojo();
				orderformdetailpojo.setFoodid(foodidArray[j]);
				orderformdetailpojo.setAmount(amountsStrArray[j]);
				orderformdetailpojo.setPrice(priceArray[j]);
				orderfoodList.add(orderformdetailpojo);
			}
			allFoodObjList.add(orderfoodList);
		}
		// 保存訂單信息
		List resultlist = dao.saveAllOrder(restaurantidarray, createtime,
				pricesumarray, address, comment, paymentmethod, accountid,
				deliveryTime, allFoodObjList, locationstr);
		return resultlist;
	}

	/**
	 * 构造配送地址.
	 * 
	 * @param areceiver
	 * @param area
	 * @param aspecifialaddress
	 * @param aphone
	 * @return
	 */
	public String buildDiliveryAddress(String areceiver,
			String aspecifialaddress, String aphone) {
		return areceiver + "| " + aspecifialaddress + "| " + aphone;
	}

	/**
	 * 订单查询
	 * 
	 * @param searchType
	 *            1-查询当日订单；2-查询历史订单.
	 * @param accoountid
	 *            账户ID 。
	 * @return
	 */
	public List searchOrder(int searchType, String accountid, int requestpagenum) {
		String firstdate = "2010-10-1";
		String lastdate = "2031-10-2";
		String todaydate = CommonFunction.getcurrentDatetext();
		List orderList = null;
		List orderDetailList = null;
		if (searchType == 1) {// 当日订单 需要展示订单明细.
			int startindex = Constant.TODAYORDERRECORDNUM * requestpagenum
					- Constant.TODAYORDERRECORDNUM;
			int recordnum = Constant.TODAYORDERRECORDNUM;

			orderList = dao.searchOrder(accountid, todaydate, lastdate,
					startindex, recordnum);
			orderDetailList = dao.searchOrderDetailByaccount(accountid,
					todaydate, lastdate, startindex, recordnum);
			buildOrderList(orderList, orderDetailList);
		} else if (searchType == 2) {
			int startindex = Constant.HISTORYRDERRECORDNUM * requestpagenum
					- Constant.HISTORYRDERRECORDNUM;
			int recordnum = Constant.HISTORYRDERRECORDNUM;
			orderList = dao.searchOrder(accountid, firstdate, todaydate,
					startindex, recordnum);
		}
		return orderList;
	}

	/**
	 * 获取我的订单的总页码数.
	 * 
	 * @param searchType
	 * @param accountid
	 * @return
	 */
	public int getTotalOrderPageNum(int searchType, String accountid) {
		int resultpagenum = 0;
		String firstdate = "2010-10-1";
		String lastdate = "2031-10-2";
		String todaydate = CommonFunction.getcurrentDatetext();
		List orderList = null;
		List orderDetailList = null;
		int totalorderPageNum = 0;
		if (searchType == 1) {// 当日订单 需要展示订单明细.
			int totalrecordnum = dao.getMyorderTotalRecords(accountid,
					todaydate, lastdate);
			resultpagenum = (totalrecordnum - 1) / Constant.TODAYORDERRECORDNUM
					+ 1;
		} else if (searchType == 2) {
			int totalrecordnum = dao.getMyorderTotalRecords(accountid,
					firstdate, todaydate);
			resultpagenum = (totalrecordnum - 1)
					/ Constant.HISTORYRDERRECORDNUM + 1;
		}
		return resultpagenum;
	}

	/**
	 * 将订单明细结构化放入订单计划对象中.
	 */
	private List buildOrderlist(List orderList, List orderDetailList) {
		for (int i = 0; i < orderList.size(); i++) {
			OrderPojo orderpojo = (OrderPojo) orderList.get(i);
			String orderid = orderpojo.getOrderid();
			for (int j = 0; j < orderDetailList.size(); j++) {
				OrderDetailPojo orderDetailPojo = (OrderDetailPojo) orderDetailList
						.get(j);
				String tmporderid = orderDetailPojo.getOrderid();
				if (orderid.equals(tmporderid)) {
					// 含份数
					String foodname = orderDetailPojo.getFoodname();
					String amount = orderDetailPojo.getFoodamount();
					String orderdetailText = orderpojo.getOrderdetailText();
					orderdetailText = orderdetailText + foodname + "|" + amount
							+ "份；  \r\n ";
					orderpojo.setOrderdetailText(orderdetailText);
					// 只放一種食物播报食物文字.
					String broadcastFoodnameText = orderpojo
							.getBroadcastFoodnameText();
					if (broadcastFoodnameText == null
							|| broadcastFoodnameText.equals(""))
						orderpojo.setBroadcastFoodnameText(foodname + "等");
				}
			}
		}
		return orderList;
	}

	/**
	 * 查询订单明细
	 * 
	 * @param orderids
	 *            多个ORDERID 逗号隔开.
	 * @return
	 */
	public List searchOrderDetail(String orderids) {
		if (orderids == null || orderids.equals("")) {
			orderids = "-1";
		}
		return dao.searchOrderDetail(orderids);
	}

	/**
	 * 更改订单状态.
	 * 
	 * @param orderid
	 * @param state
	 *            状态【订单状态1- 待确定；2-确认（准备派送）；3-派送完成；
	 *            4-派送失败（可能由于顾客地址异常）；5-作废（取消订单，但数据库中不删除）6-已经评论】
	 * @return
	 */
	public boolean changeOrderState(String orderid, String state) {
		boolean flag = dao.updateOrderState(orderid, state);
		// 完成订单 和 评论都要加积分.
		String userid = dao.getUseridByorderid(new Integer(orderid));

		if (state.equals("3")) // 成功完成订单
			bonuslogic.setBonus(userid, 2);
		else if (state.equals("6")) // 评论
			bonuslogic.setBonus(userid, 3);

		// 发邮件
		// 确认邮件在用户提交订单时发送。
		/*
		 * if (state.equals("2")) // 确认订单 sendOrderConfirmMail(new
		 * Integer(orderid));
		 */
		else if (state.equals("5")) // 取消订单
			sendOrderCancelMail(new Integer(orderid));

		return flag;
	}

	/**
	 * 更改状态的时候 判断是否更改诚信值.
	 * 
	 * @param accountid
	 * @param state
	 * @return
	 */
	public boolean changeCreditValue(String accountid, int state) {
		int HIGHCREDIT = 1;// 高诚信值;
		int LOWCREDIT = 0;// 低诚信值;
		boolean flag = false;
		if (state == 3) { // 派送完成；诚信值为高；
			flag = dao.setCreditValue(accountid, HIGHCREDIT);
		} else if (state == 4) { // 派送失败；诚信值为低 ；
			flag = dao.setCreditValue(accountid, LOWCREDIT);
		}

		return flag;
	}

	/**
	 * 设置订单显示标志为不显示状态.
	 * 
	 * @param state
	 *            1-显示状态；2-不显示状态；
	 * @return
	 */
	public boolean setOrderNotshown(int state, String orderid) {
		return dao.updateOrderIsShowState(orderid, state);
	}

	/**
	 * 判斷訂單是否能夠被取消掉.
	 * 
	 * @return
	 */
	public boolean CanCancleOrnot(String orderid) {
		logger.debug("判斷訂單是否能夠被取消掉.");
		int orderstate = dao.getOrderState(orderid);
		if (orderstate == 2) {
			return false;
		} else
			return true;
	}

	/**
	 * 订单状态更改冲突信息保存。当用户将订单由待确认改为取消时候，如果由于长时间没刷新页面导致的 实际状态和用户展示状态不一致就会出现不同步冲突.
	 * 目前只要取消状态会产生冲突.
	 * 
	 * @conflictType 5-取消状态冲突.
	 * @return
	 */
	public boolean orderConflictInfoSave(String orderid, String conflictType) {
		logger.debug("订单状态更改冲突orderid= " + orderid);
		String time = CommonFunction.getcurrentTimetext();
		return dao.saveorderConflictInfo(orderid, conflictType, time);
	}

	/**
	 * 订单查询 通过状态,（用于订单管理）
	 * 
	 * @param state
	 * @param requestpagenum
	 *            请求的页码
	 * @return
	 */
	public List searchOrderBystate(int state, int requestpagenum) {
		int startindex = requestpagenum * Constant.ORDERMANAGERECORDNUM
				- Constant.ORDERMANAGERECORDNUM;
		int recordnum = Constant.ORDERMANAGERECORDNUM;
		List orderList = dao.searchOrder(state, startindex, recordnum);
		String orderids = getOrderidsTextByOrderList(orderList);
		List orderDetailList = searchOrderDetail(orderids);
		buildOrderList(orderList, orderDetailList);
		return orderList;
	}

	/**
	 * put orderdetailpojo into orderpojo
	 * 
	 * @param orderList
	 * @param orderDetailList
	 */
	private void buildOrderList(List orderList, List orderDetailList) {
		for (int i = 0; i < orderList.size(); i++) {
			OrderPojo orderpojo = (OrderPojo) orderList.get(i);
			for (int j = 0; j < orderDetailList.size(); j++) {
				OrderDetailPojo orderDetailPojo = (OrderDetailPojo) orderDetailList
						.get(j);
				if (orderpojo.getOrderid().equals(orderDetailPojo.getOrderid())) {
					orderpojo.getOrderDetailList().add(orderDetailPojo);
				}
			}
		}
	}

	/**
	 * 获取订单总页数（用于订单管理）
	 * 
	 * @return
	 */
	public int getOrderTotalpageBystate(int state) {
		int totalrecords = dao.getTotalOrderRecordnum(state);
		int totalpagenum = -1;
		if (totalrecords % Constant.ORDERMANAGERECORDNUM != 0)
			totalpagenum = totalrecords / Constant.ORDERMANAGERECORDNUM + 1;
		else
			totalpagenum = totalrecords / Constant.ORDERMANAGERECORDNUM;
		return totalpagenum;
	}

	/**
	 * 根据订单集合获取到订单ID的字符串，多个ID逗号隔开.
	 * 
	 * @param orderList
	 * @return
	 */
	private String getOrderidsTextByOrderList(List orderList) {
		StringBuffer orderids = new StringBuffer();
		if (orderList.size() > 0) {
			for (int i = 0; i < orderList.size(); i++) {
				OrderPojo orderpojo = (OrderPojo) orderList.get(i);
				String tmpOrderid = orderpojo.getOrderid();
				orderids.append(tmpOrderid);
				orderids.append(",");
			}
			orderids = orderids.deleteCharAt(orderids.length() - 1);
		}
		return orderids.toString();
	}

	/**
	 * 电邮通知订单已经收到.
	 * 
	 * @param emailAddress
	 * @param orderPojoList
	 */
	public void mailOrderReady(String emailAddress, List orderPojoList) {
		StringBuffer subject = new StringBuffer();
		StringBuffer content = new StringBuffer();
		subject.append("订单");
		content.append(emailAddress + "您好！");
		for (int i = 0; i < orderPojoList.size(); i++) {
			OrderPojo orderpojo = (OrderPojo) orderPojoList.get(i);
			subject.append(orderpojo.getOrderid() + ", ");
		}
		subject.deleteCharAt(subject.length() - 1);
		subject.append("已收到，正在处理中!");

		mail.mailSenderForcommon(emailAddress, subject.toString(),
				subject.toString());
	}

	/**
	 * 根据订单ID获取用户ID.
	 * 
	 * @param orderid
	 * @return
	 */
	public String getUseridByOrderid(String orderid) {
		return dao.getUseridByorderid(new Integer(orderid));
	}

	/**
	 * 获取订餐播报.
	 * 
	 * @return
	 */
	public List getOrderBroadcast() {
		int recordNum = Constant.ORDER_BROADCAST_NUM;
		List tmporderList = dao.searchLastestOrder(recordNum);
		String orderids = getOrderidsTextByOrderList(tmporderList);
		List orderDetailList = searchOrderDetail(orderids);
		List orderList = buildOrderlist(tmporderList, orderDetailList);
		return orderList;
	}

	/**
	 * 发送用戶下单收到邮件
	 * 
	 * @param orderid
	 *            订单ID
	 */
	private void sendOrderConfirmMail(int orderid) {
		logger.debug("发送用戶下单收到邮件 orderid=" + orderid);
		String userid = dao.getUseridByorderid(orderid);
		String nickname = accountdao.getnickname(userid);
		String addresses = userid;
		String subject = "易食飯已收到您的訂單" + orderid;
		String url = Constant.DNS
				+ "myordersearch.action?type=1&requestpagenum=1";
		String contentHTML = "<html><body>親愛的您好：<br><br>易食飯網已於"
				+ CommonFunction.getcurrentTimetext()
				+ "收到您提交的訂單<font color=red><b>"
				+ orderid
				+ "</b></font>,我們會按時將外賣送達！<p><p>"
				+ "您可以隨時進入<a href=\""
				+ url
				+ "\" target=\"_blank\">訂單查詢</a>頁面查詢訂單詳情<p>"
				+ "如外賣沒準時送達或其他任何諮詢，都可點擊<a href='http://www.ecmeal.hk'>易食飯</a>網站首頁左下角的MSN和QQ進行在綫客服諮詢，或者致電客服專線：35683510"
				+ "<br><br><b><font color=gray>易食飯團隊<br>www.ecmeal.hk © Copyright 2012</b></font></body></html>";
		mail.mailSenderForCustom(addresses, subject, contentHTML);
	}

	/**
	 * 取消订单邮件
	 * 
	 * @param orderid
	 *            订单ID
	 */
	private void sendOrderCancelMail(int orderid) {
		logger.debug("取消订单邮件 orderid=" + orderid);
		String userid = dao.getUseridByorderid(orderid);
		String nickname = accountdao.getnickname(userid);
		String addresses = userid;
		String subject = "很抱歉，您在易食飯的訂單" + orderid + "未能成功！";
		String contentHTML = "<html><body>"
				+ "很抱歉，您在易食飯的訂單<font color=red><b>"
				+ orderid
				+ "</b></font>未能成功！<br>"
				+ "如需幫助，可點擊<a href='http://www.ecmeal.hk'>易食飯</a>網站首頁左下角的MSN和QQ進行在綫客服諮詢，或者致電客服專線：35683510"
				+ "<br><br><b><font color=gray>易食飯團隊<br>www.ecmeal.hk © Copyright 2012</b></font></body></html>";
		mail.mailSenderForCustom(addresses, subject, contentHTML);
	}

	/**
	 * 发送用戶下单收到邮件
	 * 
	 * @param orderid
	 *            订单ID
	 */
	public void sendOrderReceiveMail(String userid, String nickname) {
		logger.debug("发送用戶下单收到邮件 ");
		String addresses = userid;
		String subject = "易食飯已收到您的訂單";
		String url = Constant.DNS
				+ "myordersearch.action?type=1&requestpagenum=1";
		String contentHTML = "<html><body>親愛的<font color='blue'>"
				+ nickname
				+ "</font>您好：<br><br>易食飯網已於<font color='red'><b>"
				+ CommonFunction.getcurrentTimetext()
				+ "</b></font>收到您提交的訂單,我們會按時將外賣送達！<p><p>"
				+ "您可以隨時進入<a href=\""
				+ url
				+ "\" target=\"_blank\">訂單查詢</a>頁面查詢訂單詳情<p>"
				+ "如外賣沒準時送達或其他任何諮詢，都可點擊<a href='http://www.ecmeal.hk'>易食飯</a>網站首頁左下角的MSN和QQ進行在綫客服諮詢，或者致電客服專線：35683510"
				+ "<br><br><b><font color=gray>易食飯團隊<br>www.ecmeal.hk © Copyright 2012</b></font></body></html>";
		mail.mailSenderForCustom(addresses, subject, contentHTML);
	}

	/**
	 * 获取最早派送时间列表.
	 * 
	 * @param deliveryTimeList
	 * @return
	 */
	public List getFastDeliveryTimeList(List deliveryTimeList) {
		List fastDeliveryTimeList = new ArrayList();
		for (int i = 0; i < deliveryTimeList.size(); i++) {
			List tmpDeliveryList = (List) deliveryTimeList.get(i);
			fastDeliveryTimeList.add(tmpDeliveryList.get(0));
		}
		return fastDeliveryTimeList;
	}

	/**
	 * 获取送达时间列表
	 * 
	 * @return
	 */
	public List getDeliveryTimeList() {

		List<String> diliverList = new ArrayList();

		Calendar before1120AM = Calendar.getInstance();
		before1120AM.set(Calendar.HOUR_OF_DAY, 11);
		before1120AM.set(Calendar.MINUTE, 25);

		Calendar before1150AM = Calendar.getInstance();
		before1150AM.set(Calendar.HOUR_OF_DAY, 11);
		before1150AM.set(Calendar.MINUTE, 50);

		Calendar before1220AM = Calendar.getInstance();
		before1220AM.set(Calendar.HOUR_OF_DAY, 12);
		before1220AM.set(Calendar.MINUTE, 20);

		Calendar before1250AM = Calendar.getInstance();
		before1250AM.set(Calendar.HOUR_OF_DAY, 12);
		before1250AM.set(Calendar.MINUTE, 50);

		Calendar before1320AM = Calendar.getInstance();
		before1320AM.set(Calendar.HOUR_OF_DAY, 13);
		before1320AM.set(Calendar.MINUTE, 20);

		Calendar before1350AM = Calendar.getInstance();
		before1350AM.set(Calendar.HOUR_OF_DAY, 13);
		before1350AM.set(Calendar.MINUTE, 50);

		Calendar current = Calendar.getInstance();
		if (current.before(before1120AM)) { // 11:20前落单，最早12：00送达。
			diliverList.add(Constant.FASTEST);
			diliverList.add("12:10");
			diliverList.add("12:40");
			diliverList.add("13:10");
			diliverList.add("13:40");
			diliverList.add("14:10");
			diliverList.add("14:40");
		} else if (current.after(before1120AM) && current.before(before1150AM)) { // 11:50前落单，最早12：30送达。
			diliverList.add(Constant.FASTEST);
			diliverList.add("12:40");
			diliverList.add("13:10");
			diliverList.add("13:40");
			diliverList.add("14:10");
			diliverList.add("14:40");
		} else if (current.after(before1150AM) && current.before(before1220AM)) { // 12:20前落单，最早13：00送达。
			diliverList.add(Constant.FASTEST);
			diliverList.add("13:10");
			diliverList.add("13:40");
			diliverList.add("14:10");
			diliverList.add("14:40");
		} else if (current.after(before1220AM) && current.before(before1250AM)) { // 12:50前落单，最早13：30送达。
			diliverList.add(Constant.FASTEST);
			diliverList.add("13:40");
			diliverList.add("14:10");
			diliverList.add("14:40");
		} else if (current.after(before1250AM) && current.before(before1320AM)) { // 13:20前落单，最早14：00送达。
			diliverList.add(Constant.FASTEST);
			diliverList.add("14:10");
			diliverList.add("14:40");
		} else if (current.after(before1320AM) && current.before(before1350AM)) { // 13:50前落单，最早14：30送达。
			diliverList.add(Constant.FASTEST);
			diliverList.add("14:40");
		}
		return diliverList;
	}

	/**
	 * 获取某些个餐厅订单外卖派送时间范围.
	 * 
	 * @param restaurantids
	 * @return List 里面是集合对象 timescopeList:0-尽快 ; >=1就是其他的 一般一小时以后
	 */
	public List getOrderDeliveryTimeList(String restaurantids) {
		Map map = new HashMap();
		List deliveryTimescopeList = new ArrayList();
		// 取多家餐厅如果有有 外卖派送时间范围。
		List resultList = dao.getDeliveryTimeScope(restaurantids);
		List timescopeList = (List) resultList.get(0);
		List arrivetimeList = (List) resultList.get(1);
		List residList = (List) resultList.get(2);
		for (int i = 0; i < residList.size(); i++) {
			String tmpResid = (String) residList.get(i);
			// 计算最早送达时间.
			String tmparrivetime = (String) arrivetimeList.get(i);
			String tmptimescope = (String) timescopeList.get(i);

			// 获取最早派送和打烊时间
			Calendar endOfDeliveryTime = getEndofDeliverytime(tmptimescope);
			Calendar startOfDeliveryTime = getStartofDeliverytime(tmptimescope);

			// 判断当前下单时间是在开始派送时间前还是后
			int flag = isCurrentTimeWithinTimeScope(tmptimescope);
			if (flag == 0) { // 0-在区域内，取当前时间+送餐所需时间.
				Calendar currentTime = Calendar.getInstance();
				currentTime.add(Calendar.MINUTE, new Integer(tmparrivetime));
				deliveryTimescopeList = getDeliveryTimeList(
						currentTime.get(Calendar.HOUR_OF_DAY),
						currentTime.get(Calendar.MINUTE), endOfDeliveryTime);
				int minite = (currentTime.get(Calendar.MINUTE) / 10 * 10 + 5);
				String minitestr = null;
				if (minite < 10)
					minitestr = "0" + new Integer(minite).toString();
				else
					minitestr = new Integer(minite).toString();
				String currenttimeStr = currentTime.get(Calendar.HOUR_OF_DAY)
						+ ":" + minitestr;
				deliveryTimescopeList.add(0, currenttimeStr);// 尽快时间.
				map.put(tmpResid, deliveryTimescopeList);

			} else if (flag == 1) {// 1-开始时间前
				startOfDeliveryTime.add(Calendar.MINUTE, new Integer(
						tmparrivetime));
				deliveryTimescopeList = getDeliveryTimeList(
						startOfDeliveryTime.get(Calendar.HOUR_OF_DAY),
						startOfDeliveryTime.get(Calendar.MINUTE),
						endOfDeliveryTime);
				int minite = (startOfDeliveryTime.get(Calendar.MINUTE) / 10 * 10 + 5);
				String minitestr = null;
				if (minite < 10)
					minitestr = "0" + new Integer(minite).toString();
				else
					minitestr = new Integer(minite).toString();
				String startOfDeliveryTimeStr = startOfDeliveryTime
						.get(Calendar.HOUR_OF_DAY) + ":" + minitestr;
				deliveryTimescopeList.add(0, startOfDeliveryTimeStr);// 尽快时间.
				map.put(tmpResid, deliveryTimescopeList);
			} else if (flag == 2) {// 2-结束时间后，这里不用特别处理，过了打烊时间 当日送时间范围将不可用。
				// 目前不需要处理。
				deliveryTimescopeList.add(0, "-1");// 尽快时间.
				map.put(tmpResid, deliveryTimescopeList);
			}

		}
		// 重排序，按照resids的顺序.
		List resultDeliveryList = new ArrayList(); // 最终返回的结果集合.
		String tmpResidArray[] = restaurantids.split(",");

		for (int i = 0; i < tmpResidArray.length; i++) {
			List tmpDeliveryList = (List) map.get(tmpResidArray[i]);
			resultDeliveryList.add(tmpDeliveryList);
		}
		return resultDeliveryList;
	}

	/**
	 * 获取打烊时间
	 * 
	 * @param timescope
	 * @return
	 */
	private Calendar getEndofDeliverytime(String timescope) {
		Calendar tmpTime = Calendar.getInstance();
		String timescopearray[] = timescope.split("-");
		String endtime = timescopearray[1];
		String tmpTimeArray[] = endtime.split(":");
		int hour = new Integer(tmpTimeArray[0]);
		int minute = new Integer(tmpTimeArray[1]);
		tmpTime.set(Calendar.HOUR_OF_DAY, hour);
		tmpTime.set(Calendar.MINUTE, minute);
		return tmpTime;
	}

	/**
	 * 获取最早派送时间
	 * 
	 * @param timescope
	 * @return
	 */
	private Calendar getStartofDeliverytime(String timescope) {
		Calendar tmpTime = Calendar.getInstance();
		String timescopearray[] = timescope.split("-");
		String starttime = timescopearray[0];
		String tmpTimeArray[] = starttime.split(":");
		int hour = new Integer(tmpTimeArray[0]);
		int minute = new Integer(tmpTimeArray[1]);
		tmpTime.set(Calendar.HOUR_OF_DAY, hour);
		tmpTime.set(Calendar.MINUTE, minute);
		return tmpTime;
	}

	/**
	 * 判断当前时间是否在时间区间内
	 * 
	 * @param timescope
	 *            格式 11:00-21:00
	 * @return 0-是区域内；1-开始时间前; 2-结束时间后
	 */
	private int isCurrentTimeWithinTimeScope(String timescope) {
		int flag = 0;
		Calendar currentTime = Calendar.getInstance();
		Calendar tmpTime = Calendar.getInstance();
		String timescopearray[] = timescope.split("-");
		String starttime = timescopearray[0];
		String endtime = timescopearray[1];
		// 开始时间
		String tmpTimeArray[] = starttime.split(":");
		int hour = new Integer(tmpTimeArray[0]);
		int minute = new Integer(tmpTimeArray[1]);
		tmpTime.set(Calendar.HOUR_OF_DAY, hour);
		tmpTime.set(Calendar.MINUTE, minute);
		if (currentTime.after(tmpTime))
			flag = 0;
		else
			flag = 1;
		if (flag == 0) {
			tmpTimeArray = endtime.split(":");
			hour = new Integer(tmpTimeArray[0]);
			minute = new Integer(tmpTimeArray[1]);
			tmpTime.set(Calendar.HOUR_OF_DAY, hour);
			tmpTime.set(Calendar.MINUTE, minute);
			if (currentTime.before(tmpTime))
				flag = 0;
			else
				flag = 2;
		}
		return flag;
	}

	/**
	 * 获取派送时间列表
	 * 
	 * @param fasthourofDelivery
	 *            最早外卖能到达时间 小时 （下单时间+餐厅派送平均时间）
	 * @param fastminuteofDelivery
	 *            最早外卖能到达时间 分钟
	 * 
	 * @param endDeliveryTime
	 *            最晚派送到时间(餐厅收单结束时间+派送所需时间)
	 * @return
	 */
	private List getDeliveryTimeList(int fasthourofDelivery,
			int fastminuteofDelivery, Calendar endDeliveryTime) {
		List deliveryTimeList = new ArrayList();
		fastminuteofDelivery = fastminuteofDelivery / 10 * 10;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, fasthourofDelivery);
		cal.set(Calendar.MINUTE, fastminuteofDelivery);
		cal.add(Calendar.MINUTE, 50); // 派送时间列表从最快送达时间45分钟后开始.
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		minute = minute / 10 * 10;

		String tmpMinuteStr;
		if (minute == 0)
			tmpMinuteStr = "00";
		else
			tmpMinuteStr = new Integer(minute).toString();

		String resulttime = new Integer(hour).toString() + ":" + tmpMinuteStr;
		deliveryTimeList.add(resulttime);
		int timelimit = 72; // 24*3
		int i = 0;
		while (true) {
			cal.add(Calendar.MINUTE, 20);
			if (cal.after(endDeliveryTime)) {
				break;
			} else {
				hour = cal.get(Calendar.HOUR_OF_DAY);
				minute = cal.get(Calendar.MINUTE);
				minute = minute / 10 * 10;
				if (minute == 0)
					tmpMinuteStr = "00";
				else
					tmpMinuteStr = new Integer(minute).toString();
				resulttime = new Integer(hour).toString() + ":" + tmpMinuteStr;
				deliveryTimeList.add(resulttime);
			}
			i++;
			if (i > timelimit) {
				break;// 进入此位置说明异常.目的是避免死循环.
			}
		}

		return deliveryTimeList;
	}

	/**
	 * 根据用户ID获取用户的邮箱网址.
	 * 
	 * @param userid
	 * @return
	 */
	public String getUserEmailHost(String userid) {

		String emailhostAddress = "http://www.gmail.com";
		try {
			String emailhost = userid.split("@")[1];
			if (emailhost.contains("qq")) {
				emailhostAddress = "http://mail.qq.com";
			} else if (emailhost.contains("gmail")) {
				emailhostAddress = "http://www.gmail.com";
			} else if (emailhost.contains("yahoo")) {
				emailhostAddress = "http://mail.cn.yahoo.com";
			} else if (emailhost.contains("163")) {
				emailhostAddress = "http://mail.163.com";
			} else if (emailhost.contains("hotmail")) {
				emailhostAddress = "http://www.hotmail.com";
			} else {
				emailhostAddress = "http://mail." + emailhost.split("\\.")[0]
						+ ".com";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return emailhostAddress;
	}

	/**
	 * 判断是否达到起送价格
	 * 
	 * @return
	 */
	public boolean checkisSatisfySendprice(String restaurantids,
			List orderSummoeyList) {
		boolean isSatisfyFlag = true;
		List resPojoList = dao.getRestaurants(restaurantids);
		String residArray[] = restaurantids.split(",");

		for (int i = 0; i < residArray.length; i++) {
			String tmpresid = residArray[i].trim();
			Float tmpSummoney = (Float) orderSummoeyList.get(i);
			for (int j = 0; j < resPojoList.size(); j++) {
				RestaurantPojo respjo = (RestaurantPojo) resPojoList.get(j);
				if (tmpresid.equals(respjo.getId())) {
					if (tmpSummoney < new Float(respjo.getSend_price())) {
						isSatisfyFlag = false;
						break;
					}
				}
			}
			if (!isSatisfyFlag)
				break;
		}

		return isSatisfyFlag;
	}

	/**
	 * check new order amount
	 * 
	 * @return
	 */
	public String checkNewOrderCount() {
		int amount = dao.checkNewOrderCount();
		return new Integer(amount).toString();
	}
	/**
	 * 设置所有已确认的订单状态为派送完成
	 */
	public boolean  finishOrder(){
		return dao.updateOrderTofinish();
	}

}
