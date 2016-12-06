package gbq.com.myaccount.module.news.web;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;

/**
 * Created by gbq on 2016-12-5.
 */

public class NewsDetailActivity extends BaseActivity implements INewsDetailCtrl {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		WebView mWebView = (WebView) findViewById(R.id.wv_new_detail);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String mUrl = bundle.getString("url");
		NewsDetailPresenter mPresenter = new NewsDetailPresenter(this);
		mPresenter.initWebView(mWebView, mUrl);
	}
}
