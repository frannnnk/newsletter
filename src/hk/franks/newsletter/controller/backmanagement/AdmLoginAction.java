package hk.franks.newsletter.controller.backmanagement;

import hk.franks.newsletter.logic.backmanagement.ManagementLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 管理员登录
 * 
 * @author 胡圣朗
 * @createtime 下午05:21:08
 * 
 */
public class AdmLoginAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(AdmLoginAction.class
			.getName()); // 日志对象;
	private ManagementLogic logic = new ManagementLogic();

	private String musername;
	private String mpassword;

	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean flag = logic.admLoginCheck(musername, mpassword);
		if (flag) {
			HttpSession session = request.getSession();
			session.setAttribute("admauthority", "true");
			return this.SUCCESS;
		} else {
			return this.NONE;
		}
	}

	public String getMusername() {
		return musername;
	}

	public void setMusername(String musername) {
		this.musername = musername;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

}
