package gbq.com.myaccount.module.main;

import android.text.TextUtils;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.net.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录的支持者
 * Created by gbq on 2016-11-12.
 */

public class MainPresenter {
	private IMainCtrl mCtrl;

	public MainPresenter(IMainCtrl mainCtrl) {
		this.mCtrl = mainCtrl;
	}

	public void login(final String userName, final String password, final boolean isSave) {
		mCtrl.showProcess();
		if (TextUtils.isEmpty(userName)) {
			mCtrl.showToast("姓名不能为空");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			mCtrl.showToast("密码不能为空");
			return;
		}
		RetrofitClient.getInstance().userLogin(userName, password, new Callback<BaseResponse<User>>() {
			@Override
			public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
				if (response.isSuccess()) {
					BaseResponse<User> baseResponse = response.body();
					if (baseResponse.isOk()) {
						User user = baseResponse.getData();
						mCtrl.toPersonalActivity(userName, user.getImage());
						if (isSave) {
							mCtrl.saveLoginInfo(userName, password);
						}
					} else {
						mCtrl.showToast(baseResponse.getMsg());
					}
				} else {
					mCtrl.showToast(Define.ERROR_NET);
				}
				mCtrl.closeProcess();
			}

			@Override
			public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
				mCtrl.showToast(Define.ERROR_NET);
				mCtrl.closeProcess();
			}
		});
	}
}
