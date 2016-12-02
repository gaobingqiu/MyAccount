package gbq.com.myaccount.module.register.success;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.module.main.User;
import gbq.com.myaccount.net.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gbq on 2016-11-12.
 */

public class RegisterSuccessPresenter {
	private IRegisterSuccessCtrl mCtrl;

	RegisterSuccessPresenter(IRegisterSuccessCtrl mCtrl) {
		this.mCtrl = mCtrl;
	}

	void getUser(final String userName) {
		RetrofitClient.getInstance().getUser(userName, new Callback<BaseResponse<User>>() {
			@Override
			public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
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
				mCtrl.showToast(Define.ERROR_NET);
			}
		});
	}
}
