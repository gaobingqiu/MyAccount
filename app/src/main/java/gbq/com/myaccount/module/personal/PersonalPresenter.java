package gbq.com.myaccount.module.personal;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.net.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gbq on 2016-11-12.
 */

public class PersonalPresenter {
	private IPersonalCtrl mCtrl;

	PersonalPresenter(IPersonalCtrl personalCtrl) {
		this.mCtrl = personalCtrl;
	}

	void userLogout(String userName) {
		RetrofitClient.getInstance().logout(userName, new Callback<BaseResponse<String>>() {
			@Override
			public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
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
			}

			@Override
			public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
				mCtrl.showToast(Define.ERROR_NET);
			}
		});
	}
}
