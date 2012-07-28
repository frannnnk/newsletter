package controller.map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.map.MapRelatedLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import pojo.DeliveryScopePojo;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author 胡圣朗
 * 
 */
public class LocationConfirmAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(LocationConfirmAction.class
			.getName()); // 日志对象;
	private MapRelatedLogic logic = new MapRelatedLogic(); // 逻辑操作类.

	private String areaid;
	private String areaname; // 地點名稱 如紅磡商業中心
	private String x_coordinate;
	private String y_coordinate;

	public String execute() throws Exception {
		logger.info("输入位置确认,如何在派送外卖范围内，进入推荐外卖餐厅页面，否则进入地图页面");
		String path = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (areaid == null) {
			// 加上香港前缀.
			DeliveryScopePojo deliveryScopePojo = logic.checkScopeAvaliable(
					x_coordinate, y_coordinate);
			if (deliveryScopePojo != null) {
				areaid = deliveryScopePojo.getId();
				areaname = deliveryScopePojo.getDescription();
			} else
				areaid = "-1";
		}
		if (!areaid.equals("-1")) {
			path = this.SUCCESS;
			HttpSession session = request.getSession();
			
			session.setAttribute("areaid", areaid);
			session.setAttribute("areaname", areaname);
			session.setAttribute("x_coordinate", x_coordinate);
			session.setAttribute("y_coordinate", y_coordinate);

			// 用户所使用过的地址存入cookie中.只保存最近2此用户使用过的地址.
			Cookie[] cookies = request.getCookies();
			Cookie newcookie = logic.buildHistoryLocationCookies(cookies,
					areaname, x_coordinate, y_coordinate);
			response.addCookie(newcookie);
		} else {
			path = this.INPUT;
		}

		return path;
	}



	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(String x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public String getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(String y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

}
