package gbq.com.myaccount.module.register.success;

import org.json.JSONObject;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * Created by gbq on 2016-11-12.
 */

public class RegisterSuccessPresenter {
	private IRegisterSuccessCtrl mCtrl;

	public RegisterSuccessPresenter(IRegisterSuccessCtrl mCtrl) {
		this.mCtrl = mCtrl;
	}

	void getUser(final String userName) {
		NetApis.getInstance().getUser(userName, new HttpListener() {
			@Override
			public void onSuccess(String result) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					if (jsonObject.has("image")) {
						mCtrl.toPersonal(userName, jsonObject.getString("image"));
					}
				} catch (Exception e) {
					mCtrl.showToast(Define.ERROR_NUKNOWN);
				}
			}

			@Override
			public void onFail() {
				mCtrl.showToast(Define.ERROR_NET);
			}

			@Override
			public void onError(int errorCode, String errorMessage) {
				mCtrl.showToast(errorMessage);
			}
		});
	}
}
