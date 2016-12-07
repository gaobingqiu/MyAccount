package gbq.com.myaccount.module.personal;

import gbq.com.myaccount.base.IBaseCtrl;

/**
 *个人中心的行为接口
 * Created by gbq on 2016-11-12.
 */

interface IPersonalCtrl extends IBaseCtrl{
    void userLogout();

    void toNewsActivity();

    void changeImg();

    void clearLoginInfo();
}
