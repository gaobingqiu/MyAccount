package gbq.com.myaccount.module.main;

import gbq.com.myaccount.base.IBaseCtrl;

/**
 * Created by gbq on 2016-11-12.
 */

public interface IMainCtrl extends IBaseCtrl{
    void toRegisterActivity();

    void toPersonalActivity();

    void saveLoginInfo(String username, String password);

    void clearLoginInfo();
}
