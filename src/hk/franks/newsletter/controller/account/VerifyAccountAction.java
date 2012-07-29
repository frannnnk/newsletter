package hk.franks.newsletter.controller.account;

import hk.franks.newsletter.logic.AccountRelateLogic;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户账户验证 其实就是邮箱地址确认.
 * 
 * @author 胡圣朗
 * 
 */
public class VerifyAccountAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(VerifyAccountAction.class
			.getName()); // 日志对象;
	private AccountRelateLogic logic = new AccountRelateLogic(); // 逻辑操作类.

	private String message; //返回消息.

	// 输入参数.
	private String code;// 此为编码后的userid.

	public String execute() throws Exception {
		logger.info("用户账户验证 其实就是邮箱地址确认.");
		
		String path=null;
		int state = logic.verifyAccount(code);// 0-已经验证过；1-验证成功;2-验证失败.
		if(state==0){
			path=this.SUCCESS;
			message="郵箱已驗證過！";
		}
		else if(state==1){
			path=this.SUCCESS;
			message="郵箱驗證成功！";	
		}
		else if(state==2){
			path=this.SUCCESS;
			message="郵箱驗證失敗！";	
		}
		
		return path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
