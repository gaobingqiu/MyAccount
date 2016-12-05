package gbq.com.myaccount.module.news;

import android.util.Log;

import java.util.List;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.module.news.entity.News;
import gbq.com.myaccount.net.BaseResponse;
import gbq.com.myaccount.net.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 新闻的支持者
 * Created by gbq on 2016-11-23.
 */

class NewsPresenter {
	private INewsCtrl mCtrl;

	NewsPresenter(INewsCtrl ctrl) {
		mCtrl = ctrl;
	}

	void loadNews(String Type, int pageIndex) {
		RetrofitClient.getInstance().getNews(Type, pageIndex, new Callback<BaseResponse<List<News>>>() {
			@Override
			public void onResponse(Call<BaseResponse<List<News>>> call, Response<BaseResponse<List<News>>> response) {
				if (response.isSuccess()) {
					BaseResponse<List<News>> baseResponse = response.body();
					Log.d("NewsPresenter", JsonUtil.createJsonString(baseResponse));
					if (baseResponse.isOk()) {
						mCtrl.setNewsList(baseResponse.getData());
					} else {
						mCtrl.showToast(baseResponse.getMsg());
					}
				} else {
					mCtrl.showToast(Define.ERROR_NET);
				}
			}

			@Override
			public void onFailure(Call<BaseResponse<List<News>>> call, Throwable t) {
				mCtrl.showToast(Define.ERROR_NET);
			}
		});
	}
}
