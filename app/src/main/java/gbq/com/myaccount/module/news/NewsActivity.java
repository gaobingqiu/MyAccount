package gbq.com.myaccount.module.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import gbq.com.myaccount.Define;
import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.base.util.BaseUtil;
import gbq.com.myaccount.model.News;
import gbq.com.myaccount.module.news.web.NewsDetailActivity;

/**
 * 新闻资讯
 * Created by gbq on 2016-11-23.
 */
public class NewsActivity extends BaseActivity implements INewsCtrl, View.OnClickListener, NewsAdapter.OnRecyclerItemClickListener {
	private NewsPresenter mPresenter;
	private NewsAdapter mAdapter;

	@BindView(R.id.iv_tab_line)
	private ImageView mTabLineIv;
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
		mAdapter.setOnRecyclerItemClickListener(this);
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
		String type;
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
	public void setMarginAndColor(int marginLeft) {
		TextView globalTv = (TextView) findViewById(R.id.tv_news_global);
		TextView sportsTv = (TextView) findViewById(R.id.tv_news_sports);
		TextView scienceTv = (TextView) findViewById(R.id.tv_news_science);
		switch (marginLeft) {
			case 0:
				globalTv.setTextColor(Color.parseColor("#0000FF"));
				sportsTv.setTextColor(Color.parseColor("#000000"));
				scienceTv.setTextColor(Color.parseColor("#000000"));
				break;
			case 120:
				globalTv.setTextColor(Color.parseColor("#000000"));
				sportsTv.setTextColor(Color.parseColor("#0000FF"));
				scienceTv.setTextColor(Color.parseColor("#000000"));
				break;
			case 240:
				globalTv.setTextColor(Color.parseColor("#000000"));
				sportsTv.setTextColor(Color.parseColor("#000000"));
				scienceTv.setTextColor(Color.parseColor("#0000FF"));
				break;
			default:
				break;
		}
		mTabLineIv = (ImageView) findViewById(R.id.iv_tab_line);
		ViewGroup.LayoutParams layoutParams = BaseUtil.setViewMargin(mTabLineIv, true, marginLeft, 0, 0, 0);
		mTabLineIv.setLayoutParams(layoutParams);
	}

	@Override
	public void OnRecyclerItemClick(String url) {
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, NewsDetailActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}
}
