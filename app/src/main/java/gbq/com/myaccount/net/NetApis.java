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
		params.put("userName", account);
		params.put("password", password);
		RetrofitClient.getInstance().executePost(NetConfig.LOGIN, params, listener);
	}

	public void register(final String username, final String password, String tel, String code, final HttpListener listener) {
		final HashMap<String, String> params = new HashMap<>();
		params.put("userName", username);
		params.put("password", password);
		params.put("tel", tel);
		params.put("code", code);
		RetrofitClient.getInstance().executePost(NetConfig.REGISTER, params, listener);
	}

	public void quickRegister(final String username, final HttpListener listener) {
		final HashMap<String, String> params = new HashMap<>();
		params.put("userName", username);
		RetrofitClient.getInstance().executePost(NetConfig.QUICK_REGISTER, params, listener);
	}

	public void getCode(final String tel, HttpListener listener) {
		final HashMap<String, String> params = new HashMap<>();
		params.put("tel", tel);
		RetrofitClient.getInstance().executePost(NetConfig.GET_CODE, params, listener);
	}

	public void getUser(final String username, final HttpListener listener) {
		final HashMap<String, String> params = new HashMap<>();
		params.put("userName", username);
		RetrofitClient.getInstance().executePost(NetConfig.GET_USER, params, listener);
	}

	public void logout(final String username,final HttpListener listener) {
		final HashMap<String, String> params = new HashMap<>();
		params.put("userName", username);
		RetrofitClient.getInstance().executePost(NetConfig.LOGOUT, params, listener);
	}

	public void modifyPassword(String oldPassword, String newPassword, final HttpListener listener) {
		final HashMap<String, String> params = new HashMap<>();
		params.put("old_password", oldPassword);
		params.put("new_password", newPassword);
		RetrofitClient.getInstance().executePost(NetConfig.MODIFY_PASSWORD, params, listener);
	}
}
