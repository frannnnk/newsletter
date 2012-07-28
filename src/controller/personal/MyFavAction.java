package controller.personal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.ReviewFavoriteBonusLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 我的收藏控制类
 * 
 * @author 胡圣朗
 * 
 */
public class MyFavAction extends ActionSupport {
	private static Logger logger = Logger
			.getLogger(MyFavAction.class.getName()); // 日志对象;

	private ReviewFavoriteBonusLogic logic = new ReviewFavoriteBonusLogic();// 逻辑操作类.

	// 输入参数
	private int type;// 0或1-查询；2-删除；3-添加;
	private String favid;// 删除操作需要传入此ID.
	private String restaurantid;// 添加收藏时候需要传入.

	// 输出参数;
	List myFavoriteList;

	public String execute() throws Exception {
		logger.info("我的收藏控制类");
		String path = SUCCESS;
		HttpServletRequest request = ServletActionContext.getRequest();
		if (type == 0 || type == 1) {// 查询我的收藏
			String accountid = (String) request.getSession().getAttribute(
					"userid");
			myFavoriteList = logic.getFavorite(accountid);
			path = SUCCESS;
		} else if (type == 2) {// 删除收藏；
			logic.deleteFavorite(favid);
			String accountid = (String) request.getSession().getAttribute(
					"userid");
			myFavoriteList = logic.getFavorite(accountid);
			path = SUCCESS;
		} else if (type == 3) { // 添加收藏 Ajax操作 0-成功；1-失败.
			String accountid = (String) request.getSession().getAttribute(
					"userid");
			HttpSession session = request.getSession();
			String locationstr = (String) session.getAttribute("location");
			if (accountid != null || accountid.equals("")) {
				int resultflag = logic.saveFavorite(accountid, restaurantid,
						locationstr);//0-保存收藏;1-已经收藏;2-收藏失败
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.getWriter().print(resultflag);

			}
			path = this.NONE;
		}

		return path;
	}

	public List getMyFavoriteList() {
		return myFavoriteList;
	}

	public void setMyFavoriteList(List myFavoriteList) {
		this.myFavoriteList = myFavoriteList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getFavid() {
		return favid;
	}

	public void setFavid(String favid) {
		this.favid = favid;
	}

}
