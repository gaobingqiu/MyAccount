package gbq.com.myaccount.net;

/**
 * 定义网络参数
 * Created by gbq on 2016-09-10.
 */
public class NetConfig {
	//	public final static String URL_REL = "192.168.0.101/";
	public final static String URL_REL = "http://10.206.16.86:8080/";
	//网络超时时间
	public static final int DEFAULT_TIMEOUT = 10;
	//网络连接池个数
	public static final int CONNECT_COUNT = 5;
	//网络连接池保持时间
	public static final int CONNECT_TIME = 5;
	public final static int appId = 10001;
	public static int UId = 0;
	public static String Token = "";

	private static String IMGAGE_LOCATION = "";

	final static String LOGIN = "/loginInterface/login.do";
	final static String LOGOUT = "/login/logout.do";
	final static String QUICK_REGISTER = "/loginInterface/quickRegister.do";
	final static String REGISTER = "/loginInterface/register.do";
	final static String GET_CODE = "/loginInterface/getCode.do";


	final static String GET_USER = "loginInterface/getUser.do";
	final static String MODIFY_PASSWORD = "loginInterface/reset.do";

	public static String NEWS_URL = "/NewsInterface/getGlobalNews.do";
	public final static String NEWS_GLOBAL = "/NewsInterface/getGlobalNews.do";
	public final static String NEWS_SPORTS = "/NewsInterface/getPElNews.do";
	public final static String NEWS_SCIENCE = "/NewsInterface/getTeNews.do";
}
