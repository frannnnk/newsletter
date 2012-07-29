package hk.franks.newsletter.controller.main;

import hk.franks.newsletter.logic.CoreInfoDisplayLogic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 分页获取推荐外卖餐厅数据(用于处理AJAX请求)
 * 
 * @author 胡圣朗
 * 
 */
public class FoodRestaurantbyPagingAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(FoodRestaurantbyPagingAction.class.getName()); // 日志对象;

	private CoreInfoDisplayLogic logic = new CoreInfoDisplayLogic(); // 逻辑操作类.

	// 输入参数。
	private int type;// 1-推荐外卖分页查询；2-推荐餐厅分页查询.
	private int restaurantrequestpagenum; // 请求页码;
	private int foodrequestpagenum; // 请求页码;
	// 输出参数
	private int restaurantCurrentPagenum;// 餐厅当前页数
	private int foodCurrentPagenum;// 推荐外卖当前页数

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("获取分页餐厅或则推荐外卖数据");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String areaid = (String) session.getAttribute("areaid");
		HttpServletResponse response = ServletActionContext.getResponse();
		if (type == 1) {
			// 分页获取区域推荐外卖
			List recommendFoodlist = logic.getRecommendFood(areaid,
					foodrequestpagenum); // 推荐外卖List;
			String recommoenFoodJSONText = logic
					.buildFoodListJSONText(recommendFoodlist);
			
			response.getWriter().write(recommoenFoodJSONText);
		} else if (type == 2) {
			// 分页获取区域餐厅.
			List restaurantList = logic.getAreaRestaurant(areaid,
					restaurantrequestpagenum);
			String x_coordinate = (String) session.getAttribute("x_coordinate");
			String y_coordinate = (String) session.getAttribute("y_coordinate");
			restaurantList = logic.bulidDistanceForRestaurant(restaurantList,
					x_coordinate, y_coordinate);// 计算每个餐厅 用户距离.
			String restaurantListJsonText = logic
					.buildRestaurantListJSONText(restaurantList);

			response.getWriter().write(restaurantListJsonText);
		}
		return this.NONE;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRestaurantrequestpagenum() {
		return restaurantrequestpagenum;
	}

	public void setRestaurantrequestpagenum(int restaurantrequestpagenum) {
		this.restaurantrequestpagenum = restaurantrequestpagenum;
	}

	public int getFoodrequestpagenum() {
		return foodrequestpagenum;
	}

	public void setFoodrequestpagenum(int foodrequestpagenum) {
		this.foodrequestpagenum = foodrequestpagenum;
	}

}
