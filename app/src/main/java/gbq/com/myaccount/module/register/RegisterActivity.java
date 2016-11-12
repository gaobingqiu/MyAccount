package gbq.com.myaccount.module.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import gbq.com.myaccount.R;

public class RegisterActivity extends Activity implements View.OnClickListener,IRegisterCtrl {
    private String tag = "http";
    private String action = "com.example.demo.RegisterSuccessActivity";
    private String action_interface_register = "loginInterface/quickRegister.do";
    private String url_register_request = "loginInterface/activity_register.do";
    private String url_getCode = "index/getCode.do";
    private EditText loginNameView, telView, codeView, passwordView;
    private String loginName, tel, code, password;
    private CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 清除标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findIds();
    }

    public void findIds() {
        Button registerBt = (Button) findViewById(R.id.bt_register);
        registerBt.setOnClickListener(this);

        Button quickRegisterButton = (Button) findViewById(R.id.bt_quick_register);
        quickRegisterButton.setOnClickListener(this);

        Button fetchCodeBt = (Button) findViewById(R.id.bt_fetch_code);
        fetchCodeBt.setOnClickListener(this);

        loginNameView = (EditText) findViewById(R.id.loginName);
        telView = (EditText) findViewById(R.id.tel);
        codeView = (EditText) findViewById(R.id.code);
        passwordView = (EditText) findViewById(R.id.password);
        check = (CheckBox) findViewById(R.id.check);
    }

    public void submitRegister() {
        loginName = loginNameView.getText().toString();
        password = passwordView.getText().toString();
        code = codeView.getText().toString();
        tel = telView.getText().toString();
        if (!check.isChecked()) {
            Toast.makeText(this, "请阅读用户协议", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == loginName || loginName.isEmpty()) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == password || password.isEmpty()) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == code || code.isEmpty()) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == tel || tel.isEmpty()) {
            Toast.makeText(this, "手机不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(action));
    }

    public void quickRegister(View view) {
        Log.d(tag, "POST request");
        String name = loginNameView.getText().toString();
        String webName = "nubia";
        String verifyCode = "gbq123456";
        if (!check.isChecked()) {
            Toast.makeText(this, "请阅读用户协议", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == name || name.isEmpty()) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void toRegisterSuccessActivity() {
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, RegisterSuccessActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_register:
                toRegisterSuccessActivity();
                break;
            case R.id.bt_quick_register:
                toRegisterSuccessActivity();
                break;
            case R.id.bt_fetch_code:
                toRegisterSuccessActivity();
                break;
            default:
                break;
        }
    }
}
