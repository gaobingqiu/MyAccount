package gbq.com.myaccount.module.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.module.news.entity.News;

/**
 * 新闻资讯
 * Created by gbq on 2016-11-23.
 */
public class NewsActivity extends BaseActivity implements INewsCtrl, View.OnClickListener, NewsAdapter.OnRecyclerItemClickListener {
	private NewsPresenter mPresenter;
	private NewsAdapter mAdapter;

	private static int count_global = 1;
	private static int count_science = 1;
	private static int count_sport = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		findIds();
		mPresenter = new NewsPresenter(this);
		setRecyclerView();
	}

	private void findIds() {
		findViewById(R.id.tv_news_global).setOnClickListener(this);
		findViewById(R.id.tv_news_sports).setOnClickListener(this);
		findViewById(R.id.tv_news_science).setOnClickListener(this);
	}

	private void setRecyclerView() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_news);
		mAdapter = new NewsAdapter(this, null);
		recyclerView.setAdapter(mAdapter);
		//最后一个参数是反转布局一定是false,为true的时候为逆向显示，在聊天记录中可能会有使用
		//这个东西在显示后才会加载，不会像ScollView一样一次性加载导致内存溢出
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
		mPresenter.loadNews(Define.NEWS_GLOBAL, 1);
	}

	@Override
	public void setNewsList(List<News> newsList) {
		mAdapter.setList(newsList);
	}

	@Override
	public void onClick(View view) {
		String type = "";
		switch (view.getId()) {
			case R.id.tv_news_global:

				type = Define.NEWS_GLOBAL;
				mPresenter.loadNews(type, ++count_global);
				break;
			case R.id.tv_news_sports:
				type = Define.NEWS_SPORT;
				mPresenter.loadNews(type, ++count_sport);
				break;
			case R.id.tv_news_science:
				type = Define.NEWS_SCIENCE;
				mPresenter.loadNews(type, ++count_science);
				break;
			default:
				break;
		}

	}

	@Override
	public void OnRecyclerItemClick(String url) {
		mAdapter.setList(null);
	}
}
