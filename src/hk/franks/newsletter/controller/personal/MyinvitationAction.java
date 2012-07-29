package hk.franks.newsletter.controller.personal;

import hk.franks.newsletter.common.CommonFunction;
import hk.franks.newsletter.logic.ReviewFavoriteBonusLogic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 好友邀请
 * 
 * @author 胡圣朗
 * @createtime 上午10:00:25
 * 
 */
public class MyinvitationAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(MyinvitationAction.class
			.getName()); // 日志对象;

	private ReviewFavoriteBonusLogic logic = new ReviewFavoriteBonusLogic();// 逻辑操作类.

	// 输入参数
	private int type; // 1-好友推荐操作;其他就是好友邀请列表查询。
	private String friendemail;// 邀请好友EMAIL.

	// 输出参数
	private List myinvitationList;
	private int invitationQuota;// 用户所剩的限额.
	private String message;

	public String execute() throws Exception {
		logger.info("好友邀请");
		HttpServletRequest request = ServletActionContext.getRequest();
		String userid = (String) request.getSession().getAttribute("userid");
		String nickname = (String) request.getSession()
				.getAttribute("nickname");

		if (type == 1) {// 好友邀请操作.
			if (CommonFunction.isEmail(friendemail)) {
				if (!userid.equals(friendemail)) { // 不能邀请自己
					boolean tmpflag = logic.recommendFirend(nickname, userid,
							friendemail);
					if (tmpflag)
						message = "邀請成功!";
					else
						message = "邀請失敗!";
				} else {
					message = "不能邀請自己!";
				}
			}else{
				message = "Email格式不正確!";
			}
		}
		myinvitationList = logic.searchMyinvitation(userid);
		invitationQuota = logic.getInvitationQuota(userid);
		return this.SUCCESS;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List getMyinvitationList() {
		return myinvitationList;
	}

	public void setMyinvitationList(List myinvitationList) {
		this.myinvitationList = myinvitationList;
	}

	public int getInvitationQuota() {
		return invitationQuota;
	}

	public void setInvitationQuota(int invitationQuota) {
		this.invitationQuota = invitationQuota;
	}

	public String getFriendemail() {
		return friendemail;
	}

	public void setFriendemail(String friendemail) {
		this.friendemail = friendemail.trim();
	}

}
