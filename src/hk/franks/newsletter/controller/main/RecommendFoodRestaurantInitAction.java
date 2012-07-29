package hk.franks.newsletter.controller.main;

import hk.franks.newsletter.logic.CoreInfoDisplayLogic;
import hk.franks.newsletter.logic.map.MapRelatedLogic;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页数据初始化控制类
 * 
 * @author 胡圣朗
 * 
 */
public class RecommendFoodRestaurantInitAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(RecommendFoodRestaurantInitAction.class.getName()); // 日志对象;

	private CoreInfoDisplayLogic logic = new CoreInfoDisplayLogic(); // 逻辑操作类.
	private MapRelatedLogic maplogic = new MapRelatedLogic();// 地图处理逻辑类.

	// 输出参数
	private List recommendRestaurantlist;
	private List recommendFoodlist;
	private int totalRestaurantPagenum = 0; // 餐厅总页数.
	private int totalFoodPagenum = 0; // 外卖总页数.
	private int restaurantCurrentPagenum = 1;// 餐厅当前页数
	private int foodCurrentPagenum = 1;// 推荐外卖当前页数

	public String execute() throws Exception {
		logger.info("首页数据初始化");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String areaid = (String) session.getAttribute("areaid");
		String locationStr = (String) session.getAttribute("location");
		String x_coordinate = (String) session.getAttribute("x_coordinate");
		String y_coordinate = (String) session.getAttribute("y_coordinate");
		int requestpage = 1;// 初始化请求第一页
		// 获取推荐外卖信息.

		recommendFoodlist = logic.getRecommendFood(areaid, requestpage); // 推荐外卖List;
		totalFoodPagenum = logic.getRecommendFoodTotalPagenum(areaid);
		// 获取餐厅信息.
		recommendRestaurantlist = logic.getAreaRestaurant(areaid, requestpage); // 推荐餐厅List;
		recommendRestaurantlist = logic.bulidDistanceForRestaurant(
				recommendRestaurantlist, x_coordinate, y_coordinate);// 计算每个餐厅
																		// 用户距离.
		totalRestaurantPagenum = logic.getAreaRestaurantTotalPageNum(areaid);// 用于构建页码.

		return SUCCESS;
	}

	public int getRestaurantCurrentPagenum() {
		return restaurantCurrentPagenum;
	}

	public void setRestaurantCurrentPagenum(int restaurantCurrentPagenum) {
		this.restaurantCurrentPagenum = restaurantCurrentPagenum;
	}

	public int getFoodCurrentPagenum() {
		return foodCurrentPagenum;
	}

	public void setFoodCurrentPagenum(int foodCurrentPagenum) {
		this.foodCurrentPagenum = foodCurrentPagenum;
	}

	public List getRecommendRestaurantlist() {
		return recommendRestaurantlist;
	}

	public void setRecommendRestaurantlist(List recommendRestaurantlist) {
		this.recommendRestaurantlist = recommendRestaurantlist;
	}

	public List getRecommendFoodlist() {
		return recommendFoodlist;
	}

	public void setRecommendFoodlist(List recommendFoodlist) {
		this.recommendFoodlist = recommendFoodlist;
	}

	public int getTotalRestaurantPagenum() {
		return totalRestaurantPagenum;
	}

	public void setTotalRestaurantPagenum(int totalRestaurantPagenum) {
		this.totalRestaurantPagenum = totalRestaurantPagenum;
	}

	public int getTotalFoodPagenum() {
		return totalFoodPagenum;
	}

	public void setTotalFoodPagenum(int totalFoodPagenum) {
		this.totalFoodPagenum = totalFoodPagenum;
	}

}
