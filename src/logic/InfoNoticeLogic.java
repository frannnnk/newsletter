package logic;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pojo.OrderPojo;
import pojo.PublicNoticePojo;
import pojo.ReviewPojo;

import common.Constant;

import dao.InfoNoticeDao;
import dao.ReviewFavoriteBonusDao;

/**
 * 去公告 播报 评论等信息逻辑类
 * 
 * @author 胡圣朗
 * 
 */
public class InfoNoticeLogic {
	private static Logger logger = Logger.getLogger(InfoNoticeLogic.class
			.getName()); // 日志对象;
	private OrderRelatedLogic orderLogic = new OrderRelatedLogic();
	private InfoNoticeDao dao = new InfoNoticeDao();
	private ReviewFavoriteBonusDao reviewdao = new ReviewFavoriteBonusDao(); // 数据库操作对象.

	/**
	 * 取最新的5条公告 条数写死在CONSTANT文件里面的.
	 * 
	 * @return
	 */
	public List getPublicnotice() {
		logger.debug(" 取最新的" + Constant.NOTICENUM + "条公告");
		return dao.getPublicnotice(Constant.NOTICENUM);
	}

	/**
	 * 获取用户最新评论播报.
	 * 
	 * @return
	 */
	public List getReviewBroadcast() {
		int recordNum = Constant.REVIEW_BROADCAST_NUM;

		return reviewdao.getReview(recordNum);
	}

	/**
	 * 获取订餐播报.
	 * 
	 * @return
	 */
	public List getOrderBroadcast() {

		return orderLogic.getOrderBroadcast();
	}

	/**
	 * 获取用户最新评论播报JsonText
	 * 
	 * @return
	 */
	public String getPublicnoticeJsonText() {
		List publicnoticeList = this.getPublicnotice();
		JSONObject root = new JSONObject();
		JSONArray noticeArray = new JSONArray();
		try {
			for (int i = 0; i < publicnoticeList.size(); i++) {
				PublicNoticePojo noticePojo = (PublicNoticePojo) publicnoticeList
						.get(i);
				JSONObject noticeJSONObj = new JSONObject();

				noticeJSONObj.put("id", noticePojo.getId());
				noticeJSONObj.put("subject", noticePojo.getSubject());
				noticeJSONObj.put("time", noticePojo.getTime());
				noticeJSONObj.put("url", noticePojo.getUrl());
				noticeArray.put(noticeJSONObj);
			}
			root.put("result", noticeArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root.toString();
	}
	
	/**
	 * 获取用户最新订单播报JsonText
	 * 
	 * @return
	 */
	public String getOrderBroadcastJsonText() {
		List orderBroadcastList = this.getOrderBroadcast();
		JSONObject root = new JSONObject();
		JSONArray orderBroadcastArray = new JSONArray();
		try {
			for (int i = 0; i < orderBroadcastList.size(); i++) {
				OrderPojo orderBroadcastPojo = (OrderPojo) orderBroadcastList
						.get(i);
				JSONObject orderBroadcastJSONObj = new JSONObject();
				orderBroadcastJSONObj.put("id", orderBroadcastPojo.getOrderid());
				orderBroadcastJSONObj.put("nickname", orderBroadcastPojo.getUsernickname());
				orderBroadcastJSONObj.put("restaurantname", orderBroadcastPojo.getRestaurantname());
				orderBroadcastJSONObj.put("foodname", orderBroadcastPojo.getBroadcastFoodnameText());
				orderBroadcastJSONObj.put("bonus", orderBroadcastPojo.getBonus());
				orderBroadcastArray.put(orderBroadcastJSONObj);
			}
			root.put("result", orderBroadcastArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root.toString();
	}
	
	/**
	 * 获取用户最新评论播报JsonText
	 * 
	 * @return
	 */
	public String getReviewBroadcastJsonText() {
		List reviewBroadcastList = this.getReviewBroadcast();
		JSONObject root = new JSONObject();
		JSONArray reviewArray = new JSONArray();
		try {
			for (int i = 0; i < reviewBroadcastList.size(); i++) {
				ReviewPojo reviewPojo = (ReviewPojo) reviewBroadcastList
						.get(i);
				JSONObject reviewJSONObj = new JSONObject();
				reviewJSONObj.put("id", reviewPojo.getId());
				reviewJSONObj.put("time", reviewPojo.getTime());
				reviewJSONObj.put("restaurantname", reviewPojo.getRestaurantname());
				reviewJSONObj.put("reviewcontent", reviewPojo.getContent());
				reviewArray.put(reviewJSONObj);
			}
			root.put("result", reviewArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root.toString();
	}
}
