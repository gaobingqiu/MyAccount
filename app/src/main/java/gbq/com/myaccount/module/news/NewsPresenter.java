package gbq.com.myaccount.module.news;

/**
 * 新闻的支持者
 * Created by gbq on 2016-11-23.
 */

class NewsPresenter {
	private INewsCtrl mCtrl;

	NewsPresenter(INewsCtrl ctrl){
		mCtrl = ctrl;
	}

	public void loadNews(String newsUrl) {
	}
}
