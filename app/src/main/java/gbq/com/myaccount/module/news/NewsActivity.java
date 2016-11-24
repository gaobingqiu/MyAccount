package gbq.com.myaccount.module.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.module.news.entity.News;
import gbq.com.myaccount.net.NetConfig;

import static gbq.com.myaccount.net.NetConfig.NEWS_URL;

/**
 * 新闻资讯
 * Created by gbq on 2016-11-23.
 */
public class NewsActivity extends BaseActivity implements INewsCtrl,View.OnClickListener,NewsAdapter.OnRecyclerItemClickListener {
	private NewsPresenter mPresenter;

	private NewsAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		findIds();
		mPresenter = new NewsPresenter(this);
		setRecyclerView();
	}

	private void findIds() {
		TextView newsGlobalTv = (TextView)findViewById(R.id.tv_news_global);
		newsGlobalTv.setOnClickListener(this);
		TextView newsSportsTv = (TextView)findViewById(R.id.tv_news_sports);
		newsSportsTv.setOnClickListener(this);
		TextView newsScienceTv = (TextView)findViewById(R.id.tv_news_science);
		newsScienceTv.setOnClickListener(this);
	}

	private void setRecyclerView() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_news);
		mAdapter = new NewsAdapter(this, null);
		recyclerView.setAdapter(mAdapter);
		//最后一个参数是反转布局一定是false,为true的时候为逆向显示，在聊天记录中可能会有使用
		//这个东西在显示后才会加载，不会像ScollView一样一次性加载导致内存溢出
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
		mPresenter.loadNews();
	}

	@Override
	public void setNewsList(List<News> newsList){
		mAdapter.setList(newsList);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.tv_news_global:
				NEWS_URL  = NetConfig.NEWS_GLOBAL;
				break;
			case R.id.tv_news_sports:
				NEWS_URL  = NetConfig.NEWS_SPORTS;
				break;
			case R.id.tv_news_science:
				NEWS_URL  = NetConfig.NEWS_SCIENCE;
				break;
			default:
				break;
		}
		mPresenter.loadNews();
	}

	@Override
	public void OnRecyclerItemClick(String url) {
		mAdapter.setList(null);
	}
}
