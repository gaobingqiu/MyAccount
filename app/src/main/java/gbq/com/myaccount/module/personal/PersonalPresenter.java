package gbq.com.myaccount.module.personal;

import android.util.Log;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.model.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人中心的支持者
 * Created by gbq on 2016-11-12.
 */

class PersonalPresenter {
	private IPersonalCtrl mCtrl;

	PersonalPresenter(IPersonalCtrl personalCtrl) {
		this.mCtrl = personalCtrl;
	}

	void userLogout(String userName) {
		mCtrl.showProcess();
		RetrofitClient.getInstance().logout(userName, new Callback<BaseResponse<String>>() {
			@Override
			public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
				Log.d("PersonalPresenter", JsonUtil.createJsonString(response));
				if (response.isSuccess()) {
					BaseResponse<String> baseResponse = response.body();
					if (baseResponse.isOk()) {
						mCtrl.clearLoginInfo();
						mCtrl.userLogout();
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
				Log.d("PersonalPresenter", JsonUtil.createJsonString(t));
				mCtrl.showToast(Define.ERROR_NET);
				mCtrl.closeProcess();
			}
		});
	}
}
