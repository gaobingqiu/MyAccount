package gbq.com.myaccount.module.news;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gbq.com.myaccount.R;
import gbq.com.myaccount.model.News;

/**
 * 新闻列表的适配器
 * Created by gbq on 2016-11-23.
 */

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
	private Context mContext;

	private List<News> mList;
	private OnRecyclerItemClickListener listener;

	NewsAdapter(Context context, List<News> newList) {
		this.mContext = context;
		this.mList = newList;
	}

	/**
	 * 此方法可以更新列表的数据
	 */
	void setList(List<News> mList) {
		this.mList = mList;
		this.notifyItemRangeChanged(0, mList.size());
	}

	@Override
	public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_news, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final NewsAdapter.ViewHolder holder, int position) {
		final News news = mList.get(position);
		holder.titleTv.setText(news.getTitle());
		holder.titleTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				listener.OnRecyclerItemClick(news.getUrl());
			}
		});
		holder.timeTv.setText(news.getCtime());
		holder.timeTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				listener.OnRecyclerItemClick(news.getUrl());
			}
		});
		holder.urlTv.setText(news.getUrl());
		holder.descriptionTv.setText(news.getDescription());
		holder.descriptionTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				listener.OnRecyclerItemClick(news.getUrl());
			}
		});

		Glide.with(mContext).load(mList.get(position).getPicUrl()).error(R.mipmap.default_img).into(holder.imageView);
		holder.imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				listener.OnRecyclerItemClick(news.getUrl());
			}
		});
	}

	@Override
	public int getItemCount() {
		return null == mList ? 0 : mList.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		private final TextView titleTv;
		private final TextView timeTv;
		private final TextView urlTv;
		private final TextView descriptionTv;
		private final AppCompatImageView imageView;

		ViewHolder(View itemView) {
			super(itemView);
			titleTv = (TextView) itemView.findViewById(R.id.tv_news_title);
			timeTv = (TextView) itemView.findViewById(R.id.tv_news_time);
			urlTv = (TextView) itemView.findViewById(R.id.tv_news_url);
			urlTv.setVisibility(View.GONE);
			descriptionTv = (TextView) itemView.findViewById(R.id.tv_news_description);
			imageView = (AppCompatImageView) itemView.findViewById(R.id.iv_news_image);
		}
	}

	/**
	 * 自定义RecyclerView的点击事件
	 */
	interface OnRecyclerItemClickListener {
		void OnRecyclerItemClick(String url);
	}

	void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
		this.listener = listener;
	}
}
