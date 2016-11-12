package gbq.com.myaccount.module.news;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import gbq.com.myaccount.R;

public class NewsActivity extends FragmentActivity {
	
	private ViewPager mPageVp;


	/**
	 * Tab显示内容TextView
	 */
	private TextView mTabChatTv, mTabContactsTv, mTabFriendTv;
	/**
	 * Tab的那个引导线
	 */
	private ImageView mTabLineIv;
	/**
	 * ViewPager的当前选中页
	 */
	private int currentIndex;
	/**
	 * 屏幕的宽度
	 */
	private int screenWidth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_news);
		findById();
	}

	private void findById() {
		mTabContactsTv = (TextView) this.findViewById(R.id.id_contacts_tv);
		mTabChatTv = (TextView) this.findViewById(R.id.id_chat_tv);
		mTabFriendTv = (TextView) this.findViewById(R.id.id_friend_tv);

		mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

		mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
	}
}
