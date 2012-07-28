package pojo;

/**
 * 好友推荐POJO
 * 
 * @author 胡圣朗
 * @createtime 上午10:08:49
 * 
 */
public class FriendsInvitationPojo {
	private String id;
	private String userid;
	private String friendemail;
	private String time;
	private String state;
	private String stateText;

	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	public String getId() {
		return id;
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

	public String getFriendemail() {
		return friendemail;
	}

	public void setFriendemail(String friendemail) {
		this.friendemail = friendemail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		if(state.equals("1")){
			setStateText("好友已註冊");
		}else if(state.equals("0")){
			setStateText("邀請已發送");
		}
	}

}
