package gbq.com.myaccount.net;

/**
 * Created by yuguohe on 2016-09-10.
 */
public class NetConfig {
    //网络超时时间
    public static final int DEFAULT_TIMEOUT = 10;
    //网络连接池个数
    public static final int CONETION_COUNT = 5;
    //网络连接池保持时间
    public static final int CONETION_TIME = 5;
    public final static int appId = 10001;
    public static int UId = 0;
    public  static String Token = "";
    public final static String URL_REL= "http://cdc.server.nubia.cn/api/";
    public final static String LOGIN="loginInterface/login.do";
    public final static String LOGOUT="login/logout.do";
    public final static String MODIFY_PASSWORD ="loginInterface/reset.do";
}
