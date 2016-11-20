package gbq.com.myaccount.module.personal;

/**
 * 个人中心需要的数据
 * Created by gbq on 2016-11-20.
 */

public class PersonalData {
	private String userName;
	private String imagePath;

	private int loginCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
}
