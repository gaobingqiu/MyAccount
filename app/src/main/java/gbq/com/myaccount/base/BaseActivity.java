package gbq.com.myaccount.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import gbq.com.myaccount.base.util.CustomProgressDialog;

/**
 * 基本的Activity，主要包含一些Activity的公共方法
 * Created by gbq on 2016-11-15.
 */

public class BaseActivity extends AppCompatActivity implements IBaseCtrl{
    private CustomProgressDialog progressDialog;
    @Override
    public void showAlert(String msg) {

    }

    @Override
    public void showProcess() {
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setMessage("玩命加载中...");
        }

        progressDialog.show();
    }

    @Override
    public void closeProcess() {
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
