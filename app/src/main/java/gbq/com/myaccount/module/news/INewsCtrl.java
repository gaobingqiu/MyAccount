package gbq.com.myaccount.module.news;

import java.util.List;

import gbq.com.myaccount.base.IBaseCtrl;
import gbq.com.myaccount.module.news.entity.News;

/**
 * 新闻活动的控制方法
 * Created by gbq on 2016-11-22.
 */
interface INewsCtrl extends IBaseCtrl{

	void setNewsList(List<News> newsList);
	void setMarginAndColor(int marginLeft);
}
