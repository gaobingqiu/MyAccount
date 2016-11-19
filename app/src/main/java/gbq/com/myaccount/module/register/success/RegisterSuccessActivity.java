package gbq.com.myaccount.module.register.success;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import gbq.com.myaccount.R;
import gbq.com.myaccount.module.personal.PersonalActivity;

public class RegisterSuccessActivity extends Activity implements View.OnClickListener,IRegisterSuccessCtrl {
    private TextView userNameView, passwordView;
    private LinearLayout quickPartView;
    private String url_getUser = "loginInterface/getUser.do";
    private String tag = "RegisterSuccessActivity->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        findIds();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            String userName = bundle.getString("userName");
            String password = bundle.getString("password");
            if (TextUtils.isEmpty(password)) {
                quickPartView.setVisibility(View.GONE);
            } else {
                passwordView.setText(password);
            }
            userNameView.setText(userName);
        }
    }

    public void findIds() {
        TextView textView = (TextView) findViewById(R.id.tv_to_personal_activity);
        textView.setOnClickListener(this);

        userNameView = (TextView) findViewById(R.id.userName);
        passwordView = (TextView) findViewById(R.id.quick_password);
        quickPartView = (LinearLayout) findViewById(R.id.quick_part);
    }

    @Override
    public void toPersonal() {
        Intent intent = new Intent();
        intent.setClass(RegisterSuccessActivity.this, PersonalActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_to_personal_activity:
                toPersonal();
                break;
            case R.id.bt_fetch_code:
                break;
            default:
                break;
        }
    }
}