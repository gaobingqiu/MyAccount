package gbq.com.myaccount.module.main;

import android.text.TextUtils;

import gbq.com.myaccount.Define;
import gbq.com.myaccount.base.entity.User;
import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.net.HttpListener;
import gbq.com.myaccount.net.NetApis;

/**
 * Created by gbq on 2016-11-12.
 */

public class MainPresenter {
    private IMainCtrl mCtrl;

    public MainPresenter(IMainCtrl mainCtrl){
        this.mCtrl = mainCtrl;
    }

    public void login(final String userName, final String password, final boolean isSave){
        if (TextUtils.isEmpty(userName)) {
            mCtrl.showToast("姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mCtrl.showToast("密码不能为空");
            return;
        }
        NetApis.getInstance().login(userName, password, new HttpListener() {
            @Override
            public void onSuccess(String result) {
                User user = JsonUtil.createJsonBean(result,User.class);
                mCtrl.toPersonalActivity(userName,user.getImage());
                if (isSave) {
                    mCtrl.saveLoginInfo(userName,password);
                }
            }

            @Override
            public void onFail() {
                mCtrl.showToast(Define.ERROR_NET);
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                mCtrl.showToast(Define.ERROR_NET);
            }
        });
    }
}
