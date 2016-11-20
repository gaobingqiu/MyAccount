package gbq.com.myaccount.module.register;

import android.text.TextUtils;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.entity.RegisterRequestVo;
import gbq.com.myaccount.base.entity.RegisterResponseVo;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * 注册的支持者
 * Created by gbq on 2016-11-12.
 */

public class RegisterPresenter {
	private IRegisterCtrl mCtrl;

	RegisterPresenter(IRegisterCtrl registerCtrl) {
		this.mCtrl = registerCtrl;
	}

	void register(String userName, String password, String tel, String code, boolean isAgree) {
		if (TextUtils.isEmpty(userName)) {
			mCtrl.showToast(Define.EMPTY_USERNAME);
			return;
		}
		if (TextUtils.isEmpty(password)) {
			mCtrl.showToast(Define.EMPTY_PASSWORD);
			return;
		}
		if (TextUtils.isEmpty(tel)) {
			mCtrl.showToast(Define.EMPTY_TEL);
			return;
		}
		if (TextUtils.isEmpty(code)) {
			mCtrl.showToast(Define.EMPTY_CODE);
			return;
		}
		if (!isAgree) {
			mCtrl.showToast(Define.DIS_AGREE);
			return;
		}
		NetApis.getInstance().register(userName, password, tel, code, listener);
	}

	void quickRegister(String userName, boolean isAgree) {
		if (TextUtils.isEmpty(userName)) {
			mCtrl.showToast(Define.EMPTY_USERNAME);
			return;
		}
		if (!isAgree) {
			mCtrl.showToast(Define.DIS_AGREE);
			return;
		}
		NetApis.getInstance().quickRegister(userName, listener);
	}

	void getCode(String tel) {
		if (TextUtils.isEmpty(tel)) {
			mCtrl.showToast(Define.EMPTY_TEL);
			return;
		}
		NetApis.getInstance().getCode(tel, new HttpListener() {
			@Override
			public void onSuccess(String result) {
				mCtrl.showToast(result);
			}

			@Override
			public void onFail() {

			}

			@Override
			public void onError(int errorCode, String errorMessage) {
				mCtrl.showToast(errorMessage);
			}
		});
	}

	private HttpListener listener = new HttpListener() {
		@Override
		public void onSuccess(String result) {
			try {
				RegisterResponseVo responseVo = JsonUtil.createJsonBean(result, RegisterResponseVo.class);
				mCtrl.toRegisterSuccessActivity(responseVo.getUserName(), responseVo.getPassword());
			} catch (Exception e) {
				mCtrl.toRegisterSuccessActivity(result, "");
			}
		}

		@Override
		public void onFail() {

		}

		@Override
		public void onError(int errorCode, String errorMessage) {
			mCtrl.showToast(errorMessage);
		}
	};
}
