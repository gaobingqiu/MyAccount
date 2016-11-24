package gbq.com.myaccount.module.news;

import android.text.TextUtils;

import java.util.List;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.module.news.entity.News;
import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * 新闻的支持者
 * Created by gbq on 2016-11-23.
 */

class NewsPresenter {
	private INewsCtrl mCtrl;

	NewsPresenter(INewsCtrl ctrl) {
		mCtrl = ctrl;
	}

	void loadNews() {
		NetApis.getInstance().getNews(new HttpListener() {
			@Override
			public void onSuccess(String result) {
				List<News> newsList = JsonUtil.createJsonToListBean(result,News.class);
				mCtrl.setNewsList(newsList);
			}

			@Override
			public void onFail() {
				mCtrl.showToast(Define.ERROR_NET);
			}

			@Override
			public void onError(int errorCode, String errorMessage) {
				mCtrl.showToast(errorMessage);
			}
		});
	}
}
