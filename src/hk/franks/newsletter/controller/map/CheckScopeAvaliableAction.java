package hk.franks.newsletter.controller.map;

import hk.franks.newsletter.logic.map.MapRelatedLogic;
import hk.franks.newsletter.pojo.DeliveryScopePojo;

import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

/**
 * 检测所在位置坐标区域是否开通外卖配送业务。
 * 
 * @author 胡圣朗
 * 
 */
public class CheckScopeAvaliableAction extends ActionSupport {

	private static Logger logger = Logger
			.getLogger(CheckScopeAvaliableAction.class.getName()); // 日志对象;
	private MapRelatedLogic logic = new MapRelatedLogic(); // 逻辑操作类.

	private String x_coordinate; // X坐标;
	private String y_coordinate; // Y坐标;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		logger.info("检测坐标所在区域是否开通外卖业务");
		String areaid = "-1";
		DeliveryScopePojo deliveryScopePojo = logic.checkScopeAvaliable(
				x_coordinate, y_coordinate);
		if (deliveryScopePojo != null)
			areaid = deliveryScopePojo.getId();
		String address = "-1";
		if (!areaid.equals("-1")) {
			address = deliveryScopePojo.getDescription();
			address = areaid + "@|@" + address;
		}
		if (!areaid.equals("-1")) {
			logger.info("已经开通外卖业务");
		} else {
			logger.info("未开通外卖业务");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(address);
		return null;
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

}
