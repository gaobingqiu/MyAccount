package gbq.com.myaccount.net;

/**
 * 定义网络参数
 * Created by gbq on 2016-09-10.
 */
public class NetConfig {
	public final static String URL_REL = "192.168.0.101";
	//网络超时时间
	public static final int DEFAULT_TIMEOUT = 10;
	//网络连接池个数
	public static final int CONNECT_COUNT = 5;
	//网络连接池保持时间
	public static final int CONNECT_TIME = 5;
	public final static int appId = 10001;
	public static int UId = 0;
	public static String Token = "";

	final static String LOGIN = "/loginInterface/login.do";
	final static String LOGOUT = "/login/logout.do";
	final static String QUICK_REGISTER = "/loginInterface/quickRegister.do";
	final static String REGISTER = "/loginInterface/activity_register.do";
	final static String GET_CODE = "/index/getCode.do";
	final static String MODIFY_PASSWORD = "loginInterface/reset.do";
}