package pojo;

import common.Constant;

/**
 * 公告POJO
 * 
 * @author 胡圣朗
 * @createtime 下午02:54:50
 * 
 */
public class PublicNoticePojo {

	private String id;
	private String subject;
	private String time;
	private String webname;
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWebname() {
		return webname;
	}

	public void setWebname(String webname) {
		this.webname = webname;
		// 自动设置URL。 url=固定前缀+webname.
		String prefix = "publicnotice/";
		String url = prefix + webname;
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
