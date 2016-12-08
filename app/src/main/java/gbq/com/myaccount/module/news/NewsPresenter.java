package gbq.com.myaccount.module.news;

import android.util.Log;

import java.util.List;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.model.News;
import gbq.com.myaccount.model.BaseResponse;
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

	void loadNews(String type, int pageIndex) {
		mCtrl.showProcess();
		switch (type) {
			case Define.NEWS_GLOBAL:
				mCtrl.setMarginAndColor(0);
				mCtrl.setTabColor("#0000FF", "#000000", "#000000");
				break;
			case Define.NEWS_SPORT:
				mCtrl.setMarginAndColor(120);
				mCtrl.setTabColor("#000000", "#0000FF", "#000000");
				break;
			case Define.NEWS_SCIENCE:
				mCtrl.setMarginAndColor(240);
				mCtrl.setTabColor("#000000", "#000000", "#0000FF");
				break;
			default:
				break;
		}
		RetrofitClient.getInstance().getNews(type, pageIndex, new Callback<BaseResponse<List<News>>>() {
			@Override
			public void onResponse(Call<BaseResponse<List<News>>> call, Response<BaseResponse<List<News>>> response) {
				Log.d("NewsPresenter", JsonUtil.createJsonString(response));
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
				mCtrl.closeProcess();
			}

			@Override
			public void onFailure(Call<BaseResponse<List<News>>> call, Throwable t) {
				Log.d("NewsPresenter", JsonUtil.createJsonString(t));
				mCtrl.showToast(Define.ERROR_NET);
				mCtrl.closeProcess();
			}
		});
	}

}
