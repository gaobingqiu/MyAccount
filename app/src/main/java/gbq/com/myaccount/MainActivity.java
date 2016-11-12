package gbq.com.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import gbq.com.myaccount.module.main.IMainCtrl;
import gbq.com.myaccount.module.personal.PersonalActivity;
import gbq.com.myaccount.module.register.RegisterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IMainCtrl {
    EditText userNameView;
    EditText passwordView;
    CheckBox saveUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();
    }

    public void findIds() {
        Button userLoginBt = (Button) findViewById(R.id.bt_user_login);
        userLoginBt.setOnClickListener(this);

        TextView toRegisterTv = (TextView) findViewById(R.id.tv_to_register);
        toRegisterTv.setOnClickListener(this);
        saveUser = (CheckBox) findViewById(R.id.check);
        userNameView = (EditText) findViewById(R.id.userName);
        passwordView = (EditText) findViewById(R.id.Password);
    }

    @Override
    public void toRegisterActivity() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void toPersonalActivity() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, PersonalActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.bt_user_login:
                toPersonalActivity();
                break;
            case R.id.tv_to_register:
                toRegisterActivity();
                break;
            default:
                break;
        }
    }
}
