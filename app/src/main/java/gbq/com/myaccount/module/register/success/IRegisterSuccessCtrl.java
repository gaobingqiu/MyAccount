package gbq.com.myaccount.module.register.success;

import gbq.com.myaccount.base.IBaseCtrl;

/**
 * 注册成功后的行为接口
 * Created by gbq on 2016-11-12.
 */

interface IRegisterSuccessCtrl extends IBaseCtrl{
    void toPersonal(String userName,String imagePath);
}
