package gbq.com.myaccount.module.register.success;

import android.util.Log;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.model.User;
import gbq.com.myaccount.model.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 注册成功页面的支持者
 * Created by gbq on 2016-11-12.
 */

class RegisterSuccessPresenter {
	private IRegisterSuccessCtrl mCtrl;

	RegisterSuccessPresenter(IRegisterSuccessCtrl mCtrl) {
		this.mCtrl = mCtrl;
	}

	void getUser(final String userName) {
		RetrofitClient.getInstance().getUser(userName, new Callback<BaseResponse<User>>() {
			@Override
			public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
				Log.d("SuccessPresenter", JsonUtil.createJsonString(response));
				if (response.isSuccess()) {
					BaseResponse<User> baseResponse = response.body();
					if (baseResponse.isOk()) {
						User user = baseResponse.getData();
						mCtrl.toPersonal(user.getUserName(), user.getImage());
					} else {
						mCtrl.showToast(baseResponse.getMsg());
					}
				} else {
					mCtrl.showToast(Define.ERROR_NET);
				}
			}

			@Override
			public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
				Log.d("SuccessPresenter", JsonUtil.createJsonString(t));
				mCtrl.showToast(Define.ERROR_NET);
			}
		});
	}
}
