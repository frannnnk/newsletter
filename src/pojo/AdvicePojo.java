package pojo;

import java.util.ArrayList;
import java.util.List;

public class AdvicePojo {

	private String id;
	private String userid;
	private String username;
	private String content;
	private String time;
	private List adviceResponseList = new ArrayList(); //存放AdviceResponsePojo.

	public List getAdviceResponseList() {
		return adviceResponseList;
	}

	public void setAdviceResponseList(List adviceResponseList) {
		this.adviceResponseList = adviceResponseList;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
