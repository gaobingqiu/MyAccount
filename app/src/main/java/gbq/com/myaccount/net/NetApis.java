package gbq.com.myaccount.net;

import java.util.HashMap;

/**
 * Created by gbq on 2016-10-17.
 */

public class NetApis {
    private static final String TAG = "NetApis";
    private static NetApis mInstance;

    public static NetApis getInstance() {
        if (null == mInstance) {
            synchronized (NetApis.class) {
                mInstance = new NetApis();
            }
        }
        return mInstance;
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param listener
     */
    public void login(final String account, final String password, final HttpListener listener) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("username", account);
        params.put("password", password);
        RetrofitClient.executePost(NetConfig.LOGIN, params, listener);
    }

    public void logout(final HttpListener listener) {
        final HashMap<String, String> params = new HashMap<>();
        RetrofitClient.executePost(NetConfig.LOGOUT, params, listener);
    }

    public void modifyPassword(String oldPassword, String newPassword, final HttpListener listener) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("old_password", oldPassword);
        params.put("new_password", newPassword);
        RetrofitClient.executePost(NetConfig.MODIFY_PASSWORD, params, listener);
    }
}
