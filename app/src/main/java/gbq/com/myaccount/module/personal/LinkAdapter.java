package gbq.com.myaccount.module.personal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gbq.com.myaccount.R;

/**
 * 个人中心连接的迭代器
 * Created by gbq on 2016-11-15.
 */

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    private Context mContext;
    private int[] mImages;
    private String[] mTitles;

    public LinkAdapter(Context context, int[] images, String[] titles) {
        this.mContext = context;
        this.mImages = images;
        this.mTitles = titles;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageParamsIv;
        private final TextView TextParamTv;

        ViewHolder(View itemView) {
            super(itemView);
            imageParamsIv = (ImageView) itemView.findViewById(R.id.iv_params_recycle);
            TextParamTv = (TextView) itemView.findViewById(R.id.tv_params_recycle);
        }
    }

    @Override
    public LinkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LinkAdapter.ViewHolder holder, int position) {
        holder.imageParamsIv.setBackgroundResource(mImages[position]);
        holder.TextParamTv.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }
}
