package gbq.com.myaccount.module.personal;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * Created by gbq on 2016-11-12.
 */

public class PersonalPresenter {
	private IPersonalCtrl mCtrl;

	public PersonalPresenter(IPersonalCtrl personalCtrl) {
		this.mCtrl = personalCtrl;
	}

	void userLogout(String userName) {
		NetApis.getInstance().logout(userName, new HttpListener() {
			@Override
			public void onSuccess(String result) {
				mCtrl.clearLoginInfo();
				mCtrl.userLogout();
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
