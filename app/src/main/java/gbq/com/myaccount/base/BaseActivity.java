package gbq.com.myaccount.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 基本的Activity，主要包含一些Activity的公共方法
 * Created by gbq on 2016-11-15.
 */

public class BaseActivity extends AppCompatActivity implements IBaseCtrl{
    @Override
    public void showAlert(String msg) {

    }
    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
