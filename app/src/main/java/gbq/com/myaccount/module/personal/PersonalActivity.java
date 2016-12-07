package gbq.com.myaccount.module.personal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.MainActivity;
import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.module.news.NewsActivity;

public class PersonalActivity extends BaseActivity implements View.OnClickListener, IPersonalCtrl {
	private final static String tag = "PersonalActivity->";
	private String userName = "";

	private TextView mPersonaNameTv;
	private ImageView mPersonImageIv;

	private PersonalPresenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);
		findIds();
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		initDate(bundle);
		initRecycleView();
		mPresenter = new PersonalPresenter(this);
	}

	private void findIds() {
		TextView textView = (TextView) findViewById(R.id.tv_to_news);
		textView.setOnClickListener(this);

		mPersonaNameTv = (TextView) findViewById(R.id.tv_username);
		mPersonImageIv = (ImageView) findViewById(R.id.iv_user_img);
		mPersonaNameTv.setOnClickListener(this);

		Button button = (Button) findViewById(R.id.bt_user_out);
		button.setOnClickListener(this);
	}

	private void initDate(Bundle bundle) {
		if (null != bundle) {
			userName = bundle.getString("userName");
			mPersonaNameTv.setText(userName);
			String imagePath = bundle.getString("imagePath");
			loadHeadImage(imagePath);
		}
	}

	/**
	 * 加载网络图片
	 *
	 * @param imagePath 网络图片地址
	 */
	private void loadHeadImage(String imagePath) {
		Glide.with(this).load(imagePath).into(mPersonImageIv);
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
	public void clearLoginInfo() {
		// 获取SharedPreferences对象
		SharedPreferences sharedPre = this.getSharedPreferences("config", Context.MODE_PRIVATE);
		// 获取Editor对象
		SharedPreferences.Editor editor = sharedPre.edit();
		// 设置参数
		editor.putString("username", null);
		// 提交
		editor.apply();
	}

	@Override
	public void toNewsActivity() {
		Intent intent = new Intent();
		intent.setClass(PersonalActivity.this, NewsActivity.class);
		intent.putExtra("userName", userName);
		startActivity(intent);
	}

	@Override
	public void changeImg() {
		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.show();
		Window win = alertDialog.getWindow();
		assert win != null;
		WindowManager.LayoutParams lp = win.getAttributes();
		win.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		lp.alpha = 0.7f;
		win.setAttributes(lp);
		win.setContentView(R.layout.dialog);
		Button cancelBtn = (Button) win.findViewById(R.id.camera_cancel);
		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
		Button camera_phone = (Button) win.findViewById(R.id.camera_phone);
		camera_phone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				systemPhoto();
			}

		});
		Button camera_camera = (Button) win.findViewById(R.id.camera_camera);
		camera_camera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cameraPhoto();
			}

		});
	}

	private final static int SYS_INTENT_REQUEST = 0XFF01;
	private final static int CAMERA_INTENT_REQUEST = 0XFF02;

	/**
	 * 打开系统相册
	 */
	private void systemPhoto() {

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SYS_INTENT_REQUEST);

	}

	/**
	 * 调用相机拍照
	 */
	private void cameraPhoto() {
		String sdStatus = Environment.getExternalStorageState();
		/* 检测sd是否可用 */
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			showToast(Define.DISABLE_SD);
			return;
		}
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(intent, CAMERA_INTENT_REQUEST);
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
				mPresenter.userLogout(userName);
				break;
			default:
				break;
		}
	}
}
