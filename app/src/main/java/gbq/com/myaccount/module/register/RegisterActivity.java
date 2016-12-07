package gbq.com.myaccount.module.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import gbq.com.myaccount.R;
import gbq.com.myaccount.base.BaseActivity;
import gbq.com.myaccount.module.register.success.RegisterSuccessActivity;

import static gbq.com.myaccount.R.id.code;
import static gbq.com.myaccount.R.id.loginName;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterCtrl {
	private final static String tag = "RegisterActivity";
	private EditText loginNameView, telView, codeView, passwordView;
	private CheckBox check;

	private RegisterPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 清除标题栏
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		findIds();
		presenter = new RegisterPresenter(this);
	}

	public void findIds() {
		Button registerBt = (Button) findViewById(R.id.bt_register);
		registerBt.setOnClickListener(this);

		Button quickRegisterButton = (Button) findViewById(R.id.bt_quick_register);
		quickRegisterButton.setOnClickListener(this);

		Button fetchCodeBt = (Button) findViewById(R.id.bt_fetch_code);
		fetchCodeBt.setOnClickListener(this);

		loginNameView = (EditText) findViewById(loginName);
		telView = (EditText) findViewById(R.id.tel);
		codeView = (EditText) findViewById(code);
		passwordView = (EditText) findViewById(R.id.password);
		check = (CheckBox) findViewById(R.id.check);
	}

	@Override
	public void toRegisterSuccessActivity(String userName, String password) {
		Intent intent = new Intent();
		intent.setClass(RegisterActivity.this, RegisterSuccessActivity.class);
		intent.putExtra("userName",userName);
		intent.putExtra("password",password);
		startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.bt_register:
				String userName = loginNameView.getText().toString();
				String password = passwordView.getText().toString();
				String code = codeView.getText().toString();
				String tel = telView.getText().toString();
				presenter.register(userName, password, tel,code,check.isChecked());
				break;
			case R.id.bt_quick_register:
				userName = loginNameView.getText().toString();
				presenter.quickRegister(userName,check.isChecked());
				break;
			case R.id.bt_fetch_code:
				tel = telView.getText().toString();
				presenter.getCode(tel);
				break;
			default:
				break;
		}
	}
}
