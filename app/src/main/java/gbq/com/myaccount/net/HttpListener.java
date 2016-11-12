package gbq.com.myaccount.net;

/**
 * 接收网络返回的监听器
 * Created by gbq on 2016/10/08.
 */
public interface HttpListener {
    void onSuccess(String result);
    void onFail();
    void onError(int errorCode, String errorMessage);
}
