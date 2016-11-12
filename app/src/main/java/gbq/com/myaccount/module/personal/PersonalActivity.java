package gbq.com.myaccount.module.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import gbq.com.myaccount.MainActivity;
import gbq.com.myaccount.R;
import gbq.com.myaccount.module.news.NewsActivity;
import gbq.com.myaccount.module.photo.PhotoActivity;

public class PersonalActivity extends Activity implements View.OnClickListener,IPersonalCtrl {
    TextView userNameView;
    String tag = "activity_personal";
    ListView linkList;
    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_personal);
        findIds();
    }

    private void findIds() {
        ImageView imageView = (ImageView) findViewById(R.id.iv_user_img);
        imageView.setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.tv_to_news);
        textView.setOnClickListener(this);

        Button button = (Button) findViewById(R.id.bt_user_out);
        button.setOnClickListener(this);
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
