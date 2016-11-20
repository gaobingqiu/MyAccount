package gbq.com.myaccount.module.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import gbq.com.myaccount.MainActivity;
import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.module.news.NewsActivity;
import gbq.com.myaccount.module.photo.PhotoActivity;

import static android.R.attr.button;

public class PersonalActivity extends BaseActivity implements View.OnClickListener, IPersonalCtrl {
	private final static String tag = "PersonalActivity->";
	private String userName = "";

	private TextView mPersonaNameTv;
	private ImageView mPersonImageIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		findIds();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		initDate(bundle);
		initRecycleView();
	}

	private void initDate(Bundle bundle) {
		if (null != bundle) {
			userName = bundle.getString("userName");
			mPersonaNameTv.setText(userName);
			String image = bundle.getString("imagePath");
			showImage(image);
		}
	}

	private void showImage(String image){

	}

	private void findIds() {
		ImageView imageView = (ImageView) findViewById(R.id.iv_user_img);
		imageView.setOnClickListener(this);

		TextView textView = (TextView) findViewById(R.id.tv_to_news);
		textView.setOnClickListener(this);

		mPersonaNameTv = (TextView) findViewById(R.id.tv_username);
		mPersonImageIv = (ImageView)findViewById(R.id.iv_user_img);

		Button button = (Button) findViewById(R.id.bt_user_out);
		button.setOnClickListener(this);

	}

	private void initRecycleView() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
		int[] images = {R.mipmap.mus_cai_icon03, R.mipmap.mus_cai_icon04, R.mipmap.mus_cai_icon05,
				R.mipmap.mus_cai_icon06, R.mipmap.mus_cai_icon07};
		String[] titles = {"实名认证", "手机绑定", "邮箱绑定", "登录密码管理", "常见问题"};
		LinkAdapter adapter = new LinkAdapter(this, images, titles);
		recyclerView.setAdapter(adapter);
		//最后一个参数是反转布局一定是false,为true的时候为逆向显示，在聊天记录中可能会有使用
		//这个东西在显示后才会加载，不会像ScollView一样一次性加载导致内存溢出
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
	}

	@Override
	public void userLogout() {
		Log.d(tag, "userLogout");
		Intent intent = new Intent();
		intent.setClass(PersonalActivity.this, MainActivity.class);
		startActivity(intent);
	}

	@Override
	public void toNewsActivity() {
		Intent intent = new Intent();
		intent.setClass(PersonalActivity.this, NewsActivity.class);
		startActivity(intent);
	}

	@Override
	public void changeImg() {
		Intent intent = new Intent();
		intent.setClass(PersonalActivity.this, PhotoActivity.class);
		startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.iv_user_img:
				changeImg();
				break;
			case R.id.tv_to_news:
				toNewsActivity();
				break;
			case R.id.bt_user_out:
				userLogout();
				break;
			default:
				break;
		}
	}
}
