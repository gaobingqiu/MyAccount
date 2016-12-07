package gbq.com.myaccount.net;

/**
 * 定义网络参数
 * Created by gbq on 2016-09-10.
 */
class NetConfig {
	//gitHug ip地址：204.232.175.78   github.global.ssl.fastly.net
	//	public final static String URL_REL = "192.168.0.101/";
	final static String URL_REL = "http://10.206.16.86:8080/";
	//网络超时时间
	static final int DEFAULT_TIMEOUT = 10;
	//网络连接池个数
	static final int CONNECT_COUNT = 5;
	//网络连接池保持时间
	static final int CONNECT_TIME = 5;
	public final static int appId = 10001;
	public static int UId = 0;
	public static String Token = "";

	private static String IMGAGE_LOCATION = "";

}
