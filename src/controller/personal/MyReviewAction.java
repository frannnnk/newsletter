package controller.personal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.OrderRelatedLogic;
import logic.ReviewFavoriteBonusLogic;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 我的评论控制类
 * 
 * @author 胡圣朗
 * 
 */
public class MyReviewAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(MyReviewAction.class
			.getName()); // 日志对象;

	private ReviewFavoriteBonusLogic logic = new ReviewFavoriteBonusLogic();// 逻辑操作类.
	private OrderRelatedLogic orederlogic = new OrderRelatedLogic(); //订单逻辑类.

	// 输入参数
	private int type;// 0或1-查询；2-删除；3-添加;
	private String reviewid;// 删除操作需要传入此ID.

	private String content; // 评论内容.
	private String score; // 评价分数.
	private String orderid;// 所评价订单ID.
	
	private int requestPageNum; //请求的页码。

	// 输出参数;
	List myReviewList;
	
	private int totalPageNum; //总共有多少页.

	public String execute() throws Exception {
		String path = SUCCESS;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String accountid = (String) request.getSession().getAttribute("userid");
		if (type == 0 || type == 1) {// 查询我的评论
			totalPageNum = logic.getReviewTotalPages(accountid);
			myReviewList = logic.getReview(accountid, requestPageNum);
			path = SUCCESS;
		} else if (type == 2) {// 删除评论
			logic.deleteReview(reviewid);
			totalPageNum = logic.getReviewTotalPages(accountid);
			myReviewList = logic.getReview(accountid, 1);
			path = SUCCESS;
		} else if (type == 3) {// 添加评论
			logic.saveReview(accountid, content, orderid, score);
			orederlogic.changeOrderState(orderid, "6"); //订单状态6-已经评论.
			response.getWriter().print("<br><br><br><center><font color=RED><h1>評價成功！</h1></font></center>");
			path = NONE;
		}

		return path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getReviewid() {
		return reviewid;
	}

	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}

	public List getMyReviewList() {
		return myReviewList;
	}

	public void setMyReviewList(List myReviewList) {
		this.myReviewList = myReviewList;
	}

	public String getContent() {
		return content;
	}

	public int getRequestPageNum() {
		return requestPageNum;
	}

	public void setRequestPageNum(int requestPageNum) {
		this.requestPageNum = requestPageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

}
