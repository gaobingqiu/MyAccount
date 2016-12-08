package gbq.com.myaccount.module.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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

import static gbq.com.myaccount.Define.NEWS_GLOBAL;
import static gbq.com.myaccount.Define.NEWS_SCIENCE;
import static gbq.com.myaccount.Define.NEWS_SPORT;

/**
 * 新闻资讯
 * Created by gbq on 2016-11-23.
 */
public class NewsActivity extends BaseActivity implements INewsCtrl, View.OnClickListener,
		NewsAdapter.OnRecyclerItemClickListener {
	private static final String TAG = "NewsActivity";
	private NewsPresenter mPresenter;
	private NewsAdapter mAdapter;

	@BindView(R.id.iv_tab_line)
	private ImageView mTabLineIv;
	private static int count_global = 1;
	private static int count_science = 1;
	private static int count_sport = 1;
	private String mType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		findIds();
		mPresenter = new NewsPresenter(this);
		setRecyclerView();
		//创建手势检测器
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
		recyclerView.setLongClickable(false);
		recyclerView.setAdapter(mAdapter);
		//最后一个参数是反转布局一定是false,为true的时候为逆向显示，在聊天记录中可能会有使用
		//这个东西在显示后才会加载，不会像ScollView一样一次性加载导致内存溢出
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
		mPresenter.loadNews(NEWS_GLOBAL, 1);
	}

	@Override
	public void setNewsList(List<News> newsList) {
		mAdapter.setList(newsList);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_news_global:
				mType = NEWS_GLOBAL;
				mPresenter.loadNews(mType, ++count_global);
				break;
			case R.id.tv_news_sports:
				mType = Define.NEWS_SPORT;
				mPresenter.loadNews(mType, ++count_sport);
				break;
			case R.id.tv_news_science:
				mType = Define.NEWS_SCIENCE;
				mPresenter.loadNews(mType, ++count_science);
				break;
			default:
				break;
		}

	}

	@Override
	public void setMarginAndColor(int marginLeft) {
		mTabLineIv = (ImageView) findViewById(R.id.iv_tab_line);
		ViewGroup.LayoutParams layoutParams = BaseUtil.setViewMargin(mTabLineIv, true, marginLeft, 0, 0, 0);
		mTabLineIv.setLayoutParams(layoutParams);
	}

	@Override
	public void setTabColor(String globalColor, String sportsColor, String scienceColor) {
		TextView globalTv = (TextView) findViewById(R.id.tv_news_global);
		TextView sportsTv = (TextView) findViewById(R.id.tv_news_sports);
		TextView scienceTv = (TextView) findViewById(R.id.tv_news_science);
		globalTv.setTextColor(Color.parseColor(globalColor));
		sportsTv.setTextColor(Color.parseColor(sportsColor));
		scienceTv.setTextColor(Color.parseColor(scienceColor));
	}

	@Override
	public void OnRecyclerItemClick(String url) {
		Intent intent = new Intent();
		intent.setClass(NewsActivity.this, NewsDetailActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

	RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
			Log.i(TAG, "-----------onScrollStateChanged-----------");
			Log.i(TAG, "newState: " + newState);
		}

		@Override
		public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			Log.i(TAG, "-----------onScrolled-----------");
			Log.i(TAG, "dx: " + dx);
			Log.i(TAG, "dy: " + dy);
			if(dy>50||dy<-50){
				if (TextUtils.equals(mType, NEWS_GLOBAL)) {
					mPresenter.loadNews(mType, ++count_global);
				}
				else if (TextUtils.equals(mType, NEWS_SPORT)) {
					mPresenter.loadNews(mType, ++count_sport);
				}
				else if (TextUtils.equals(mType, NEWS_SCIENCE)) {
					mPresenter.loadNews(mType, ++count_science);
				}
			}
			if (dx > 0) {
				if (TextUtils.equals(mType, NEWS_GLOBAL)) {
					mType = NEWS_SPORT;
					mPresenter.loadNews(mType, ++count_sport);
				}
				if (TextUtils.equals(mType, NEWS_SPORT)) {
					mType = NEWS_SCIENCE;
					mPresenter.loadNews(mType, ++count_science);
				}
			}
			if (dx < 0) {
				if (TextUtils.equals(mType, NEWS_SCIENCE)) {
					mType = NEWS_SPORT;
					mPresenter.loadNews(mType, ++count_sport);
				}
				if (TextUtils.equals(mType, NEWS_SPORT)) {
					mType = NEWS_GLOBAL;
					mPresenter.loadNews(mType, ++count_global);
				}
			}
		}
	};
}
