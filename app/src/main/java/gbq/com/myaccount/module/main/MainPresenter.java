package gbq.com.myaccount.module.main;

import android.text.TextUtils;

import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * Created by gbq on 2016-11-12.
 */

public class MainPresenter {
    private IMainCtrl mainCtrl;

    public MainPresenter(IMainCtrl mainCtrl){
        this.mainCtrl = mainCtrl;
    }

    public void login(final String userName, final String password, final boolean isSave){
        if (TextUtils.isEmpty(userName)) {
            mainCtrl.showToast("姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mainCtrl.showToast("密码不能为空");
            return;
        }
        NetApis.getInstance().login(userName, password, new HttpListener() {
            @Override
            public void onSuccess(String result) {
                mainCtrl.toPersonalActivity();
                if (isSave) {
                    mainCtrl.saveLoginInfo(userName,password);
                }
            }

            @Override
            public void onFail() {
                mainCtrl.toPersonalActivity();
                if (isSave) {
                    mainCtrl.saveLoginInfo(userName,password);
                }
            }

            @Override
            public void onError(int errorCode, String errorMessage) {

                mainCtrl.toPersonalActivity();
            }
        });
    }
}
