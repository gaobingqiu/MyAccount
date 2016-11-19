package gbq.com.myaccount.module.register;

import android.text.TextUtils;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * 注册的支持者
 * Created by gbq on 2016-11-12.
 */

public class RegisterPresenter {
	private IRegisterCtrl registerCtrl;

	RegisterPresenter(IRegisterCtrl registerCtrl) {
		this.registerCtrl = registerCtrl;
	}

	void register(String userName, String password, String tel, String code, boolean isAgree) {
		if (TextUtils.isEmpty(userName)) {
			registerCtrl.showToast(Define.EMPTY_USERNAME);
			return;
		}
		if (TextUtils.isEmpty(password)) {
			registerCtrl.showToast(Define.EMPTY_PASSWORD);
			return;
		}
		if (TextUtils.isEmpty(tel)) {
			registerCtrl.showToast(Define.EMPTY_TEL);
			return;
		}
		if (TextUtils.isEmpty(code)) {
			registerCtrl.showToast(Define.EMPTY_USERNAME);
			return;
		}
		if (!isAgree) {
			registerCtrl.showToast(Define.DIS_AGREE);
			return;
		}
		NetApis.getInstance().register(userName, password, tel, code, listener);
	}

	void quickRegister(String userName, boolean isAgree){
		if (TextUtils.isEmpty(userName)) {
			registerCtrl.showToast(Define.EMPTY_USERNAME);
			return;
		}
		if (!isAgree) {
			registerCtrl.showToast(Define.DIS_AGREE);
			return;
		}
		String tel = "";
		NetApis.getInstance().quickRegister(userName,tel,listener);
	}

	void getCode(String tel){
		if (TextUtils.isEmpty(tel)) {
			registerCtrl.showToast(Define.EMPTY_TEL);
			return;
		}
		NetApis.getInstance().getCode(tel, new HttpListener() {
			@Override
			public void onSuccess(String result) {

			}

			@Override
			public void onFail() {

			}

			@Override
			public void onError(int errorCode, String errorMessage) {

			}
		});
	}

	private HttpListener listener = new HttpListener() {
		@Override
		public void onSuccess(String result) {
			registerCtrl.toRegisterSuccessActivity("","");
		}

		@Override
		public void onFail() {

		}

		@Override
		public void onError(int errorCode, String errorMessage) {

		}
	};
}
