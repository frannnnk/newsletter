package hk.franks.newsletter.pojo;

/**
 * 评论表
 * 
 * @author 胡圣朗
 * 
 */
public class ReviewPojo {

	private String id;
	private String content;
	private String time;
	private String userid;
	private String nickname;
	private String restaurantid;
	private String restaurantname;
	private String orderid;
	private String score;
	private String scoretext;

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(String restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
		if (score.equals("1")) {
			this.setScoretext("好評");
		} else if (score.equals("2")) {
			this.setScoretext("中評");
		} else if (score.equals("3")) {
			this.setScoretext("差評");
		}
	}

	public String getScoretext() {
		return scoretext;
	}

	public void setScoretext(String scoretext) {
		this.scoretext = scoretext;
	}

}
