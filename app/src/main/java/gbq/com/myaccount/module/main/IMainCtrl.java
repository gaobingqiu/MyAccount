package gbq.com.myaccount.module.main;

/**
 * Created by gbq on 2016-11-12.
 */

public interface IMainCtrl{
    void toRegisterActivity();

    void toPersonalActivity();

    void showToast(String msg);

    void showAlert(String msg);

    void saveLoginInfo(String username, String password);

    void clearLoginInfo();
}
