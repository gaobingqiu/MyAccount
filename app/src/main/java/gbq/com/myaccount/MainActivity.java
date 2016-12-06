package gbq.com.myaccount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.module.main.IMainCtrl;
import gbq.com.myaccount.module.main.MainPresenter;
import gbq.com.myaccount.module.personal.PersonalActivity;
import gbq.com.myaccount.module.register.RegisterActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, IMainCtrl {
	private EditText userNameView;
	private EditText passwordView;
	private CheckBox mSaveUserCb;

	private String mUsername;
	private String mPassword;
	private MainPresenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findIds();
		getUserInfo();
		mPresenter = new MainPresenter(this);
	}

	public void findIds() {
		Button userLoginBt = (Button) findViewById(R.id.bt_user_login);
		userLoginBt.setOnClickListener(this);
		TextView toRegisterTv = (TextView) findViewById(R.id.tv_to_register);
		toRegisterTv.setOnClickListener(this);
		mSaveUserCb = (CheckBox) findViewById(R.id.check);
		mSaveUserCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!isChecked) {
					clearLoginInfo();
				}
			}
		});
		userNameView = (EditText) findViewById(R.id.userName);
		passwordView = (EditText) findViewById(R.id.Password);
	}

	public void getUserInfo() {
		SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
		mUsername = sharedPre.getString("username", "");
		mPassword = sharedPre.getString("password", "");
		if (!TextUtils.isEmpty(mUsername)) {
			mSaveUserCb.setChecked(true);
			userNameView.setText(mUsername);
			//设置光标的位置
			userNameView.setSelection(mUsername.length());
		}
		if (!TextUtils.isEmpty(mPassword)) {
			passwordView.setText(mPassword);
		}
	}

	@Override
	public void saveLoginInfo(String username, String password) {
		// 获取SharedPreferences对象
		SharedPreferences sharedPre = this.getSharedPreferences("config", Context.MODE_PRIVATE);
		// 获取Editor对象
		SharedPreferences.Editor editor = sharedPre.edit();
		// 设置参数
		editor.putString("username", username);
		editor.putString("password", password);
		// 提交
		editor.apply();
	}

	@Override
	public void clearLoginInfo() {
		// 获取SharedPreferences对象
		SharedPreferences sharedPre = this.getSharedPreferences("config", Context.MODE_PRIVATE);
		// 获取Editor对象
		SharedPreferences.Editor editor = sharedPre.edit();
		// 设置参数
		editor.putString("username", null);
		editor.putString("password", null);
		// 提交
		editor.apply();
	}

	@Override
	public void toRegisterActivity() {
		Intent intent = new Intent();
		intent.setClass(this, RegisterActivity.class);
		startActivity(intent);
	}

	@Override
	public void toPersonalActivity(String userName, String imagePath) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, PersonalActivity.class);
		startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.bt_user_login:
				mUsername = userNameView.getText().toString();
				mPassword = passwordView.getText().toString();
				boolean isSave = mSaveUserCb.isChecked();
				mPresenter.login(mUsername, mPassword, isSave);
				break;
			case R.id.tv_to_register:
				toRegisterActivity();
				break;
			default:
				break;
		}
	}
}
