package gbq.com.myaccount.module.register;

import android.text.TextUtils;
import android.util.Log;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.model.RegisterResponseVo;
import gbq.com.myaccount.model.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册的支持者
 * Created by gbq on 2016-11-12.
 */

class RegisterPresenter {
	private IRegisterCtrl mCtrl;

	RegisterPresenter(IRegisterCtrl registerCtrl) {
		this.mCtrl = registerCtrl;
	}

	void register(String userName, String password, String tel, String code, boolean isAgree) {
		mCtrl.showProcess();
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
		RetrofitClient.getInstance().register(userName, password, tel, code, callback);
	}

	void quickRegister(String userName, boolean isAgree) {
		mCtrl.showProcess();
		if (TextUtils.isEmpty(userName)) {
			mCtrl.showToast(Define.EMPTY_USERNAME);
			return;
		}
		if (!isAgree) {
			mCtrl.showToast(Define.DIS_AGREE);
			return;
		}
		RetrofitClient.getInstance().quickRegister(userName, callback);
	}

	void getCode(String tel) {
		mCtrl.showProcess();
		if (TextUtils.isEmpty(tel)) {
			mCtrl.showToast(Define.EMPTY_TEL);
			return;
		}
		RetrofitClient.getInstance().getCode(tel, new Callback<BaseResponse<String>>() {
			@Override
			public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
				Log.d("RegisterPresenter", JsonUtil.createJsonString(response));
				if (response.isSuccess()) {
					BaseResponse<String> baseResponse = response.body();
					if (baseResponse.isOk()) {
						mCtrl.showToast(baseResponse.getData());
					} else {
						mCtrl.showToast(baseResponse.getMsg());
					}
				} else {
					mCtrl.showToast(Define.ERROR_NET);
				}
				mCtrl.closeProcess();
			}

			@Override
			public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
				Log.d("RegisterPresenter", JsonUtil.createJsonString(t));
				mCtrl.showToast(Define.ERROR_NET);
				mCtrl.closeProcess();
			}
		});
	}

	private Callback<BaseResponse<RegisterResponseVo>> callback = new Callback<BaseResponse<RegisterResponseVo>>() {
		@Override
		public void onResponse(Call<BaseResponse<RegisterResponseVo>> call, Response<BaseResponse<RegisterResponseVo>> response) {
			Log.d("RegisterPresenter", JsonUtil.createJsonString(response));
			if (response.isSuccess()) {
				BaseResponse<RegisterResponseVo> baseResponse = response.body();
				if (baseResponse.isOk()) {
					RegisterResponseVo responseVo = baseResponse.getData();
					mCtrl.toRegisterSuccessActivity(responseVo.getUserName(), responseVo.getPassword());
				} else {
					mCtrl.showToast(baseResponse.getMsg());
				}
			} else {
				mCtrl.showToast(Define.ERROR_NET);
			}
			mCtrl.closeProcess();
		}

		@Override
		public void onFailure(Call<BaseResponse<RegisterResponseVo>> call, Throwable t) {
			Log.d("RegisterPresenter", JsonUtil.createJsonString(t));
			mCtrl.showToast(Define.ERROR_NET);
			mCtrl.closeProcess();
		}
	};

//	private HttpListener listener = new HttpListener() {
//		@Override
//		public void onSuccess(String result) {
//			try {
//				RegisterResponseVo responseVo = JsonUtil.createJsonBean(result, RegisterResponseVo.class);
//				mCtrl.toRegisterSuccessActivity(responseVo.getUserName(), responseVo.getPassword());
//			} catch (Exception e) {
//				mCtrl.toRegisterSuccessActivity(result, "");
//			}
//		}
//
//		@Override
//		public void onFail() {
//
//		}
//
//		@Override
//		public void onError(int errorCode, String errorMessage) {
//			mCtrl.showToast(errorMessage);
//		}
//	};
}
